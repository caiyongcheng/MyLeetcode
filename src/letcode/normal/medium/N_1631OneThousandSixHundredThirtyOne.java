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

import java.util.Arrays;

/**
 * 你准备参加一场远足活动。给你一个二维rows x columns的地图heights，其中heights[row][col]表示格子(row, col)的高度。
 * 一开始你在最左上角的格子(0, 0)，且你希望去最右下角的格子(rows-1, columns-1)（注意下标从 0 开始编号）。你每次可以往 上，下，左，右四个方向之一移动，
 * 你想要找到耗费 体力 最小的一条路径。  一条路径耗费的 体力值是路径上相邻格子之间 高度差绝对值的 最大值决定的。  请你返回从左上角走到右下角的最小体力消耗值。 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/path-with-minimum-effort 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-04-19 09:39
 **/
public class N_1631OneThousandSixHundredThirtyOne {


    public int minimumEffortPath(int[][] heights) {
        //根据题目  一条路径耗费的 体力值是路径上相邻格子之间 高度差绝对值的 最大值决定的。
        //使用动态规划的话 当前节点到达终点的最小体力 = Σmin（Math.max(当前节点到达下一节点的高度差绝对值，下一节点到达终点的最小体力)）
        //而采用图的算法，将路径上的体力值 看作 边的权值，于是问题变成求两点之间的最短距离。
        int rowLength = heights.length;
        int colLength = heights[0].length;
        int[][] record = new int[rowLength][colLength];
        int row = 0;
        int col = 0;
        int limitRow;
        int limitCol;
        for (int[] ints : record) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }
        record[0][0] = 0;
        while (true) {
            limitRow = row >= rowLength ? rowLength - 1 : row;
            limitCol = col >= colLength ? colLength - 1 : col;
            for (int i = 0; i <= limitCol; i++) {
                if (limitRow - 1 > -1) {
                    record[limitRow][i] = Math.min(record[limitRow][i], Math.max(Math.abs(heights[limitRow-1][i] - heights[limitRow][i]), record[limitRow-1][i]));
                }
                if (i-1 > -1) {
                    record[limitRow][i] = Math.min(record[limitRow][i], Math.max(Math.abs(heights[limitRow][i-1] - heights[limitRow][i]), record[limitRow][i-1]));
                }
            }
            for (int i = 0; i <= limitRow; i++) {
                if (limitCol - 1 > -1) {
                    record[i][limitCol] = Math.min(record[i][limitCol], Math.max(Math.abs(heights[i][limitCol] - heights[i][limitCol-1]), record[i][limitCol-1]));
                }
                if (i-1 > -1) {
                    record[i][limitCol] = Math.min(record[i][limitCol], Math.max(Math.abs(heights[i][limitCol] - heights[i-1][limitCol]), record[i-1][limitCol]));
                }
            }
            ++row;
            ++col;
            if (row >= rowLength && col >= colLength) {
                break;
            }
        }
        return record[rowLength-1][colLength-1];
    }


    /**
     * 示例 1：
     * 输入：heights = {{1,2,2},{3,8,2},{5,3,5}}
     * 输出：2
     * 解释：路径 {1,3,5,3,5} 连续格子的差值绝对值最大为 2 。
     * 这条路径比路径 {1,2,2,2,5} 更优，因为另一条路径差值最大值为 3 。
     *
     * 示例 2：
     * 输入：heights = {{1,2,3},{3,8,4},{5,3,5}}
     * 输出：1
     * 解释：路径 {1,2,3,4,5} 的相邻格子差值绝对值最大为 1 ，比路径 {1,3,5,3,5} 更优。
     *
     * 示例 3：
     * 输入：heights = {{1,2,1,1,1},{1,2,1,2,1},{1,2,1,2,1},{1,2,1,2,1},{1,1,1,2,1}}
     * 输出：0
     * 解释：上图所示路径不需要消耗任何体力。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/path-with-minimum-effort
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     *
     * {{1,10,6,7,9,10,4,9}}
     * 
     * {{2,3,6,3,6,6,1,2},
     * {4,5,6,5,5,10,1,2},
     * {9,1,4,10,4,7,7,3}}
     *
     *
     * [
     * [1,2,1,1,1],
     * [1,2,1,2,1],
     * [1,2,1,2,1],
     * [1,2,1,2,1],
     * [1,1,1,2,1]
     * ]
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new N_1631OneThousandSixHundredThirtyOne().minimumEffortPath(
                new int[][] {{1,2,1,1,1},{1,2,1,2,1},{1,2,1,2,1},{1,2,1,2,1},{1,1,1,2,1}}
        ));
    }
}
