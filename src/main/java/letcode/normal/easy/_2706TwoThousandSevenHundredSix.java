package letcode.normal.easy;

/**
 * 给你一个整数数组 prices ，它表示一个商店里若干巧克力的价格。同时给你一个整数 money ，表示你一开始拥有的钱数。
 * 你必须购买 恰好 两块巧克力，而且剩余的钱数必须是 非负数 。同时你想最小化购买两块巧克力的总花费。
 * 请你返回在购买两块巧克力后，最多能剩下多少钱。如果购买任意两块巧克力都超过了你拥有的钱，请你返回 money 。注意剩余钱数必须是非负数。
 *
 * 提示：
 *
 * 2 <= prices.length <= 50
 * 1 <= prices[i] <= 100
 * 1 <= money <= 100
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2023/12/29 09:59
 */
public class _2706TwoThousandSevenHundredSix {

    public int buyChoco(int[] prices, int money) {
        int min1 = 101;
        int min2 = 101;
        for (int price : prices) {
            if (price <= min1) {
                min2 = min1;
                min1 = price;
                continue;
            }
            if (price < min2) {
                min2 = price;
            }
        }
        return min1 + min2 > money ? money : money - min1 - min2;
    }

}
