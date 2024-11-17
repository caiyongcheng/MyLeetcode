package letcode.normal.unansweredquestions.medium;

import letcode.utils.TestUtil;

/**
 * There are some prizes on the X-axis. You are given an integer array prizePositions that is sorted in non-decreasing order, where prizePositions[i] is the position of the ith prize. There could be different prizes at the same position on the line. You are also given an integer k.  You are allowed to select two segments with integer endpoints. The length of each segment must be k. You will collect all prizes whose position falls within at least one of the two selected segments (including the endpoints of the segments). The two selected segments may intersect.  For example if k = 2, you can choose segments [1, 3] and [2, 4], and you will win any prize i that satisfies 1 <= prizePositions[i] <= 3 or 2 <= prizePositions[i] <= 4. Return the maximum number of prizes you can win if you choose the two segments optimally.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-09-11 13:21
 */
public class N_2555 {

    public int maximizeWin(int[] prizePositions, int k) {
        int ans = 0;
        int startIdx = prizePositions.length - (k << 1) + 1;
        startIdx = Math.max(startIdx, 0);
        while (startIdx < prizePositions.length) {
            ans += prizePositions[startIdx++];
        }
        return ans;
    }

    /**
     * Example 1:
     *
     * Input: prizePositions = [1,1,2,2,3,3,5], k = 2
     * Output: 7
     * Explanation: In this example, you can win all 7 prizes by selecting two segments [1, 3] and [3, 5].
     * Example 2:
     *
     * Input: prizePositions = [1,2,3,4], k = 0
     * Output: 2
     * Explanation: For this example, one choice for the segments is [3, 3] and [4, 4], and you will be able to get 2 prizes.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test(N_2555.class);
    }

}
