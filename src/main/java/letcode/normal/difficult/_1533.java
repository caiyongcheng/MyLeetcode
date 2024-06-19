package letcode.normal.difficult;

import java.util.HashMap;
import java.util.Map;

/**
 * 厨房里总共有 n 个橘子，你决定每一天选择如下方式之一吃这些橘子：
 * 吃掉一个橘子。
 * 如果剩余橘子数 n 能被 2 整除，那么你可以吃掉 n/2 个橘子。
 * 如果剩余橘子数 n 能被 3 整除，那么你可以吃掉 2*(n/3) 个橘子。 每天你只能从以上 3 种方案中选择一种方案。
 * 请你返回吃掉所有 n 个橘子的最少天数。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/5/14 16:45
 */
public class _1533 {

    public int minDays(int n) {
        /*
        初始思路是穷举
        但是考虑到操作1的收益是最小的 所以尽量使用操作2或者3代替
        那么当 当前数字不满足条件2、3时候 使用操作1使其满足条件

        那么按照数据规模也只需要迭代不到64次即可 加上记忆化可以减少重复搜索
        实际上需要记忆化的点很少 将其看成图的话 就变成求图的最短路径
        在搜索过程中 不停减枝即可
         */
        HashMap<Integer, Integer> cache = new HashMap<>(128);
        cache.put(0, 0);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 2);
        cache.put(4, 3);
        cache.put(5, 4);
        return search(n, cache);
    }

    public int search(int n, Map<Integer, Integer> cache) {
        Integer nOptCnt = cache.get(n);
        if (nOptCnt != null) {
            return nOptCnt;
        }

        Integer n2OptCnt = cache.get(n / 2);
        if (n2OptCnt == null) {
            n2OptCnt = search(n / 2, cache);
            cache.put(n / 2, n2OptCnt);
        }
        Integer n3OptCnt = cache.get(n / 3);
        if (n3OptCnt == null) {
            n3OptCnt = search(n / 3, cache);
            cache.put(n / 3, n3OptCnt);
        }

        nOptCnt = Math.min(n % 2 + n2OptCnt, n % 3 + n3OptCnt) + 1;
        cache.put(n, nOptCnt);
        return nOptCnt;
    }


    /**
     * 示例 1：
     *
     * 输入：n = 10
     * 输出：4
     * 解释：你总共有 10 个橘子。
     * 第 1 天：吃 1 个橘子，剩余橘子数 10 - 1 = 9。
     * 第 2 天：吃 6 个橘子，剩余橘子数 9 - 2*(9/3) = 9 - 6 = 3。（9 可以被 3 整除）
     * 第 3 天：吃 2 个橘子，剩余橘子数 3 - 2*(3/3) = 3 - 2 = 1。
     * 第 4 天：吃掉最后 1 个橘子，剩余橘子数 1 - 1 = 0。
     * 你需要至少 4 天吃掉 10 个橘子。
     * 示例 2：
     *
     * 输入：n = 6
     * 输出：3
     * 解释：你总共有 6 个橘子。
     * 第 1 天：吃 3 个橘子，剩余橘子数 6 - 6/2 = 6 - 3 = 3。（6 可以被 2 整除）
     * 第 2 天：吃 2 个橘子，剩余橘子数 3 - 2*(3/3) = 3 - 2 = 1。（3 可以被 3 整除）
     * 第 3 天：吃掉剩余 1 个橘子，剩余橘子数 1 - 1 = 0。
     * 你至少需要 3 天吃掉 6 个橘子。
     * 示例 3：
     *
     * 输入：n = 1
     * 输出：1
     * 示例 4：
     *
     * 输入：n = 56
     * 输出：6
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1533().minDays(
                2000000000
        ));
    }


}
