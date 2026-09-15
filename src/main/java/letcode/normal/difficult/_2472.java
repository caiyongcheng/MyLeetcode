package letcode.normal.difficult;

/**
 * 2472. Maximum Number of Non-overlapping Palindrome Substrings
 * Difficulty: Hard
 * Link: https://leetcode.cn/problems/maximum-number-of-non-overlapping-palindrome-substrings/
 * <p>
 * You are given a string s and a positive integer k .
 * <p>
 * Select a set of non-overlapping substrings from the string s that satisfy the following conditions:
 * <p>
 * - The length of each substring is at least k .
 * <p>
 * - Each substring is a palindrome .
 * <p>
 * Return the maximum number of substrings in an optimal selection .
 * <p>
 * A substring is a contiguous sequence of characters within a string.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abaccdbbd", k = 3
 * Output: 2
 * Explanation: We can select the substrings underlined in s = " aba cc dbbd ". Both "aba" and "dbbd"
 * are palindromes and have a length of at least k = 3.
 * It can be shown that we cannot find a selection with more than two valid substrings.
 * <p>
 * Example 2:
 * <p>
 * Input: s = "adbcda", k = 2
 * Output: 0
 * Explanation: There is no palindrome substring of length at least 2 in the string.
 * <p>
 * Constraints:
 * <p>
 * - 1 <= k <= s.length <= 2000
 * <p>
 * - s consists of lowercase English letters.
 */
public class _2472 {

    public int maxPalindromes(String s, int k) {
        int length = s.length();
        boolean[][] palindrome = new boolean[length][length];
        char[] charArray = s.toCharArray();

        // 预计算 i j 是不是回文字符串
        for (int i = 0; i < length; i++) {
            for (int j = i; j >= 0; j--) {
                palindrome[j][i] = charArray[i] == charArray[j] && (i - j < 2 || palindrome[j + 1][i - 1]);
            }
        }

        /*
        假设 dp[i] 表示 s[0,i-1]能组成的回文串数量
        那么 对于dp[i+1],
        等于枚举每个满足palindrome[j][i]等于true，并且Length(j, i)>=k的数据，值就等于dp[j] + 1，同时也可以取s[i]不作为回文串的部分，
        那么还要验证dp[i],这其中的最大值就是结果

        同时注意到 只需要寻找到满足条件的最大的j即可，dp总是递增的。如果某个回文串的长度是l，l > 2的话，那么l-2也是回文串，并且使用的字符更少，显然是结果更优的。
         */

        int[] dp = new int[length + 1];

        for (int i = 1; i <= length; i++) {
            dp[i] = dp[i - 1];

            /*
            for (int j = i - k; j >= 0; j--) {
                if (palindrome[j][i - 1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    break;
            }
            原逻辑是这样，迭代每个符合条件的j，但是如果找到的回文串长度大于k+1了,
            那么我们可以缩减成k或者k+1长度，此时使用的区间还更少，明显是更优的做法。
            所以只需要验证k与k+1做法。
            假设最优方案是包括了长度大于k+1的回文子串，那我们也可以将其变为长度k或k+1的，证明完毕。
             */

            // 长度 k
            if (i >= k && palindrome[i - k][i - 1]) {
                dp[i] = Math.max(dp[i], dp[i - k] + 1);
            }

            // 长度 k + 1
            if (i >= k + 1 && palindrome[i - k - 1][i - 1]) {
                dp[i] = Math.max(dp[i], dp[i - k - 1] + 1);
            }
        }

        return dp[length];


    }
}
