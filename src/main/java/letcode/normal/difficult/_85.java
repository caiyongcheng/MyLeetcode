package letcode.normal.difficult;

/**
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 *
 * @author CaiYongcheng
 * @since 2021-09-16 14:13
 **/
public class _85 {

    private char[][] matrix;

    private int rowLen;
    private int colLen;

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        /*
         穷举加剪枝
         */
        this.matrix = matrix;
        rowLen = matrix.length;
        colLen = matrix[0].length;
        int maxArea = 0;
        int right;
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (maxArea >= (rowLen - row) * (colLen - col)) {
                    break;
                }
                //以matrix[row][col]为左上角 所能返回的最大矩形面积 横向扩展
                if (matrix[row][col] != '0') {
                    maxArea = Math.max(maxRectangleArea(row, col), maxArea);
                }
            }
        }
        return maxArea;
    }


    public int maxRectangleArea(int startRow, int startCol) {
        int maxArea = 0;
        int[] maxHeight = new int[colLen - startCol];
        int row = startRow;
        int col = startCol;
        int minHeght = Integer.MAX_VALUE;
        while (col < colLen && matrix[startRow][col] == '1') {
            while (row < rowLen && matrix[row][col] == '1') {
                ++maxHeight[col - startCol];
                ++row;
            }
            row = startRow;
            ++col;
        }
        for (int i = 0; i < maxHeight.length && maxHeight[i] > 0; i++) {
            minHeght = Math.min(minHeght, maxHeight[i]);
            maxArea = Math.max(maxArea, (i + 1) * minHeght);
        }
        return maxArea;
    }

}
