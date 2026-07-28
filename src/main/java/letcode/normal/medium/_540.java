package letcode.normal.medium;

/**
 * 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。  请你找出并返回只出现一次的那个数。
 * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/single-element-in-a-sorted-array 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-02-14 09:03
 **/
public class _540 {

    public int singleNonDuplicate(int[] nums) {
        /**
         * 根据题意 x一定在偶数下标（从0开始）
         * 所以 数组构成是 奇数长度有序对l1 x 奇数长度有序对l2， 对于l1而言，有序对下标是（偶奇），对于l2而言则是（奇偶）
         * 每次二分 如果当前点p，不等于前一个且不等于后一个元素，那么就是x
         * 如果p点等于前一个，且p点下标是奇数，那么p点在序列l1，此时应当向后二分，否则向前二分
         * 如果p点等于后一个，且p点下标是偶数，那么p点在序列l2，此时应当向前二分，否则向后二分
         */
        int li = 0;
        int ri = nums.length - 1;
        int mid;
        while (li < ri) {
            mid = (li + ri) >>> 1;
            if (li == mid) {
                break;
            }
            if (nums[mid] == nums[mid - 1]) {
                if ((mid & 1) == 1) {
                    li = mid + 1;
                } else {
                    ri = mid - 2;
                }
            } else if (nums[mid] == nums[mid + 1]) {
                if ((mid & 1) == 1) {
                    ri = mid - 1;
                } else {
                    li = mid + 2;
                }
            } else {
                return nums[mid];
            }
        }
        return nums[li];
    }

}
