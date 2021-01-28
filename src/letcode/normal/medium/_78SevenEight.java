package letcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Leetcode
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集
 *
 * @author : CaiYongcheng
 * @date : 2020-07-18 11:17
 **/
public class _78SevenEight {

    private static ArrayList<List<Integer>> lists;
    private static ArrayList<Integer> list;
    private static int[] data;

    private static void dfs(int startIndex) {
        if (startIndex >= data.length) {
            return;
        }
        for (; startIndex < data.length; ++startIndex) {
            list.add(data[startIndex]);
            lists.add(new ArrayList<>(list));
            dfs(startIndex + 1);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 示例:
     * 输入: nums = [1,2,3]
     * 输出:
     * [
     * [3],
     *   [1],
     *   [2],
     *   [1,2,3],
     *   [1,3],
     *   [2,3],
     *   [1,2],
     *   []
     * ]
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets(int[] nums) {
        list = new ArrayList<>(nums.length);
        lists = new ArrayList<List<Integer>>(1 << nums.length);
        lists.add(new ArrayList<>(list));
        data = nums;
        dfs(0);
        return lists;
    }

    public static void main(String[] args) {
        List<List<Integer>> subsets = subsets(new int[]{1, 2, 3});
        for (List<Integer> subset : subsets) {
            System.out.println(subset);
        }
    }
}
