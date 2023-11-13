package letcode.normal.easy;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/11/10 11:14
 * description    给你一个仅由 0 和 1 组成的二进制字符串 s 。
 * 如果子字符串中 所有的 0 都在 1 之前 且其中 0 的数量等于 1 的数量，则认为 s 的这个子字符串是平衡子字符串。请注意，空子字符串也视作平衡子字符串。
 * 返回  s 中最长的平衡子字符串长度。
 * 子字符串是字符串中的一个连续字符序列。
 */
public class _2609TwoThousandSixHundredNine {

    public int findTheLongestBalancedSubstring(String s) {
        char[] charArray = s.toCharArray();
        int len0, len1;
        int ans = 0;
        for (int i = 0; i < charArray.length; ) {
            len0 = len1 = 0;
            for (; i < charArray.length && charArray[i] == '0'; ++i, ++len0);
            for (; i < charArray.length && charArray[i] == '1'; ++i, ++len1);
            ans = Math.max(ans, Math.min(len0, len1) << 1);
        }
        return ans;
    }

    /**
     * 示例 1：
     *
     * 输入：s = "01000111"
     * 输出：6
     * 解释：最长的平衡子字符串是 "000111" ，长度为 6 。
     * 示例 2：
     *
     * 输入：s = "00111"
     * 输出：4
     * 解释：最长的平衡子字符串是 "0011" ，长度为  4 。
     * 示例 3：
     *
     * 输入：s = "111"
     * 输出：0
     * 解释：除了空子字符串之外不存在其他平衡子字符串，所以答案为 0 。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2609TwoThousandSixHundredNine().findTheLongestBalancedSubstring(
                "00"
        ));
    }

}
