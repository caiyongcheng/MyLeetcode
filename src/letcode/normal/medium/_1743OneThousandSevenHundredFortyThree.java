package letcode.normal.medium;

import letcode.utils.FormatPrintUtils;

import java.util.*;

/**
 * 存在一个由 n 个不同元素组成的整数数组 nums ，但你已经记不清具体内容。好在你还记得 nums 中的每一对相邻元素。
 * 给你一个二维整数数组 adjacentPairs ，大小为 n - 1 ，其中每个 adjacentPairs[i] = [ui, vi] 表示元素 ui 和 vi 在 nums 中相邻。
 * 题目数据保证所有由元素 nums[i] 和 nums[i+1] 组成的相邻元素对都存在于 adjacentPairs 中，存在形式可能是 [nums[i], nums[i+1]] ，也可能是 [nums[i+1], nums[i]] 。
 * 这些相邻元素对可以 按任意顺序 出现。  返回 原始数组 nums 。如果存在多种解答，返回 其中任意一个 即可。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/restore-the-array-from-adjacent-pairs 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-07-25 15:29
 **/
public class  _1743OneThousandSevenHundredFortyThree {


    public int[] restoreArray(int[][] adjacentPairs) {
        /**
         * 明显题目答案不唯一
         * 根据题目 找出 只有一个关系的元素 那么这个元素一定是头或者尾
         * 再根据头或者尾去一步步推到即可
         */
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        int[] ans;
        for (int[] item : adjacentPairs) {
            HashSet<Integer> set = map.getOrDefault(item[0], new HashSet<>());
            set.add(item[1]);
            map.put(item[0], set);
            set = map.getOrDefault(item[1], new HashSet<>());
            set.add(item[0]);
            map.put(item[1], set);
        }
        /**
         * 遍历找到头尾
         *
         */
        ans = new int[map.keySet().size()];
        Set<Map.Entry<Integer, HashSet<Integer>>> entries = map.entrySet();
        for (Map.Entry<Integer, HashSet<Integer>> entry : entries) {
            if (entry.getValue().size() == 1) {
                ans[0] = entry.getKey();
                for (Integer item : entry.getValue()) {
                    ans[1] = item;
                }
                break;
            }
        }
        for (int i = 1; i < ans.length - 1; i++) {
            HashSet<Integer> set = map.get(ans[i]);
            for (Integer item : set) {
                if (item != ans[i-1]) {
                    ans[i+1] = item;
                    break;
                }
            }
        }
        return ans;
    }


    /**
     * 示例 1：
     *
     * 输入：adjacentPairs = {{2,1},{3,4},{3,2}}
     * 输出：{1,2,3,4}
     * 解释：数组的所有相邻元素对都在 adjacentPairs 中。
     * 特别要注意的是，adjacentPairs{i} 只表示两个元素相邻，并不保证其 左-右 顺序。
     * 示例 2：
     *
     * 输入：adjacentPairs = {{4,-2},{1,4},{-3,1}}
     * 输出：{-2,4,1,-3}
     * 解释：数组中可能存在负数。
     * 另一种解答是 {-3,1,4,-2} ，也会被视作正确答案。
     * 示例 3：
     *
     * 输入：adjacentPairs = {{100000,-100000}}
     * 输出：{100000,-100000}
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/restore-the-array-from-adjacent-pairs
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(FormatPrintUtils.formatArray(new _1743OneThousandSevenHundredFortyThree().restoreArray(
                new int[][]{{100000,-100000}}
        )));
    }

}
