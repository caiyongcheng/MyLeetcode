package letcode.normal.difficult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 3501. Maximize Active Section with Trade II
 * Difficulty: Hard
 * Link: https://leetcode.cn/problems/maximize-active-section-with-trade-ii/
 *
 * You are given a binary string s of length n , where:
 *
 * - '1' represents an active section.
 *
 * - '0' represents an inactive section.
 *
 * You can perform at most one trade to maximize the number of active sections in s . In a trade, you:
 *
 * - Convert a contiguous block of '1' s that is surrounded by '0' s to all '0' s.
 *
 * - Afterward, convert a contiguous block of '0' s that is surrounded by '1' s to all '1' s.
 *
 * Additionally, you are given a 2D array queries , where queries[i] = [l i , r i ] represents a
 * substring s[l i ...r i ] .
 *
 * For each query, determine the maximum possible number of active sections in s after making the
 * optimal trade on the substring s[l i ...r i ] .
 *
 * Return an array answer , where answer[i] is the result for queries[i] .
 *
 * Note
 *
 * - For each query, treat s[l i ...r i ] as if it is augmented with a '1' at both ends, forming t =
 * '1' + s[l i ...r i ] + '1' . The augmented '1' s do not contribute to the final count.
 *
 * - The queries are independent of each other.
 *
 * Example 1:
 *
 * Input: s = "01", queries = [[0,1]]
 *
 * Output: [1]
 *
 * Explanation:
 *
 * Because there is no block of '1' s surrounded by '0' s, no valid trade is possible. The maximum
 * number of active sections is 1.
 *
 * Example 2:
 *
 * Input: s = "0100", queries = [[0,3],[0,2],[1,3],[2,3]]
 *
 * Output: [4,3,1,1]
 *
 * Explanation:
 *
 * -
 *
 * Query [0, 3] &rarr; Substring "0100" &rarr; Augmented to "101001"
 *
 * Choose "0100" , convert "0100" &rarr; "0000" &rarr; "1111" .
 *
 * The final string without augmentation is "1111" . The maximum number of active sections is 4.
 *
 * -
 *
 * Query [0, 2] &rarr; Substring "010" &rarr; Augmented to "10101"
 *
 * Choose "010" , convert "010" &rarr; "000" &rarr; "111" .
 *
 * The final string without augmentation is "1110" . The maximum number of active sections is 3.
 *
 * -
 *
 * Query [1, 3] &rarr; Substring "100" &rarr; Augmented to "11001"
 *
 * Because there is no block of '1' s surrounded by '0' s, no valid trade is possible. The maximum
 * number of active sections is 1.
 *
 * -
 *
 * Query [2, 3] &rarr; Substring "00" &rarr; Augmented to "1001"
 *
 * Because there is no block of '1' s surrounded by '0' s, no valid trade is possible. The maximum
 * number of active sections is 1.
 *
 * Example 3:
 *
 * Input: s = "1000100", queries = [[1,5],[0,6],[0,4]]
 *
 * Output: [6,7,2]
 *
 * Explanation:
 *
 * -
 *
 * Query [1, 5] &rarr; Substring "00010" &rarr; Augmented to "1000101"
 * Choose "00010" , convert "00010" &rarr; "00000" &rarr; "11111" .
 *
 * The final string without augmentation is "1111110" . The maximum number of active sections is 6.
 *
 * -
 *
 * Query [0, 6] &rarr; Substring "1000100" &rarr; Augmented to "110001001"
 * Choose "000100" , convert "000100" &rarr; "000000" &rarr; "111111" .
 *
 * The final string without augmentation is "1111111" . The maximum number of active sections is 7.
 *
 * -
 *
 * Query [0, 4] &rarr; Substring "10001" &rarr; Augmented to "1100011"
 * Because there is no block of '1' s surrounded by '0' s, no valid trade is possible. The maximum
 * number of active sections is 2.
 *
 * Example 4:
 *
 * Input: s = "01010", queries = [[0,3],[1,4],[1,3]]
 *
 * Output: [4,4,2]
 *
 * Explanation:
 *
 * -
 *
 * Query [0, 3] &rarr; Substring "0101" &rarr; Augmented to "101011"
 *
 * Choose "010" , convert "010" &rarr; "000" &rarr; "111" .
 *
 * The final string without augmentation is "11110" . The maximum number of active sections is 4.
 *
 * -
 *
 * Query [1, 4] &rarr; Substring "1010" &rarr; Augmented to "110101"
 *
 * Choose "010" , convert "010" &rarr; "000" &rarr; "111" .
 *
 * The final string without augmentation is "01111" . The maximum number of active sections is 4.
 *
 * -
 *
 * Query [1, 3] &rarr; Substring "101" &rarr; Augmented to "11011"
 *
 * Because there is no block of '1' s surrounded by '0' s, no valid trade is possible. The maximum
 * number of active sections is 2.
 *
 * Constraints:
 *
 * - 1 <= n == s.length <= 10 5
 *
 * - 1 <= queries.length <= 10 5
 *
 * - s[i] is either '0' or '1' .
 *
 * - queries[i] = [l i , r i ]
 *
 * - 0 <= l i <= r i < n
 */
