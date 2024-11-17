package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.Arrays;

/**
 * 你有一个数组 nums ，它只包含 正 整数，所有正整数的数位长度都 相同 。
 * 两个整数的 数位不同 指的是两个整数 相同 位置上不同数字的数目。  请你返回 nums 中 所有 整数对里，数位不同之和。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-08-30 09:02
 */
public class _3153 {

    public long sumDigitDifferences(int[] nums) {
        long[] num2CountMap = new long[10];
        int idx = 1;
        long ans = 0;
        while (nums[0] / idx > 0) {
            for (int num : nums) {
                num2CountMap[num / idx % 10]++;
            }
            for (int i = 0; i < num2CountMap.length; i++) {
                for (int j = i + 1; j < num2CountMap.length; j++) {
                    ans = ans + num2CountMap[i] * num2CountMap[j];
                }
            }
            idx *= 10;
            Arrays.fill(num2CountMap, 0);
        }
        return ans;
    }

    /**
     * Example 1:
     *
     * Input: nums = [13,23,12]
     *
     * Output: 4
     *
     * Explanation:
     * We have the following:
     * - The digit difference between 13 and 23 is 1.
     * - The digit difference between 13 and 12 is 1.
     * - The digit difference between 23 and 12 is 2.
     * So the total sum of digit differences between all pairs of integers is 1 + 1 + 2 = 4.
     *
     * Example 2:
     *
     * Input: nums = [10,10,10,10]
     *
     * Output: 0
     *
     * Explanation:
     * All the integers in the array are the same. So the total sum of digit differences between all pairs of integers will be 0.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test(_3153.class);
    }

}
