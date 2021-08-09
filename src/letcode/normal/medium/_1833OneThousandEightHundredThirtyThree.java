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
 * @date 2021-07-30 15:06
 **/
public class _1833OneThousandEightHundredThirtyThree {

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
        System.out.println(new _1833OneThousandEightHundredThirtyThree().maxIceCream(
                new int[]{7,3,3,6,6,6,10,5,9,2},
                56
        ));
    }


}
