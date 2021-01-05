package offer.medium;

/**
 * @program: Leetcode
 * @description: 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
 * @author: 蔡永程
 * @create: 2020-11-19 17:31
 */
public class _Offer_63SixtyThree {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2){
            return 0;
        }
        int maxVaule = 0;
        int[] maxPrices = new int[prices.length];
        maxPrices[prices.length-1] = prices[prices.length-1];
        for (int i=prices.length-2; i>-1; --i){
            if (prices[i] > maxPrices[i+1]) {
                maxPrices[i] = prices[i];
            }else{
                maxPrices[i] = maxPrices[i+1];
            }
            if (maxVaule < maxPrices[i+1] - prices[i]){
                maxVaule = maxPrices[i+1] - prices[i];
            }
        }
        return maxVaule;
    }

    /**
     * 示例 1:
     * 输入: [7,1,5,3,6,4]
     * 输出: 5
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
     * 示例 2:
     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/gu-piao-de-zui-da-li-run-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _Offer_63SixtyThree().maxProfit(new int[]{7,6,4,3,1}));
    }
}