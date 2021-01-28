package letcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Leetcode
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * @author : CaiYongcheng
 * @date : 2020-07-07 12:22
 **/
public class _46FortySix {

    static int[] datas;
    static boolean[] used;
    static ArrayList<Integer> list = new ArrayList<Integer>();
    static ArrayList<List<Integer>> lists = new ArrayList<List<Integer>>();

    public static void search(int size) {
        if (size == used.length) {
            lists.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < used.length; ++i) {
            if (!used[i]) {
                used[i] = true;
                list.add(datas[i]);
                search(size + 1);
                used[i] = false;
                list.remove(list.size() - 1);
            }
        }
    }

    /**
     * 输入: [1,2,3]
     * 输出:
     * [
     * [1,2,3],
     * [1,3,2],
     * [2,1,3],
     * [2,3,1],
     * [3,1,2],
     * [3,2,1]
     * ]
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        datas = nums;
        used = new boolean[nums.length];
        search(0);
        return lists;
    }

    public static void main(String[] args) {
        List<List<Integer>> permute = permute(new int[]{1, 1, 3});
        for (List<Integer> integers : permute) {
            System.out.println(integers);
        }
    }
}
