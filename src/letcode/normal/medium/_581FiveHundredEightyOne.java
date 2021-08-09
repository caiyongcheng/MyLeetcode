package letcode.normal.medium;


/**
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。  请你找出符合题意的 最短 子数组，并输出它的长度。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-08-03 10:05
 **/
public class _581FiveHundredEightyOne {

    public int findUnsortedSubarray(int[] nums) {
        /**
         * 将 nums 划分为 3 部分 na nb nc
         * 其中na nc是符合要求的 nb是需要求的
         * 解法1 排序 逐个位置比较
         * 解法2 na nc 都满足 num[0..i-1] <= num[i] <= num[i+1, n-1]
         * 而nb的左边界，右边界是第一个和最后一个不满足条件的
         * 求出na nb的左右边界即可
         */
        int len = nums.length - 1;
        int nowMaxVal = Integer.MIN_VALUE;
        int nowMinVal = Integer.MAX_VALUE;
        int left = -1;
        int right = -1;
        for (int i = 0; i < nums.length; i++) {
            //右边界
            if (nums[i] < nowMaxVal) {
                right = i;
            } else {
                nowMaxVal = nums[i];
            }
            //左边界
            if (nums[len-i] > nowMinVal) {
                left = len - i;
            } else {
                nowMinVal = nums[len - i];
            }
        }
        return right == -1 ? 0 : right - left + 1;
    }


    /**
     * 示例 1：
     * 输入：nums = [2,6,4,8,10,9,15]
     * 输出：5
     * 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
     *
     * 示例 2：
     * 输入：nums = [1,2,3,4]
     * 输出：0
     *
     * 示例 3：
     * 输入：nums = [1]
     * 输出：0
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _581FiveHundredEightyOne().findUnsortedSubarray(
                new int[]{1,2,3,3,3}
        ));
    }

}
