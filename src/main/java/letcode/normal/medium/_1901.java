package letcode.normal.medium;

import letcode.utils.TestCaseInputUtils;

import java.util.Arrays;

/**
 * 一个 2D 网格中的 峰值 是指那些 严格大于 其相邻格子(上、下、左、右)的元素。
 * 给你一个 从 0 开始编号 的 m x n 矩阵 mat ，其中任意两个相邻格子的值都 不相同 。找出 任意一个 峰值 mat[i][j] 并 返回其位置 [i,j] 。
 * 你可以假设整个矩阵周边环绕着一圈值为 -1 的格子。  要求必须写出时间复杂度为 O(m log(n)) 或 O(n log(m)) 的算法
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2023/12/25 11:08
 */
public class _1901 {

    public int[] findPeakGrid(int[][] mat) {
        // 实现做法不是题目要求的O(m log(n)) 或 O(n log(m))
        // O(m log(n)) 或 O(n log(m)) 的做法可以参考_162
        // 以O(n log(m))为例，log(m)对行进行二分，n表示求每行的最大值
        // 1 为什么存在峰值
        // 因为每一行的最大值肯定比同行值要大，
        //      a、如果小于下一行的值，那么下一行的最大值肯定大于他的上一行值，此时要不存在峰值的话，
        //      那么必须下一行的最大值小于下一行的下一行的值，以此类推。得到一个类似递增的关系，那么最后一行的最大值就会满足条件。
        //      b、如果大于上一行的值，那么就往上去比较，最后原因也是一样的。
        // 综上，所以存在峰值
        // 2 怎么二分
        // 第一行是满足最大值大于前一行值的情况的，因为没有前一行，最后一行也满足大于后一行的情况，因为没有后一行
        // 所以不停的缩小这个范围，保证两边端点一直满足条件即可


        // 每次迭代都向着更大的元素走
        int rowLen = mat.length;
        int colLen = mat[0].length;
        int[][] nextStepArr = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        int curRow = 0;
        int curCol = 0;
        int nextRow = 0;
        int nextCol = 0;
        while (true) {
            nextRow = curRow;
            nextCol = curCol;
            for (int[] nextStep : nextStepArr) {
                if (curRow + nextStep[0] >= 0 && curRow + nextStep[0] < rowLen
                        && curCol + nextStep[1] >= 0 && curCol + nextStep[1] < colLen
                        && mat[curRow + nextStep[0]][curCol + nextStep[1]] > mat[nextRow][nextCol]) {
                    nextRow = curRow + nextStep[0];
                    nextCol = curCol + nextStep[1];
                }
            }
            if (nextRow == curRow && nextCol == curCol) {
                return new int[]{nextRow, nextCol};
            }
            curRow = nextRow;
            curCol = nextCol;
        }
    }

    public int findPeakGrid(int[] mat) {
        int left = 0;
        int right = mat.length - 1;
        int mid;
        while (true) {
            mid = (left + right) >> 1;
            if (mid == left) {
                break;
            }
            if (mid + 1 == mat.length || mat[mid] > mat[mid + 1]) {
                // mid是峰值
                if (mid - 1 == -1 || mat[mid] > mat[mid - 1]) {
                    return mid;
                }
                right = mid;
            } else {
                left = mid;
            }
        }
        return mid == mat.length - 1 || mat[mid] > mat[mid + 1] ? mid : mid + 1;
    }


    /**
     *
     示例 1:
     输入: mat = [[1,4],[3,2]]
     输出: [0,1]
     解释: 3 和 4 都是峰值，所以[1,0]和[0,1]都是可接受的答案。
     示例 2:
     输入: mat = [[10,20,15],[21,30,14],[7,16,32]]
     输出: [1,1]
     解释: 30 和 32 都是峰值，所以[1,1]和[2,2]都是可接受的答案。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new _1901().findPeakGrid(
                TestCaseInputUtils.get2DIntArr("[[1,2,6],[3,4,5]]")
        )));
    }

}
