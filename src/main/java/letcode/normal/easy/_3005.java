package letcode.normal.easy;

import letcode.utils.TestUtil;

/**
 * You are given an array nums consisting of positive integers.
 * Return the total frequencies of elements in nums such that those elements all have the maximum frequency.
 * The frequency of an element is the number of occurrences of that element in the array.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-09-22 11:01
 */
public class _3005 {

    public int maxFrequencyElements(int[] nums) {
        int[] num2FrequencyMap = new int[101];
        int maxFrequency = 0;
        int ans = 0;

        for (int num : nums) {
            num2FrequencyMap[num]++;
            if (num2FrequencyMap[num] > maxFrequency) {
                maxFrequency = num2FrequencyMap[num];
                ans = num2FrequencyMap[num];
            } else if (num2FrequencyMap[num] == maxFrequency) {
                ans += num2FrequencyMap[num];
            }
        }

        return ans;
    }


}
