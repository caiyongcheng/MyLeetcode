package letcode.normal.medium;

/**
 * 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。  例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
 * 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。  子数组 是数组中的一个连续序列。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/arithmetic-slices 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-08-10 09:04
 **/
public class _413 {


    public int numberOfArithmeticSlices(int[] nums) {
        int start = 0;
        int end;
        int diff;
        int length;
        int ans = 0;
        while (start < nums.length - 1) {
            end = start;
            diff = nums[end + 1] - nums[end];
            while (end < nums.length - 1 && nums[end + 1] - nums[end] == diff) {
                ++end;
            }
            length = end - start + 1;
            if (length > 2) {
                ans = ans + (length + 1) * length / 2 - 2 * length + 1;
            }
            start = end;
        }
        return ans;
    }


}
