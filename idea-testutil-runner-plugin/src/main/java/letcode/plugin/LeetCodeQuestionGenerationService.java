package letcode.plugin;

import com.google.gson.JsonObject;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.codeStyle.CodeStyleManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * 每日题、随机题与指定题的共享生成流程。
 */
final class LeetCodeQuestionGenerationService {

    private static final int PAGE_SIZE = 100;

    private LeetCodeQuestionGenerationService() {
    }

    @FunctionalInterface
    interface GenerationCompletionListener {
        void onComplete(@NotNull LeetCodeProblemPresentation presentation);
    }

    @FunctionalInterface
    interface PreviewCompletionListener {
        void onComplete(@NotNull LeetCodeProblemPresentation presentation);
    }

    static void runDailyGenerate(@NotNull Project project,
                                 @NotNull String basePath,
                                 @NotNull LeetCodeSettings settings,
                                 @NotNull ProgressIndicator indicator) throws Exception {
        runDailyGenerate(project, basePath, settings, indicator, null);
    }

    static void runDailyGenerate(@NotNull Project project,
                                 @NotNull String basePath,
                                 @NotNull LeetCodeSettings settings,
                                 @NotNull ProgressIndicator indicator,
                                 @Nullable GenerationCompletionListener listener) throws Exception {
        LeetCodeGraphqlClient client = new LeetCodeGraphqlClient(project, settings);
        indicator.setText("正在获取每日一题 slug...");
        String titleSlug = client.fetchDailyTitleSlug();
        indicator.setText("正在获取题目详情: " + titleSlug);
        JsonObject question = client.fetchQuestionDetail(titleSlug);
        LeetCodeDailyGenerator generator = new LeetCodeDailyGenerator(basePath, settings);
        LeetCodeDailyGenerator.GenerationResult result = generator.generate(question, titleSlug);
        LeetCodeProblemPresentation presentation = listener == null
                ? null
                : LeetCodeProblemPresentation.from(question, titleSlug, result);
        ApplicationManager.getApplication().invokeLater(() ->
                handleResult(project, basePath, settings, result, titleSlug, true,
                        "生成 LeetCode 每日一题", presentation, listener));
    }

    static void runRandomGenerate(@NotNull Project project,
                                  @NotNull String basePath,
                                  @NotNull LeetCodeSettings settings,
                                  @NotNull String difficultyLabel,
                                  @NotNull ProgressIndicator indicator) throws Exception {
        runRandomGenerate(project, basePath, settings, difficultyLabel, indicator, null);
    }

    static void runRandomGenerate(@NotNull Project project,
                                  @NotNull String basePath,
                                  @NotNull LeetCodeSettings settings,
                                  @NotNull String difficultyLabel,
                                  @NotNull ProgressIndicator indicator,
                                  @Nullable GenerationCompletionListener listener) throws Exception {
        String graphqlDifficulty = toGraphqlDifficulty(difficultyLabel);
        LeetCodeGraphqlClient client = new LeetCodeGraphqlClient(project, settings);
        LeetCodeQuestionSelector selector = new LeetCodeQuestionSelector(basePath);
        LeetCodeDailyGenerator generator = new LeetCodeDailyGenerator(basePath, settings);

        LeetCodeGraphqlClient.QuestionListItem picked =
                pickRandomUnsolvedCandidate(project, client, selector, settings,
                        graphqlDifficulty, difficultyLabel, indicator);

        indicator.setText("正在获取题目详情: " + picked.titleSlug);
        JsonObject question = client.fetchQuestionDetail(picked.titleSlug);
        String frontendId = LeetCodeGraphqlClient.textOrNull(question.get("questionFrontendId"));
        if (frontendId != null && selector.exists(frontendId)) {
            throw new IOException("选中的题目 " + frontendId + " 在项目中已存在，请重试");
        }

        LeetCodeDailyGenerator.GenerationResult result =
                generator.generate(question, picked.titleSlug);
        String titleSlug = picked.titleSlug;
        LeetCodeProblemPresentation presentation = listener == null
                ? null
                : LeetCodeProblemPresentation.from(question, titleSlug, result);
        ApplicationManager.getApplication().invokeLater(() ->
                handleResult(project, basePath, settings, result, titleSlug, false,
                        "获取随机新题", presentation, listener));
    }

