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
 * 在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。  现在，可以绘制一些连接两个数字 nums1[i]和 nums2[j]的直线，这些直线需要同时满足满足：
 * nums1[i] == nums2[j] 且绘制的直线不与任何其他连线（非水平线）相交。 请注意，连线即使在端点也不能相交：每个数字只能属于一条连线。  以这种方法绘制线条，并返回可以绘制的最大连线数。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/uncrossed-lines 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-05-21 09:14
 **/
public class _1035 {

    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int rowLen = nums1.length + 1;
        int colLen = nums2.length + 1;
        int[][] dp = new int[rowLen][colLen];
        for (int row = 1; row < rowLen; row++) {
            for (int col = 1; col < colLen; col++) {
                dp[row][col] = nums1[row-1] == nums2[col-1] ? (dp[row-1][col-1] + 1) : Math.max(dp[row-1][col], dp[row][col-1]);
            }
        }
        return dp[rowLen-1][colLen-1];
    }

    /**
     * 示例 1：
     * 输入：nums1 = [1,4,2], nums2 = [1,2,4]
     * 输出：2
     * 解释：可以画出两条不交叉的线，如上图所示。
     * 但无法画出第三条不相交的直线，因为从 nums1[1]=4 到 nums2[2]=4 的直线将与从 nums1[2]=2 到 nums2[1]=2 的直线相交。
     *
     * 示例 2：
     * 输入：nums1 = [2,5,1,2,5], nums2 = [10,5,2,1,5,2]
     * 输出：3
     *
     * 示例 3：
     * 输入：nums1 = [1,3,7,1,7,5], nums2 = [1,9,2,5,1]
     *
     * 输出：2
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/uncrossed-lines
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1035().maxUncrossedLines(
                new int[]{1,3,7,1,7,5},
                new int[]{1,9,2,5,1}
        ));
    }


}
