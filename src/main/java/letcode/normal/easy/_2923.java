package letcode.normal.easy;

import letcode.utils.TestCaseInputUtils;

/**
 * 一场比赛中共有 n 支队伍，按从 0 到  n - 1 编号。  给你一个下标从 0 开始、大小为 n * n 的二维布尔矩阵 grid 。
 * 对于满足 0 <= i, j <= n - 1 且 i != j 的所有 i, j ：如果 grid[i][j] == 1，那么 i 队比 j 队 强 ；否则，j 队比 i 队 强 。
 * 在这场比赛中，如果不存在某支强于 a 队的队伍，则认为 a 队将会是 冠军 。  返回这场比赛中将会成为冠军的队伍。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/4/12 09:04
 */
public class _2923 {

    public int findChampion(int[][] grid) {
        int len = grid.length;
        int[] flagArr = new int[len];
        for (int[] arr : grid) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == 1) {
                    flagArr[j] = 1;
                }
            }
        }
        for (int i = 0; i < flagArr.length; i++) {
            if (flagArr[i] == 0) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 示例 1：
     *
     * 输入：grid = [[0,1],[0,0]]
     * 输出：0
     * 解释：比赛中有两支队伍。
     * grid[0][1] == 1 表示 0 队比 1 队强。所以 0 队是冠军。
     * 示例 2：
     *
     * 输入：grid = [[0,0,1],[1,0,1],[0,0,0]]
     * 输出：1
     * 解释：比赛中有三支队伍。
     * grid[1][0] == 1 表示 1 队比 0 队强。
     * grid[1][2] == 1 表示 1 队比 2 队强。
     * 所以 1 队是冠军。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2923().findChampion(
                TestCaseInputUtils.get2DIntArr("[[0,0,1],[1,0,1],[0,0,0]]")
        ));
    }

}
