package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * In a row of dominoes, tops[i] and bottoms[i] represent the top and bottom halves of the ith domino.
 * (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)
 * We may rotate the ith domino, so that tops[i] and bottoms[i] swap values.
 * Return the minimum number of rotations so that all the values in tops are the same,
 * or all the values in bottoms are the same.  If it cannot be done, return -1.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-05-09 14:39
 */
public class _1077 {

    public int minDominoRotations(int[] tops, int[] bottoms) {
        int ans1 = getMinSwapTime(tops, bottoms, tops[0]);
        int ans2 = getMinSwapTime(tops, bottoms, bottoms[0]);
        return (ans1 == Integer.MAX_VALUE && ans2 == Integer.MAX_VALUE) ? -1 : Math.min(ans1, ans2);
    }

    private int getMinSwapTime(int[] tops, int[] bottoms, int targetNum) {
        int onTopCount = 0;
        int onBottomCount = 0;
        for (int i = 0; i < tops.length; i++) {
            if (tops[i] != targetNum && bottoms[i] != targetNum) {
                return Integer.MAX_VALUE;
            }
            if (tops[i] == targetNum) {
                onTopCount++;
            }
            if (bottoms[i] == targetNum) {
                onBottomCount++;
            }
        }
        return tops.length - Math.max(onTopCount, onBottomCount);
    }

}
