package letcode.plugin;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 生成成功后仅将指定路径加入 git 暂存区；失败时弹窗提示但不影响生成结果。
 */
final class GitAddHelper {

    private static final String DIALOG_TITLE = "生成 LeetCode 每日一题";

    private GitAddHelper() {
    }

    static void addGeneratedFiles(@NotNull Project project,
                                  @NotNull String projectBasePath,
                                  @NotNull Path javaPath,
                                  @Nullable Path testCasePath) {
        File repoRoot = new File(projectBasePath);
        if (!new File(repoRoot, ".git").isDirectory()) {
            return;
        }
        Path base = Paths.get(projectBasePath);
        List<String> relPaths = new ArrayList<>();
        relPaths.add(toRepoRelative(base, javaPath));
        if (testCasePath != null) {
            relPaths.add(toRepoRelative(base, testCasePath));
        }
        List<String> command = new ArrayList<>();
        command.add("git");
        command.add("add");
        command.add("--");
        command.addAll(relPaths);
        try {
            Process process = new ProcessBuilder(command)
                    .directory(repoRoot)
                    .redirectErrorStream(true)
                    .start();
            boolean finished = process.waitFor(30, TimeUnit.SECONDS);
            if (!finished) {
                process.destroyForcibly();
                showWarning(project, "git add 超时:\n" + String.join("\n", relPaths));
                return;
            }
            if (process.exitValue() != 0) {
                String output = new String(process.getInputStream().readAllBytes());
                showWarning(project, "git add 失败（退出码 " + process.exitValue() + "）:\n" + output.trim());
            }
        } catch (Exception ex) {
            showWarning(project, "git add 失败: " + ex.getMessage());
        }
    }

    private static String toRepoRelative(Path base, Path path) {
        return base.relativize(path).toString().replace('\\', '/');
    }

    private static void showWarning(@NotNull Project project, String message) {
        Messages.showWarningDialog(project, message, DIALOG_TITLE);
    }
}
