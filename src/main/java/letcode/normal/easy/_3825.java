package letcode.normal.easy;

import letcode.utils.TestUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * There are n mountains in a row, and each mountain has a height. You are given an integer array height where height[i]
 * represents the height of mountain i, and an integer threshold.  A mountain is called stable if the mountain just
 * before it (if it exists) has a height strictly greater than threshold.
 * Note that mountain 0 is not stable.
 * Return an array containing the indices of all stable mountains in any order.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-12-19 14:13
 */
public class _3825 {

    public List<Integer> stableMountains(int[] height, int threshold) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i < height.length; i++) {
            if (height[i - 1] > threshold) {
                ans.add(i);
            }
        }
        return ans;
    }

}
