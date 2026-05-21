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

}
