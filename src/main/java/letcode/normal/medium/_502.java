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
 * @since 2021-09-08 09:03
 **/
public class _502 {


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




