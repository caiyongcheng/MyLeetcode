package letcode.normal.easy;

/**
 * 给你一个 二进制 字符串 s ，其中至少包含一个 '1' 。  你必须按某种方式 重新排列 字符串中的位，使得到的二进制数字是可以由该组合生成的 最大二进制奇数 。
 * 以字符串形式，表示并返回可以由给定组合生成的最大二进制奇数。  注意 返回的结果字符串 可以 含前导零。
 *
 * 提示：
 *
 * 1 <= s.length <= 100
 * s 仅由 '0' 和 '1' 组成
 * s 中至少包含一个 '1'
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/3/13 10:52
 */
public class _2864 {


    public String maximumOddBinaryNumber(String s) {
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        int oneChSize = 0;
        for (int i = 0; i < len; i++) {
            oneChSize += s.charAt(i) == '1' ? 1 : 0;
        }
        --oneChSize;
        for (int i = 0; i < oneChSize; i++) {
            sb.append('1');
        }
        len = len - oneChSize - 1;
        for (int i = 0; i < len; i++) {
            sb.append('0');
        }
        if (oneChSize >= 0) {
            sb.append('1');
        }
        return sb.toString();
    }

}
