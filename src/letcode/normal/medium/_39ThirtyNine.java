package letcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Leetcode
 * 给定一个无重复元素的数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 * candidates中的数字可以无限制重复被选取。  说明：  所有数字（包括target）都是正整数。 解集不能包含重复的组合。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : CaiYongcheng
 * @date : 2020-07-05 16:25
 **/
public class _39ThirtyNine {

    static List<List<Integer>> lists = new ArrayList<List<Integer>>();

    static List<Integer> list = new ArrayList<Integer>();

    static int[] data;

    static int goal;

    public static void search(int index, int sum, List<Integer> list) {
        int limit = goal - sum;
        if (sum == goal) {
            ArrayList<Integer> res = new ArrayList<>();
            for (Integer integer : list) {
                res.add(integer);
            }
            lists.add(res);
            return;
        }
        for (; index < data.length && data[index] <= limit; ++index) {
            list.add(data[index]);
            search(index, sum + data[index], list);
            list.remove(list.size() - 1);
        }
    }

    /**
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        data = candidates;
        goal = target;
        search(0, 0, list);
        return lists;
    }

    public static void main(String[] args) {
        int[] ints = new int[]{2, 3, 5};
        List<List<Integer>> rlists = combinationSum(ints, 8);
        for (List<Integer> rlist : rlists) {
            System.out.println(rlist);
        }
    }

}
