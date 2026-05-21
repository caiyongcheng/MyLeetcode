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

}
