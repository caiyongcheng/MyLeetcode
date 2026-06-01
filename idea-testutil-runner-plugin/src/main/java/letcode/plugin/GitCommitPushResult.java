package letcode.plugin;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Accepted 后本地 git commit/push 结果，供判题弹窗展示。
 */
final class GitCommitPushResult {

    final boolean noChanges;
    final boolean commitSuccess;
    final boolean pushSuccess;
    @Nullable
    final String commitHash;
    @Nullable
    final String errorMessage;

    private GitCommitPushResult(boolean noChanges,
                                boolean commitSuccess,
                                boolean pushSuccess,
                                @Nullable String commitHash,
                                @Nullable String errorMessage) {
        this.noChanges = noChanges;
        this.commitSuccess = commitSuccess;
        this.pushSuccess = pushSuccess;
        this.commitHash = commitHash;
        this.errorMessage = errorMessage;
    }

    @NotNull
    static GitCommitPushResult noChanges() {
        return new GitCommitPushResult(true, false, false, null, null);
    }

    @NotNull
    static GitCommitPushResult success(@NotNull String commitHash) {
        return new GitCommitPushResult(false, true, true, commitHash, null);
    }

    @NotNull
    static GitCommitPushResult failure(@NotNull String errorMessage) {
        return new GitCommitPushResult(false, false, false, null, errorMessage);
    }

    @NotNull
    static GitCommitPushResult commitOnly(@NotNull String commitHash, @NotNull String pushError) {
        return new GitCommitPushResult(false, true, false, commitHash, pushError);
    }

    @NotNull
    String formatForDialog() {
        if (noChanges) {
            return "Accepted, but no local changes to commit";
        }
        if (commitSuccess && pushSuccess && commitHash != null) {
            return "Git commit: " + commitHash + "\nGit push: OK";
        }
        StringBuilder sb = new StringBuilder(128);
        if (commitSuccess && commitHash != null) {
            sb.append("Git commit: ").append(commitHash).append('\n');
        }
        if (errorMessage != null && !errorMessage.isEmpty()) {
            sb.append("Git error: ").append(errorMessage.trim());
        }
        String text = sb.toString().trim();
        return text.isEmpty() ? "Git operation failed" : text;
    }
}
