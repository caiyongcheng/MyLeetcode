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

package letcode.normal.easy;

import letcode.utils.FormatUtils;

/**
 * @program: MyLeetcode
 * @description: 「无零整数」是十进制表示中 不含任何 0的正整数。
 * 给你一个整数n，请你返回一个 由两个整数组成的列表 [A, B]，满足：
 * A 和 B都是无零整数 A + B = n 题目数据保证至少有一个有效的解决方案。
 * 如果存在多个有效解决方案，你可以返回其中任意一个。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/convert-integer-to-the-sum-of-two-no-zero-integers 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.easy
 * @author: 6JSh5rC456iL
 * @since: 2021-03-03 17:21
 **/
public class _1317 {


    public int[] getNoZeroIntegers(int n) {
        int[] ans = new int[2];
        int exp = 1;
        int modolNum = 0;
        while (n > 9) {
            modolNum = n % 10;
            if (modolNum < 2) {
                modolNum += 10;
                n -= 10;
                ans[0] += 2 * exp;
                ans[1] += (modolNum-2) * exp;
            }else {
                ans[0] += exp;
                ans[1] += (modolNum-1) * exp;
            }
            exp *= 10;
            n /= 10;
        }
        if (n > 0) {
            ans[0] += exp;
            ans[1] += (n-1) * exp;
        }
        return ans;
    }

    /**
     * 示例 1：
     * 输入：n = 2
     * 输出：[1,1]
     * 解释：A = 1, B = 1. A + B = n 并且 A 和 B 的十进制表示形式都不包含任何 0 。
     *
     * 示例 2：
     * 输入：n = 11
     * 输出：[2,9]
     *
     * 示例 3：
     * 输入：n = 10000
     * 输出：[1,9999]
     *
     * 示例 4：
     * 输入：n = 69
     * 输出：[1,68]
     *
     * 示例 5：
     * 输入：n = 1010
     * 输出：[11,999]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/convert-integer-to-the-sum-of-two-no-zero-integers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(FormatUtils.formatArray(new _1317().getNoZeroIntegers(1501)));
    }

}
