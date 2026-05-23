package letcode.plugin;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 从题解类 Javadoc 的 Link 行解析 titleSlug。
 */
final class LeetCodeTitleSlugResolver {

    private static final Pattern LINK_PATTERN = Pattern.compile(
            "Link:\\s*https?://(?:www\\.)?leetcode(?:\\.cn|\\.com)/problems/([^/\\s>]+)/?",
            Pattern.CASE_INSENSITIVE
    );

    private LeetCodeTitleSlugResolver() {
    }

    @Nullable
    static String fromPsiClass(@NotNull PsiClass psiClass) {
        PsiFile file = psiClass.getContainingFile();
        if (file == null) {
            return null;
        }
        return fromText(file.getText());
    }

    @Nullable
    static String fromText(@NotNull String source) {
        Matcher matcher = LINK_PATTERN.matcher(source);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return null;
    }
}
