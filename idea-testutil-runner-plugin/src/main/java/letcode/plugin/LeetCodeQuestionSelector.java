package letcode.plugin;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 扫描项目中已存在的 LeetCode 题解文件（按 questionFrontendId 对应的 _&lt;id&gt;.java）。
 */
final class LeetCodeQuestionSelector {

    private static final Pattern QUESTION_FILE = Pattern.compile("_(\\d+)\\.java");

    private final Set<String> existingFrontendIds = new HashSet<>();

    LeetCodeQuestionSelector(@NotNull String projectBasePath) {
        scan(Paths.get(projectBasePath));
    }

    boolean exists(@NotNull String questionFrontendId) {
        return existingFrontendIds.contains(questionFrontendId);
    }

    private void scan(Path projectRoot) {
        Path letcodeRoot = projectRoot.resolve("src/main/java/letcode");
        if (!Files.isDirectory(letcodeRoot)) {
            return;
        }
        try {
            Files.walkFileTree(letcodeRoot, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(@NotNull Path file, @NotNull BasicFileAttributes attrs) {
                    collectFromFileName(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(@NotNull Path file, @NotNull IOException exc) {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException ignored) {
            // 部分目录不可访问时保留已扫描到的题号
        }
    }

    private void collectFromFileName(@NotNull Path file) {
        String name = file.getFileName().toString();
        Matcher matcher = QUESTION_FILE.matcher(name);
        if (matcher.matches()) {
            existingFrontendIds.add(matcher.group(1));
        }
    }
}
