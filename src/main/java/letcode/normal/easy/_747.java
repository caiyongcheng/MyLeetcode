package letcode.normal.easy;

/**
 * 给你一个整数数组 nums ，其中总是存在 唯一的 一个最大整数 。  请你找出数组中的最大元素并检查它是否 至少是数组中每个其他数字的两倍 。
 * 如果是，则返回 最大元素的下标 ，否则返回 -1 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/largest-number-at-least-twice-of-others 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-01-13 09:00
 **/
public class _747 {

    public int dominantIndex(int[] nums) {
        /*
         * 只关注最大的和第二大的数即可
         */
        if (nums.length == 1) {
            return 0;
        }
        int firstIndex = 0;
        int secondIndex = 1;
        if (nums[0] < nums[1]) {
            firstIndex = 1;
            secondIndex = 0;
        }
        for (int index = 2; index < nums.length; index++) {
            if (nums[index] > nums[firstIndex]) {
                secondIndex = firstIndex;
                firstIndex = index;
            } else if (nums[index] > nums[secondIndex]) {
                secondIndex = index;
            }
        }
        return nums[firstIndex] >= (nums[secondIndex] << 1) ? firstIndex : -1;
    }

}
