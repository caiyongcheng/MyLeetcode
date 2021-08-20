package letcode.normal.medium;

/**
 * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每 2k 个字符反转前 k 个字符。
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/reverse-string-ii 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-08-20 14:31
 **/
public class _541FiveHundredFortyOne {

    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        int right;
        int left;
        char tmp;
        int k2 = k << 1;
        for (int i = 0; i < chars.length; i += k2) {
            right = i + k > chars.length ? chars.length - 1 : i + k - 1;
            left = i;
            while (left < right) {
                tmp = chars[left];
                chars[left] = chars[right];
                chars[right] = tmp;
                ++left;
                --right;
            }
        }
        return new String(chars);
    }


    /**
     * 示例 1：
     * 输入：s = "abcdefg", k = 2
     * 输出："bacdfeg"
     *
     * 示例 2：
     * 输入：s = "abcd", k = 2
     * 输出："bacd"
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-string-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _541FiveHundredFortyOne().reverseStr(
                "bacdfeg",  2
        ));
    }


}
