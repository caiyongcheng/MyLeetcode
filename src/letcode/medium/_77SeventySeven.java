package letcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Leetcode
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合
 *
 * @author : CaiYongcheng
 * @date : 2020-07-17 08:59
 **/
public class _77SeventySeven {

    private static ArrayList<List<Integer>> lists;
    private static ArrayList<Integer> list;
    private static int limit;
    private static int size;

    public static void dfs(int index, int currentSize){
        if (currentSize == size){
            lists.add(new ArrayList<>(list));
            return;
        }
        if (size - currentSize > limit - index){
            return;
        }
        for (++index; index< limit; ++index){
            list.add(index);
            dfs(index, currentSize+1);
            list.remove(list.size()-1);
        }
    }

    /**
     * 示例:
     * 输入: n = 4, k = 2
     * 输出:
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     * @param n
     * @param k
     * @return
     */
    public static List<List<Integer>> combine(int n, int k) {
        if (n < k){
            return null;
        }
        list = new ArrayList<Integer>();
        lists = new ArrayList<List<Integer>>();
        limit = n+1;
        size = k;
        dfs(0, 0);
        return lists;
    }

    public static void main(String[] args) {
        List<List<Integer>> combine = combine(1, 1);
        for (List<Integer> integers : combine) {
            System.out.println(integers);
        }
    }


}
