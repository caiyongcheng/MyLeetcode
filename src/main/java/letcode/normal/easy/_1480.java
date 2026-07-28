package letcode.normal.easy;

import letcode.utils.TestCaseOutputUtils;

/**
 * 给你一个数组 nums 。数组「动态和」的计算公式为：runningSum[i] = sum(nums[0]…nums[i]) 。 
 * 请返回 nums 的动态和。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/running-sum-of-1d-array 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-08-28 23:46
 **/
public class _1480 {


    public int[] runningSum(int[] nums) {
        //就是前缀和
        int[] ans = new int[nums.length];
        ans[0] = nums[0];
        for (int i = 1; i < ans.length; i++) {
            ans[i] = nums[i] + ans[i-1];
        }
        return ans;
    }


}
