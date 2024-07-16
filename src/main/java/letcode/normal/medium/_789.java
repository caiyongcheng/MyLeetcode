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
 * 你在进行一个简化版的吃豆人游戏。你从 [0, 0] 点开始出发，你的目的地是target = [xtarget, ytarget] 。
 * 地图上有一些阻碍者，以数组 ghosts 给出，第 i 个阻碍者从ghosts[i] = [xi, yi]出发。所有输入均为 整数坐标 。
 * 每一回合，你和阻碍者们可以同时向东，西，南，北四个方向移动，每次可以移动到距离原位置 1 个单位 的新位置。当然，也可以选择 不动 。所有动作 同时 发生。
 * 如果你可以在任何阻碍者抓住你 之前 到达目的地（阻碍者可以采取任意行动方式），则被视为逃脱成功。如果你和阻碍者同时到达了一个位置（包括目的地）都不算是逃脱成功。
 * 只有在你有可能成功逃脱时，输出 true ；否则，输出 false 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/escape-the-ghosts 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-08-22 10:14
 **/
public class _789 {

    
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        /*
         * 位置在(x,y)的的阻碍者到达target的距离为abs(x-xtarget)+abs(y-ytarget)，也就是在
         * 最小的abs(x-xtarget)+abs(y-ytarget)之前必须到达目的地。
         * 换句话说,可以看成是豆豆人和阻碍者一起向目的地跑去，谁先到谁就赢。
         */
        int standard = Math.abs(target[0]) + Math.abs(target[1]);
        for (int[] ghost : ghosts) {
            if (Math.abs(ghost[0] - target[0]) + Math.abs(ghost[1] - target[1]) <= standard) {
                return false;
            }
        }
        return true;
    }

    /**
     * 示例 1：
     * 输入：ghosts = {{1,0},{0,3}}, target = {0,1}
     * 输出：true
     * 解释：你可以直接一步到达目的地 (0,1) ，在 (1, 0) 或者 (0, 3) 位置的阻碍者都不可能抓住你。 
     * 
     * 示例 2：
     * 输入：ghosts = {{1,0}}, target = {2,0}
     * 输出：false
     * 解释：你需要走到位于 (2, 0) 的目的地，但是在 (1, 0) 的阻碍者位于你和目的地之间。 
     * 
     * 示例 3：
     * 输入：ghosts = {{2,0}}, target = {1,0}
     * 输出：false
     * 解释：阻碍者可以和你同时达到目的地。 
     * 
     * 示例 4：
     * 输入：ghosts = {{5,0},{-10,-2},{0,-5},{-2,-2},{-7,1}}, target = {7,7}
     * 输出：false
     * 
     * 示例 5：
     * 输入：ghosts = {{-1,0},{0,1},{-1,0},{0,1},{-1,0}}, target = {0,0}
     * 输出：true
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/escape-the-ghosts
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _789().escapeGhosts(
                new int[][]{{-1,0},{0,1},{-1,0},{0,1},{-1,0}},
                new int[]{0,0}
        ));
    }
    
}
