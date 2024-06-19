package letcode.normal.easy;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/10/17 10:08
 * description 给你一个正整数 n ，请你计算在 [1，n] 范围内能被 3、5、7 整除的所有整数之和。  返回一个整数，用于表示给定范围内所有满足约束条件的数字之和。
 */
public class _2652 {

    public int f(int n, int m) {
        return (m + n / m * m) * (n / m) / 2;
    }

    public int sumOfMultiples(int n) {
        return f(n, 3) + f(n, 5) + f(n, 7) - f(n, 3 * 5) - f(n, 3 * 7) - f(n, 5 * 7) + f(n, 3 * 5 * 7);
    }


}
