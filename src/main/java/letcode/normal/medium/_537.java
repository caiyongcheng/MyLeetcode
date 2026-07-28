package letcode.normal.medium;

/**
 * 复数 可以用字符串表示，遵循 "实部+虚部i" 的形式，并满足下述条件：  实部 是一个整数，取值范围是 [-100, 100] 虚部 也是一个整数，
 * 取值范围是 [-100, 100] i2 == -1 给你两个字符串表示的复数 num1 和 num2 ，请你遵循复数表示形式，返回表示它们乘积的字符串。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/complex-number-multiplication 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-02-25 09:03
 **/
public class _537 {

    public String complexNumberMultiply(String num1, String num2) {
        int[] cn1 = parseComplexNumStr(num1);
        int[] cn2 = parseComplexNumStr(num2);
        int[] ans = {0, 0};
        ans[0] = cn1[0] * cn2[0] - cn1[1] * cn2[1];
        ans[1] = cn1[0] * cn2[1] + cn2[0] * cn1[1];
        return ans[0] + "+" + ans[1] + "i";
    }

    public int[] parseComplexNumStr(String num) {
        int[] complexNum = new int[]{0, 0};
        int i = 0;
        char ch;
        int flag = 1;
        if (num.charAt(0) == '-') {
            flag = -1;
            ++i;
        }
        while (i < num.length()) {
            ch = num.charAt(i);
            if (ch >= '0' && ch <= '9') {
                complexNum[0] = complexNum[0] * 10 + (ch - '0');
                ++i;
            } else {
                break;
            }
        }
        complexNum[0] *= flag;
        ++i;
        if (num.charAt(i) == '-') {
            flag = -1;
            ++i;
        } else {
            flag = 1;
        }
        while (i < num.length()) {
            ch = num.charAt(i);
            if (ch >= '0' && ch <= '9') {
                complexNum[1] = complexNum[1] * 10 + (ch - '0');
            }
            ++i;
        }
        complexNum[1] *= flag;
        return complexNum;
    }

}
