package letcode.normal.medium;

/**
 * 假设有从 1 到 N 的N个整数，如果从这N个数字中成功构造出一个数组，
 * 使得数组的第 i位 (1 <= i <= N) 满足如下两个条件中的一个，
 * 我们就称这个数组为一个优美的排列。条件：  第i位的数字能被i整除 i 能被第 i 位上的数字整除 现在给定一个整数 N，请问可以构造多少个优美的排列？  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/beautiful-arrangement 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-08-16 16:18
 **/
public class _526FiveHundredTwentySix {

    /**
     * 其中 {num}({mask})num(mask) 表示二进制数 {mask}mask 中 11 的个数，x \mid yx∣y 表示 xx 可以整除 yy。
     *
     * 状态转移方程的含义为，当我们想要计算 f[{mask}]f[mask] 时，我们只需要在前 {num}({mask}) - 1num(mask)−1 位都已经放置了数的情况下，考虑第 {num}({mask})num(mask) 位要放置的数即可，我们枚举当前位的符合条件的数，并将方案数累加到 f[{mask}]f[mask] 中即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/beautiful-arrangement/solution/you-mei-de-pai-lie-by-leetcode-solution-vea2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public int countArrangement(int n) {
        /*
         * num 表示当前要放入的数
         * mask 的二进制表示数的分布
         * 所以num可能放在mask二进制位1的位置上
         */
        int[] f = new int[1 << n];
        f[0] = 1;
        for (int mask = 1; mask < (1 << n); mask++) {
            //当前要放入的数
            int num = Integer.bitCount(mask);
            for (int i = 0; i < n; i++) {
                //i+1位上有数，并且第i+1位上的数可以是num
                if ((mask & (1 << i)) != 0 && ((num % (i + 1)) == 0 || (i + 1) % num == 0)) {
                    //第i+1位上没有数，且当前放入的数是num-1
                    f[mask] += f[mask ^ (1 << i)];
                }
            }
        }
        return f[(1 << n) - 1];
    }

    public static void main(String[] args) {

    }


}