    static void runSpecifiedGenerate(@NotNull Project project,
                                     @NotNull String basePath,
                                     @NotNull LeetCodeSettings settings,
                                     @NotNull String frontendId,
                                     @NotNull ProgressIndicator indicator) throws Exception {
        runSpecifiedGenerate(project, basePath, settings, frontendId, indicator, null);
    }

    static void runSpecifiedGenerate(@NotNull Project project,
                                     @NotNull String basePath,
                                     @NotNull LeetCodeSettings settings,
                                     @NotNull String frontendId,
                                     @NotNull ProgressIndicator indicator,
                                     @Nullable GenerationCompletionListener listener) throws Exception {
        LeetCodeGraphqlClient client = new LeetCodeGraphqlClient(project, settings);
        indicator.setText("正在按题号检索: " + frontendId);
        LeetCodeGraphqlClient.QuestionListItem item = client.fetchQuestionListItemByFrontendId(frontendId);
        if (item.paidOnly) {
            throw new IOException("题号 " + frontendId + " 为付费题目，无法下载");
        }

        indicator.setText("正在获取题目详情: " + item.titleSlug);
        JsonObject question = client.fetchQuestionDetail(item.titleSlug);
        String actualFrontendId = LeetCodeGraphqlClient.textOrNull(question.get("questionFrontendId"));
        if (actualFrontendId == null || !frontendId.equals(actualFrontendId)) {
            throw new IOException("题号不一致：请求 " + frontendId + "，实际得到 "
                    + (actualFrontendId == null ? "空" : actualFrontendId));
        }

        LeetCodeDailyGenerator generator = new LeetCodeDailyGenerator(basePath, settings);
        LeetCodeDailyGenerator.GenerationResult result =
                generator.generate(question, item.titleSlug);
        String titleSlug = item.titleSlug;
        LeetCodeProblemPresentation presentation = listener == null
                ? null
                : LeetCodeProblemPresentation.from(question, titleSlug, result);
        ApplicationManager.getApplication().invokeLater(() ->
                handleResult(project, basePath, settings, result, titleSlug, false,
                        "下载指定题目", presentation, listener));
    }

    static void runRandomPreview(@NotNull Project project,
                                 @NotNull String basePath,
                                 @NotNull LeetCodeSettings settings,
                                 @NotNull String difficultyLabel,
                                 @NotNull ProgressIndicator indicator,
                                 @NotNull PreviewCompletionListener listener) throws Exception {
        String graphqlDifficulty = toGraphqlDifficulty(difficultyLabel);
        LeetCodeGraphqlClient client = new LeetCodeGraphqlClient(project, settings);
        LeetCodeQuestionSelector selector = new LeetCodeQuestionSelector(basePath);

        LeetCodeGraphqlClient.QuestionListItem picked =
                pickRandomUnsolvedCandidate(project, client, selector, settings,
                        graphqlDifficulty, difficultyLabel, indicator);

        indicator.setText("正在获取题目详情: " + picked.titleSlug);
        JsonObject question = client.fetchQuestionDetail(picked.titleSlug);
        String frontendId = LeetCodeGraphqlClient.textOrNull(question.get("questionFrontendId"));
        if (frontendId == null || selector.exists(frontendId)) {
            throw new IOException(frontendId == null
                    ? "选中的题目详情缺少题号，请重试"
                    : "选中的题目 " + frontendId + " 在项目中已存在，请重试");
        }
        LeetCodeProblemPresentation presentation = LeetCodeProblemPresentation.preview(question, picked.titleSlug);
        ApplicationManager.getApplication().invokeLater(() -> listener.onComplete(presentation));
    }

    static void runSpecifiedPreview(@NotNull Project project,
                                    @NotNull LeetCodeSettings settings,
                                    @NotNull String frontendId,
                                    @NotNull ProgressIndicator indicator,
                                    @NotNull PreviewCompletionListener listener) throws Exception {
        LeetCodeGraphqlClient client = new LeetCodeGraphqlClient(project, settings);
        indicator.setText("正在按题号检索: " + frontendId);
        LeetCodeGraphqlClient.QuestionListItem item = client.fetchQuestionListItemByFrontendId(frontendId);
        if (item.paidOnly) {
            throw new IOException("题号 " + frontendId + " 为付费题目，无法下载");
        }
        indicator.setText("正在获取题目详情: " + item.titleSlug);
        JsonObject question = client.fetchQuestionDetail(item.titleSlug);
        String actualFrontendId = LeetCodeGraphqlClient.textOrNull(question.get("questionFrontendId"));
        if (!frontendId.equals(actualFrontendId)) {
            throw new IOException("题号不一致：请求 " + frontendId + "，实际得到 "
                    + (actualFrontendId == null ? "空" : actualFrontendId));
        }
        LeetCodeProblemPresentation presentation = LeetCodeProblemPresentation.preview(question, item.titleSlug);
        ApplicationManager.getApplication().invokeLater(() -> listener.onComplete(presentation));
    }

