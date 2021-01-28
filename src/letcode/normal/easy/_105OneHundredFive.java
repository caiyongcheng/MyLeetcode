package letcode.easy;

/**
 * @program: StudyHTTP
 * @description: 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * @author: 蔡永程
 * @create: 2020-06-19 20:52
 */
public class _105OneHundredFive {

    public static boolean isLegal(char ch) {
        if (ch >= '0' && ch <= '9') {
            return true;
        }
        if (ch >= 'a' && ch <= 'z') {
            return true;
        }
        if (ch >= 'A' && ch <= 'Z') {
            return true;
        }
        return false;
    }

    /**
     * 示例 1:
     * 输入: "A man, a plan, a canal: Panama"
     * 输出: true
     * 示例 2:
     * 输入: "race a car"
     * 输出: false
     *
     * @param s
     * @return
     */
    public static boolean isPalindrome(String s) {
        if (s == null || s.length() == 0 || s.length() == 1) {
            return true;
        }
        int left = 0;
        int right = s.length() - 1;
        int t = 0;
        char leftch = '0';
        char rightch = '0';
        while (left <= right) {
            while (left <= right) {
                leftch = s.charAt(left);
                if (isLegal(leftch)) break;
                ++left;
            }
            if (left > right) break;
            while (left <= right) {
                rightch = s.charAt(right);
                if (isLegal(rightch)) break;
                --right;
            }
            if (left > right) break;
            t = Math.abs(leftch - rightch);
            if (t != 0 && t != 32) return false;
            if (t == 32 && ((leftch >= '0' && leftch <= '9') || (rightch >= '0' && rightch <= '9')))
                return false;
            ++left;
            --right;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("0P"));
    }
}