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


}
