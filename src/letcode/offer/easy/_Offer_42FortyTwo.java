package letcode.offer.easy;

/**
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。  要求时间复杂度为O(n)。
 *
 * @author CaiYongcheng
 * @date 2021-07-17 22:46
 **/
public class _Offer_42FortyTwo {

    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int now = 0;
        for (int num : nums) {
            now += num;
            if (now < 0) {
                now = 0;
            } else if (now > max){
                max = now;
            }
        }
        if (max == 0) {
            max = Integer.MIN_VALUE;
            for (int num : nums) {
                if (num > max) {
                    max = num;
                }
            }
        }
        return max;
    }

    /**
     * 示例1:
     * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _Offer_42FortyTwo().maxSubArray(
                new int[]{-2,1,-3,4,-1,2,1,-5,4}
        ));
    }


}
