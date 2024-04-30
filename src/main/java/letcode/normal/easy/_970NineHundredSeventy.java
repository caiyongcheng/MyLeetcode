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

package normal.easy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @program: Leetcode
 * @description: 强整数
 * @author: 蔡永程
 * @create: 2020-11-19 11:01
 */
public class _970NineHundredSeventy {

    /**
     * 示例 1：
     * 输入：x = 2, y = 3, bound = 10
     * 输出：[2,3,4,5,7,9,10]
     * 解释：
     * 2 = 2^0 + 3^0
     * 3 = 2^1 + 3^0
     * 4 = 2^0 + 3^1
     * 5 = 2^1 + 3^1
     * 7 = 2^2 + 3^1
     * 9 = 2^3 + 3^0
     * 10 = 2^0 + 3^2
     * 示例 2：
     * 输入：x = 3, y = 5, bound = 15
     * 输出：[2,4,6,8,10,14]
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/powerful-integers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        List<Integer> integers = new _970NineHundredSeventy().powerfulIntegers(1, 1, 3);
        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }

    /**
     * 给定两个正整数 x 和 y，如果某一整数等于 x^i + y^j，其中整数i >= 0 且j >= 0，那么我们认为该整数是一个强整数。
     * 返回值小于或等于bound的所有强整数组成的列表。
     * 你可以按任何顺序返回答案。在你的回答中，每个值最多出现一次
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/powerful-integers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param x
     * @param y
     * @param bound
     * @return
     */

    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        HashSet<Integer> result = new HashSet<>();
        int xx = 1;
        int yy = 1;
        int xlimit = bound + 1;
        int ylimit = 0;
        if (x != 1 && y != 1) {
            while (xx < xlimit) {
                yy = 1;
                ylimit = bound - xx + 1;
                while (yy < ylimit) {
                    result.add(xx + yy);
                    yy *= y;
                }
                xx *= x;
            }
        } else {
            if (x == 1 && y == 1) {
                if (bound >= 2) {
                    result.add(2);
                }
            } else {
                x = x > y ? x : y;
                y = 1;
                xlimit += 1;
                while (xx < xlimit) {
                    result.add(xx + 1);
                    xx *= x;
                }
            }
        }
        return new ArrayList<>(result);
    }
}