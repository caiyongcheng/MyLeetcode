package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a positive integer n, there exists a 0-indexed array called powers, composed of the minimum number of
 * powers of 2 that sum to n. The array is sorted in non-decreasing order, and there is only one way to form the array.
 * You are also given a 0-indexed 2D integer array queries, where queries[i] = [lefti, righti]. Each queries[i]
 * represents a query where you have to find the product of all powers[j] with lefti <= j <= righti.
 * Return an array answers, equal in length to queries, where answers[i] is the answer to the ith query.
 * Since the answer to the ith query may be too large, each answers[i] should be returned modulo 109 + 7.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-08-11 10:42
 */
public class _2438 {

    private static int mod = 1000_000_000 + 7;

    private static int[] baseArr = new int[] {
            1, 1 << 1, 1 << 2, 1 << 3, 1 << 4, 1 << 5, 1 << 6, 1 << 7, 1 << 8, 1 << 9, 1 << 10,
            1 << 11, 1 << 12, 1 << 13, 1 << 14, 1 << 15, 1 << 16, 1 << 17, 1 << 18, 1 << 19, 1 << 20,
            1 << 21, 1 << 22, 1 << 23, 1 << 24, 1 << 25, 1 << 26, 1 << 27, 1 << 28, 1 << 29, 1 << 30,
    };


    public int[] productQueries(int n, int[][] queries) {
        List<Integer> bitRespect = new ArrayList<>();
        for (int base : baseArr) {
            if ((n & base) == base) {
                bitRespect.add(base % mod);
            }
        }

        /*
        比使用cache的方式好 因为使用cache 每次计算ans的时候都需要判断一次cache是否存在
         */
        int[][] preCalculate = new int[bitRespect.size()][bitRespect.size()];
        for (int i = 0; i < preCalculate.length; i++) {
            preCalculate[i][i] = bitRespect.get(i);
            for (int j = i + 1; j < preCalculate.length; j++) {
                preCalculate[i][j] = (int) ((long) preCalculate[i][j - 1] * bitRespect.get(j) % mod);
            }
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = preCalculate[queries[i][0]][queries[i][1]];
        }
        return ans;
    }

    /**
     * Example 1:
     *
     * Input: n = 15, queries = [[0,1],[2,2],[0,3]]
     * Output: [2,4,64]
     * Explanation:
     * For n = 15, powers = [1,2,4,8]. It can be shown that powers cannot be a smaller size.
     * Answer to 1st query: powers[0] * powers[1] = 1 * 2 = 2.
     * Answer to 2nd query: powers[2] = 4.
     * Answer to 3rd query: powers[0] * powers[1] * powers[2] * powers[3] = 1 * 2 * 4 * 8 = 64.
     * Each answer modulo 109 + 7 yields the same answer, so [2,4,64] is returned.
     * Example 2:
     *
     * Input: n = 2, queries = [[0,0]]
     * Output: [2]
     * Explanation:
     * For n = 2, powers = [2].
     * The answer to the only query is powers[0] = 2. The answer modulo 109 + 7 is the same, so [2] is returned.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }

}
