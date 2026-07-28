package letcode.normal.medium;

import letcode.utils.TestCaseOutputUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 存在一个由 n 个不同元素组成的整数数组 nums ，但你已经记不清具体内容。好在你还记得 nums 中的每一对相邻元素。
 * 给你一个二维整数数组 adjacentPairs ，大小为 n - 1 ，其中每个 adjacentPairs[i] = [ui, vi] 表示元素 ui 和 vi 在 nums 中相邻。
 * 题目数据保证所有由元素 nums[i] 和 nums[i+1] 组成的相邻元素对都存在于 adjacentPairs 中，存在形式可能是 [nums[i], nums[i+1]] ，也可能是 [nums[i+1], nums[i]] 。
 * 这些相邻元素对可以 按任意顺序 出现。  返回 原始数组 nums 。如果存在多种解答，返回 其中任意一个 即可。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/restore-the-array-from-adjacent-pairs 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-07-25 15:29
 **/
public class  _1743 {


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


}
