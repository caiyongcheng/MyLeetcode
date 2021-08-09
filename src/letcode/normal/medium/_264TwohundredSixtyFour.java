package letcode.normal.medium;

import java.util.*;

/**
 * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。  丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 *
 * @author CaiYongcheng
 * @date 2021-08-09 09:08
 **/
public class _264TwohundredSixtyFour {

    public int nthUglyNumber(int n) {
        //最小堆
        int[] factors = {2, 3, 5};
        Set<Long> seen = new HashSet<Long>();
        PriorityQueue<Long> heap = new PriorityQueue<Long>();
        seen.add(1L);
        heap.offer(1L);
        int ugly = 0;
        for (int i = 0; i < n; i++) {
            long curr = heap.poll();
            ugly = (int) curr;
            for (int factor : factors) {
                long next = curr * factor;
                if (seen.add(next)) {
                    heap.offer(next);
                }
            }
        }
        return ugly;
    }

    public int nthUglyNumberForDP(int n) {
        /*
         * dp
         * 假设 dp[i]表示第i个丑数, b[i]表示第i个质因数
         * 那么 dp[i] = Math.min(dp[a]*2, dp[b]*3, dp[c]*5...)
         * a 表示 dp[a...j] * 2 时候大于 dp[i-1] 的下标
         * 按这样的关系，就可找到dp[i]，之后将对应的 dp[x] * bx进行x+1。其余的不变。
         * 则其余的依旧满足条件。
         */
        long[] uglyNumbers = new long[n+1];
        long[] factor = new long[]{2,3,5};
        long[] preVal = new long[]{2, 3, 5};
        int[] preIndex = new int[]{1, 1, 1};
        HashSet<Long> uglyNumberSet = new HashSet(Collections.singletonList(factor));
        int changeIndex;
        uglyNumbers[1] = 1;
        for (int i = 2; i <= n; i++) {
            changeIndex = 0;
            for (int j = 1; j < preVal.length; j++) {
                if (preVal[j] < preVal[changeIndex]) {
                    changeIndex = j;
                }
            }
            uglyNumbers[i] = preVal[changeIndex];
            uglyNumberSet.remove(uglyNumbers[i]);
            do {
                ++preIndex[changeIndex];
                preVal[changeIndex] = uglyNumbers[preIndex[changeIndex]] * factor[changeIndex];
            } while (uglyNumberSet.contains(preVal[changeIndex]));
            uglyNumberSet.add(preVal[changeIndex]);
        }
        return (int) uglyNumbers[n];
    }

    /**
     * 示例 1：
     * 输入：n = 10
     * 输出：12
     * 解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
     *
     * 示例 2：
     * 输入：n = 1
     * 输出：1
     * 解释：1 通常被视为丑数。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/ugly-number-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _264TwohundredSixtyFour().nthUglyNumberForDP(1690));
    }


}
