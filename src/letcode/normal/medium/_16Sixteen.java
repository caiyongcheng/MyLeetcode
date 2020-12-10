package letcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * StudyHTTP
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
 * 找出 nums 中的三个整数，使得它们的和与 target 最接近。
 * 返回这三个数的和。假定每组输入只存在唯一答案。   
 * 提示：
 * 3 <= nums.length <= 10^3
 * -10^3 <= nums[i] <= 10^3
 * -10^4 <= target <= 10^4
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author : CaiYongcheng
 * @date : 2020-06-26 14:14
 **/
public class _16Sixteen {

    /**
     * 示例：
     * 输入：nums = [-1,2,1,-4], target = 1
     * 输出：2
     * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
     * @param nums
     * @param target
     * @return
     */
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int nearlySum = 0;
        int sum = 0;
        int minDiff = Integer.MAX_VALUE;
        int diff = 0;
        int i, n=nums.length-2;
        int left;
        int right;
        for(i=0; i<n; ++i){
            //保证不重复
            if(i>0 && nums[i] == nums[i-1]){
                continue;
            }
            left = i+1;
            right = n+1;
            while (left < right){
                sum = nums[i] + nums[left] + nums[right];
                diff = Math.abs(sum - target);
                if(diff < minDiff){
                    minDiff = diff;
                    nearlySum = sum;
                }
                sum -= target;
                if(sum == 0){
                    return target;
                }else if(sum > 0){
                    while (left < right && nums[right] == nums[right-1]) --right;
                    --right;
                }else{
                    while (left < right && nums[left] == nums[left+1]) ++left;
                    ++left;
                }
            }
        }
        return nearlySum;
    }

    public static void main(String[] args) {
        int[] ints = {-1,2,1,-4};
        System.out.println(threeSumClosest(ints,1));
    }
}
