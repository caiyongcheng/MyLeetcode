package letcode.normal.medium;

import letcode.utils.TestCaseUtils;

/**
 * 给你一个下标从 0 开始的整数数组 nums ，它包含 n 个 互不相同 的正整数。
 * 如果 nums 的一个排列满足以下条件，我们称它是一个特别的排列：  对于 0 <= i < n - 1 的下标 i ，要么 nums[i] % nums[i+1] == 0 ，要么 nums[i+1] % nums[i] == 0 。
 * 请你返回特别排列的总数目，由于答案可能很大，请将它对 109 + 7 取余 后返回。
 *
 * 2 <= nums.length <= 14
 * 1 <= nums[i] <= 109
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-06-26 11:14
 */
public class _2741 {

    public int specialPerm(int[] nums) {
        /*
         * 穷举显然时间复杂度是n!,最多会达到14!,显然会超时
         * 假设当前已经选择了排列 p[a,b,c,d,e,g]，因为nums的长度最大是14(题目限制)
         * 所以我们可以对某个状态使用二进制进行表示，然后要做的是在当前状态下继续选择下一个数。
         * 反过来想，当前状态可以由上一个状态添加一个数得到。为了去除重复，每次都添加在末尾。
         * 这样就可以计算出所有的结果。
         *
         * 使用记忆化搜索的话，深度也只有14层，其实也是可以接受的。
         */

        // cache[i][j] 表示当前状态为i，并且最后一个数是j的方案数
        // cache[i][j] = 所有 (i ^ 1 << j)的状态 并且结尾与j满足条件的状态数之和
        int mod = 1_000_000_000 + 7;
        int[][] cache = new int[1 << nums.length][nums.length];

        // 只有一个结尾的状态
        for (int i = 0; i < nums.length; i++) {
            cache[1 << i][i] = 1;
        }

        int lastState;
        for (int state = 0; state < cache.length; state++) {
            for (int j = 0; j < cache[state].length; j++) {
                // 过滤不存在的情况 即当前状态为state 当是j位上是0
                if ((state >> j & 1) == 0) {
                    continue;
                }
                lastState = state ^ (1 << j);
                for (int endIdx = 0; endIdx < cache[lastState].length; endIdx++) {
                    if ((j != endIdx && (nums[j] % nums[endIdx] == 0 || nums[endIdx] % nums[j] == 0))) {
                        cache[state][j] = (cache[state][j] + cache[lastState][endIdx]) % mod;
                    }
                }
            }
        }

        int ans = 0;
        for (int cnt : cache[(1 << nums.length) - 1]) {
            ans = (ans + cnt) % mod;
        }
        return ans;
    }


    /**
     * 示例 1：
     *
     * 输入：nums = [2,3,6]
     * 输出：2
     * 解释：[3,6,2] 和 [2,6,3] 是 nums 两个特别的排列。
     * 示例 2：
     *
     * 输入：nums = [1,4,3]
     * 输出：2
     * 解释：[3,1,4] 和 [4,1,3] 是 nums 两个特别的排列。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2741().specialPerm(
                TestCaseUtils.getIntArr("[1,1,1]")
        ));
    }


}
