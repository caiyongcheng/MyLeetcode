package letcode.normal.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 给你一个整数数组nums和一个整数k，请你返回数对(i, j)的数目，
 * 满足i < j且|nums[i] - nums[j]| == k。
 * |x|的值定义为：  如果x >= 0，那么值为x。 如果x < 0，那么值为-x。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/count-number-of-pairs-with-absolute-difference-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-02-09 15:38
 **/
public class _2006 {

    public int countKDifference(int[] nums, int k) {
        HashMap<Integer, List<Integer>> valToIndex = new HashMap<>();
        List<Integer> indexList;
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            indexList = valToIndex.getOrDefault(nums[i], new ArrayList<>());
            indexList.add(i);
            valToIndex.put(nums[i], indexList);
        }
        for (int i = 0; i < nums.length; i++) {
            if (valToIndex.containsKey(nums[i] - k)) {
                indexList = valToIndex.get(nums[i] - k);
                for (int index = 0; index < indexList.size(); index++) {
                    if (indexList.get(index) > i) {
                        ans += (indexList.size() - index);
                        break;
                    }
                }
            }
            if (valToIndex.containsKey(nums[i] + k)) {
                indexList = valToIndex.get(nums[i] + k);
                for (int index = 0; index < indexList.size(); index++) {
                    if (indexList.get(index) > i) {
                        ans += (indexList.size() - index);
                        break;
                    }
                }
            }
        }
        return ans;
    }

}
