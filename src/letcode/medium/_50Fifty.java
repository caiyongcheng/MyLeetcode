package letcode.medium;

/**
 * Leetcode
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 *
 * @author : CaiYongcheng
 * @date : 2020-07-09 09:32
 **/
public class _50Fifty {

    public static double Pow(double x, long n){
        double res = 1;
        while (n > 0){
            if (n % 2 == 1){
                res *= x;
            }
            x *= x;
            n = n >>> 1;
        }
        return res;
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
     *
     * 说明:
     * -100.0 < x < 100.0
     * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
     * @param x
     * @param n
     * @return
     */
    public static double myPow(double x, int n) {
        long N = n;
        if (N == 0) return 1;
        if (N == 1) return x;
        if (x == 0) return 0;
        return n > 0 ? Pow(x, N) : 1.0 / Pow(x ,-N);
    }

    public static void main(String[] args) {
        System.out.println(myPow(2.00000,
                -2147483648));
    }

}
