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

import java.math.BigInteger;

/**
 * 累加数 是一个字符串，组成它的数字可以形成累加序列。
 * 一个有效的 累加序列 必须 至少 包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。
 * 给你一个只包含数字'0'-'9'的字符串，编写一个算法来判断给定输入是否是 累加数 。如果是，返回 true ；否则，返回 false 。
 * 说明：累加序列里的数 不会 以 0 开头，所以不会出现1, 2, 03 或者1, 02, 3的情况。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/additive-number 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-01-10 09:21
 **/
public class _306 {

    public boolean isAdditiveNumber(String num) {
        /*
         * 不停枚举开头的两个数 进行判断
         */
        if (num.length() < 3) {
            return false;
        }
        int length = num.length();
        int firstEnd = (length - 1) >> 1;
        int scIndex;
        int tmpIndex;
        BigInteger first = BigInteger.ZERO;
        BigInteger second = BigInteger.ZERO;
        BigInteger addFirst = BigInteger.ZERO;
        BigInteger addSecond = BigInteger.ZERO;
        BigInteger ten = BigInteger.valueOf(10);
        BigInteger sum;
        for (int index = 0; index <= firstEnd; index++) {
            first = first.multiply(ten).add(BigInteger.valueOf(num.charAt(index) - '0'));
            if (index >= 1 && first.compareTo(ten) < 0) {
                break;
            }
            addFirst = first.multiply(BigInteger.ONE);
            scIndex = index + 1;
            second = BigInteger.ZERO;
            while (Math.max(index + 1, scIndex - index) <= length - scIndex - 1) {
                addFirst = first.multiply(BigInteger.ONE);
                second = second.multiply(ten).add(BigInteger.valueOf(num.charAt(scIndex) - '0'));
                if (scIndex - index >= 2 && second.compareTo(ten) < 0) {
                    break;
                }
                addSecond = second.multiply(BigInteger.ONE);
                tmpIndex = scIndex;
                sum = addFirst.add(addSecond);
                while (tmpIndex + 1 < length && startIndex(sum.toString(), num, tmpIndex + 1)) {
                    tmpIndex += sum.toString().length();
                    addFirst = addSecond;
                    addSecond = sum;
                    sum = addFirst.add(addSecond);
                }
                if (tmpIndex == length - 1) {
                    return true;
                }
                ++scIndex;
            }
        }
        return false;
    }


    public boolean startIndex(String target, String source, int startIndex) {
        if (source.length() < target.length() + startIndex) {
            return false;
        }
        int length = target.length();
        for (int index = 0; index < length; index++) {
            if (target.charAt(index) != source.charAt(startIndex + index)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 示例 1：
     * <p>
     * 输入："112358"
     * 输出：true
     * 解释：累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
     * 示例2：
     * <p>
     * 输入："199100199"
     * 输出：true
     * 解释：累加序列为: 1, 99, 100, 199。1 + 99 = 100, 99 + 100 = 199
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/additive-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _306().isAdditiveNumber(
                "111122335588143"
        ));
    }

}
