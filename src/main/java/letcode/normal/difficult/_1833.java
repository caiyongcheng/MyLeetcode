package letcode.normal.difficult;

/**
 * 给你一个整数 hoursBefore ，表示你要前往会议所剩下的可用小时数。要想成功抵达会议现场，你必须途经 n 条道路。道路的长度用一个长度为 n 的整数数组 dist 表示
 * ，其中 dist[i] 表示第 i 条道路的长度（单位：千米）。另给你一个整数 speed ，表示你在道路上前进的速度（单位：千米每小时）。
 * 当你通过第 i 条路之后，就必须休息并等待，直到 下一个整数小时 才能开始继续通过下一条道路。注意：你不需要在通过最后一条道路后休息，因为那时你已经抵达会议现场。
 * 例如，如果你通过一条道路用去 1.4 小时，那你必须停下来等待，到 2 小时才可以继续通过下一条道路。如果通过一条道路恰好用去 2 小时，就无需等待，可以直接继续。
 * 然而，为了能准时到达，你可以选择 跳过 一些路的休息时间，这意味着你不必等待下一个整数小时。注意，这意味着与不跳过任何休息时间相比，你可能在不同时刻到达接下来的道路。
 * 例如，假设通过第 1 条道路用去 1.4 小时，且通过第 2 条道路用去 0.6 小时。跳过第 1 条道路的休息时间意味着你将会在恰好 2 小时完成通过第 2 条道路，且你能够立即开始通过第 3 条道路。
 * 返回准时抵达会议现场所需要的 最小跳过次数 ，如果 无法准时参会 ，返回 -1 。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/4/19 16:42
 */
public class _1833 {

    public int minSkips(int[] dist, int speed, int hoursBefore) {
        /*
        例如，假设通过第 1 条道路用去 1.4 小时，且通过第 2 条道路用去 0.6 小时。
        跳过第 1 条道路的休息时间意味着你将会在恰好 2 小时完成通过第 2 条道路，且你能够立即开始通过第 3 条道路。

        这句话意味着 跳过i的时候，对于i+1、i+2都是有影响的 也就是跳过i实际上可能等价于跳过i、i+1、i+2...

        因为求得是最少跳过次数
        可不可以二分枚举呢

        二分枚举的话 涉及到一个问题 就是怎么计算 次数是符合条件的
        如果能计算的话 是不是直接就可以求出最少的了

        考虑动态规划
        cache[i][j] 表示第i段路径在j次跳过情况下的最少花费时间
        那么 cache[i][j] = min([cache[i-1][j] + dist[i] / speed取整], [cache[i-1][j-1] + dist[i] / speed])
         */

        /*
        避免浮点数的精度计算
         */
        for (int i = 0; i < dist.length; i++) {
            dist[i] *= speed;
        }
        hoursBefore *= speed;

        int n = dist.length;
        long[][] cache = new long[n + 1][n + 1];
        return 0;

    }






}
