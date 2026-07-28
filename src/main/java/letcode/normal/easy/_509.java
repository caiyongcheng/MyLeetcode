package normal.easy;

/**
 * @program: Leetcode
 * @description: 斐波那契数，通常用F(n) 表示，形成的序列称为 斐波那契数列 。
 * 该数列由0 和 1 开始，后面的每一项数字都是前面两项数字的和。
 * 也就是：  F(0) = 0，F(1)= 1 F(n) = F(n - 1) + F(n - 2)，其中 n > 1 给你 n ，请计算 F(n) 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/fibonacci-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2021-01-04 11:06
 */
public class _509 {

    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        int n1 = 0;
        int n2 = 1;
        int n3;
        int index = 2;
        while (index <= n) {
            n3 = n1 + n2;
            n1 = n2;
            n2 = n3;
            ++index;
        }
        return n2;
    }

}
