package letcode.normal.medium;

/**
 * 给你一个整数数组nums ，判断这个数组中是否存在长度为 3 的递增子序列。
 * 如果存在这样的三元组下标 (i, j, k)且满足 i < j < k ，使得nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/increasing-triplet-subsequence 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-01-12 09:01
 **/
public class _334 {

    public boolean increasingTriplet(int[] nums) {
        /*
        1. dp 两个数组 三次遍历
        2. 贪心，两个数，一次遍历。维护两个数 x1， x2。 x1表示当前找到的长度为2的递增子序列的最大值，x2表示当前找到的最小值。
           如果当前遍历的数大于x1，那么也就是表示找到了符合条件的三元组。否则如果当前数大于x2，那么更新x1。
           因为更小的x1更具有优势。如果当前数大于小于x2，那么就更新x2。
         */
        int x1 = Integer.MAX_VALUE;
        int x2 = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > x1) {
                return true;
            }
            if (nums[i] < x1 && nums[i] > x2) {
                x1 = nums[i];
            }
            if (nums[i] < x2) {
                x2 = nums[i];
            }
        }
        return false;
    }

}
