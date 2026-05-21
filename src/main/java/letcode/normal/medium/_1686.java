package letcode.normal.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Alice 和 Bob 轮流玩一个游戏，Alice 先手。
 * 一堆石子里总共有 n 个石子，轮到某个玩家时，他可以 移出 一个石子并得到这个石子的价值。Alice 和 Bob 对石子价值有 不一样的的评判标准 。双方都知道对方的评判标准。
 * 给你两个长度为 n 的整数数组 aliceValues 和 bobValues 。aliceValues[i] 和 bobValues[i] 分别表示 Alice 和 Bob 认为第 i 个石子的价值。
 * 所有石子都被取完后，得分较高的人为胜者。如果两个玩家得分相同，那么为平局。两位玩家都会采用 最优策略 进行游戏。
 * 请你推断游戏的结果，用如下的方式表示：
 * 如果 Alice 赢，返回 1 。
 * 如果 Bob 赢，返回 -1 。
 * 如果游戏平局，返回 0 。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/2/4 15:09
 */
public class _1686 {

    public int stoneGameVI(int[] aliceValues, int[] bobValues) {
        /*
        对于一个位置的棋子取值而言 收益为 我方得到的价值+因我方选取了而对方选取不到从而失去的价值
        也就是 aliceValues[i]+bobValues[i]
        那么每一步都选择最优的 就可以得到结果
         */
        int[][] profit = new int[aliceValues.length][2];
        for (int i = 0; i < aliceValues.length; i++) {
            profit[i][0] = aliceValues[i];
            profit[i][1] = bobValues[i];
        }
        Arrays.sort(profit, Comparator.comparingInt(a -> a[0] + a[1]));
        int aScore = 0;
        int bScore = 0;
        boolean curOpt = true;
        for (int i = profit.length - 1; i >= 0; i--) {
            if (curOpt) {
                aScore += profit[i][0];
            } else {
                bScore += profit[i][1];
            }
            curOpt = !curOpt;
        }
        return Integer.compare(aScore, bScore);
    }

}
