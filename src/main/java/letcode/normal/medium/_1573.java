package letcode.normal.medium;

/**
 * 1573. Number of Ways to Split a String
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/number-of-ways-to-split-a-string/
 * <p>
 * Given a binary string s , you can split s into 3 non-empty strings s1 , s2 , and s3 where s1 + s2 +
 * s3 = s .
 * <p>
 * Return the number of ways s can be split such that the number of ones is the same in s1 , s2 , and
 * s3 . Since the answer may be too large, return it modulo 10 9 + 7 .
 * <p>
 * Example 1:
 * <p>
 * Input: s = "10101"
 * Output: 4
 * Explanation: There are four ways to split s in 3 parts where each part contain the same number of
 * letters '1'.
 * "1|010|1"
 * "1|01|01"
 * "10|10|1"
 * "10|1|01"
 * <p>
 * Example 2:
 * <p>
 * Input: s = "1001"
 * Output: 0
 * <p>
 * Example 3:
 * <p>
 * Input: s = "0000"
 * Output: 3
 * Explanation: There are three ways to split s in 3 parts.
 * "0|0|00"
 * "0|00|0"
 * "00|0|0"
 * <p>
 * Constraints:
 * <p>
 * - 3 <= s.length <= 10 5
 * <p>
 * - s[i] is either '0' or '1' .
 */
public class _1573 {

    public static final int MOD = 1_000_000_000 + 7;

    public int numWays(String s) {
        int oneCnt = 0;
        char[] charArray = s.toCharArray();
        for (char ch : charArray) {
            if (ch == '1') {
                ++oneCnt;
            }
        }

        if (oneCnt % 3 != 0) {
            return 0;
        }

        if (oneCnt == 0) {
            return (int) ((long) (charArray.length - 1) * (charArray.length - 2)  / 2 % MOD);
        }

        int splitOneCnt = oneCnt / 3;
        int[] firstSplitExtentRange = getSplitExtendRange(charArray, 0, splitOneCnt);
        int[] secondSplitExtentRange = getSplitExtendRange(charArray, firstSplitExtentRange[1], splitOneCnt);

        int firstChooseCnt = firstSplitExtentRange[1] - firstSplitExtentRange[0];
        int secondChooseCnt = secondSplitExtentRange[1] - secondSplitExtentRange[0];

        return (int) (((long) firstChooseCnt) * secondChooseCnt % MOD);
    }

    private int[] getSplitExtendRange(char[] charArr, int startIdx, int oneCntLimit) {
        int oneCnt = 0;
        int i = startIdx;
        for (; i < charArr.length; i++) {
            if (charArr[i] == '1') {
                ++oneCnt;
            }
            if (oneCnt == oneCntLimit) {
                break;
            }
        }

        int j = i + 1;
        while (j < charArr.length && charArr[j] == '0') {
            ++j;
        }
        return new int[]{i, j};
    }


}
