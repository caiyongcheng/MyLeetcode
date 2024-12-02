package letcode.normal.easy;

import letcode.utils.TestUtil;

/**

 * There is a circle of red and blue tiles. You are given an array of integers colors.
 * The color of tile i is represented by colors[i]:
 * colors[i] == 0 means that tile i is red.
 * colors[i] == 1 means that tile i is blue.
 * Every 3 contiguous tiles in the circle with alternating colors (the middle tile has a different color
 * from its left and right tiles) is called an alternating group.
 * Return the number of alternating groups.
 *
 * Note that since colors represents a circle, the first and the last tiles are considered to be next to each other.
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-11-26 09:10
 */
public class _3206 {

    public int numberOfAlternatingGroups(int[] colors) {
        if (colors.length < 3) {
            return 0;
        }
        int ans = 0;
        for (int i = 1; i < colors.length - 1; i++) {
            if (colors[i] != colors[i - 1] && colors[i] != colors[i + 1]) {
                ans++;
            }
        }
        if (colors[0] != colors[1] && colors[0] != colors[colors.length - 1]) {
            ans++;
        }
        if (colors[colors.length - 1] != colors[colors.length - 2] && colors[colors.length - 1] != colors[0]) {
            ans++;
        }
        return ans;
    }

    /**
     * Example 1:
     *
     * Input: colors = [1,1,1]
     *
     * Output: 0
     *
     * Example 2:
     *
     * Input: colors = [0,1,0,0,1]
     *
     * Output: 3
     *
     * Explanation:
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test(_3206.class);
    }

}
