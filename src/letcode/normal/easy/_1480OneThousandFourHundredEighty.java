package letcode.normal.easy;

import letcode.utils.FormatPrintUtils;

/**
 * 给你一个数组 nums 。数组「动态和」的计算公式为：runningSum[i] = sum(nums[0]…nums[i]) 。 
 * 请返回 nums 的动态和。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/running-sum-of-1d-array 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-08-28 23:46
 **/
public class _1480OneThousandFourHundredEighty {


    public int[] runningSum(int[] nums) {
        //就是前缀和
        int[] ans = new int[nums.length];
        ans[0] = nums[0];
        for (int i = 1; i < ans.length; i++) {
            ans[i] = nums[i] + ans[i-1];
        }
        return ans;
    }


    /**
     * 示例 1：
     * 输入：nums = {1,2,3,4}
     * 输出：{1,3,6,10}
     * 解释：动态和计算过程为 {1, 1+2, 1+2+3, 1+2+3+4} 。
     * 
     * 示例 2：
     * 输入：nums = {1,1,1,1,1}
     * 输出：{1,2,3,4,5}
     * 解释：动态和计算过程为 {1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1} 。
     * 
     * 示例 3：
     * 输入：nums = {3,1,2,10,1}
     * 输出：{3,4,6,16,17}
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/running-sum-of-1d-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(FormatPrintUtils.formatArray(new _1480OneThousandFourHundredEighty().runningSum(
           new int[]{3,1,2,10,1}
        )));
    }

}
