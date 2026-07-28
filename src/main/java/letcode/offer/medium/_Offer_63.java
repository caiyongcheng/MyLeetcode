package letcode.offer.medium;

/**
 * @program: Leetcode
 * @description: 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
 * @author: 蔡永程
 * @create: 2020-11-19 17:31
 */
public class _Offer_63 {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int maxVaule = 0;
        int[] maxPrices = new int[prices.length];
        maxPrices[prices.length - 1] = prices[prices.length - 1];
        for (int i = prices.length - 2; i > -1; --i) {
            if (prices[i] > maxPrices[i + 1]) {
                maxPrices[i] = prices[i];
            } else {
                maxPrices[i] = maxPrices[i + 1];
            }
            if (maxVaule < maxPrices[i + 1] - prices[i]) {
                maxVaule = maxPrices[i + 1] - prices[i];
            }
        }
        return maxVaule;
    }
}
