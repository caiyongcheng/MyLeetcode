package letcode.easy;

import java.util.Arrays;

/**
 * @program: StudyHTTP
 * @description: 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/implement-strstr
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-06-20 11:05
 */
public class _28TwentyEight {

    /**
     * 示例 1:
     * 输入: haystack = "hello", needle = "ll"
     * 输出: 2
     * <p>
     * 示例 2:
     * 输入: haystack = "aaaaa", needle = "bba"
     * 输出: -1
     * 说明:
     * <p>
     * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
     * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
     *
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStrUseKMP(String haystack, String needle) {
        if (haystack == null) {
            return -1;
        }
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        //1 构造next数组
        int i = 0;
        int k = -1;
        int pn = needle.length();
        int sn = haystack.length();
        int[] next = new int[pn];
        next[0] = -1;
        while (i < pn - 1) {
            if (k == -1 || next[i] == next[k]) {
                i++;
                k++;
                next[i] = k;
            } else {
                k = next[k];
            }
        }
        i = 0;
        k = 0;
        while (i < sn && k < pn) {
            if (k == -1 || haystack.charAt(i) == needle.charAt(k)) {
                System.out.println(i + "  " + k);
                ++i;
                ++k;
            } else {
                k = next[k];
            }
        }
        System.out.println(Arrays.toString(next));
        if (k >= pn) return i - k;
        return -1;
    }

    public static void main(String[] args) {
        /**
         * "mississippi"
         * "issip"
         */
        System.out.println(strStrUseKMP("mississippi", "issip"));
    }
}