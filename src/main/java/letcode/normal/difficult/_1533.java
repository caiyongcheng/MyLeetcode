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


}
