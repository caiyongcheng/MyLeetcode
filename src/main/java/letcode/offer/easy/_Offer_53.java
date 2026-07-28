package letcode.offer.easy;

/**
 * 统计一个数字在排序数组中出现的次数。
 *
 * @author CaiYongcheng
 * @since 2021-07-16 14:45
 **/
public class _Offer_53 {

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

}
