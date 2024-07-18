package letcode.normal.medium;

import letcode.utils.TestCaseInputUtils;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 给你一个下标从 0 开始的字符串 s 和一个单词字典 dictionary 。你需要将 s 分割成若干个 互不重叠 的子字符串，每个子字符串都在 dictionary 中出现过。
 * s 中可能会有一些 额外的字符 不在任何子字符串中。  请你采取最优策略分割 s ，使剩下的字符 最少 。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/1/9 09:51
 */
public class _2707 {

    public int minExtraChar(String s, String[] dictionary) {
        /*
        第一思路是 搜索+记忆化
        如果将s分成两部分l，r
        最优策略就是l的最优策略+r的最优策略 + l、r合并的结果
        l、r的合并结果就是 如果l剩余尾部 r剩余头部 然后拼接在一起形成的结果
        只是这样的话 似乎无法保证最佳

        假设已经计算出 si的最优解了 加上字符i+1时，（如果尾部仍然在字典中 那么结果不变！这是错误的，因为加上最后字符后，可能导致合并）
        不然的话就一直向前拼接 因为之前的结果都是计算好的 如果在位置t满足尾部在字典中 那么结果就是到t位置的最优解+1 一直计算到第一位
        再考虑字符串拼接的话  可能反转字符串存入字典会更好 使用前缀树
         */

        // 建立字典
        HashSet<String> dict = new HashSet<>(Arrays.asList(dictionary));
        // dp[i] 表示到[0, i-1]的最优结果
        int len = s.length();
        int[] dp = new int[len + 1];
        StringBuilder sb;
        int optimum;
        for (int i = 0; i < len; i++) {
            optimum = dp[i] + 1;
            sb = new StringBuilder();
            for (int j = i; j > -1; j--) {
                sb.insert(0, s.charAt(j));
                if (dict.contains(sb.toString())) {
                    optimum = Integer.min(optimum, dp[j]);
                }
            }
            dp[i + 1] = optimum;
        }
        return dp[len];
    }


    /**
     * 示例 1：
     *
     * 输入：s = "leetscode", dictionary = ["leet","code","leetcode"]
     * 输出：1
     * 解释：将 s 分成两个子字符串：下标从 0 到 3 的 "leet" 和下标从 5 到 8 的 "code" 。只有 1 个字符没有使用（下标为 4），所以我们返回 1 。
     * 示例 2：
     *
     * 输入：s = "sayhelloworld", dictionary = ["hello","world"]
     * 输出：3
     * 解释：将 s 分成两个子字符串：下标从 3 到 7 的 "hello" 和下标从 8 到 12 的 "world" 。下标为 0 ，1 和 2 的字符没有使用，所以我们返回 3 。
     *
     * "dwmodizxvvbosxxw"
     * ["ox","lb","diz","gu","v","ksv","o","nuq","r","txhe","e","wmo","cehy","tskz","ds","kzbu"]
     * 7
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2707().minExtraChar(
                "leetscode",
                TestCaseInputUtils.getStrArrIgnoreDoubleQuote("[\"leet\",\"code\",\"leetcode\"]")
        ));
    }


}
