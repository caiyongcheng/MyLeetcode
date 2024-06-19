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
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * 假设 力扣（LeetCode）即将开始 IPO 。为了以更高的价格将股票卖给风险投资公司，力扣 希望在 IPO 之前开展一些项目以增加其资本。
 * 由于资源有限，它只能在 IPO 之前完成最多 k 个不同的项目。
 * 帮助 力扣 设计完成最多 k 个不同项目后得到最大总资本的方式。
 * 给你 n 个项目。对于每个项目 i ，它都有一个纯利润 profits[i] ，和启动该项目需要的最小资本 capital[i] 。
 * 最初，你的资本为 w 。当你完成一个项目时，你将获得纯利润，且利润将被添加到你的总资本中。
 * 总而言之，从给定项目中选择 最多 k 个不同项目的列表，以 最大化最终资本 ，并输出最终可获得的最多资本。
 * 答案保证在 32 位有符号整数范围内。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/ipo 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-09-08 09:03
 **/
public class _502 {


    /**
     * 示例 1：
     * <p>
     * 输入：k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
     * 输出：4
     * 解释：
     * 由于你的初始资本为 0，你仅可以从 0 号项目开始。
     * 在完成后，你将获得 1 的利润，你的总资本将变为 1。
     * 此时你可以选择开始 1 号或 2 号项目。
     * 由于你最多可以选择两个项目，所以你需要完成 2 号项目以获得最大的资本。
     * 因此，输出最后最大化的资本，为 0 + 1 + 3 = 4。
     * <p>
     * 示例 2：
     * <p>
     * 输入：k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
     * 输出：6
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/ipo
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _502().findMaximizedCapital(
                10, 0,
                new int[]{1, 2, 3},
                new int[]{0, 1, 2}
        ));
    }

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        /*
        因为总资本是在增加的，所以适用贪心。
        每次找到符合资本条件的项目范围，从中选择纯利润最大的项目来完成。
        所以对数据按照总资本排序，同时维护一个当前符合资本范围的优先队列。
         */
        int length = profits.length;
        Proj[] projs = new Proj[length];
        TreeSet<Proj> treeSet = new TreeSet<>((p1, p2) -> -Integer.compare(p1.profit, p2.profit));
        Iterator<Proj> iterator;
        for (int i = 0; i < projs.length; i++) {
            projs[i] = new Proj(profits[i], capital[i], i);
        }
        Arrays.sort(projs, Comparator.comparingInt(p -> p.capital));
        int limit = 0;
        int start = 0;
        for (int i = 0; i < k; i++) {
            limit = serarch(projs, w, start);
            if (limit > -1) {
                while (start <= limit) {
                    treeSet.add(projs[start++]);
                }
            }
            iterator = treeSet.iterator();
            if (iterator.hasNext()) {
                Proj next = iterator.next();
                w += next.profit;
                treeSet.remove(next);
            }
            start = limit + 1;
        }
        return w;
    }


    public int serarch(Proj[] projs, int capital, int left) {
        //循环不变式 保证 projs[left].profit <= profit < projs[right].profit
        int right = projs.length - 1;
        if (left < 0 || left > right || projs[left].capital > capital) {
            return Integer.MIN_VALUE;
        }
        if (projs[right].capital <= capital) {
            return right;
        }
        int mid;
        while (left < right) {
            mid = (left + right) >> 1;
            if (mid == left) {
                while (mid + 1 < right && projs[mid].capital == projs[mid + 1].capital) {
                    ++mid;
                }
                return mid;
            }
            if (projs[mid].capital <= capital) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    class Proj {
        public int profit;
        public int capital;
        public int index;

        public Proj(int profit, int capital, int index) {
            this.profit = profit;
            this.capital = capital;
            this.index = index;
        }

        @Override
        public boolean equals(Object obj) {
            Proj obj1 = (Proj) obj;
            return index == obj1.index;
        }

        @Override
        public int hashCode() {
            return index;
        }
    }


}




