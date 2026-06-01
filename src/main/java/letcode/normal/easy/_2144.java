package letcode.normal.easy;

import java.util.Arrays;

/**
 * 2144. Minimum Cost of Buying Candies With Discount
 * Difficulty: Easy
 * Link: https://leetcode.cn/problems/minimum-cost-of-buying-candies-with-discount/
 * A shop is selling candies at a discount. For every two candies sold, the shop gives a third candy for free .
 * The customer can choose any candy to take away for free as long as the cost of the chosen candy is less than or equal
 * to the minimum cost of the two candies bought. - For example, if there are 4 candies with costs 1 , 2 , 3 , and 4 ,
 * and the customer buys candies with costs 2 and 3 , they c...
 */
public class _2144 {

    public int minimumCost(int[] cost) {
        int ans = 0;
        Arrays.sort(cost);
        int i;
        for (i = cost.length - 1; i > 1; i-=3) {
            ans += cost[i] + cost[i - 1];
        }
        while (i >= 0) {
            ans += cost[i];
            i--;
        }
        return ans;
    }

}
