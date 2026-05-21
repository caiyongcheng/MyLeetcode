package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.Arrays;

/**
 * You are given a 2D array points and a string s where, points[i] represents the coordinates of point i,
 * and s[i] represents the tag of point i.  A valid square is a square centered at the origin (0, 0),
 * has edges parallel to the axes, and does not contain two points with the same tag.
 * Return the maximum number of points contained in a valid square.  Note:  A point is considered to
 * be inside the square if it lies on or within the square's boundaries. The side length of the square can be zero.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-08-03 17:24
 */
public class _3143 {

    public int maxPointsInsideSquare(int[][] points, String s) {
        int[] minDistanceFromTag = new int[26];
        int[] secondDistanceFromTag = new int[26];
        Arrays.fill(minDistanceFromTag, Integer.MAX_VALUE);
        Arrays.fill(secondDistanceFromTag, Integer.MAX_VALUE);

        int idx;
        int distance;
        for (int i = 0; i < points.length; i++) {
            idx = s.charAt(i) - 'a';
            distance = Integer.max(Math.abs(points[i][0]), Math.abs(points[i][1]));
            if (minDistanceFromTag[idx] >= distance) {
                secondDistanceFromTag[idx] = minDistanceFromTag[idx];
                minDistanceFromTag[idx] = distance;
            } else if (secondDistanceFromTag[idx] >= distance) {
                secondDistanceFromTag[idx] = distance;
            }
        }

        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < secondDistanceFromTag.length; i++) {
            minDistance = Math.min(minDistance, secondDistanceFromTag[i]);
        }
        --minDistance;

        int ans = 0;
        for (int i = 0; i < minDistanceFromTag.length; i++) {
            if (minDistanceFromTag[i] <= minDistance) {
                ++ans;
            }
        }
        return ans;
    }

}