public class _3501 {

    /**
     * 核心结论（同 3499）：
     * answer = 全串 '1' 的个数 + 一次 trade 的最大收益
     * 收益 = 被翻成 '1' 的两段相邻 '0' 的长度之和
     *
     * 与 3499 的差别：
     * trade 只能发生在子串 s[l..r] 内（两端按虚拟 '1' 判断包围关系），
     * 但最终统计的是整个 s 的 '1' 个数。
     *
     * 因此每个询问只要求：在 [l,r] 约束下，两段相邻零的最大可贡献长度。
     */
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        char[] cs = s.toCharArray();
        int n = cs.length;

        // base：全串原有 1 的数量（所有询问共用）
        int base = 0;
        // zeros[i] = {start, len}，第 i 段连续 0
        List<int[]> zeros = new ArrayList<>();
        // belong[i]：
        //   - s[i]=='0' 时，属于哪一段零
        //   - s[i]=='1' 时，等于「i 之前最后一段零」的下标；若前面没有零则为 -1
        // 这样 belong[l]+1 正好是「第一个可能完全落在 [l,r] 内的零段」
        int[] belong = new int[n];

        for (int i = 0; i < n; i++) {
            if (cs[i] == '1') {
                ++base;
            } else if (i > 0 && cs[i - 1] == '0') {
                zeros.get(zeros.size() - 1)[1]++;
            } else {
                zeros.add(new int[]{i, 1});
            }
            belong[i] = zeros.size() - 1;
        }

        // 不足两段 0，不可能出现「被 0 包围的 1」，无法 trade
        if (zeros.size() < 2) {
            return new ArrayList<>(Collections.nCopies(queries.length, base));
        }

        // merge[i] = 第 i 段 0 的长度 + 第 i+1 段 0 的长度
        // 对应一次「牺牲中间那段 1，吃掉左右两段 0」的完整收益
        int m = zeros.size() - 1;
        int[] merge = new int[m];
        for (int i = 0; i < m; i++) {
            merge[i] = zeros.get(i)[1] + zeros.get(i + 1)[1];
        }
        // 静态区间最大值 → 稀疏表 O(1) 查询
        SparseTable st = new SparseTable(merge);

