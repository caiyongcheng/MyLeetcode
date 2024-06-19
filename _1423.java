package letcode.normal.medium;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/12/3 22:17
 * description 几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。
 * 每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。  你的点数就是你拿到手中的所有卡牌的点数之和。
 * 给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。
 */
public class _1423 {

    public int maxScore(int[] cardPoints, int k) {
        int[] preSumArr = new int[k];
        int[] lastSumArr = new int[k];
        preSumArr[0] = cardPoints[0];
        for (int i = 1; i < preSumArr.length; i++) {
            preSumArr[i] = preSumArr[i - 1] + cardPoints[i];
        }
        lastSumArr[0] = cardPoints[cardPoints.length - 1];
        for (int i = 1; i < lastSumArr.length; i++) {
            lastSumArr[i] = lastSumArr[i - 1] + cardPoints[cardPoints.length - i - 1];
        }
        int ans = Integer.max(preSumArr[k - 1], lastSumArr[k - 1]);
        for (int i = 0; i < k - 1; i++) {
            ans = Integer.max(ans, preSumArr[i] + lastSumArr[k - i - 2]);
        }
        return ans;
    }

    /**
     * 示例 1：
     *
     * 输入：cardPoints = [1,2,3,4,5,6,1], k = 3
     * 输出：12
     * 解释：第一次行动，不管拿哪张牌，你的点数总是 1 。但是，先拿最右边的卡牌将会最大化你的可获得点数。最优策略是拿右边的三张牌，最终点数为 1 + 6 + 5 = 12 。
     * 示例 2：
     *
     * 输入：cardPoints = [2,2,2], k = 2
     * 输出：4
     * 解释：无论你拿起哪两张卡牌，可获得的点数总是 4 。
     * 示例 3：
     *
     * 输入：cardPoints = [9,7,7,9,7,7,9], k = 7
     * 输出：55
     * 解释：你必须拿起所有卡牌，可以获得的点数为所有卡牌的点数之和。
     * 示例 4：
     *
     * 输入：cardPoints = [1,1000,1], k = 1
     * 输出：1
     * 解释：你无法拿到中间那张卡牌，所以可以获得的最大点数为 1 。
     * 示例 5：
     *
     * 输入：cardPoints = [1,79,80,1,1,1,200,1], k = 3
     * 输出：202
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1423().maxScore(
                new int[]{10,79,80,1,1,1,200,1},
                3
        ));
    }

}
