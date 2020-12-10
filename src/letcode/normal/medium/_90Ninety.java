package letcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Leetcode
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 * 先将数组排序
 * 统计数据有哪些元素以及元素的数量
 * 每次决定选择哪个元素以及该元素的数量
 * @author : CaiYongcheng
 * @date : 2020-07-22 08:58
 **/
public class _90Ninety {

    public int[] data;
    public int[] dataSizes;
    public int data_length;
    public List<List<Integer>> lists;
    public ArrayList<Integer> list;

    public void dfs(int index){
        if (index >= data_length){
            lists.add(new ArrayList<>(list));
            return;
        }
        dfs(index+1);
        for (int i=1; i<dataSizes[index]; ++i){
            for (int j=0; j<i; ++j){
                list.add(data[index]);
            }
            dfs(index+1);
            for (int j=0; j<i; ++j){
                list.remove(list.size()-1);
            }
        }
    }

    /**
     * 示例:
     * 输入: [1,2,2]
     * 输出:
     * [
     *   [2],
     *   [1],
     *   [1,2,2],
     *   [2,2],
     *   [1,2],
     *   []
     * ]
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (null == nums || nums.length < 1){
            return null;
        }
        Arrays.sort(nums);
        data = new int[nums.length];
        dataSizes = new int[nums.length];
        data_length = 0;
        int tmp = nums[0];
        data[0] = tmp;
        ++dataSizes[0];
        for (int i=1; i<nums.length; ++i){
            if (nums[i] != tmp){
                ++dataSizes[data_length];
                tmp = nums[i];
                data[++data_length] = tmp;
            }
            ++dataSizes[data_length];
        }
        ++dataSizes[data_length];
        list = new ArrayList<>(nums.length);
        lists = new ArrayList<List<Integer>>(2<<data_length);
        ++data_length;
        dfs(0);
        return lists;
    }

    public static void main(String[] args) {
        int[] ints = new int[]{1,2,2};
        List<List<Integer>> lists = new _90Ninety().subsetsWithDup(ints);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }
}
