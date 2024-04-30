package letcode.normal.easy;

/**
 * 给你一个按 非递减顺序 排列的数组 nums ，返回正整数数目和负整数数目中的最大值。  换句话讲，如果 nums 中正整数的数目是 pos ，而负整数的数目是 neg ，返回 pos 和 neg二者中的最大值。 注意：0 既不是正整数也不是负整数。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/4/9 09:06
 */
public class _2529TwoThousandFiveHundredTwentyNine {

    public int maximumCount(int[] nums) {
        int pos1 = lowerBound(nums, 0);
        int pos2 = lowerBound(nums, 1);
        return Math.max(pos1, nums.length - pos2);
    }

    public int lowerBound(int[] nums, int val) {
        int l = 0, r = nums.length;
        while (l < r) {
            int m = (l + r) / 2;
            if (nums[m] >= val) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }




}
