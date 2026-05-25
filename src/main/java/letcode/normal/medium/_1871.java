package letcode.normal.medium;

/**
 * 1871. Jump Game VII
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/jump-game-vii/
 * You are given a 0-indexed binary string s and two integers minJump and maxJump .
 * In the beginning, you are standing at index 0 , which is equal to '0' .
 * You can move from index i to index j if the following
 * conditions are fulfilled: - i + minJump <= j <= min(i + maxJump, s.length - 1) , and - s[j] == '0' .
 * Return true if you can reach index s.length - 1 in s , or false otherwise.
 */

public class _1871 {

    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        if (s.charAt(n - 1) != '0') {
            return false;
        }

        boolean[] dp = new boolean[n];
        // 窗口 [i - maxJump, i - minJump] 内可达下标个数
        int reachableInWindow = 0;

        for (int i = 0; i < n; i++) {
            if (i > maxJump && dp[i - maxJump - 1]) {
                reachableInWindow--;
            }
            if (i >= minJump && dp[i - minJump]) {
                reachableInWindow++;
            }
            if (s.charAt(i) == '0' && (i == 0 || reachableInWindow > 0)) {
                dp[i] = true;
            }
        }
        return dp[n - 1];
    }
}