    /**
     * 共用缓存读取/刷新与蓄水池抽样：有效缓存时仅本地筛选，绝不分页请求题库。
     */
    @NotNull
    private static LeetCodeGraphqlClient.QuestionListItem pickRandomUnsolvedCandidate(
            @NotNull Project project,
            @NotNull LeetCodeGraphqlClient client,
            @NotNull LeetCodeQuestionSelector selector,
            @NotNull LeetCodeSettings settings,
            @NotNull String graphqlDifficulty,
            @NotNull String difficultyLabel,
            @NotNull ProgressIndicator indicator) throws IOException {
        List<LeetCodeGraphqlClient.QuestionListItem> questions =
                LeetCodeQuestionStatusCache.loadValid(project, settings.randomQuestionCacheTtlHours);
        if (questions == null) {
            // 缓存无效：认证分页拉全量 status，写完整快照后再筛选。
            questions = fetchAndSaveAllProblemsetWithStatus(project, client, indicator);
        } else {
            indicator.setText("使用本地题库状态缓存（" + questions.size() + " 题）...");
        }

        Random random = new Random();
        LeetCodeGraphqlClient.QuestionListItem picked = null;
        int candidateCount = 0;
        for (LeetCodeGraphqlClient.QuestionListItem item : questions) {
            indicator.checkCanceled();
            if (!difficultyMatches(item.difficulty, graphqlDifficulty)) {
                continue;
            }
            if (item.paidOnly || item.isAccepted()) {
                continue;
            }
            if (item.questionFrontendId == null || item.questionFrontendId.isEmpty()) {
                continue;
            }
            if (selector.exists(item.questionFrontendId)) {
                continue;
            }
            candidateCount++;
            // 蓄水池：第 k 个候选以 1/k 概率替换，保证等概率、不偏向低题号。
            if (random.nextInt(candidateCount) == 0) {
                picked = item;
            }
        }

        if (picked == null) {
            throw new IOException("没有可用的 " + difficultyLabel
                    + " 题目（项目中已包含、账号已通过、均为付费题，或列表已遍历完毕）");
        }
        indicator.setText("已从 " + candidateCount + " 道未解候选中随机选出: " + picked.titleSlug);
        return picked;
    }

    /**
     * 认证分页拉取全部题库 status 并写入完整快照。
     */
    @NotNull
    private static List<LeetCodeGraphqlClient.QuestionListItem> fetchAndSaveAllProblemsetWithStatus(
            @NotNull Project project,
            @NotNull LeetCodeGraphqlClient client,
            @NotNull ProgressIndicator indicator) throws IOException {
        indicator.checkCanceled();
        indicator.setText("正在刷新题库状态缓存（第 1 页）...");
        LeetCodeGraphqlClient.ProblemsetPage firstPage =
                client.fetchAllProblemsetWithStatus(PAGE_SIZE, 0);
        int total = firstPage.total;
        if (total <= 0 && firstPage.questions.isEmpty()) {
            throw new IOException("刷新题库状态缓存失败：服务端返回空题库");
        }

        int pageCount = total <= 0 ? 1 : (total + PAGE_SIZE - 1) / PAGE_SIZE;
        List<LeetCodeGraphqlClient.QuestionListItem> all = new ArrayList<>(Math.max(total, PAGE_SIZE));
        for (LeetCodeGraphqlClient.QuestionListItem item : firstPage.questions) {
            indicator.checkCanceled();
            all.add(item);
        }

        for (int pageIndex = 1; pageIndex < pageCount; pageIndex++) {
            indicator.checkCanceled();
            int pageNo = pageIndex + 1;
            indicator.setText("正在刷新题库状态缓存（第 " + pageNo + " 页 / 共 " + pageCount + " 页）...");
            LeetCodeGraphqlClient.ProblemsetPage pageResult =
                    client.fetchAllProblemsetWithStatus(PAGE_SIZE, pageIndex * PAGE_SIZE);
            for (LeetCodeGraphqlClient.QuestionListItem item : pageResult.questions) {
                indicator.checkCanceled();
                all.add(item);
            }
            if (pageResult.questions.isEmpty() || pageResult.questions.size() < PAGE_SIZE) {
                break;
            }
        }

        LeetCodeQuestionStatusCache.save(project, all);
        indicator.setText("题库状态缓存已刷新（" + all.size() + " 题）");
        return all;
    }

