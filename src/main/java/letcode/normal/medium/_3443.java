package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * You are given a string s consisting of the characters 'N', 'S', 'E', and 'W',
 * where s[i] indicates movements in an infinite grid:
 *  'N' : Move north by 1 unit.
 *  'S' : Move south by 1 unit.
 *  'E' : Move east by 1 unit.
 *  'W' : Move west by 1 unit.
 *  Initially, you are at the origin (0, 0).
 *  You can change at most k characters to any of the four directions.
 *  Find the maximum Manhattan distance from the origin
 *  that can be achieved at any time while performing the movements in order.
 *  The Manhattan Distance between two cells (xi, yi) and (xj, yj) is |xi - xj| + |yi - yj|.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-06-20 10:53
 */
public class _3443 {

    public int maxDistance(String s, int k) {
        int ans = 0;
        int[] cntArr = new int[26];
        int length = s.length();
        for (int i = 0; i < length; i++) {
            cntArr[s.charAt(i) - 'A']++;
            int totalMinCnt = Math.min(k, Math.min(cntArr[4], cntArr[22]) + Math.min(cntArr[13], cntArr[18]));
            ans = Math.max(ans, Math.abs(cntArr[4] - cntArr[22]) + Math.abs(cntArr[13] - cntArr[18]) + (totalMinCnt << 1));
        }
        return ans;
    }

}
