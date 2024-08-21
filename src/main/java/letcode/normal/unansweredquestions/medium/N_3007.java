package letcode.normal.unansweredquestions.medium;

/**
 * 给你一个整数 k 和一个整数 x 。整数 num 的价值是它的二进制表示中在 x，2x，3x 等位置处
 * 设置位  的数目（从最低有效位开始）。下面的表格包含了如何计算价值的例子。
 * 提示：
 * 1 <= k <= 1015
 * 1 <= x <= 8
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-08-21 09:17
 */
public class N_3007 {

    public long findMaximumNumber(long k, int x) {
        /*
        朴素思路 对于每个数字可以快速计算出他的价值 然后从1开始 一直计算到结果即可
        但是这样 按照数据规模 会超时
        设最高位为x的 价值和为 total(1), 有价值元素数量为count  total(1) = count = 1 << 2x-1
        那么total(2)可以看作是 total(1)[3x为0] + (total(1) + 1) * count + count = total(1) + (total(1) + 2) * count
        那么total(3)可以看作是 total(2)[4x为0] + (total(2) + 1) * count + count = total(2) + (total(2) + 2) * count

        count = 1
        t2 = 1 + (1 + 1) * 1 + 1 = 4
        t3 = 4 + (4 + 2) + 1 = 10

         */
        long nxLong = Long.highestOneBit(k);
        return 1L;
    }

    public static void main(String[] args) {
        System.out.println(Integer.highestOneBit(15));
    }


}
