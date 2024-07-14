package letcode.normal.easy;

/**
 * 给你一个用字符串表示的正整数 num ，请你以字符串形式返回不含尾随零的整数 num 。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-07-01 09:03
 */
public class _2710 {

    public String removeTrailingZeros(String num) {
        int len = num.length();
        for (int i = len - 1; i >= 0; i--) {
            if (num.charAt(i) != '0') {
                return num.substring(0, i + 1);
            }
        }
        return "";
    }

    /**
     * 示例 1：
     *
     * 输入：num = "51230100"
     * 输出："512301"
     * 解释：整数 "51230100" 有 2 个尾随零，移除并返回整数 "512301" 。
     * 示例 2：
     *
     * 输入：num = "123"
     * 输出："123"
     * 解释：整数 "123" 不含尾随零，返回整数 "123" 。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2710().removeTrailingZeros("123"));
    }

}
