package letcode.plugin;

import com.google.gson.JsonObject;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.codeStyle.CodeStyleManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.file.Path;
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
                pickRandomUnsolvedCandidate(client, selector, graphqlDifficulty, difficultyLabel, indicator);

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
                pickRandomUnsolvedCandidate(client, selector, graphqlDifficulty, difficultyLabel, indicator);

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
     * 按服务端 total 遍历该难度全部页，过滤付费/无题号/本地已有/账号已 AC，
     * 再用蓄水池抽样等概率选出一道，避免偏向首个可用页的小题号。
     */
    @NotNull
    private static LeetCodeGraphqlClient.QuestionListItem pickRandomUnsolvedCandidate(
            @NotNull LeetCodeGraphqlClient client,
            @NotNull LeetCodeQuestionSelector selector,
            @NotNull String graphqlDifficulty,
            @NotNull String difficultyLabel,
            @NotNull ProgressIndicator indicator) throws IOException {
        Random random = new Random();
        LeetCodeGraphqlClient.QuestionListItem picked = null;
        int candidateCount = 0;

        indicator.checkCanceled();
        indicator.setText("正在扫描 " + difficultyLabel + " 未解题目（第 1 页）...");
        LeetCodeGraphqlClient.ProblemsetPage firstPage =
                client.fetchProblemsetByDifficulty(graphqlDifficulty, PAGE_SIZE, 0);
        int total = firstPage.total;
        // total <= 0 且首页为空：无候选；首页有题时即使 total 异常也至少扫描实际返回。
        if (total <= 0 && firstPage.questions.isEmpty()) {
            throw new IOException("没有可用的 " + difficultyLabel
                    + " 题目（项目中已包含、账号已通过、均为付费题，或列表已遍历完毕）");
        }

        int coverCount = Math.max(Math.max(total, 0), firstPage.questions.size());
        int pageCount = coverCount <= 0 ? 1 : (coverCount + PAGE_SIZE - 1) / PAGE_SIZE;

        for (int pageIndex = 0; pageIndex < pageCount; pageIndex++) {
            indicator.checkCanceled();
            int pageNo = pageIndex + 1;
            indicator.setText("正在扫描 " + difficultyLabel + " 未解题目（第 " + pageNo
                    + " 页 / 共 " + pageCount + " 页）...");

            LeetCodeGraphqlClient.ProblemsetPage pageResult = pageIndex == 0
                    ? firstPage
                    : client.fetchProblemsetByDifficulty(
                            graphqlDifficulty, PAGE_SIZE, pageIndex * PAGE_SIZE);
            if (pageResult.questions.isEmpty()) {
                break;
            }

            for (LeetCodeGraphqlClient.QuestionListItem item : pageResult.questions) {
                indicator.checkCanceled();
                if (item.paidOnly) {
                    continue;
                }
                if (item.questionFrontendId == null || item.questionFrontendId.isEmpty()) {
                    continue;
                }
                if (selector.exists(item.questionFrontendId)) {
                    continue;
                }
                // 账号已 AC 的题不进入候选；未开始 / 尝试未通过均可选。
                if (item.isAccepted()) {
                    continue;
                }
                candidateCount++;
                // 蓄水池：第 k 个候选以 1/k 概率替换当前选择，保证等概率。
                if (random.nextInt(candidateCount) == 0) {
                    picked = item;
                }
            }

            int scanned = pageIndex * PAGE_SIZE + pageResult.questions.size();
            if (pageResult.questions.size() < PAGE_SIZE) {
                break;
            }
            // total 低估时继续翻页至短页/空页，不设固定页数上限。
            if (pageIndex + 1 >= pageCount && scanned > Math.max(total, 0)) {
                pageCount = pageIndex + 2;
            }
        }

        if (picked == null) {
            throw new IOException("没有可用的 " + difficultyLabel
                    + " 题目（项目中已包含、账号已通过、均为付费题，或列表已遍历完毕）");
        }
        indicator.setText("已从 " + candidateCount + " 道未解候选中随机选出: " + picked.titleSlug);
        return picked;
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
        VirtualFile vf = LocalFileSystem.getInstance().refreshAndFindFileByPath(result.javaPath.toString());
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
