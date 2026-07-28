package letcode.lcp;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/9/20 9:00
 * description 桌上有 n 堆力扣币，每堆的数量保存在数组 coins 中。我们每次可以选择任意一堆，拿走其中的一枚或者两枚，求拿完所有力扣币的最少次数。
 */
public class _6 {

    public int minCount(int[] coins) {
        int ans = 0;
        for (int coin : coins) {
            ans += (coin + 1) >>> 1;
        }
        return ans;
        //上面的方法更快
        //return Arrays.stream(coins).map(coin -> (coin + 1) >>> 1).sum();
    }

}
