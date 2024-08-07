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
 * 夏日炎炎，小男孩 Tony 想买一些雪糕消消暑。
 * 商店中新到 n 支雪糕，用长度为 n 的数组 costs 表示雪糕的定价，其中 costs[i] 表示第 i 支雪糕的现金价格。Tony 一共有 coins 现金可以用于消费，他想要买尽可能多的雪糕。
 * 给你价格数组 costs 和现金量 coins ，请你计算并返回 Tony 用 coins 现金能够买到的雪糕的 最大数量 。
 * 注意：Tony 可以按任意顺序购买雪糕。
 * 1 <= n <= 10^5
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/maximum-ice-cream-bars 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-07-30 15:06
 **/
public class _1833 {

    public int maxIceCream(int[] costs, int coins) {
        /**
         * 题目看起来很像01背包，实际上注意题目要求。
         * 就会发现实际上是贪心，优先买价格最低的即可。
         * 所以就是排序 + 统计
         * n数据量不大，计数排序即可
         */
        int[] countSort = new int[10001];
        int ans = 0;
        for (int cost : costs) {
            countSort[cost]++;
        }
        for (int i = 0; i < countSort.length; i++) {
            if (i > coins) {
                break;
            }
            while (coins >= i && countSort[i] > 0) {
                coins -= i;
                --countSort[i];
                ++ans;
            }
        }
        return ans;
    }


    /**
     * 示例 1：
     * 输入：costs = [1,3,2,4,1], coins = 7
     * 输出：4
     * 解释：Tony 可以买下标为 0、1、2、4 的雪糕，总价为 1 + 3 + 2 + 1 = 7
     *
     * 示例 2：
     * 输入：costs = [10,6,8,7,7,8], coins = 5
     * 输出：0
     * 解释：Tony 没有足够的钱买任何一支雪糕。
     *
     * 示例 3：
     * 输入：costs = [1,6,3,1,2,5], coins = 20
     * 输出：6
     * 解释：Tony 可以买下所有的雪糕，总价为 1 + 6 + 3 + 1 + 2 + 5 = 18 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-ice-cream-bars
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1833().maxIceCream(
                new int[]{7,3,3,6,6,6,10,5,9,2},
                56
        ));
    }


}
