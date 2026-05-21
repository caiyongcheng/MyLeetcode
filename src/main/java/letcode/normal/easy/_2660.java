package letcode.normal.easy;

import letcode.utils.TestCaseInputUtils;

/**
 * 给你两个下标从 0 开始的整数数组 player1 和 player2 ，分别表示玩家 1 和玩家 2 击中的瓶数。  保龄球比赛由 n 轮组成，每轮的瓶数恰好为 10 。  假设玩家在第 i 轮中击中 xi 个瓶子。玩家第 i 轮的价值为：  如果玩家在该轮的前两轮的任何一轮中击中了 10 个瓶子，则为 2xi 。 否则，为 xi 。 玩家的得分是其 n 轮价值的总和。  返回  如果玩家 1 的得分高于玩家 2 的得分，则为 1 ； 如果玩家 2 的得分高于玩家 1 的得分，则为 2 ； 如果平局，则为 0 。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2023/12/27 09:40
 */
public class _2660 {

    public int isWinner(int[] player1, int[] player2) {
        int score1 = getTotalScore(player1);
        int score2 = getTotalScore(player2);
        return score1 == score2 ? 0 : (score1 > score2 ? 1 : 2);
    }

    public int getTotalScore(int[] player) {
        int score = 0;
        for (int i = 0; i < player.length; i++) {
            if ((i > 0 && player[i - 1] == 10) || (i > 1 && player[i - 2] == 10)) {
                score = score + player[i] << 1;
            } else {
                score += player[i];
            }
        }
        return score;
    }


}
