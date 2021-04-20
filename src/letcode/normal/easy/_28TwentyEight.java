package letcode.easy;

import java.util.Arrays;

/**
 * @program: StudyHTTP
 * @description: 给定一个haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回 -1。
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
     * 当needle是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
     * 对于本题而言，当needle是空字符串时我们应当返回 0 。这与C语言的strstr()以及 Java的indexOf()定义相符。
     *
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStrUseKMP(String haystack, String needle) {
        if (needle.length() > haystack.length()) {
            return -1;
        }
        if ("".equals(needle)) {
            return 0;
        }
        char[] textArr = haystack.toCharArray();
        char[] targetArr = needle.toCharArray();
        int[] next = new int[targetArr.length];
        next[0] = -1;
        int start = -1;
        int end = 0;
        int length = targetArr.length - 1;
        while (end < length) {
            if (start == -1 || targetArr[start] == targetArr[end]) {
                ++end;
                ++start;
                next[end] = start;
            } else {
                start = next[start];
            }
        }
        int indexi = 0;
        int indexj = 0;
        while (indexi < textArr.length && indexj < targetArr.length) {
            if (indexj == -1 || textArr[indexi] == targetArr[indexj]) {
                ++indexj;
                ++indexi;
            } else {
                indexj = next[indexj];
            }
        }
        return indexj >= targetArr.length ? indexi - targetArr.length : -1;
    }

    /**
     * 示例 1：
     *
     * 输入：haystack = "hello", needle = "ll"
     * 输出：2
     * 示例 2：
     *
     * 输入：haystack = "aaaaa", needle = "bba"
     * 输出：-1
     * 示例 3：
     *
     * 输入：haystack = "", needle = ""
     * 输出：0
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/implement-strstr
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(strStrUseKMP("mississippi", "issi"));
    }
}