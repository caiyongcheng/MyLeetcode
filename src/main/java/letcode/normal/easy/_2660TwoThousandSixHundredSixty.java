package letcode.normal.easy;

import letcode.utils.TestCaseUtils;

/**
 * 给你两个下标从 0 开始的整数数组 player1 和 player2 ，分别表示玩家 1 和玩家 2 击中的瓶数。  保龄球比赛由 n 轮组成，每轮的瓶数恰好为 10 。  假设玩家在第 i 轮中击中 xi 个瓶子。玩家第 i 轮的价值为：  如果玩家在该轮的前两轮的任何一轮中击中了 10 个瓶子，则为 2xi 。 否则，为 xi 。 玩家的得分是其 n 轮价值的总和。  返回  如果玩家 1 的得分高于玩家 2 的得分，则为 1 ； 如果玩家 2 的得分高于玩家 1 的得分，则为 2 ； 如果平局，则为 0 。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2023/12/27 09:40
 */
public class _2660TwoThousandSixHundredSixty {

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


    /**
     * 示例 1：
     *
     * 输入：player1 = [4,10,7,9], player2 = [6,5,2,3]
     * 输出：1
     * 解释：player1 的得分是 4 + 10 + 2*7 + 2*9 = 46 。
     * player2 的得分是 6 + 5 + 2 + 3 = 16 。
     * player1 的得分高于 player2 的得分，所以 play1 在比赛中获胜，答案为 1 。
     * 示例 2：
     *
     * 输入：player1 = [3,5,7,6], player2 = [8,10,10,2]
     * 输出：2
     * 解释：player1 的得分是 3 + 5 + 7 + 6 = 21 。
     * player2 的得分是 8 + 10 + 2*10 + 2*2 = 42 。
     * player2 的得分高于 player1 的得分，所以 play2 在比赛中获胜，答案为 2 。
     * 示例 3：
     *
     * 输入：player1 = [2,3], player2 = [4,1]
     * 输出：0
     * 解释：player1 的得分是 2 + 3 = 5 。
     * player2 的得分是 4 + 1 = 5 。
     * player1 的得分等于 player2 的得分，所以这一场比赛平局，答案为 0 。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2660TwoThousandSixHundredSixty().isWinner(
                TestCaseUtils.getIntArr("[4,10,7,9]"),
                TestCaseUtils.getIntArr("[6,5,2,3]")
        ));
    }
}