        List<Integer> ans = new ArrayList<>(queries.length);
        for (int[] query : queries) {
            ans.add(base + maxGain(cs, zeros, belong, st, query[0], query[1]));
        }
        return ans;
    }

    /**
     * 计算询问 [l,r] 内一次 trade 的最大收益（没有合法操作则为 0）。
     *
     * 设零段下标为 0..k-1。对询问 [l,r]：
     * - 若端点落在 0 上，该零段可能被截断，只能用落在区间内的那一截；
     * - 完全落在区间内部的相邻零段对，用稀疏表取 max(merge[i])；
     * - 再特判「左截断 + 下一段完整」「上一段完整 + 右截断」
     *   以及「l、r 恰好落在相邻两段 0」这几种边界最优。
     */
    private int maxGain(char[] cs, List<int[]> zeros, int[] belong, SparseTable st, int l, int r) {
        int lG = belong[l];
        int rG = belong[r];

        // leftClip：从 l 到「l 所在零段」结尾的 0 个数；l 不是 0 则为 -1
        // 例：零段 [2..5]=0000，l=3 → leftClip=3（下标 3,4,5）
        int leftClip = -1;
        if (cs[l] == '0') {
            int[] block = zeros.get(lG);
            leftClip = block[1] - (l - block[0]);
        }

        // rightClip：从「r 所在零段」开头到 r 的 0 个数；r 不是 0 则为 -1
        int rightClip = -1;
        if (cs[r] == '0') {
            int[] block = zeros.get(rG);
            rightClip = r - block[0] + 1;
        }

        /*
         * 完全落在 [l,r] 内的零段下标范围 [fullL, fullR]：
         *
         * - l 在 0 上：当前这段被截断，完整段从 lG+1 开始
         * - l 在 1 上：belong[l] 是左侧最近零段，完整段从 lG+1 开始
         * - r 在 0 上：当前这段被截断，完整段到 rG-1 结束
         * - r 在 1 上：belong[r] 是左侧最近零段，若它在 r 前则整段可用，fullR=rG
         */
        int fullL = lG + 1;
        int fullR = cs[r] == '1' ? rG : rG - 1;

        int maxGain = 0;

        // 情况 A：l、r 落在相邻两段 0 上（中间恰好一段 1）
        // 收益只能用两段截断后的长度：leftClip + rightClip
        if (cs[l] == '0' && cs[r] == '0' && lG + 1 == rG) {
            maxGain = leftClip + rightClip;
        } else if (fullL < fullR) {
            // 情况 B：至少两段完整 0，可用稀疏表查 max(merge[fullL .. fullR-1])
            // merge[i] 对应零段 (i, i+1)，故完整段 [fullL, fullR] 对应 merge 下标 [fullL, fullR-1]
            maxGain = st.query(fullL, fullR - 1);
        }

        // 情况 C：左截断 + 下一段完整 0
        // 要求下一段下标 <= fullR（保证下一段完全在区间内）
        if (cs[l] == '0' && lG + 1 <= fullR) {
            maxGain = Math.max(maxGain, leftClip + zeros.get(lG + 1)[1]);
        }

        // 情况 D：右截断 + 上一段完整 0
        // lG < rG-1 保证「r 前一段」不是 l 所在段本身（避免和截断段重复/越界）
        if (cs[r] == '0' && lG < rG - 1) {
            maxGain = Math.max(maxGain, rightClip + zeros.get(rG - 1)[1]);
        }

        return maxGain;
    }

    /** 静态 RMQ：预处理 O(n log n)，查询 O(1) 区间最大值。 */
    private static final class SparseTable {
        private final int[][] st;
        private final int[] log2;

        SparseTable(int[] arr) {
            int n = arr.length;
            log2 = new int[n + 1];
            for (int i = 2; i <= n; i++) {
                log2[i] = log2[i >> 1] + 1;
            }
            int maxLog = log2[n] + 1;
            st = new int[maxLog][n];
            System.arraycopy(arr, 0, st[0], 0, n);
            for (int k = 1; k < maxLog; k++) {
                int len = 1 << k;
                int half = len >> 1;
                for (int i = 0; i + len <= n; i++) {
                    st[k][i] = Math.max(st[k - 1][i], st[k - 1][i + half]);
                }
            }
        }

        /** 返回 arr[left..right]（闭区间）的最大值。 */
        int query(int left, int right) {
            int k = log2[right - left + 1];
            return Math.max(st[k][left], st[k][right - (1 << k) + 1]);
        }
    }
}
