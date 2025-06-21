package letcode.normal.easy;

import letcode.utils.TestUtil;

/**
 * You are given two strings, coordinate1 and coordinate2,
 * representing the coordinates of a square on an 8 x 8 chessboard.  Below is the chessboard for reference.
 * Return true if these two squares have the same color and false otherwise.  The coordinate will always represent a
 * valid chessboard square. The coordinate will always have the letter first (indicating its column), and
 * the number second (indicating its row).
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-12-04 10:44
 */
public class _3274 {

    public boolean checkTwoChessboards(String coordinate1, String coordinate2) {
        return (Math.abs(coordinate1.charAt(0) - coordinate2.charAt(0)) & 1)
                == (Math.abs(coordinate1.charAt(1) - coordinate2.charAt(1)) & 1);
    }

    /**
     * Example 1:
     *
     * Input: coordinate1 = "a1", coordinate2 = "c3"
     *
     * Output: true
     *
     * Explanation:
     *
     * Both squares are black.
     *
     * Example 2:
     *
     * Input: coordinate1 = "a1", coordinate2 = "h3"
     *
     * Output: false
     *
     * Explanation:
     *
     * Square "a1" is black and "h3" is white.
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test(_3274.class);
    }

}
