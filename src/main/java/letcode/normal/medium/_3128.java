package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.Arrays;

/**
 * 请你返回使用 grid 中的 3 个元素可以构建的 直角三角形 数目，且满足 3 个元素值 都 为 1 。
 * 注意：  如果 grid 中 3 个元素满足：一个元素与另一个元素在 同一行，
 * 同时与第三个元素在 同一列 ，那么这 3 个元素称为一个 直角三角形 。这 3 个元素互相之间不需要相邻。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-08-02 10:05
 */
public class _3128 {

    public long numberOfRightTriangles(int[][] grid) {
        long ans = 0;
        int[] rowNum = new int[grid.length];
        int[] colNum = new int[grid[0].length];
        Arrays.fill(rowNum, -1);
        Arrays.fill(colNum, -1);

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                rowNum[row] += grid[row][col];
                colNum[col] += grid[row][col];
            }
        }

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 1) {
                    ans += (long) rowNum[row] * (colNum[col]);
                }
            }
        }

        return ans;
    }

    /**
     * Example 1:
     * Input: grid = [[0,1,0],[0,1,1],[0,1,0]]
     * Output: 2
     * Explanation:
     * There are two right triangles.
     * Example 2:
     * Input: grid = [[1,0,0,0],[0,1,0,1],[1,0,0,0]]
     * Output: 0
     * Explanation:
     * There are no right triangles.
     * Example 3:
     * Input: grid = [[1,0,1],[1,0,0],[1,0,0]]
     *
     * Output: 2
     *
     * Explanation:
     *
     * There are two right triangles.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.testWithTestClassFile(_3128.class);
        //TestUtil.testWithTestFile(_3128.class);
    }
}
