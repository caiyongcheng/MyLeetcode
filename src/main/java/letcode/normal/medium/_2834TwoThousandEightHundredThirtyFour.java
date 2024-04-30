package letcode.normal.medium;

/**
 * 给你两个正整数：n 和 target 。  如果数组 nums 满足下述条件，则称其为 美丽数组 。  nums.length == n. nums 由两两互不相同的正整数组成。
 * 在范围 [0, n-1] 内，不存在 两个 不同 下标 i 和 j ，使得 nums[i] + nums[j] == target 。
 * 返回符合条件的美丽数组所可能具备的 最小 和，并对结果进行取模 109 + 7。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/3/8 10:04
 */
public class _2834TwoThousandEightHundredThirtyFour {

    public static long MOD = 1000_000_007;

    public int minimumPossibleSum(int n, int target) {
        /*
        满足条件1 数组长度是n，且元素各不相同
        那么和最小的形式就是 1,2,...n
        如果 target > n-1+n 那么当前数组就是最小美丽数组
        否则 那么对于 数组中大于等于target的部分无需变动
        需要考虑的是 1...target-1 的部分
        1 2 3 4 5 6 7
        这一部分中 可以分割成 [1,(target-1)/2], [(target-1)/2+1,target-1]
        在这部分中 最多能选择(n+1)/2个元素 剩余[target - 1 - (target+1)/2]个元素就要从n后开始选择
         */
        int base = getSerializeSum(1, n);
        if ((n << 1) + 1 < target) {
            return base;
        }
        return (int) (((base + (target - 1L - (target >>> 1)) * (n - (target >>> 1)) % MOD)) % MOD);
    }

    public int getSerializeSum(int start, int end) {
        return (int) ((((((long) start + end)) * (end - start + 1)) >>> 1) % MOD);
    }

    /**
     * 示例 1：
     *
     * 输入：n = 2, target = 3
     * 输出：4
     * 解释：nums = [1,3] 是美丽数组。
     * - nums 的长度为 n = 2 。
     * - nums 由两两互不相同的正整数组成。
     * - 不存在两个不同下标 i 和 j ，使得 nums[i] + nums[j] == 3 。
     * 可以证明 4 是符合条件的美丽数组所可能具备的最小和。
     * 示例 2：
     *
     * 输入：n = 3, target = 3
     * 输出：8
     * 解释：
     * nums = [1,3,4] 是美丽数组。
     * - nums 的长度为 n = 3 。
     * - nums 由两两互不相同的正整数组成。
     * - 不存在两个不同下标 i 和 j ，使得 nums[i] + nums[j] == 3 。
     * 可以证明 8 是符合条件的美丽数组所可能具备的最小和。
     * 示例 3：
     *
     * 输入：n = 1, target = 1
     * 输出：1
     * 解释：nums = [1] 是美丽数组。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2834TwoThousandEightHundredThirtyFour().minimumPossibleSum(100000, 100000));
    }

}
