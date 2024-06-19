package letcode.normal.medium;

/**
 * 给你一个二进制字符串 binary ，它仅有 0 或者 1 组成。你可以使用下面的操作任意次对它进行修改：
 * 操作 1 ：如果二进制串包含子字符串 "00" ，你可以用 "10" 将其替换。 比方说， "00010" -> "10010"
 * 操作 2 ：如果二进制串包含子字符串 "10" ，你可以用 "01" 将其替换。
 * 比方说， "00010" -> "00001" 请你返回执行上述操作任意次以后能得到的 最大二进制字符串 。
 * 如果二进制字符串 x 对应的十进制数字大于二进制字符串 y 对应的十进制数字，那么我们称二进制字符串 x 大于二进制字符串 y 。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/4/10 09:15
 */
public class _1702 {


    public String maximumBinaryString(String binary) {
        // 对于操作1  可以将00...0共p位长度 变化为 11111...0
        // 对于操作2  可以将 1110 => 0111
        // 由操作1、2可以得出结果中最多有1个0
        // 操作是这样 首先找到 第一个0 形式为 111...0xxxxxxxx
        // 之后找到下一个111...10形式的 将其变为 0111...1形式
        // 这样构成111...0 0111...1xxxx =》 111...1 0111...1xxxx 等价于第一个0向后移动了1位
        // 而111...1000..000形式的有几个0就会移动几位
        int zeroCnt = -1;
        StringBuilder sb = new StringBuilder(binary);
        int len = sb.length();
        for (int i = 0; i < len; i++) {
            if (sb.charAt(i) == '0') {
                ++zeroCnt;
            }
        }
        if (zeroCnt <= 0) {
            return binary;
        }
        for (int i = 0; i < len; i++) {
            if (sb.charAt(i) == '0') {
                sb.setCharAt(i, '1');
                int startIdx = i + zeroCnt;
                for (++i; i < len; ++i) {
                    if (sb.charAt(i) == '0') {
                        sb.setCharAt(i, '1');
                    }
                }
                sb.setCharAt(startIdx, '0');
            }
        }
        return sb.toString();
    }


    /**
     * 示例 1：
     * 0000 1110
     * 输入：binary = "000110"
     * 输出："111011"
     * 解释：一个可行的转换为：
     * "000110" -> "000101"
     * "000101" -> "100101"
     * "100101" -> "110101"
     * "110101" -> "110011"
     * "110011" -> "111011"
     * 示例 2：
     *
     * 输入：binary = "01"
     * 输出："01"
     * 解释："01" 没办法进行任何转换。
     *
     * 01111001100000110010
     * 0 111100 1100000 1100 10
     * 0 111110 1111110 1110 10
     * 0 011111 1111110 1110 10
     * 10 111111111110 1110 10
     * 11110111111111111111
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1702().maximumBinaryString(
                "01111001100000110010"
        ));
    }

}
