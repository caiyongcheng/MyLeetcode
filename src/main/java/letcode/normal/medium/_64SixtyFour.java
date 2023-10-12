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
     * [1,3,1],
     * [1,5,1],
     * [4,2,1]
     * ]
     * 输出: 7
     *
     * @param grid
     * @return
     */
    public static int minPathSum(int[][] grid) {
        int y_length = grid.length;
        int x_length = grid[0].length;
        int minN = Integer.MAX_VALUE;
        for (int y = 0; y < y_length; ++y) {
            for (int x = 0; x < x_length; ++x) {
                minN = Integer.MAX_VALUE;
                if (y - 1 > -1 && grid[y - 1][x] < minN) {
                    minN = grid[y - 1][x];
                }
                if (x - 1 > -1 && grid[y][x - 1] < minN) {
                    minN = grid[y][x - 1];
                }
                if (minN != Integer.MAX_VALUE) {
                    grid[y][x] += minN;
                }
            }
        }
        for (int[] ints : grid) {
            System.out.println(Arrays.toString(ints));
        }
        return grid[y_length - 1][x_length - 1];
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
                {1, 2, 5},
                {1, 3, 1},
        }));
    }

}
