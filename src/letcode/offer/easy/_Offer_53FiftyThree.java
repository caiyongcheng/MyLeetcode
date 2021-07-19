package letcode.offer.easy;

import java.util.Arrays;

/**
 * 统计一个数字在排序数组中出现的次数。
 *
 * @author CaiYongcheng
 * @date 2021-07-16 14:45
 **/
public class _Offer_53FiftyThree {

    public int search(int[] nums, int target) {
        /**
         * 二分 找到该数字后 向前 向后统计
         * 二分可以用Arrays.binarySearch()
         */
        int left = 0;
        int right = nums.length-1;
        int mid;
        int ans = 1;
        while (left <= right) {
            mid = (left + right) >>> 1;
            if (nums[mid] > target) {
                right = mid -1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                 int index = mid + 1;
                 while (index < nums.length && nums[index] == target) {
                     ++index;
                     ++ans;
                 }
                 index = mid - 1;
                while (index > -1 && nums[index] == target) {
                    --index;
                    ++ans;
                }
                return ans;
            }
        }
        return 0;
    }

    /**
     * 示例 1:
     * 输入: nums = [5,7,7,8,8,10], target = 8
     * 输出: 2
     *
     * 示例 2:
     * 输入: nums = [5,7,7,8,8,10], target = 6
     * 输出: 0
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _Offer_53FiftyThree().search(
                new int[]{1,4},
                4
        ));
    }


}
