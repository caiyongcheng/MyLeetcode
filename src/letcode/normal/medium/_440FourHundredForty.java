package letcode.normal.medium;

/**
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。  子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/longest-palindromic-subsequence 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-08-12 09:12
 **/
public class _440FourHundredForty {


    public int longestPalindromeSubseq(String s) {
        /*
         * 动态规划 参考5题的做法
         * 如果 s[i] == s[j] => dp[i][j] == s[i+1]+s[j-1] + 2
         * 否则 dp[i][j] = max(dp[i+1][j], dp[i][j-1])
         * 从上面可以看出
         */
        int len = s.length();
        int[][] dp = new int[len][len];
        int ans = 0;
        char[] chars = s.toCharArray();
        for (int i = len - 1; i > -1; i--) {
            dp[i][i] = 1;
            for (int j = i+1; j < len; j++) {
                if (chars[i] == chars[j]) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][len-1];
    }


    /**
     * 示例 1：
     * 输入：s = "bbbab"
     * 输出：4
     * 解释：一个可能的最长回文子序列为 "bbbb" 。
     *
     * 示例 2：
     * 输入：s = "cbbd"
     * 输出：2
     * 解释：一个可能的最长回文子序列为 "bb" 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-palindromic-subsequence
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _440FourHundredForty().longestPalindromeSubseq("cbbd"));
    }

}
