package letcode.easy;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 */
public class ThirtyFive {

    /**
     * 示例 1:
     * 输入: [1,3,5,6], 5
     * 输出: 2
     *
     * 示例 2:
     * 输入: [1,3,5,6], 2
     * 输出: 1
     *
     * 示例 3:
     * 输入: [1,3,5,6], 7
     * 输出: 4
     *
     * 示例 4:
     * 输入: [1,3,5,6], 0
     * 输出: 0
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert(int[] nums, int target) {

        if(nums == null || nums.length == 0 || target <= nums[0]) {
            return 0;
        }

        if (target > nums[nums.length-1]) {
            return nums.length;
        }
        if (target == nums[nums.length-1]) {
            return nums.length-1;
        }

        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (left < right){
            mid = (left + right)/2;
            if(mid == left) return right;
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] > target){
                right = mid;
            }else{
                left = mid;
            }
        }
        return target > nums[left]? left+1: left;
    }

    public static void main(String[] args) {
        int[] ints = new int[]{1,3,5};
        System.out.println(searchInsert(ints,3));
    }

}