    private static boolean difficultyMatches(@Nullable String itemDifficulty,
                                             @NotNull String graphqlDifficulty) {
        if (itemDifficulty == null || itemDifficulty.trim().isEmpty()) {
            return false;
        }
        return graphqlDifficulty.equalsIgnoreCase(itemDifficulty.trim());
    }

    private static void handleResult(@NotNull Project project,
                                     @NotNull String basePath,
                                     @NotNull LeetCodeSettings settings,
                                     @NotNull LeetCodeDailyGenerator.GenerationResult result,
                                     @NotNull String titleSlug,
                                     boolean updateLastDailyId,
                                     @NotNull String actionTitle,
                                     @Nullable LeetCodeProblemPresentation presentation,
                                     @Nullable GenerationCompletionListener listener) {
        refreshPath(result.javaPath);
        if (result.testCasePath != null) {
            refreshPath(result.testCasePath);
        }
        // 与「打开题解」一致：绝对路径 + system-independent，避免 Windows 反斜杠导致找不到。
        String javaVfsPath = FileUtil.toSystemIndependentName(
                result.javaPath.toAbsolutePath().normalize().toString());
        VirtualFile vf = LocalFileSystem.getInstance().refreshAndFindFileByPath(javaVfsPath);
        if (vf != null) {
            FileEditorManager.getInstance(project).openFile(vf, true);
        }

        if (updateLastDailyId && result.questionFrontendId != null) {
            settings.lastDailyQuestionFrontendId = result.questionFrontendId;
            settings.save(project);
        }

        if (result.isJavaCreated()) {
            formatGeneratedJavaFile(project, vf);
            GitAddHelper.addGeneratedFiles(project, basePath, result.javaPath, result.testCasePath);
            if (listener != null && presentation != null) {
                listener.onComplete(presentation);
                return;
            }
            StringBuilder msg = new StringBuilder("已生成:\n").append(result.javaPath);
            if (result.testCasePath != null) {
                msg.append("\n").append(result.testCasePath);
            }
            msg.append("\n题目: ").append(titleSlug);
            Messages.showInfoMessage(project, msg.toString(), actionTitle);
            return;
        }

        if (listener != null && presentation != null) {
            listener.onComplete(presentation);
            return;
        }

        if (result.isMetadataUpdated()) {
            Messages.showInfoMessage(
                    project,
                    "已更新题目注释，保留原有实现\n" + result.javaPath + "\n题目: " + titleSlug,
                    actionTitle
            );
            return;
        }

        Messages.showInfoMessage(
                project,
                "题目注释无需更新，保留原有实现\n" + result.javaPath + "\n题目: " + titleSlug,
                actionTitle
        );
    }

    private static void refreshPath(Path path) {
        LocalFileSystem.getInstance().refreshIoFiles(java.util.Collections.singletonList(path.toFile()));
    }

    private static void formatGeneratedJavaFile(@NotNull Project project, @Nullable VirtualFile vf) {
        if (vf == null) {
            return;
        }
        // 格式化会修改 PSI，必须在可撤销的 IDEA 写命令中执行。
        WriteCommandAction.runWriteCommandAction(project, () -> {
            PsiFile psiFile = PsiManager.getInstance(project).findFile(vf);
            if (psiFile != null) {
                CodeStyleManager.getInstance(project).reformat(psiFile);
            }
        });
    }

    @NotNull
    private static String toGraphqlDifficulty(@NotNull String difficultyLabel) {
        String lower = difficultyLabel.trim().toLowerCase(Locale.ROOT);
        if ("easy".equals(lower)) {
            return "EASY";
        }
        if ("hard".equals(lower)) {
            return "HARD";
        }
        return "MEDIUM";
    }
}
