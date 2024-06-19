/*
 * 版权所有（c）<2021><蔡永程>
 *
 * 反996许可证版本1.0
 *
 * 在符合下列条件的情况下，特此免费向任何得到本授权作品的副本（包括源代码、文件和/或相关内容，以
 * 下统称为“授权作品”）的个人和法人实体授权：被授权个人或法人实体有权以任何目的处置授权作品，包括
 * 但不限于使用、复制，修改，衍生利用、散布，发布和再许可：
 *
 * 1. 个人或法人实体必须在许可作品的每个再散布或衍生副本上包含以上版权声明和本许可证，不得自行修
 * 改。
 * 2. 个人或法人实体必须严格遵守与个人实际所在地或个人出生地或归化地、或法人实体注册地或经营地（
 * 以较严格者为准）的司法管辖区所有适用的与劳动和就业相关法律、法规、规则和标准。如果该司法管辖区
 * 没有此类法律、法规、规章和标准或其法律、法规、规章和标准不可执行，则个人或法人实体必须遵守国际
 * 劳工标准的核心公约。
 * 3. 个人或法人不得以任何方式诱导、暗示或强迫其全职或兼职员工或其独立承包人以口头或书面形式同意
 * 直接或间接限制、削弱或放弃其所拥有的，受相关与劳动和就业有关的法律、法规、规则和标准保护的权利
 * 或补救措施，无论该等书面或口头协议是否被该司法管辖区的法律所承认，该等个人或法人实体也不得以任
 * 何方法限制其雇员或独立承包人向版权持有人或监督许可证合规情况的有关当局报告或投诉上述违反许可证
 * 的行为的权利。
 *
 * 该授权作品是"按原样"提供，不做任何明示或暗示的保证，包括但不限于对适销性、特定用途适用性和非侵
 * 权性的保证。在任何情况下，无论是在合同诉讼、侵权诉讼或其他诉讼中，版权持有人均不承担因本软件或
 * 本软件的使用或其他交易而产生、引起或与之相关的任何索赔、损害或其他责任。
 */

package letcode.normal.medium;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/9/13 9:23
 * description 骑士在一张 n x n 的棋盘上巡视。在有效的巡视方案中，骑士会从棋盘的 左上角 出发，并且访问棋盘上的每个格子 恰好一次 。
 * 给你一个 n x n 的整数矩阵 grid ，由范围 [0, n * n - 1] 内的不同整数组成，
 * 其中 grid[row][col] 表示单元格 (row, col) 是骑士访问的第 grid[row][col] 个单元格。骑士的行动是从下标 0 开始的。
 * 如果 grid 表示了骑士的有效巡视方案，返回 true；否则返回 false。
 * 注意，骑士行动时可以垂直移动两个格子且水平移动一个格子，或水平移动两个格子且垂直移动一个格子。
 * 下图展示了骑士从某个格子出发可能的八种行动路线。
 */
public class _2596 {

    private final int[][] move = new int[][]{
            {-2, 1},
            {-1, 2},
            {1, 2},
            {2, 1},
            {2, -1},
            {1, -2},
            {-1, -2},
            {-2, -1}
    };

    private int cnt;

    public boolean checkValidGrid(int[][] grid) {
        if (grid[0][0] != 0) {
            return false;
        }
        int len = grid.length;
        int nextRow = 0;
        int nextCol = 0;
        int limit = len * len - 1;
        boolean arrive;
        for (int row = 0; row < len; row++) {
            for (int col = 0; col < len; col++) {
                arrive = false;
                for (int[] step : move) {
                    nextRow = row + step[0];
                    nextCol = col + step[1];
                    if (nextRow > -1 && nextRow < len
                            && nextCol > -1 && nextCol < len
                            && (grid[nextRow][nextCol] == grid[row][col] + 1 || grid[row][col] == limit)) {
                        arrive = true;
                        break;
                    }
                }
                if (!arrive) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 输入：grid = {{0,11,16,5,20},{17,4,19,10,15},{12,1,8,21,6},{3,18,23,14,9},{24,13,2,7,22}}
     * 输出：true
     * 解释：grid 如上图所示，可以证明这是一个有效的巡视方案。
     * <p>
     * 输入：grid = {{0,3,6},{5,8,1},{2,7,4}}
     * 输出：false
     * 解释：grid 如上图所示，考虑到骑士第 7 次行动后的位置，第 8 次行动是无效的。
     * <p>
     * {{24,11,22,17,4},
     * {21,16,5,12,9},
     * {6,23,10,3,18},
     * {15,20,1,8,13},
     * {0,7,14,19,2}}
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2596().checkValidGrid(
                new int[][]{{24, 11, 22, 17, 4}, {21, 16, 5, 12, 9}, {6, 23, 10, 3, 18}, {15, 20, 1, 8, 13}, {0, 7, 14, 19, 2}}
        ));
    }

}