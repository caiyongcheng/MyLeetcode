package letcode.normal.unansweredquestions.medium;

import letcode.utils.TestUtil;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * You are given a 2D array points of size n x 2 representing integer coordinates of some points on a 2D plane, where points[i] = [xi, yi].
 * Count the number of pairs of points (A, B), where  A is on the upper left side of B, and there are no other points in the rectangle (or line) they make (including the border).
 * Return the count.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-09-02 09:17
 */
public class N_3025 {

    public int numberOfPairs(int[][] points) {
        Arrays.sort(points, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return b[1] - a[1];
        });

        int ans = 0;
        ArrayDeque<int[]> leftPoints = new ArrayDeque<>();
        for (int[] point : points) {
            while (!leftPoints.isEmpty() && leftPoints.peek()[1] >= point[1]) {
                leftPoints.pop();
                ++ans;
            }
            if (leftPoints.isEmpty() || leftPoints.peek()[1] < point[1]) {
                leftPoints.push(point);
            }
        }

        return ans;
    }


}
