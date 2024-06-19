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

import java.util.LinkedList;

/**
 * n 张多米诺骨牌排成一行，将每张多米诺骨牌垂直竖立。在开始时，同时把一些多米诺骨牌向左或向右推。
 * 每过一秒，倒向左边的多米诺骨牌会推动其左侧相邻的多米诺骨牌。同样地，倒向右边的多米诺骨牌也会推动竖立在其右侧的相邻多米诺骨牌。
 * 如果一张垂直竖立的多米诺骨牌的两侧同时有多米诺骨牌倒下时，由于受力平衡， 该骨牌仍然保持不变。
 * 就这个问题而言，我们会认为一张正在倒下的多米诺骨牌不会对其它正在倒下或已经倒下的多米诺骨牌施加额外的力。
 * 给你一个字符串 dominoes 表示这一行多米诺骨牌的初始状态，其中：  dominoes[i] = 'L'，表示第 i 张多米诺骨牌被推向左侧，
 * dominoes[i] = 'R'，表示第 i 张多米诺骨牌被推向右侧， dominoes[i] = '.'，表示没有推动第 i 张多米诺骨牌。 返回表示最终状态的字符串。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/push-dominoes 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2022-02-21 09:12
 **/
public class _838 {

    public String pushDominoes(String dominoes) {
        /*
         * 根据题意（就这个问题而言，我们会认为一张正在倒下的多米诺骨牌不会对其它正在倒下或已经倒下的多米诺骨牌施加额外的力。）
         * 对于每张多米诺骨牌而言，是不动，还是向左或者向右由 左右两个力决定。维护两个状态，
         * 向左的力以及最早受力时间，向右的力以及最早受力时间。根据状态去判断。
         * 采用拓扑排序的方法即可。
         */
        int length = dominoes.length();
        int[][] states = new int[length][2];
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            states[i][0] = Integer.MAX_VALUE - 3;
            states[i][1] = Integer.MAX_VALUE - 3;
            if (dominoes.charAt(i) == '.') {
                continue;
            }
            if (dominoes.charAt(i) == 'L') {
                states[i][0] = 1;
            } else {
                states[i][1] = 1;
            }
            list.addLast(i);
        }
        while (!list.isEmpty()) {
            Integer nowIndex = list.removeFirst();
            if (states[nowIndex][0] == states[nowIndex][1]) {
                continue;
            }
            if (states[nowIndex][0] < states[nowIndex][1]) {
                if (nowIndex - 1 > -1 && states[nowIndex - 1][0] > states[nowIndex][0] + 1) {
                    states[nowIndex - 1][0] = states[nowIndex][0] + 1;
                    list.addLast(nowIndex - 1);
                }
            } else {
                if (nowIndex + 1 < length && states[nowIndex + 1][1] > states[nowIndex][1] + 1) {
                    states[nowIndex + 1][1] = states[nowIndex][1] + 1;
                    list.addLast(nowIndex + 1);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (states[i][0] == states[i][1]) {
                sb.append('.');
            } else if (states[i][0] < states[i][1]) {
                sb.append('L');
            } else {
                sb.append('R');
            }
        }
        return sb.toString();
    }


    /**
     * 示例 1：
     * <p>
     * 输入：dominoes = "RR.L"
     * 输出："RR.L"
     * 解释：第一张多米诺骨牌没有给第二张施加额外的力。
     * 示例 2：
     * <p>
     * <p>
     * 输入：dominoes = ".L.R...LR..L.."
     * 输出："LL.RR.LLRRLL.."
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/push-dominoes
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _838().pushDominoes("RR.L"));
    }

}
