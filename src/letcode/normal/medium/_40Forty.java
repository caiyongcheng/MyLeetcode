package letcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Leetcode
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/combination-sum-ii
 * 说明：
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : CaiYongcheng
 * @date : 2020-07-06 11:05
 **/
public class _40Forty {

    public static int[] datas;

    public static int datasLength;

    public static int goal;

    public static HashMap<Integer, Integer> numSize = new HashMap<Integer, Integer>();

    public static List<Integer> list = new ArrayList<Integer>();
    ;

    public static List<List<Integer>> lists = new ArrayList<List<Integer>>();

    public static void search(int index, int sum) {
        if (sum == goal) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            for (Integer integer : list) {
                arrayList.add(integer);
            }
            lists.add(arrayList);
            return;
        }
        if (index >= datasLength) {
            return;
        }
        search(index + 1, sum);
        int size = numSize.get(datas[index]) + 1;
        int i = 1;
        for (; i < size && datas[index] * i + sum <= goal; ++i) {
            list.add(datas[index]);
            search(index + 1, sum + datas[index] * i);
        }
        for (int j = 1; j < i; ++j) {
            list.remove(list.size() - 1);
        }
    }

    /**
     * 示例 1:
     * <p>
     * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
     * 所求解集为:
     * [
     * [1, 7],
     * [1, 2, 5],
     * [2, 6],
     * [1, 1, 6]
     * ]
     * <p>
     * 示例 2:
     * 输入: candidates = [2,5,2,1,2], target = 5,
     * 所求解集为:
     * [
     *   [1,2,2],
     *   [5]
     * ]
     *
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        goal = target;
        Arrays.sort(candidates);
        datas = new int[candidates.length];
        for (int i = 0, j = 0; i < candidates.length && candidates[i] <= target; ) {
            for (j = i;
                 j < candidates.length && candidates[i] == candidates[j];
                 ++j)
                ;
            datas[datasLength++] = candidates[i];
            numSize.put(candidates[i], j - i);
            i = j;
        }
        search(0, 0);
        return lists;
    }

    public static void main(String[] args) {
        combinationSum2(new int[]{2, 5, 2, 1, 2}, 5);
        for (List<Integer> integers : lists) {
            System.out.println(integers);
        }
    }

}
