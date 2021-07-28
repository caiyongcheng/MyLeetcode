package letcode.difficult;

/**
 * StudyHTTP
 * 给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。
 * 说明:  s可能为空，且只包含从a-z的小写字母。
 * p可能为空，且只包含从a-z的小写字母，以及字符.和*。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/regular-expression-matching 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : CaiYongcheng
 * @date : 2020-06-22 15:29
 **/
public class _10Ten {


    /**
     * 示例 1:
     * 输入:
     * s = "aa"
     * p = "a"
     * 输出: false
     * 解释: "a" 无法匹配 "aa" 整个字符串。
     * <p>
     * 示例 2:
     * 输入:
     * s = "aa"
     * p = "a*"
     * 输出: true
     * 解释:因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
     * <p>
     * 示例3:
     * 输入:
     * s = "ab"
     * p = ".*"
     * 输出: true
     * 解释:".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
     * <p>
     * 示例 4:
     * 输入:
     * s = "aab"
     * p = "c*a*b"
     * 输出: true
     * 解释:因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
     * <p>
     * 示例 5:
     * 输入:
     * s = "mississippi"
     * p = "mis*is*p*."
     * 输出: false
     *
     * @param s
     * @param p
     * @return
     */

    static short[][] ref;

    public static short isMatch(String s, int si, String p, int pi) {


        if (ref[si][pi] != 0) return ref[si][pi];

        int i = si;
        int j = pi;
        int sn = s.length();
        int pn = p.length();
        char ch1 = '-';
        char ch2 = '-';

        while (i < sn && j < pn) {
            ch1 = s.charAt(i);
            ch2 = p.charAt(j);
            if (j + 1 < pn && p.charAt(j + 1) == '*') {
                break;
            } else if (ch1 == ch2 || ch2 == '.') {
                ++i;
                ++j;
            } else {
                ref[si][pi] = 1;
                return ref[si][pi];
            }
        }
        if (i == sn) {
            if (j == pn) {
                ref[si][pi] = 2;
            } else if (j == pn - 2 && p.charAt(pn - 1) == '*') {
                ref[si][pi] = 2;
            } else {
                ref[si][pi] = 1;
            }
        } else if (j == pn) {
            ref[si][pi] = 1;
        } else {
            int zero = isMatch(s, i, p, j + 2);
            int one = 0;
            if (ch1 == ch2 || ch2 == '.') {
                one = isMatch(s, i + 1, p, j);
            }
            if (zero == 2 || one == 2) {
                ref[si][pi] = 2;
            }
        }
        return ref[si][pi];
    }


    public static boolean isMatch(String s, String p) {
        if (s.length() == 0 && p.length() == 0) return true;
        if (p.length() == 0) return false;
        if (s.length() == 0) {
            int n = p.length();
            if (n % 2 == 1) return false;
            for (int i = 1; i < n; i += 2) {
                if (p.charAt(i) != '*') {
                    return false;
                }
            }
            return true;
        }
        ref = new short[s.length() + 1][p.length() + 1];
        isMatch(s, 0, p, 0);
        return ref[0][0] == 2;
    }

    //"aabcbcbcaccbcaabc"
    //".*a*aa*.*b*.c*.*a*"
    public static void main(String[] args) {
        System.out.println(isMatch("aabcbcbcaccbcaabc", ".*a*aa*.*b*.c*.*a*"));
    }
}
