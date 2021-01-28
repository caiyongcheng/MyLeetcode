package normal.easy;

import java.util.Arrays;

/**
 * @program: Leetcode
 * @description: 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 * @author: 蔡永程
 * @create: 2021-01-20 14:08
 */
public class _628SiundredTwentyEight {

    /**
     * 示例 1:
     * 输入: [1,2,3]
     * 输出: 6
     * 示例 2:
     * 输入: [1,2,3,4]
     * 输出: 24
     *
     * @param args
     */
    public static void main(String[] args) {
        final int[] ints = {-100, -98, -1, 2, 3, 4};
        System.out.println(new _628SiundredTwentyEight().maximumProduct(ints));
    }

    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int m1 = nums[nums.length - 3] * nums[nums.length - 2] * nums[nums.length - 1];
        int m2 = nums[0] * nums[1] * nums[nums.length - 1];
        return Math.max(m1, m2);
    }

}