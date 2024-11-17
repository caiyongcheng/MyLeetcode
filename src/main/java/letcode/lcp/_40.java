package letcode.lcp;

import letcode.utils.TestUtil;

import java.util.Arrays;

/**
 * 「力扣挑战赛」心算项目的挑战比赛中，要求选手从 N 张卡牌中选出 cnt 张卡牌，若这 cnt 张卡牌数字总和为偶数，则选手成绩「有效」且得分为 cnt 张卡牌数字总和。
 * 给定数组 cards 和 cnt，其中 cards[i] 表示第 i 张卡牌上的数字。 请帮参赛选手计算最大的有效得分。若不存在获取有效得分的卡牌方案，则返回 0。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-08-01 10:04
 */
public class _40 {

    public int maxmiumScore(int[] cards, int cnt) {
        Arrays.sort(cards);
        int ans = 0;
        for (int i = cards.length - cnt; i < cards.length; i++) {
            ans += cards[i];
        }
        if ((ans & 1) == 0) {
            return ans;
        }
        int sum = ans;
        ans = 0;
        int startIdx = cards.length - cnt;
        int endEven = search(cards, startIdx, 1, 0);
        int startOdd = search(cards, startIdx - 1, -1, 1);
        if (endEven > 0 && startOdd > 0) {
            ans = Math.max(ans, sum - endEven + startOdd);
        }
        int endOdd = search(cards, startIdx, 1, 1);
        int startEven = search(cards, startIdx - 1, -1, 0);
        if (startEven > 0 && endOdd > 0) {
            ans = Math.max(ans, sum - endOdd + startEven);
        }
        return ans;
    }

    private int search(int[] cards, int startIdx, int step, int isOdd) {
        while (startIdx > -1 && startIdx < cards.length) {
            if ((cards[startIdx] & 1) == isOdd) {
                return cards[startIdx];
            }
            startIdx += step;
        }
        return -1;
    }

    /**
     * 示例 1：
     *
     * 输入：cards = [1,2,8,9], cnt = 3
     *
     * 输出：18
     *
     * 解释：选择数字为 1、8、9 的这三张卡牌，此时可获得最大的有效得分 1+8+9=18。
     *
     * 示例 2：
     *
     * 输入：cards = [3,3,1], cnt = 1
     *
     * 输出：0
     *
     * 解释：不存在获取有效得分的卡牌方案。
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test(_40.class, "=[7,4,1], =1");
    }

}
