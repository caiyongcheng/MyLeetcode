package letcode.medium;

/**
 * Leetcode
 * 编写一个高效的算法来判断m x n矩阵中，是否存在一个目标值。
 * 该矩阵具有如下特性：  每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : CaiYongcheng
 * @date : 2020-07-16 09:03
 **/
public class _74SenvenFour {

    /**
     * 示例1:
     * 输入:
     * matrix = {
     * {1,   3,  5,  7},
     * {10, 11, 16, 20},
     * {23, 30, 34, 50}
     * }
     * target = 3
     * 输出: true
     * <p>
     * 示例2:
     * 输入:
     * matrix = {
     * {1,   3,  5,  7},
     * {10, 11, 16, 20},
     * {23, 30, 34, 50}
     * }
     * target = 13
     * 输出: false
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param matrix
     * @param target
     * @return
     */
    
    /*
      matrix = {
        {1,   3,  5,  7},
        {10, 11, 16, 20},
        {23, 30, 34, 50}
      }
      {
        {1,   3,  5,  7},
        {10, 11, 16, 20},
        {23, 30, 34, 50}
      }
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (null == matrix || matrix.length < 1
                || null == matrix[0] || matrix[0].length < 1) {
            return false;
        }
        int rowSize = matrix.length;
        int colSize = matrix[0].length;
        int left = 0;
        int right = rowSize * colSize - 1;
        int mid = 0;
        int row = 0;
        int col = 0;
        if (target < matrix[0][0] || target > matrix[rowSize - 1][colSize - 1]) {
            return false;
        }
        while (left <= right) {
            mid = (left + right) / 2;
            row = mid / colSize;
            col = mid - row * colSize;
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(searchMatrix(new int[][]{
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        }, 13));
    }

}
