package letcode.normal.medium;

import letcode.utils.TestCaseInputUtils;
import letcode.utils.TestCaseOutputUtils;

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

}
