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
 * 在一个n x n的国际象棋棋盘上，一个骑士从单元格 (row, column)开始，并尝试进行 k 次移动。行和列是 从 0 开始 的，
 * 所以左上单元格是 (0,0) ，右下单元格是 (n - 1, n - 1) 。
 * 象棋骑士有8种可能的走法，如下图所示。每次移动在基本方向上是两个单元格，然后在正交方向上是一个单元格。
 * 每次骑士要移动时，它都会随机从8种可能的移动中选择一种(即使棋子会离开棋盘)，然后移动到那里。
 * 骑士继续移动，直到它走了 k 步或离开了棋盘。  返回 骑士在棋盘停止移动后仍留在棋盘上的概率 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/knight-probability-in-chessboard 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-02-17 09:06
 **/
public class _688 {


    private final int[][] moved = new int[][]{{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};

    public double knightProbability(int n, int k, int row, int column) {
        if (k == 0) {
            return 1;
        }
        double[][][] probabilities = new double[k][n][n];
        int nextR;
        int nextC;
        for (int step = 0; step < k - 1; step++) {
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    for (int[] move : moved) {
                        nextR = r + move[0];
                        nextC = c + move[1];
                        if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= n) {
                            continue;
                        }
                        if (step == 0) {
                            probabilities[step + 1][nextR][nextC] += 0.125;
                        } else {
                            probabilities[step + 1][nextR][nextC] += 0.125 * probabilities[step][r][c];
                        }
                    }
                }
            }
        }
        double probability = 0;
        int lastR;
        int lastC;
        for (int[] move : moved) {
            lastR = row - move[0];
            lastC = column - move[1];
            if (lastR < 0 || lastR >= n || lastC < 0 || lastC >= n) {
                continue;
            }
            probability += (k - 1 == 0 ? 1 : probabilities[k - 1][lastR][lastC]) * 0.125;
        }
        return probability;
    }


    /**
     * 示例 1：
     * <p>
     * 输入: n = 3, k = 2, row = 0, column = 0
     * 输出: 0.0625
     * 解释: 有两步(到(1,2)，(2,1))可以让骑士留在棋盘上。
     * 在每一个位置上，也有两种移动可以让骑士留在棋盘上。
     * 骑士留在棋盘上的总概率是0.0625。
     * 示例 2：
     * <p>
     * 输入: n = 1, k = 0, row = 0, column = 0
     * 输出: 1.00000
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/knight-probability-in-chessboard
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * x x x
     * x x x
     * x x x
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _688().knightProbability(
                3,
                1,
                0,
                0
        ));
    }


}
