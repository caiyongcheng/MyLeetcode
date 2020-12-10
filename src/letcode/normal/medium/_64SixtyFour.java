package letcode.medium;

import java.util.Arrays;

/**
 * Leetcode
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 *
 * @author : CaiYongcheng
 * @date : 2020-07-15 09:01
 **/
public class _64SixtyFour {

    /**
     * 输入:
     * [
     *   [1,3,1],
     *   [1,5,1],
     *   [4,2,1]
     * ]
     * 输出: 7
     * @param grid
     * @return
     */
    public static int minPathSum(int[][] grid) {
        int y_length = grid.length;
        int x_length = grid[0].length;
        int minN = Integer.MAX_VALUE;
        for (int y=0; y<y_length; ++y){
            for (int x=0; x<x_length; ++x){
                minN = Integer.MAX_VALUE;
                if (y-1 > -1&&grid[y-1][x] < minN){
                    minN = grid[y-1][x];
                }
                if (x-1 > -1&&grid[y][x-1] < minN){
                    minN = grid[y][x-1];
                }
                if (minN != Integer.MAX_VALUE){
                    grid[y][x] += minN;
                }
            }
        }
        for (int[] ints : grid) {
            System.out.println(Arrays.toString(ints));
        }
        return grid[y_length-1][x_length-1];
    }

    /*
       {
         {1,3,1},
         {1,5,1},
         {4,2,1}
       }
     */
    public static void main(String[] args) {
        System.out.println(minPathSum(new int[][]{
                {1,2,5},
                {1,3,1},
        }));
    }

}
