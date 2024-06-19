package letcode.normal.medium;

import letcode.utils.FormatUtils;
import letcode.utils.TestCaseUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 给你一个整数数组 matches 其中 matches[i] = [winneri, loseri] 表示在一场比赛中 winneri 击败了 loseri 。
 * 返回一个长度为 2 的列表 answer ：  answer[0] 是所有 没有 输掉任何比赛的玩家列表。
 * answer[1] 是所有恰好输掉 一场 比赛的玩家列表。 两个列表中的值都应该按 递增 顺序返回。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/5/22 09:03
 */
public class  _2225 {

    public List<List<Integer>> findWinners(int[][] matches) {
        Set<Integer> lose0Set = new HashSet<>();
        Set<Integer> lose1Set = new HashSet<>();
        Set<Integer> loseMoreSet = new HashSet<>();
        for (int[] match : matches) {
            if (!loseMoreSet.contains(match[1])) {
                if (lose1Set.contains(match[1])) {
                    lose1Set.remove(match[1]);
                    loseMoreSet.add(match[1]);
                } else {
                    lose1Set.add(match[1]);
                    lose0Set.remove(match[1]);
                }
            }
            if (!loseMoreSet.contains(match[0]) && !lose1Set.contains(match[0])) {
                lose0Set.add(match[0]);
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        ans.add(lose0Set.stream().sorted().collect(Collectors.toList()));
        ans.add(lose1Set.stream().sorted().collect(Collectors.toList()));
        return ans;
    }

    /**
     * 示例 1：
     *
     * 输入：matches = [[1,3],[2,3],[3,6],[5,6],[5,7],[4,5],[4,8],[4,9],[10,4],[10,9]]
     * 输出：[[1,2,10],[4,5,7,8]]
     * 解释：
     * 玩家 1、2 和 10 都没有输掉任何比赛。
     * 玩家 4、5、7 和 8 每个都输掉一场比赛。
     * 玩家 3、6 和 9 每个都输掉两场比赛。
     * 因此，answer[0] = [1,2,10] 和 answer[1] = [4,5,7,8] 。
     * 示例 2：
     *
     * 输入：matches = [[2,3],[1,3],[5,4],[6,4]]
     * 输出：[[1,2,5,6],[]]
     * 解释：
     * 玩家 1、2、5 和 6 都没有输掉任何比赛。
     * 玩家 3 和 4 每个都输掉两场比赛。
     * 因此，answer[0] = [1,2,5,6] 和 answer[1] = [] 。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(FormatUtils.formatNestList(
                new _2225().findWinners(
                        TestCaseUtils.get2DIntArr("[[1,3],[2,3],[3,6],[5,6],[5,7],[4,5],[4,8],[4,9],[10,4],[10,9]]")
                )
        ));
    }

}
