package letcode.normal.unansweredquestions.easy;

import letcode.utils.TestUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given an array nums of n integers and two integers k and x.  The x-sum of an array is calculated by the following procedure:  Count the occurrences of all elements in the array. Keep only the occurrences of the top x most frequent elements. If two elements have the same number of occurrences, the element with the bigger value is considered more frequent. Calculate the sum of the resulting array. Note that if an array has less than x distinct elements, its x-sum is the sum of the array.  Return an integer array answer of length n - k + 1 where answer[i] is the x-sum of the subarray nums[i..i + k - 1].
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-11-05 22:17
 */
public class N_3318 {

    public int[] findXSum(int[] nums, int k, int x) {
        int[] ans = new int[nums.length - k + 1];
        Map<Integer, Integer> num2CntMap = new HashMap<>();

        for (int i = 0; i < k; i++) {
            num2CntMap.put(nums[i], num2CntMap.getOrDefault(nums[i], 0) + 1);
        }

        int i = k;
        while (i < nums.length) {
            ans[i - k] = num2CntMap.entrySet()
                    .stream()
                    .sorted((e1, e2) ->
                            e1.getValue() > e2.getValue() ? -1 : (e1.getValue() < e2.getValue() ? 1 : (e2.getKey() - e1.getKey()))
                    )
                    .limit(Math.min(x, num2CntMap.size()))
                    .map(e -> e.getKey() * e.getValue())
                    .reduce(Integer::sum)
                    .orElse(0);
            num2CntMap.put(nums[i - k], num2CntMap.getOrDefault(nums[i - k], 1) - 1);
            num2CntMap.put(nums[i], num2CntMap.getOrDefault(nums[i], 0) + 1);
            ++i;
        }
        return ans;
    }


}
