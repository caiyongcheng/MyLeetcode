package letcode.normal.medium;

import java.util.HashMap;

/**
 * 给你一个整数数组nums 和一个整数k ，判断数组中是否存在两个 不同的索引i和j ，满足 nums[i] == nums[j] 且 abs(i - j) <= k 。
 * 如果存在，返回 true ；否则，返回 false 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/contains-duplicate-ii 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-01-19 09:01
 **/
public class _219 {


    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> hash = new HashMap<>();
        Integer lastIndex;
        for (int i = 0; i < nums.length; i++) {
            lastIndex = hash.getOrDefault(nums[i], -1000000);
            if (i - lastIndex <= k) {
                return true;
            }
            hash.put(nums[i], i);
        }
        return false;
    }

}
