package letcode.normal.medium;

import java.util.Arrays;

/**
 * 给你一个下标从 0 开始的字符串 word ，长度为 n ，由从 0 到 9 的数字组成。另给你一个正整数 m 。
 * word 的 可整除数组 div  是一个长度为 n 的整数数组，并满足：  如果 word[0,...,i] 所表示的 数值 能被 m 整除，div[i] = 1
 * 否则，div[i] = 0 返回 word 的可整除数组。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/3/7 09:09
 */
public class _2575 {


    public int[] divisibilityArray(String word, int m) {
        /*
         * 考虑 ans[i] 和 ans[i+1] 的关系
         * 如果 ans[i] == 1 那么 ans[i+1] = ans[i+1] % m 的结果
         * 否则 ans[i] == 0 那么 就有ans[i+1] = (modRes[i] * 10 + ans[i+1]) % m
         * 合并得 ans[i+1] = (modRes[i] * 10 + ans[i+1]) % m
         * 这样做的好处是避免大整数的计算
         */
        long lastModRes = 0;
        int[] ans = new int[word.length()];
        for (int i = 0; i < ans.length; i++) {
            lastModRes = (lastModRes * 10 + word.charAt(i) - '0') % m;
            ans[i] = lastModRes == 0L ? 1 : 0;
        }
        return ans;
    }

    /**
     * 示例 1：
     *
     * 输入：word = "998244353", m = 3
     * 输出：[1,1,0,0,0,1,1,0,0]
     * 解释：仅有 4 个前缀可以被 3 整除："9"、"99"、"998244" 和 "9982443" 。
     * 示例 2：
     *
     * 输入：word = "1010", m = 10
     * 输出：[0,1,0,1]
     * 解释：仅有 2 个前缀可以被 10 整除："10" 和 "1010" 。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new _2575().divisibilityArray(
                "1010",
                10
        )));
    }

}
