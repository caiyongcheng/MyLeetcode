package letcode.normal.medium;

import letcode.utils.TestCaseInputUtils;

/**
 * 给你一个下标从 0 开始的整数数组 nums 和一个正整数 x 。
 * 你 一开始 在数组的位置 0 处，你可以按照下述规则访问数组中的其他位置：  如果你当前在位置 i ，那么你可以移动到满足 i < j 的 任意 位置 j 。
 * 对于你访问的位置 i ，你可以获得分数 nums[i] 。 如果你从位置 i 移动到位置 j 且 nums[i] 和 nums[j] 的 奇偶性 不同，那么你将失去分数 x 。
 * 请你返回你能得到的 最大 得分之和。  注意 ，你一开始的分数为 nums[0] 。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-06-14 09:03
 */
public class _2786 {

    public long maxScore(int[] nums, int x) {
        /*
        对于每个位置来说 只关心在他身后 奇数、偶数能获取最大得分的地方
         */
        long evenScore = 0;
        long oddScore = 0;

        for (int i = nums.length - 1; i >= 1; i--) {
            if ((nums[i] & 1) == 1) {
                oddScore = nums[i] + Math.max(oddScore, evenScore - x);
            } else {
                evenScore = nums[i] + Math.max(evenScore, oddScore - x);
            }
        }

        if ((nums[0] & 1) == 1) {
            return nums[0] + Math.max(evenScore - x, oddScore);
        } else {
            return nums[0] + Math.max(evenScore, oddScore - x);
        }

    }

    /**
     * Example 1:
     *
     * Input: nums = [2,3,6,1,9,2], x = 5
     * Output: 13
     * Explanation: We can visit the following positions in the array: 0 -> 2 -> 3 -> 4.
     * The corresponding values are 2, 6, 1 and 9. Since the integers 6 and 1 have different parities, the move 2 -> 3 will make you lose a score of x = 5.
     * The total score will be: 2 + 6 + 1 + 9 - 5 = 13.
     * Example 2:
     *
     * Input: nums = [2,4,6,8], x = 3
     * Output: 20
     * Explanation: All the integers in the array have the same parities, so we can visit all of them without losing any score.
     * The total score is: 2 + 4 + 6 + 8 = 20.
     *
     * [38,92,23,30,25,96,6,71,78,77,33,23,71,48,87,77,53,28,6,20,90,83,42,21,64,95,84,29,22,21,33,36,53,51,85,25,80,56,71,69,5,21,4,84,28,16,65,7]
     * 52
     * 1545
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2786().maxScore(
                TestCaseInputUtils.getIntArr("[2,3,6,1,9,2]"),
                5
        ));
        // 56 71
    }

}
