package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * Alice 和 Bob 正在玩一个有 n 个关卡的游戏，游戏中有一些关卡是 困难 模式，其他的关卡是 简单 模式。如果 possible[i] == 0 ，
 * 那么第 i 个关卡是 困难 模式。一个玩家通过一个简单模式的关卡可以获得 1 分，通过困难模式的关卡将失去 1 分。
 * 游戏的一开始，Alice 将从第 0 级开始 按顺序 完成一些关卡，然后 Bob 会完成剩下的所有关卡。
 * 假设两名玩家都采取最优策略，目的是 最大化 自己的得分，Alice 想知道自己 最少 需要完成多少个关卡，才能获得比 Bob 更多的分数。
 * 请你返回 Alice 获得比 Bob 更多的分数所需要完成的 最少 关卡数目，如果 无法 达成，那么返回 -1 。
 * 注意，每个玩家都至少需要完成 1 个关卡。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-07-19 09:28
 */
public class _3096 {

    public int minimumLevels(int[] possible) {
        int totalScore = 0;
        for (int score : possible) {
            totalScore += (score == 0 ? - 1 : 1);
        }

        int curScore = 0;
        for (int i = 0; i < possible.length - 1; i++) {
            curScore += (possible[i] == 0 ? - 1 : 1);
            if (curScore > totalScore - curScore) {
                return i + 1;
            }
        }
        return -1;
    }


    /**
     Example 1:
     Input: possible = [1,0,1,0]
     Output: 1
     Explanation:
     Let's look at all the levels that Alice can play up to:
     If Alice plays only level 0 and Bob plays the rest of the levels, Alice has 1 point, while Bob has -1 + 1 - 1 = -1 point.
     If Alice plays till level 1 and Bob plays the rest of the levels, Alice has 1 - 1 = 0 points, while Bob has 1 - 1 = 0 points.
     If Alice plays till level 2 and Bob plays the rest of the levels, Alice has 1 - 1 + 1 = 1 point, while Bob has -1 point.
     Alice must play a minimum of 1 level to gain more points.
     Example 2:
     Input: possible = [1,1,1,1,1]
     Output: 3
     Explanation:
     Let's look at all the levels that Alice can play up to:
     If Alice plays only level 0 and Bob plays the rest of the levels, Alice has 1 point, while Bob has 4 points.
     If Alice plays till level 1 and Bob plays the rest of the levels, Alice has 2 points, while Bob has 3 points.
     If Alice plays till level 2 and Bob plays the rest of the levels, Alice has 3 points, while Bob has 2 points.
     If Alice plays till level 3 and Bob plays the rest of the levels, Alice has 4 points, while Bob has 1 point.
     Alice must play a minimum of 3 levels to gain more points.
     Example 3:
     Input: possible = [0,0]
     Output: -1
     Explanation:
     The only possible way is for both players to play 1 level each. Alice plays level 0 and loses 1 point. Bob plays level 1 and loses 1 point. As both players have equal points, Alice can't gain more points than Bob.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.testBatch(_3096.class,
                "Example 1: Input: possible = [1,0,1,0] Output: 1 Explanation: Let's look at all the levels that Alice can play up to: If Alice plays only level 0 and Bob plays the rest of the levels, Alice has 1 point, while Bob has -1 + 1 - 1 = -1 point. If Alice plays till level 1 and Bob plays the rest of the levels, Alice has 1 - 1 = 0 points, while Bob has 1 - 1 = 0 points. If Alice plays till level 2 and Bob plays the rest of the levels, Alice has 1 - 1 + 1 = 1 point, while Bob has -1 point. Alice must play a minimum of 1 level to gain more points. Example 2: Input: possible = [1,1,1,1,1] Output: 3 Explanation: Let's look at all the levels that Alice can play up to: If Alice plays only level 0 and Bob plays the rest of the levels, Alice has 1 point, while Bob has 4 points. If Alice plays till level 1 and Bob plays the rest of the levels, Alice has 2 points, while Bob has 3 points. If Alice plays till level 2 and Bob plays the rest of the levels, Alice has 3 points, while Bob has 2 points. If Alice plays till level 3 and Bob plays the rest of the levels, Alice has 4 points, while Bob has 1 point. Alice must play a minimum of 3 levels to gain more points. Example 3: Input: possible = [0,0] Output: -1 Explanation: The only possible way is for both players to play 1 level each. Alice plays level 0 and loses 1 point. Bob plays level 1 and loses 1 point. As both players have equal points, Alice can't gain more points than Bob.");

    }

}
