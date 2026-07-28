package letcode.normal.easy;

/**
 * 给你一个正整数数组arr，请你计算所有可能的奇数长度子数组的和。
 * 子数组 定义为原数组中的一个连续子序列。
 * 请你返回 arr中 所有奇数长度子数组的和 。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-all-odd-length-subarrays 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-08-29 22:00
 **/
public class _1588 {


    public int sumOddLengthSubarrays(int[] arr) {
        int ans = 0;
        for (int data : arr) {
            ans += data;
        }
        int initSum = arr[0];
        int sum;
        for (int len = 3; len <= arr.length; len+=2) {
            initSum += arr[len-1] + arr[len-2];
            sum = initSum;
            ans += initSum;
            for (int start = 1; start + len <= arr.length ; start++) {
                sum = sum - arr[start-1] + arr[start + len - 1];
                ans += sum;
            }
        }
        return ans;
    }


}
