package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.Arrays;
import java.util.Comparator;

/**
 * You are given a 2D integer array points, where points[i] = [xi, yi]. You are also given an integer w.
 * Your task is to cover all the given points with rectangles.  Each rectangle has its lower end at some point (x1, 0)
 * and its upper end at some point (x2, y2), where x1 <= x2, y2 >= 0, and the condition x2 - x1 <= w must be
 * satisfied for each rectangle.  A point is considered covered by a rectangle if it lies within or on the boundary
 * of the rectangle.  Return an integer denoting the minimum number of rectangles needed so that each point
 * is covered by at least one rectangle.  Note: A point may be covered by more than one rectangle.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-07-31 09:43
 */
public class _3111 {

    public int minRectanglesToCoverPoints(int[][] points, int w) {
        int ans = 1;
        // 从速率上来说 Arrays.sort(points, (a, b) -> a[0] - b[0]) 要比下面的快得多
        Arrays.sort(points, Comparator.comparingInt(a -> a[0]));

        int startIdx = points[0][0];
        for (int i = 0; i < points.length; i++) {
            if (points[i][0] - startIdx > w) {
                ++ans;
                startIdx = points[i][0];
            }
        }
        return ans;
    }

    /**
     * Example 1:
     *
     *
     *
     * Input: points = [[2,1],[1,0],[1,4],[1,8],[3,5],[4,6]], w = 1
     *
     * Output: 2
     *
     * Explanation:
     *
     * The image above shows one possible placement of rectangles to cover the points:
     *
     * A rectangle with a lower end at (1, 0) and its upper end at (2, 8)
     * A rectangle with a lower end at (3, 0) and its upper end at (4, 8)
     * Example 2:
     *
     *
     *
     * Input: points = [[0,0],[1,1],[2,2],[3,3],[4,4],[5,5],[6,6]], w = 2
     *
     * Output: 3
     *
     * Explanation:
     *
     * The image above shows one possible placement of rectangles to cover the points:
     *
     * A rectangle with a lower end at (0, 0) and its upper end at (2, 2)
     * A rectangle with a lower end at (3, 0) and its upper end at (5, 5)
     * A rectangle with a lower end at (6, 0) and its upper end at (6, 6)
     * Example 3:
     *
     *
     *
     * Input: points = [[2,3],[1,2]], w = 0
     *
     * Output: 2
     *
     * Explanation:
     *
     * The image above shows one possible placement of rectangles to cover the points:
     *
     * A rectangle with a lower end at (1, 0) and its upper end at (1, 2)
     * A rectangle with a lower end at (2, 0) and its upper end at (2, 3)
     *
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.testUseTestFile(_3111.class);
    }

}
