package letcode.normal.difficult;

import java.util.Arrays;

/**
 * 有台奇怪的打印机有以下两个特殊要求：  打印机每次只能打印由 同一个字符 组成的序列。 每次可以在任意起始和结束位置打印新字符，并且会覆盖掉原来已有的字符。
 * 给你一个字符串 s ，你的任务是计算这个打印机打印它需要的最少打印次数。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/strange-printer 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-05-24 09:01
 **/
public class _664SixHundredSixtyFour {



    public int strangePrinter(String s) {
        //s[i]==s[j] -> dp[i][j-1]
        //s[i]!=s[j] -> i<=k<=j, max(dp[i][k]+dp[k+1][j])
        int len = s.length();
        int temporary = 0;
        char[] chars = s.toCharArray();
        int[][] dp = new int[len][len];
        for (int[] ints : dp) {
            Arrays.fill(ints, 1);
        }
        for (int i = dp.length - 1; i >= 0; i--) {
            for (int j = i+1; j < dp[i].length; j++) {
                if (chars[i] == chars[j]) {
                    dp[i][j] = dp[i][j-1];
                } else {
                    temporary = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        temporary = Math.min(temporary, dp[i][k]+dp[k+1][j]);
                    }
                    dp[i][j] = temporary;
                }
            }
        }
        return dp[0][len-1];
    }


    /**
     * 示例 1：
     * 输入：s = "aaabbb"
     * 输出：2
     * 解释：首先打印 "aaa" 然后打印 "bbb"。
     *
     * 示例 2：
     * 输入：s = "aba"
     * 输出：2
     * 解释：首先打印 "aaa" 然后在第二个位置打印 "b" 覆盖掉原来的字符 'a'。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/strange-printer
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _664SixHundredSixtyFour().strangePrinter("aba"));
    }

}
