package letcode.normal.easy;

import letcode.utils.TestUtil;

/**
 * You are given coordinates, a string that represents the coordinates of a square of the chessboard.
 * Below is a chessboard for your reference.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-12-09 10:41
 */
public class _1812 {

    public boolean squareIsWhite(String coordinates) {
        return (coordinates.charAt(0) - 'a' & 1) + ((coordinates.charAt(1) - '1') & 1) == 1;
    }


    /**
     * Example 1:
     *
     * Input: coordinates = "a1"
     * Output: false
     * Explanation: From the chessboard above, the square with coordinates "a1" is black, so return false.
     * Example 2:
     *
     * Input: coordinates = "h3"
     * Output: true
     * Explanation: From the chessboard above, the square with coordinates "h3" is white, so return true.
     * Example 3:
     *
     * Input: coordinates = "c7"
     * Output: false
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test(_1812.class);
    }


}
