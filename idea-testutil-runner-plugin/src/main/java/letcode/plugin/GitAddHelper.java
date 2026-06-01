package letcode.plugin;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 生成成功后 git add；Accepted 后仅提交当前题目相关文件并 push。
 */
final class GitAddHelper {

    private static final String DIALOG_TITLE = "Generate LeetCode Daily Question";
    private static final long GIT_TIMEOUT_SECONDS = 60;

    private GitAddHelper() {
    }

    static void addGeneratedFiles(@NotNull Project project,
                                  @NotNull String projectBasePath,
                                  @NotNull Path javaPath,
                                  @Nullable Path testCasePath) {
        File repoRoot = resolveRepoRoot(projectBasePath);
        if (repoRoot == null) {
            return;
        }
        List<String> relPaths = collectRelativePaths(repoRoot, javaPath, testCasePath);
        GitCommandResult result = runGit(repoRoot, GIT_TIMEOUT_SECONDS, toGitArgs(buildAddCommand(relPaths)));
        if (!result.success) {
            showWarning(project, "git add failed:\n" + result.output.trim());
        }
    }

    @NotNull
    static GitCommitPushResult commitAndPushAcceptedSolution(@NotNull String projectBasePath,
                                                             @NotNull Path javaPath,
                                                             @Nullable Path testCasePath,
                                                             @NotNull String commitMessage) {
        File repoRoot = resolveRepoRoot(projectBasePath);
        if (repoRoot == null) {
            return GitCommitPushResult.failure("Not a git repository: " + projectBasePath);
        }

        List<String> relPaths = collectRelativePaths(repoRoot, javaPath, testCasePath);
        GitCommandResult addResult = runGit(repoRoot, GIT_TIMEOUT_SECONDS, toGitArgs(buildAddCommand(relPaths)));
        if (!addResult.success) {
            return GitCommitPushResult.failure("git add failed: " + addResult.output.trim());
        }

        GitCommandResult stagedDiff = runGit(repoRoot, GIT_TIMEOUT_SECONDS, "diff", "--cached", "--quiet");
        if (stagedDiff.exitCode == 0) {
            return GitCommitPushResult.noChanges();
        }
        if (stagedDiff.exitCode != 1) {
            return GitCommitPushResult.failure("git diff --cached failed: " + stagedDiff.output.trim());
        }

        GitCommandResult commitResult = runGit(
                repoRoot,
                GIT_TIMEOUT_SECONDS,
                "commit", "-m", commitMessage
        );
        if (!commitResult.success) {
            return GitCommitPushResult.failure("git commit failed: " + commitResult.output.trim());
        }

        GitCommandResult hashResult = runGit(repoRoot, GIT_TIMEOUT_SECONDS, "rev-parse", "HEAD");
        if (!hashResult.success) {
            return GitCommitPushResult.failure("git rev-parse HEAD failed: " + hashResult.output.trim());
        }
        String commitHash = hashResult.output.trim();

        GitCommandResult pushResult = pushCurrentBranch(repoRoot);
        if (!pushResult.success) {
            return GitCommitPushResult.commitOnly(commitHash, pushResult.output.trim());
        }
        return GitCommitPushResult.success(commitHash);
    }

    @Nullable
    static Path resolveTestCasePath(@NotNull String projectBasePath, @NotNull String className) {
        String simpleName = className.startsWith("_") ? className.substring(1) : className;
        Path testCasePath = Paths.get(projectBasePath, "src/main/resources", "TestCase_" + simpleName + ".txt");
        return Files.exists(testCasePath) ? testCasePath : null;
    }

    @NotNull
    static String buildCommitMessage(@Nullable String packageName, @NotNull Path javaPath) {
        String difficulty = difficultyFromPackage(packageName);
        if (difficulty == null) {
            difficulty = difficultyFromPath(javaPath);
        }
        if (difficulty == null) {
            difficulty = "medium";
        }
        return "feat<" + difficulty + ">: question of the day";
    }

    @Nullable
    private static File resolveRepoRoot(@NotNull String projectBasePath) {
        File repoRoot = new File(projectBasePath);
        if (!new File(repoRoot, ".git").isDirectory()) {
            return null;
        }
        return repoRoot;
    }

