package letcode.medium;

import java.util.Arrays;

/**
 * Leetcode
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。  必须原地修改，只允许使用额外常数空间。
 *
 * @author : CaiYongcheng
 * @date : 2020-07-01 12:35
 **/
public class _31ThirtyOne {

    /**
     * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
     * 1,2,3 → 1,3,2
     * 3,2,1 → 1,2,3
     * 1,1,5 → 1,5,1
     *
     * @param nums
     */
    // 1 7 9 8 6 4
    // 1 8 4 6 7 9

    // 保证前面不变 交换后面 这样才能保证交换后的差距最小
    // 如果子序列[i+1...n]是降序 说明子序列是最大的 不能再增加 此时应该调整[i..n]
    // 接下来应该找到比nums[i]大 并且最接近nums[i]的数 然后与nums[i]交换
    // 这样子序列[i+1...n]才会变大 并且增加幅度小
    // 交换后的[i+1...n]要比原来的大 但不是最接近的 所以要对[i+1...n]升序排列
    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return;
        }
        int i = nums.length - 1;
        for (; i > 0 && nums[i] <= nums[i - 1]; --i) ;
        if (i == 0) {
            Arrays.sort(nums);
            return;
        }
        // nums[i] > nums[i-1] 需要从nums[i...n]中找到比nums[i-1]大但是最接近的
        int j = i;
        int k = j;
        int dif = Integer.MAX_VALUE;
        for (; j < nums.length; ++j) {
            // nums[j] - nums[i-1] <= dif 不需要 <=，因为最后还要重排序
            if (nums[j] > nums[i - 1] && nums[j] - nums[i - 1] < dif) {
                k = j;
                dif = nums[j] - nums[i - 1];
            }
        }
        // 交换
        int p = nums[i - 1];
        nums[i - 1] = nums[k];
        nums[k] = p;
        //对nums[i..n]做升序排列
        Arrays.sort(nums, i, nums.length);
    }

    public static void main(String[] args) {
        int[] ints = new int[]{1, 7, 9, 8, 6, 4};
        nextPermutation(ints);
        System.out.println(Arrays.toString(ints));
    }
}
