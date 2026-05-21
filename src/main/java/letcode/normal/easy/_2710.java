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

}
