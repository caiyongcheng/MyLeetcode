package letcode.offer.easy;

/**
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。
 * 斐波那契数列的定义如下：  F(0) = 0, F(1)= 1 F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-09-04 22:18
 **/
public class _Offer_10Ten {


    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        int fc1 = 0;
        int fc2 = 1;
        final int MOD_NUM = 1000000000 + 7;
        while (n >= 2) {
            fc2 = (fc2 + fc1) % MOD_NUM;
            fc1 = (fc2 + MOD_NUM - fc1) % MOD_NUM;
            --n;
        }
        return fc2;
    }

    public static void main(String[] args) {
        System.out.println(new _Offer_10Ten().fib(5));
    }

}
