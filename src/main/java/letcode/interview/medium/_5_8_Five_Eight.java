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

package interview.medium;

import java.util.Arrays;

/**
 * @program: Leetcode
 * @description: 绘制直线。有个单色屏幕存储在一个一维数组中，
 * 使得32个连续像素可以存放在一个 int 里。屏幕宽度为w，且w可被32整除（即一个 int 不会分布在两行上），
 * 屏幕高度可由数组长度及屏幕宽度推算得出。请实现一个函数，绘制从点(x1, y)到点(x2, y)的水平线。
 * 给出数组的长度 length，宽度 w（以比特为单位）、直线开始位置 x1（比特为单位）、直线结束位置 x2（比特为单位）、直线所在行数y。返回绘制过后的数组。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/draw-line-lcci 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-12-10 14:23
 */
public class _5_8_Five_Eight {


    /**
     * 示例1:
     * 输入：length = 1, w = 32, x1 = 30, x2 = 31, y = 0
     * 输出：[3]
     * 说明：在第0行的第30位到第31为画一条直线，屏幕表示为[0b000000000000000000000000000000011]
     * 示例2:
     * 输入：length = 3, w = 96, x1 = 0, x2 = 95, y = 0
     * 输出：[-1, -1, -1]
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/draw-line-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new _5_8_Five_Eight().drawLine(3, 96, 0, 95, 0)));
    }

    private int getIntForBinaryStr(int startInex, int endIndex) {
        int b = 1 << (31 - endIndex);
        int resultInt = b;
        while (endIndex > startInex) {
            b = b << 1;
            resultInt += b;
            --endIndex;
        }
        return resultInt;
    }

    public int[] drawLine(int length, int w, int x1, int x2, int y) {
        int[] result = new int[length];
        //行高、列宽 以int表示
        int widthInt = w / 32;
        //计算x1所在int
        int xInt1 = y * widthInt + x1 / 32;
        int xInt2 = y * widthInt + x2 / 32;
        if (xInt1 == xInt2) {
            result[xInt1] = getIntForBinaryStr(x1 % 32, x2 % 32);
            return result;
        }
        result[xInt1] = getIntForBinaryStr(x1 % 32, 31);
        //计算x2所在int
        result[xInt2] = getIntForBinaryStr(0, x2 % 32);
        ++xInt1;
        //计算中间int
        while (xInt1 < xInt2) {
            result[xInt1] = -1;
            ++xInt1;
        }
        return result;
    }
}