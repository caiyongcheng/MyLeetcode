package letcode.normal.medium;

/**
 * 给你一个正整数 p 。你有一个下标从 1 开始的数组 nums ，这个数组包含范围 [1, 2p - 1] 内所有整数的二进制形式（两端都 包含）。
 * 你可以进行以下操作 任意 次：  从 nums 中选择两个元素 x 和 y  。 选择 x 中的一位与 y 对应位置的位交换。对应位置指的是两个整数 相同位置 的二进制位。
 * 比方说，如果 x = 1101 且 y = 0011 ，交换右边数起第 2 位后，我们得到 x = 1111 和 y = 0001 。
 * 请你算出进行以上操作 任意次 以后，nums 能得到的 最小非零 乘积。将乘积对 109 + 7 取余 后返回。  注意：答案应为取余 之前 的最小值。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/3/20 09:12
 */
public class _1969 {

    public static int MOD_NUM = 10_0000_0000 + 7;

    public int minNonZeroProduct(int p) {
        /*
        1、无论怎么交换数字 数字的和是不会变的 也就是数字总和不会变 每一个位上的和也不会变
        2、在和不变的情况下 想要乘积最小 那么元素之前的差距就要尽可能的大
        所以最终结果为 (2^p-2)^(2^p-2/2)
        对结果使用递归计算即可

        为什么 想要乘积最小 那么元素之前的差距就要尽可能的大 这样说呢
        因为 交换k位上的数字 本质上就是让一个数a增加2^(k-1),另外一个数b减少2^(k-1) 假设剩余数字乘积为c
        那么变化量为 [(a+2^(k-1)) * (b - 2^(k-1)) - ab] * c = S
        我们要求最小的乘积 也就是 S最小
        设 2^(k-1) 为 t
        那么 (a+t) * (b - t)) - ab 最小
        ab + tb - ta - t^2 - ab = t(b-a) - t^ 最小
        t是固定值，所以要让b-a最小 也就是b是最小元素，a是最大元素
        */
        return p == 1 ? 1 :
                (int) (calculate(
                        ((1L << p) - 2) % MOD_NUM,
                        (((1L << p) - 2) >> 1)
                ) * (((1L << p) - 1) % MOD_NUM) % MOD_NUM);
    }

    public long calculate(long baseNum, long indexNum) {
        if (indexNum <= 1) {
            return baseNum % MOD_NUM;
        }
        if ((indexNum & 1) == 0) {
            return calculate(baseNum * baseNum % MOD_NUM, indexNum >> 1) % MOD_NUM;
        }
        return calculate(baseNum * baseNum % MOD_NUM, indexNum >> 1) % MOD_NUM * baseNum % MOD_NUM;
    }

    /**
     * 示例 1：
     *
     * 输入：p = 1
     * 输出：1
     * 解释：nums = [1] 。
     * 只有一个元素，所以乘积为该元素。
     * 示例 2：
     *
     * 输入：p = 2
     * 输出：6
     * 解释：nums = [01, 10, 11] 。
     * 所有交换要么使乘积变为 0 ，要么乘积与初始乘积相同。
     * 所以，数组乘积 1 * 2 * 3 = 6 已经是最小值。
     * 示例 3：
     *
     * 输入：p = 3
     * 输出：1512
     * 解释：nums = [001, 010, 011, 100, 101, 110, 111]
     * - 第一次操作中，我们交换第二个和第五个元素最左边的数位。
     *     - 结果数组为 [001, 110, 011, 100, 001, 110, 111] 。
     * - 第二次操作中，我们交换第三个和第四个元素中间的数位。
     *     - 结果数组为 [001, 110, 001, 110, 001, 110, 111] 。
     * 数组乘积 1 * 6 * 1 * 6 * 1 * 6 * 7 = 1512 是最小乘积。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1969().minNonZeroProduct(4));
    }



}
