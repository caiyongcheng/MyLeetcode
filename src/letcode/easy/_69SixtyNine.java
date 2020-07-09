package letcode.easy;

/**
 * StudyHTTP
 * 实现 int sqrt(int x) 函数。  计算并返回 x 的平方根，其中 x 是非负整数。  由
 * 于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/sqrtx 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : CaiYongcheng
 * @date : 2020-06-26 10:47
 **/
public class _69SixtyNine {

    /**
     * 示例 1:
     * 输入: 4
     * 输出: 2
     *
     * 示例 2:
     * 输入: 8
     * 输出: 2
     * 说明: 8 的平方根是 2.82842...,
     *      由于返回类型是整数，小数部分将被舍去。
     * @param x
     * @return
     */
    public static int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }

        double C = x, x0 = x;
        while (true) {
            double xi = 0.5 * (x0 + C / x0);
            if (Math.abs(x0 - xi) < 1e-7) {
                break;
            }
            x0 = xi;
        }
        return (int)x0;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(2147395599));
    }

}
