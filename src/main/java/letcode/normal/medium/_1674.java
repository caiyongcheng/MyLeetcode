package letcode.normal.medium;

/**
 * 1674. Minimum Moves to Make Array Complementary
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/minimum-moves-to-make-array-complementary/
 * <p>
 * You are given an integer array nums of even length n and an integer limit . In one move, you can
 * replace any integer from nums with another integer between 1 and limit , inclusive.
 * <p>
 * The array nums is complementary if for all indices i ( 0-indexed ), nums[i] + nums[n - 1 - i] equals
 * the same number. For example, the array [1,2,3,4] is complementary because for all indices i ,
 * nums[i] + nums[n - 1 - i] = 5 .
 * <p>
 * Return the minimum number of moves required to make nums complementary .
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,4,3], limit = 4
 * Output: 1
 * Explanation: In 1 move, you can change nums to [1,2, 2 ,3] (underlined elements are changed).
 * nums[0] + nums[3] = 1 + 3 = 4.
 * nums[1] + nums[2] = 2 + 2 = 4.
 * nums[2] + nums[1] = 2 + 2 = 4.
 * nums[3] + nums[0] = 3 + 1 = 4.
 * Therefore, nums[i] + nums[n-1-i] = 4 for every i, so nums is complementary.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1,2,2,1], limit = 2
 * Output: 2
 * Explanation: In 2 moves, you can change nums to [ 2 ,2,2, 2 ]. You cannot change any number to 3
 * since 3 > limit.
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [1,2,1,2], limit = 2
 * Output: 0
 * Explanation: nums is already complementary.
 * <p>
 * Constraints:
 * <p>
 * - n == nums.length
 * <p>
 * - 2 <= n <= 10 5
 * <p>
 * - 1 <= nums[i] <= limit <= 10 5
 * <p>
 * - n is even.
 */
public class _1674 {

    public int minMoves(int[] nums, int limit) {
        /*
        枚举每个可能的targetSum 判断哪个需要修改的次数最少
        使用差分数组减少计算
         */

        // 因为 nums[i] <= limit 所以 2 <= targetSum <= limit * 2
        // 取limit * 2 + 2 是为了防止下表越界
        int[] diff = new int[(limit << 1) + 2];

        // 将每一个数对的差分合并计算
        int pairCnt = nums.length >> 1;
        int maxNum;
        int minNum;
        int targetSum;
        for (int i = 0; i < pairCnt; i++) {
            maxNum = nums[i];
            minNum = nums[nums.length - i - 1];
            targetSum = maxNum + minNum;
            if (maxNum < minNum) {
                maxNum = minNum;
                minNum = nums[i];
            }

            // 当前数对在 targetSum 时不需要调整
            diff[targetSum]--;
            diff[targetSum + 1]++;

            // 在范围[min+1, max+limit]内只需要调整一次
            diff[minNum + 1]--;
            diff[maxNum + limit + 1]++;

            // 剩余区间都需要调整2次
            diff[2] += 2;
            diff[(limit << 1) + 1] -= 2;
        }

        // 前缀和表示当前targetSum下，需要的move次数
        int ans = nums.length << 1;
        int minMoveCnt = 0;
        for (int i = 2; i <= limit << 1; i++) {
            minMoveCnt += diff[i];
            if (minMoveCnt < ans) {
                ans = minMoveCnt;
            }
        }
        return ans;

    }
}
