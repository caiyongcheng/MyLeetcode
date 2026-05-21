package letcode.normal.easy;

import letcode.utils.TestCaseInputUtils;

/**
 * 给你两个下标从 0 开始的整数数组 nums 和 divisors 。  divisors[i] 的 可整除性得分 等于满足 nums[j] 能被 divisors[i] 整除的下标 j 的数量。
 * 返回 可整除性得分 最大的整数 divisors[i] 。如果有多个整数具有最大得分，则返回数值最小的一个。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/5/20 10:59
 */
public class _2644 {

    public int maxDivScore(int[] nums, int[] divisors) {
        int ans = divisors[0];
        int maxScore = 0;
        int score;
        for (int divisor : divisors) {
            score = 0;
            for (int num : nums) {
                if (num >= divisor && num % divisor == 0) {
                    ++score;
                }
            }
            if (score > maxScore) {
                maxScore = score;
                ans = divisor;
            } else if (score == maxScore && divisor < ans) {
                ans = divisor;
            }
        }
        return ans;
    }


}
