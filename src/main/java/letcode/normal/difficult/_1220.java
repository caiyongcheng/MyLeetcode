package letcode.normal.difficult;

/**
 * 给你一个整数n，请你帮忙统计一下我们可以按下述规则形成多少个长度为n的字符串：
 * 字符串中的每个字符都应当是小写元音字母（'a', 'e', 'i', 'o', 'u'）
 * 每个元音'a'后面都只能跟着'e'
 * 每个元音'e'后面只能跟着'a'或者是'i'
 * 每个元音'i'后面不能 再跟着另一个'i'
 * 每个元音'o'后面只能跟着'i'或者是'u'
 * 每个元音'u'后面只能跟着'a'
 * 由于答案可能会很大，所以请你返回 模10^9 + 7之后的结果
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/count-vowels-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-01-17 09:10
 **/
public class _1220 {

    private static final int MOD = 1000000000 + 7;

    public int countVowelPermutation(int n) {
        /*
         * 考虑使用dp 长度为n的字符串可以由长度为n的构造出来
         */
        // a0 e1 i2 o3 u4
        int[] dp = new int[]{1, 1, 1, 1, 1};
        int[] tmp;
        int len = 1;
        while (len < n) {
            tmp = new int[]{0, 0, 0, 0, 0};
            tmp[0] = dp[1] % MOD;
            tmp[1] = ((dp[0] % MOD) + (dp[2] % MOD)) % MOD;
            tmp[2] = ((((((dp[0] % MOD) + (dp[1] % MOD)) % MOD) + (dp[3] % MOD)) % MOD) + (dp[4] % MOD)) % MOD;
            tmp[3] = ((dp[2] % MOD) + (dp[4] % MOD)) % MOD;
            tmp[4] = dp[0] % MOD;
            dp[0] = tmp[0];
            dp[1] = tmp[1];
            dp[2] = tmp[2];
            dp[3] = tmp[3];
            dp[4] = tmp[4];
            ++len;
        }
        return ((((((((dp[0] % MOD) + (dp[1] % MOD)) % MOD) + (dp[2] % MOD)) % MOD) + (dp[3] % MOD)) % MOD) + (dp[4] % MOD)) % MOD;
    }

}
