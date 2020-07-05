package letcode.medium;

import java.util.Arrays;

/**
 * Leetcode
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。
 * 找出给定目标值在数组中的开始位置和结束位置。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * @author : CaiYongcheng
 * @date : 2020-07-04 23:14
 **/
public class ThirtyFour {

    /**
     * 示例 1:
     * 输入: nums = [5,7,7,8,8,10], target = 8
     * 输出: [3,4]
     *
     * 示例 2:
     * 输入: nums = [5,7,7,8,8,10], target = 6
     * 输出: [-1,-1]
     * @param nums
     * @param target
     * @return
     */
    public static int[] searchRange(int[] nums, int target) {
        if ((nums == null) || (nums.length < 1)) {
            return new int[]{-1,-1};
        }
        if (nums.length == 1) {
            return nums[0] == target ? new int[]{0,0} : new int[]{-1,-1};
        }
        int[] result = new int[]{-1, -1};
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (left <= right){
            mid = (left + right)/2;
            if (nums[mid] == target) {
                break;
            }else if (nums[mid] > target){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        if (nums[mid] == target){
            int midl = mid;
            int midr = mid;
            left = 0;
            right = nums.length - 1;
            while (left < midl){
                if (nums[left] == target && left != mid){
                    result[0] = left;
                    midl = left;
                    left = 0;
                }else {
                    left += (midl - left + 1) / 2;
                }
            }
            while (midr < right){
                if (nums[right] == target && right != mid){
                    result[1] = right;
                    midr = right;
                    right = nums.length - 1;
                }else {
                    right -= (right - midr + 1) / 2;
                }
            }
            if (result[0] == -1) {
                result[0] = mid;
            }
            if (result[1] == -1) {
                result[1] = mid;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] ints = new int[]{1,3};
        System.out.println(Arrays.toString(searchRange(ints,1)));
    }






}
