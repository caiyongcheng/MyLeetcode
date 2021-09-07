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
