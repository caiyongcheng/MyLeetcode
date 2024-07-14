package letcode.normal.easy;

/**
 * 我们定义，在以下情况时，单词的大写用法是正确的：  全部字母都是大写，比如 "USA" 。 单词中所有字母都不是大写，比如 "leetcode" 。
 * 如果单词不只含有一个字母，只有首字母大写， 比如 "Google" 。 给你一个字符串 word 。如果大写用法正确，返回 true ；否则，返回 false 。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-06-24 14:13
 */
public class _520 {

    public boolean detectCapitalUse(String word) {
        int length = word.length();
        char ch = word.charAt(0);
        if (ch >= 'A' && ch <= 'Z') {
            if (length == 1) {
                return true;
            }
            ch = word.charAt(1);
            return ch < 'a' || ch > 'z' ? isAllUppercase(word) : isAllLowercase(word, 1);
        } else {
            return isAllLowercase(word, 0);
        }
    }

    public boolean isAllUppercase(String word) {
        int length = word.length();
        char ch;
        for (int i = 1; i < length; i++) {
            ch = word.charAt(i);
            if (ch < 'A' || ch > 'Z') {
                return false;
            }
        }
        return true;
    }

    public boolean isAllLowercase(String word, int start) {
        int length = word.length();
        char ch;
        for (int i = start; i < length; i++) {
            ch = word.charAt(i);
            if (ch < 'a' || ch > 'z') {
                return false;
            }
        }
        return true;
    }


    /**
     * 示例 1：
     *
     * 输入：word = "USA"
     * 输出：true
     * 示例 2：
     *
     * 输入：word = "FlaG"
     * 输出：false
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _520().detectCapitalUse(
                "USA"
        ));
        System.out.println(new _520().detectCapitalUse(
                "FlaG"
        ));
    }

}
