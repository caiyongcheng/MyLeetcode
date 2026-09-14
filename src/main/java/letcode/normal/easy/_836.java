package letcode.normal.easy;

/**
 * 836. Rectangle Overlap
 * Difficulty: Easy
 * Link: https://leetcode.cn/problems/rectangle-overlap/
 * <p>
 * An axis-aligned rectangle is represented as a list [x1, y1, x2, y2] , where (x1, y1) is the
 * coordinate of its bottom-left corner, and (x2, y2) is the coordinate of its top-right corner. Its
 * top and bottom edges are parallel to the X-axis, and its left and right edges are parallel to the
 * Y-axis.
 * <p>
 * Two rectangles overlap if the area of their intersection is positive . To be clear, two rectangles
 * that only touch at the corner or edges do not overlap.
 * <p>
 * Given two axis-aligned rectangles rec1 and rec2 , return true if they overlap, otherwise return
 * false .
 * <p>
 * Example 1:
 * <p>
 * Input: rec1 = [0,0,2,2], rec2 = [1,1,3,3]
 * Output: true
 * <p>
 * Example 2:
 * <p>
 * Input: rec1 = [0,0,1,1], rec2 = [1,0,2,1]
 * Output: false
 * <p>
 * Example 3:
 * <p>
 * Input: rec1 = [0,0,1,1], rec2 = [2,2,3,3]
 * Output: false
 * <p>
 * Constraints:
 * <p>
 * - rec1.length == 4
 * <p>
 * - rec2.length == 4
 * <p>
 * - -10 9 <= rec1[i], rec2[i] <= 10 9
 * <p>
 * - rec1 and rec2 represent a valid rectangle with a non-zero area.
 */
public class _836 {

    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return !(rec1[2] <= rec2[0]
                || rec2[2] <= rec1[0]
                || rec1[3] <= rec2[1]
                || rec2[3] <= rec1[1]);
    }
}
