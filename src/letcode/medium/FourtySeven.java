package letcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Leetcode
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * @author : CaiYongcheng
 * @date : 2020-07-07 12:32
 **/
public class FourtySeven {

    static int[] datas;
    static boolean[] used;
    static ArrayList<Integer> list = new ArrayList<Integer>();
    static ArrayList<List<Integer>> lists = new ArrayList<List<Integer>>();

    public static void search(int size){
        if (size == used.length){
            lists.add(new ArrayList<>(list));
            return;
        }
        for (int i=0; i<used.length; ++i){
            if (!used[i] && ((i < 1)
                    || (datas[i] == datas[i-1] && used[i-1])
                    || (datas[i] != datas[i-1]))){
                used[i] = true;
                list.add(datas[i]);
                search(size+1);
                used[i] = false;
                list.remove(list.size()-1);
            }
        }
    }

    /**
     * 输入: [1,1,2]
     * 输出:
     * [
     *   [1,1,2],
     *   [1,2,1],
     *   [2,1,1]
     * ]
     * @param nums
     * @return
     */
    public static List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        datas = nums;
        used = new boolean[nums.length];
        search(0);
        return lists;
    }

    public static void main(String[] args) {
        List<List<Integer>> permute = permuteUnique(new int[]{3, 3, 0, 3});
        for (List<Integer> integers : permute) {
            System.out.println(integers);
        }
    }
}
