package letcode.medium;

/**
 * Leetcode
 * 给定一个 m x n 的矩阵，如果一个元素为 0，
 * 则将其所在行和列的所有元素都设为 0。请使用原地算法。
 *
 * @author : CaiYongcheng
 * @date : 2020-07-15 09:27
 **/
public class _73SeventyThree {

    private static int y_flag = 0;
    private static int x_flag = 0;

    /**
     * 输入:
     * [
     *  [1,1,1],
     *  [1,0,1],
     *  [1,1,1]
     * ]
     * 输出:
     * [
     *  [1,0,1],
     *  [0,0,0],
     *  [1,0,1]
     * ]
     *
     * @param matrix
     */
    public static void setZeroes(int[][] matrix) {
        int y_length = matrix.length;
        int x_length = matrix[0].length;
        int y = 0;
        int x = 0;
        for (y = 0; y < y_length; ++y) {
            for (x = 0; x < x_length; ++x) {
                if (matrix[y][x] == 0) {
                    y_flag = y_flag | (1 << y);
                    x_flag = x_flag | (1 << x);
                }
            }
        }
        y = 0;
        while (y_flag != 0) {
            if (y_flag % 2 == 1) {
                for (x = 0; x < x_length; ++x) {
                    matrix[y][x] = 0;
                }
            }
            y_flag = y_flag >>> 1;
            ++y;
        }
        x = 0;
        while (x_flag != 0) {
            if (x_flag % 2 == 1) {
                for (y = 0; y < y_length; ++y) {
                    matrix[y][x] = 0;
                }
            }
            x_flag = x_flag >>> 1;
            ++x;
        }
    }

    public static void setZeroes2(int[][] matrix) {
        int y_length = matrix.length;
        int x_length = matrix[0].length;
        int y = 0;
        int x = 0;
        boolean row = false;
        boolean col = false;
        for (y = 0; y < y_length && !col; ++y) {
            if (matrix[y][0] == 0) {
                col = true;
            }
        }
        for (x = 0; x < x_length && !row; ++x) {
            if (matrix[0][x] == 0) {
                row = true;
            }
        }
        for (y = 0; y < y_length; ++y) {
            for (x = 0; x < x_length; ++x) {
                if (matrix[y][x] == 0) {
                    matrix[0][x] = matrix[y][0] = 0;
                }
            }
        }
        for (y = y_length - 1; y > 0; --y) {
            if (matrix[y][0] == 0) {
                for (x = 0; x < x_length; ++x) {
                    matrix[y][x] = 0;
                }
            }
        }
        for (x = x_length - 1; x > 0; --x) {
            if (matrix[0][x] == 0) {
                for (y = 0; y < y_length; ++y) {
                    matrix[y][x] = 0;
                }
            }
        }
        if (row) {
            for (x = 0; x < x_length; ++x) {
                matrix[0][x] = 0;
            }
        }
        if (col) {
            for (y = 0; y < y_length; ++y) {
                matrix[y][0] = 0;
            }
        }
    }


    public static void main(String[] args) {
        setZeroes2(new int[][]{
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        });
    }

}
