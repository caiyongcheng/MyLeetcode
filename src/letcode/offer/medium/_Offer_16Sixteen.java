package offer.medium;

/**
 * @program: Leetcode
 * @description: 实现函数double Power(double base, int exponent)，求base的exponent次方。不得使用库函数，同时不需要考虑大数问题。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2021-01-21 10:34
 */
public class _Offer_16Sixteen {


    public  double myPowRecursion(double x, int n) {
        if (n == 1) {
            return x;
        }
        if (n == 0) {
            return 1;
        }
        double mid = myPowRecursion(x, n/2);
        if ((n & 1) == 0) {
            return mid * mid;
        }
        return mid * mid * x;
    }


    public double myPow(double x, int n) {
        if (x == 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        if (n > 0) {
            return ((int)((myPowRecursion(x, n)) * 100000)) / 100000.0;
        }
        return ((int)((1 / myPowRecursion(x, n)) * 100000)) / 100000.0;
    }


    /**
     * 示例 1:
     * 输入: 2.00000, 10
     * 输出: 1024.00000
     *
     * 示例 2:
     * 输入: 2.10000, 3
     * 输出: 9.26100
     *
     * 示例 3:
     * 输入: 2.00000, -2
     * 输出: 0.25000
     * 解释: 2-2 = 1/22 = 1/4 = 0.25
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _Offer_16Sixteen().myPow(2.10000, 3));

    }


}