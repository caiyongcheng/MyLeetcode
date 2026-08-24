package letcode.normal.difficult;

/**
 * 3116. Kth Smallest Amount With Single Denomination Combination
 * Difficulty: Hard
 * Link: https://leetcode.cn/problems/kth-smallest-amount-with-single-denomination-combination/
 * <p>
 * You are given an integer array coins representing coins of different denominations and an integer k
 * .
 * <p>
 * You have an infinite number of coins of each denomination. However, you are not allowed to combine
 * coins of different denominations.
 * <p>
 * Return the k th smallest amount that can be made using these coins.
 * <p>
 * Example 1:
 * <p>
 * Input: coins = [3,6,9], k = 3
 * <p>
 * Output: 9
 * <p>
 * Explanation: The given coins can make the following amounts:
 * <p>
 * Coin 3 produces multiples of 3: 3, 6, 9, 12, 15, etc.
 * <p>
 * Coin 6 produces multiples of 6: 6, 12, 18, 24, etc.
 * <p>
 * Coin 9 produces multiples of 9: 9, 18, 27, 36, etc.
 * <p>
 * All of the coins combined produce: 3, 6, 9 , 12, 15, etc.
 * <p>
 * Example 2:
 * <p>
 * Input: coins = [5,2], k = 7
 * <p>
 * Output: 12
 * <p>
 * Explanation: The given coins can make the following amounts:
 * <p>
 * Coin 5 produces multiples of 5: 5, 10, 15, 20, etc.
 * <p>
 * Coin 2 produces multiples of 2: 2, 4, 6, 8, 10, 12, etc.
 * <p>
 * All of the coins combined produce: 2, 4, 5, 6, 8, 10, 12 , 14, 15, etc.
 * <p>
 * Constraints:
 * <p>
 * - 1 <= coins.length <= 15
 * <p>
 * - 1 <= coins[i] <= 25
 * <p>
 * - 1 <= k <= 2 * 10 9
 * <p>
 * - coins contains pairwise distinct integers.
 */
public class _3116 {


    public long findKthSmallest(int[] coins, int k) {

        /*
        核心
            1、如果给定一个数x。硬币能组合的，不重复，且不大于x的组合有p种，那么就找到了第p大的数。那么可以不断二分枚举x。
            2、怎么求数x对应的组合数，对于每个coin，根据x/coin可以求出每种硬币的组合，但是存在重复（lcm）的情况。此时使用容斥原理进行计算。
            3、容斥原理，计算多个集合的并集元素数量。核心就是二项式保证重复次数只被计算一次。证明方法很多。
         */

        // 压缩，例如coins中有2，那么剩余的所有偶数都不需要被计算
        coins = compact(coins);

        // 用mask表示组合方式 求组合的最小公倍数 再根据容斥原理计算贡献
        // 这里可以用dp去优化 不过根据题目规模 这里的意义不是很大
        long[] lcmCache = new long[1 << coins.length];
        for (int i = 1; i < lcmCache.length; i++) {
            lcmCache[i] = 1;
            for (int j = 0; j < coins.length; j++) {
                if ((i & 1 << j) == 0) {
                    continue;
                }
                lcmCache[i] = lcm(lcmCache[i], coins[j]);
            }
            // 容斥原理 奇数加 偶数减
            if ((Integer.bitCount(i) & 1) == 0) {
                lcmCache[i] = -lcmCache[i];
            }
        }

        // compact后已经有序 取coins[0] * k 作为上边界
        long r = (long) coins[0] * k;
        long l = 0;
        long mid = r;
        long count;
        while (true) {
            count = getCoinCount(mid, lcmCache);
            if (count == k) {
                break;
            }
            if (count > k) {
                r = mid;
            } else {
                l = mid;
            }
            mid = (l + r) >> 1;
        }

        // 最接近mid的组合数就是结果
        long diff = Long.MAX_VALUE;
        long cur;
        for (int coin : coins) {
            cur = mid / coin * coin;
            if (mid - cur < diff) {
                diff = mid - cur;
            }
        }
        return mid - diff;
    }


    private static int[] compact(int[] coins) {
        int[] count = new int[26];
        for (int coin : coins) {
            count[coin]++;
        }


        for (int i = 1; i <= count.length >> 1; i++) {
            if (count[i] == 0) {
                continue;
            }
            for (int j = i << 1; j < count.length; j += i) {
                count[j] = 0;
            }
        }

        int len = 0;
        for (int num : count) {
            if (num != 0) {
                len++;
            }
        }

        coins = new int[len];
        int i = 0;
        for (int j = 0; j < count.length; j++) {
            if (count[j] != 0) {
                coins[i++] = j;
            }
        }
        return coins;
    }

    private long lcm(long x, long y) {
        long mx = x;
        long mi = y;

        if (x < y) {
            mx = y;
            mi = x;
        }

        long temp;
        while (mx % mi != 0) {
            temp = mi;
            mi = mx % mi;
            mx = temp;
        }

        return (x / mi) * (y / mi) * mi;

    }



    private long getCoinCount(long sum, long[] lcmCache) {
        long count = 0;
        for (int i = 1; i < lcmCache.length; i++) {
            count +=  sum / lcmCache[i];
        }
        return count;
    }


}