    @NotNull
    private static List<String> collectRelativePaths(@NotNull File repoRoot,
                                                     @NotNull Path javaPath,
                                                     @Nullable Path testCasePath) {
        Path base = repoRoot.toPath();
        List<String> relPaths = new ArrayList<>();
        relPaths.add(toRepoRelative(base, javaPath));
        if (testCasePath != null && Files.exists(testCasePath)) {
            relPaths.add(toRepoRelative(base, testCasePath));
        }
        return relPaths;
    }

    @NotNull
    private static List<String> buildAddCommand(@NotNull List<String> relPaths) {
        List<String> command = new ArrayList<>();
        command.add("add");
        command.add("--");
        command.addAll(relPaths);
        return command;
    }

    @NotNull
    private static GitCommandResult pushCurrentBranch(@NotNull File repoRoot) {
        GitCommandResult upstream = runGit(
                repoRoot,
                GIT_TIMEOUT_SECONDS,
                "rev-parse",
                "--abbrev-ref",
                "--symbolic-full-name",
                "@{u}"
        );
        if (upstream.success) {
            return runGit(repoRoot, GIT_TIMEOUT_SECONDS, "push");
        }

        GitCommandResult branchResult = runGit(repoRoot, GIT_TIMEOUT_SECONDS, "rev-parse", "--abbrev-ref", "HEAD");
        if (!branchResult.success) {
            return GitCommandResult.failure("git rev-parse HEAD failed: " + branchResult.output.trim());
        }
        String branch = branchResult.output.trim();
        if (branch.isEmpty()) {
            return GitCommandResult.failure("Cannot resolve current branch");
        }
        return runGit(repoRoot, GIT_TIMEOUT_SECONDS, "push", "origin", branch);
    }

    @Nullable
    private static String difficultyFromPackage(@Nullable String packageName) {
        if (packageName == null || packageName.isEmpty()) {
            return null;
        }
        switch (packageName) {
            case "letcode.normal.easy":
                return "easy";
            case "letcode.normal.medium":
                return "medium";
            case "letcode.normal.difficult":
                return "difficult";
            default:
                return null;
        }
    }

    @Nullable
    private static String difficultyFromPath(@NotNull Path javaPath) {
        String normalized = javaPath.toString().replace('\\', '/').toLowerCase();
        if (normalized.contains("/easy/")) {
            return "easy";
        }
        if (normalized.contains("/medium/")) {
            return "medium";
        }
        if (normalized.contains("/difficult/")) {
            return "difficult";
        }
        return null;
    }

    @NotNull
    private static String[] toGitArgs(@NotNull List<String> args) {
        return args.toArray(new String[0]);
    }

    @NotNull
    private static GitCommandResult runGit(@NotNull File repoRoot,
                                           long timeoutSeconds,
                                           String... args) {
        List<String> command = new ArrayList<>();
        command.add("git");
        for (String arg : args) {
            command.add(arg);
        }
        try {
            Process process = new ProcessBuilder(command)
                    .directory(repoRoot)
                    .redirectErrorStream(true)
                    .start();
            boolean finished = process.waitFor(timeoutSeconds, TimeUnit.SECONDS);
            if (!finished) {
                process.destroyForcibly();
                return GitCommandResult.failure("git command timed out: git " + String.join(" ", args));
            }
            String output = readProcessOutput(process);
            int exitCode = process.exitValue();
            return new GitCommandResult(exitCode == 0, exitCode, output);
        } catch (Exception ex) {
            return GitCommandResult.failure(ex.getMessage() == null ? ex.toString() : ex.getMessage());
        }
    }

    @NotNull
    private static String readProcessOutput(@NotNull Process process) throws java.io.IOException {
        InputStream stream = process.getInputStream();
        byte[] bytes = stream.readAllBytes();
        return new String(bytes, StandardCharsets.UTF_8);
    }

    private static String toRepoRelative(Path base, Path path) {
        return base.relativize(path).toString().replace('\\', '/');
    }

    private static void showWarning(@NotNull Project project, String message) {
        Messages.showWarningDialog(project, message, DIALOG_TITLE);
    }

    private static final class GitCommandResult {
        final boolean success;
        final int exitCode;
        @NotNull
        final String output;

        private GitCommandResult(boolean success, int exitCode, @NotNull String output) {
            this.success = success;
            this.exitCode = exitCode;
            this.output = output;
        }

        @NotNull
        static GitCommandResult failure(@NotNull String output) {
            return new GitCommandResult(false, -1, output);
        }
    }
}
