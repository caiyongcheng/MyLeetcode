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
 * 总共有 n个颜色片段排成一列，每个颜色片段要么是'A'要么是'B'。给你一个长度为n的字符串colors，
 * 其中colors[i]表示第i个颜色片段的颜色。  Alice 和 Bob 在玩一个游戏，他们 轮流从这个字符串中删除颜色。Alice 先手。 
 * 如果一个颜色片段为 'A'且 相邻两个颜色都是颜色 'A'，那么 Alice 可以删除该颜色片段。
 * Alice不可以删除任何颜色'B'片段。 如果一个颜色片段为 'B'且 相邻两个颜色都是颜色 'B'，那么 Bob 可以删除该颜色片段。
 * Bob 不可以删除任何颜色 'A'片段。 Alice 和 Bob 不能从字符串两端删除颜色片段。 
 * 如果其中一人无法继续操作，则该玩家 输掉游戏且另一玩家 获胜。 假设 Alice 和 Bob 都采用最优策略，如果 Alice 获胜，请返回true，否则 Bob 获胜，返回false。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/remove-colored-pieces-if-both-neighbors-are-the-same-color 
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-03-22 09:07
 **/
public class _2038 {

    public boolean winnerOfGame(String colors) {
        /*
        由题意可知只有三个及以上的连续相同字符才可以进行删除，这些意味着连续字符最多删除除了首尾两个字符外的所有字符。那么就不会出现因为某
        个连续字符被删除导致其他不同颜色的字符合在一起的情况。所以题目实际上等价于判断谁可以删除的连续字符多。
         */
        int sequenceA = 0;
        int sequenceB = 0;
        int oi = 0;
        int ii = 0;
        char ch;
        while (oi < colors.length()) {
            ch = colors.charAt(oi);
            for (ii = oi + 1; ii < colors.length(); ++ii) {
                if (colors.charAt(ii) != ch) {
                    break;
                }
            }
            if (ii - oi > 2) {
                if (ch == 'A') {
                    sequenceA += ii - oi - 2;
                } else {
                    sequenceB += ii - oi - 2;
                }
            }
            oi = ii;
        }
        return sequenceA > sequenceB;
    }


    /**
     * 示例 1：
     *
     * 输入：colors = "AAABABB"
     * 输出：true
     * 解释：
     * AAABABB -> AABABB
     * Alice 先操作。
     * 她删除从左数第二个 'A' ，这也是唯一一个相邻颜色片段都是 'A' 的 'A' 。
     *
     * 现在轮到 Bob 操作。
     * Bob 无法执行任何操作，因为没有相邻位置都是 'B' 的颜色片段 'B' 。
     * 因此，Alice 获胜，返回 true 。
     * 示例 2：
     *
     * 输入：colors = "AA"
     * 输出：false
     * 解释：
     * Alice 先操作。
     * 只有 2 个 'A' 且它们都在字符串的两端，所以她无法执行任何操作。
     * 因此，Bob 获胜，返回 false 。
     * 示例 3：
     *
     * 输入：colors = "ABBBBBBBAAA"
     * 输出：false
     * 解释：
     * ABBBBBBBAAA -> ABBBBBBBAA
     * Alice 先操作。
     * 她唯一的选择是删除从右数起第二个 'A' 。
     *
     * ABBBBBBBAA -> ABBBBBBAA
     * 接下来轮到 Bob 操作。
     * 他有许多选择，他可以选择任何一个 'B' 删除。
     *
     * 然后轮到 Alice 操作，她无法删除任何片段。
     * 所以 Bob 获胜，返回 false 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-colored-pieces-if-both-neighbors-are-the-same-color
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2038().winnerOfGame(
                "AAAABBBB"
        ));
    }


}
