package letcode.medium;

/**
 * Leetcode
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/divide-two-integers 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : CaiYongcheng
 * @date : 2020-06-30 21:02
 **/
public class _29TwentyNine {

    /**
     * 示例 1:
     * 输入: dividend = 10, divisor = 3
     * 输出: 3
     * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
     *
     * 示例 2:
     * 输入: dividend = 7, divisor = -3
     * 输出: -2
     * 解释: 7/-3 = truncate(-2.33333..) = -2
     * @param dividend
     * @param divisor
     * @return
     */
    public static int divide(int dividend, int divisor) {
        boolean sign = (dividend < 0 && divisor < 0) || (dividend > 0 && divisor > 0);
        long dividendl = dividend;
        long divisorl = divisor;
        dividendl = Math.abs(dividendl);
        divisorl = Math.abs(divisorl);
        if (divisorl > dividendl) {
            return 0;
        }
        if (divisorl == dividendl) {
            return sign ? 1 : -1;
        }
        long doubleDivisor = divisorl;
        long exp = 1;
        long div = 0;
        while (true){
            if (doubleDivisor <= dividendl){
                dividendl -= doubleDivisor;
                div += exp;
                doubleDivisor = doubleDivisor << 1;
                exp = exp << 1;
            }else{
                if (dividendl < divisorl){
                    break;
                }
                doubleDivisor = doubleDivisor >> 1;
                exp = exp >> 1;
            }
        }
        return (int) (sign ? (div > Integer.MAX_VALUE ? Integer.MAX_VALUE : div)
                : (-div < Integer.MIN_VALUE ? Integer.MAX_VALUE : -div));
    }

    public static void main(String[] args) {
        System.out.println(divide(-2147483648 ,-1));
    }

}
