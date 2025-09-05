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


    /**
     * Example 1:
     * Input: points = [[1,1],[2,2],[3,3]]
     * Output: 0
     * Explanation:
     * There is no way to choose A and B so A is on the upper left side of B.
     * Example 2:
     * Input: points = [[6,2],[4,4],[2,6]]
     * Output: 2
     * Explanation:
     * The left one is the pair (points[1], points[0]), where points[1] is on the upper left side of points[0] and the rectangle is empty.
     * The middle one is the pair (points[2], points[1]), same as the left one it is a valid pair.
     * The right one is the pair (points[2], points[0]), where points[2] is on the upper left side of points[0], but points[1] is inside the rectangle so it's not a valid pair.
     * Example 3:
     * Input: points = [[3,1],[1,3],[1,1]]
     * Output: 2
     * Explanation:
     * The left one is the pair (points[2], points[0]), where points[2] is on the upper left side of points[0] and there are no other points on the line they form. Note that it is a valid state when the two points form a line.
     * The middle one is the pair (points[1], points[2]), it is a valid pair same as the left one.
     * The right one is the pair (points[1], points[0]), it is not a valid pair as points[2] is on the border of the rectangle.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }

}
