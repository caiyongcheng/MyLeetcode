package letcode.normal.medium;

import letcode.utils.TestCaseUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 值 0 代表空单元格； 值 1 代表新鲜橘子； 值 2 代表腐烂的橘子。 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
 * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/5/14 15:55
 */
public class _994 {

    public int orangesRotting(int[][] grid) {
        /*
        两种思路
        1、统计每个完好橘子到腐烂橘子的最远距离
        2、模拟
         */


        List<Integer> curRot = new ArrayList<>();
        List<Integer> immediatelyRot;
        int row;
        int col;
        int rowLen = grid.length;
        int colLen = grid[0].length;
        int[][] moveArr = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int rotMinute = 0;
        int freshOrangeCnt = 0;

        // 初始化
        for (row = 0; row < rowLen; row++) {
            for (col = 0; col < colLen; col++) {
                if (grid[row][col] == 1) {
                    ++freshOrangeCnt;
                } else if (grid[row][col] == 2) {
                    curRot.add(row * colLen + col);
                }
            }
        }

        // 模拟扩散
        while (!curRot.isEmpty()) {
            immediatelyRot = new ArrayList<>();
            int nextRow;
            int nextCol;
            for (Integer rotPoint : curRot) {
                row = rotPoint / colLen;
                col = rotPoint % colLen;
                for (int[] move : moveArr) {
                    nextRow = row + move[0];
                    nextCol = col + move[1];
                    if (nextRow >= 0 && nextRow < rowLen
                            && nextCol >= 0 && nextCol < colLen
                            && grid[nextRow][nextCol] == 1) {
                        grid[nextRow][nextCol] = 2;
                        --freshOrangeCnt;
                        immediatelyRot.add(nextRow * colLen + nextCol);
                    }
                }
            }
            curRot = immediatelyRot;
            ++rotMinute;
        }

        // 验证结果
        return freshOrangeCnt == 0 ? Math.max(0, rotMinute - 1) : -1;
    }

    public static void main(String[] args) {
        System.out.println(new _994().orangesRotting(
                TestCaseUtils.get2DIntArr("[[0,2]]")
        ));
    }

}
