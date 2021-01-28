package letcode.normal.medium;

/**
 * @program: Leetcode
 * @description: 请你帮忙设计一个程序，用来找出第 n 个丑数。 丑数是可以被 a 或 b 或 c 整除的 正整数。
 * @author: 蔡永程
 * @create: 2021-01-21 09:17
 */
public class N_1201OneThousandTwoHundredOne {


    public static void main(String[] args) {

    }

    public int calculateGCD(int x, int y) {
        if (x < y) {
            return calculateGCD(y, x);
        }
        int mod = x % y;
        while (mod != 0) {
            x = y;
            y = mod;
            mod = x % y;
        }
        return y;
    }

    //public int getTargetAmount(int )

    public int calculateLCM(long x, long y) {
        return (int) (x * y / calculateGCD((int) x, (int) y));
    }

    public int nthUglyNumber(int n, int a, int b, int c) {
        int lo = Math.min(Math.min(a, b), c);
        int hi = lo * n;
/*        while (lo <= hi) {

        }*/
        return 0;
    }

}