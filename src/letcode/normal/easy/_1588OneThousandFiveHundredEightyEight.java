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
 * @date 2021-08-29 22:00
 **/
public class _1588OneThousandFiveHundredEightyEight {


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


    /**
     * 示例 1：
     * 输入：arr = [1,4,2,5,3]
     * 输出：58
     * 解释：所有奇数长度子数组和它们的和为：
     * [1] = 1
     * [4] = 4
     * [2] = 2
     * [5] = 5
     * [3] = 3
     * [1,4,2] = 7
     * [4,2,5] = 11
     * [2,5,3] = 10
     * [1,4,2,5,3] = 15
     * 我们将所有值求和得到 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
     *
     * 示例 2：
     * 输入：arr = [1,2]
     * 输出：3
     * 解释：总共只有 2 个长度为奇数的子数组，[1] 和 [2]。它们的和为 3 。
     *
     * 示例 3：
     * 输入：arr = [10,11,12]
     * 输出：66
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sum-of-all-odd-length-subarrays
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1588OneThousandFiveHundredEightyEight().sumOddLengthSubarrays(
                new int[]{10,11,12}
        ));
    }


}
