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
import java.util.HashSet;

/**
 * @program: MyLeetcode
 * @description: 返回所有长度为 n 且满足其每两个连续位上的数字之间的差的绝对值为 k 的 非负整数 。  请注意，除了 数字 0 本身之外，答案中的每个数字都 不能 有前导零。
 * 例如，01 有一个前导零，所以是无效的；但 0是有效的。  你可以按 任何顺序 返回答案。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/numbers-with-same-consecutive-differences 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2021-01-25 14:49
 */
public class _967NineHundredSixtyseven {


    /**
     * 数字上限
     */
    private int floor;

    private HashSet<Integer> res = new HashSet<>(1 >> 11);

    private int spacing;

    /**
     * 示例 1：
     * 输入：n = 3, k = 7
     * 输出：[181,292,707,818,929]
     * 解释：注意，070 不是一个有效的数字，因为它有前导零。
     * <p>
     * 示例 2：
     * 输入：n = 2, k = 1
     * 输出：[10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
     * <p>
     * 示例 3：
     * 输入：n = 2, k = 0
     * 输出：[11,22,33,44,55,66,77,88,99]
     * <p>
     * 示例 4：
     * 输入：n = 2, k = 1
     * 输出：[10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
     * <p>
     * 示例 5：
     * 输入：n = 2, k = 2
     * 输出：[13,20,24,31,35,42,46,53,57,64,68,75,79,86,97]
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/numbers-with-same-consecutive-differences
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        final int[] ints = new _967NineHundredSixtyseven().numsSameConsecDiff(3, 7);
        Arrays.sort(ints);
        System.out.println(Arrays.toString(ints));
    }

    public void searchNum(int nowValue, int nowNum, int nowlength) {
        if (nowlength > floor) {
            return;
        }
        if (nowlength == floor) {
            res.add(nowValue);
            return;
        }
        nowNum -= spacing;
        if (nowNum > -1) {
            searchNum(nowValue * 10 + nowNum, nowNum, nowlength + 1);
        }
        nowNum = nowNum + spacing + spacing;
        if (nowNum < 10) {
            searchNum(nowValue * 10 + nowNum, nowNum, nowlength + 1);
        }
    }

    public int[] numsSameConsecDiff(int n, int k) {
        if (n == 1) {
            return k == 0 ? new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9} : new int[0];
        }
        spacing = k;
        floor = n;
        int index = 0;
        for (int i = 1; i < 10; i++) {
            searchNum(i, i, 1);
        }
        if (n == 1 && k == 0) {
            res.add(0);
        }
        final int[] resArr = new int[res.size()];
        for (Integer item : res) {
            resArr[index++] = item;
        }
        return resArr;
    }


}