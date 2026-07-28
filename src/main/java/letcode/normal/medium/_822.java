package letcode.normal.medium;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/8/2 10:37
 * description 在桌子上有 N 张卡片，每张卡片的正面和背面都写着一个正数（正面与背面上的数有可能不一样）。
 * 我们可以先翻转任意张卡片，然后选择其中一张卡片。  如果选中的那张卡片背面的数字 X 与任意一张卡片的正面的数字都不同，那么这个数字是我们想要的数字。
 * 哪个数是这些想要的数字中最小的数（找到这些数中的最小值）呢？如果没有一个数字符合要求的，输出 0。
 * 其中, fronts[i] 和 backs[i] 分别代表第 i 张卡片的正面和背面的数字。
 * 如果我们通过翻转卡片来交换正面与背面上的数，那么当初在正面的数就变成背面的数，背面的数就变成正面的数。
 */
public class _822 {


    public int flipgame(int[] fronts, int[] backs) {
        /*
        对于正反面一样数字n的牌 不管怎么反转 一定会重复
        至于其他情况 基本与正面一致 只需要反转一致的正面牌即可 因为不是正反面一样数字 所以一定不会重复
         */

        int rst = Integer.MAX_VALUE;

        //获取正反面相同的牌数值
        int[] sameNumSet = new int[2001];
        //当前反面统计
        for (int i = 0; i < fronts.length; i++) {
            if (fronts[i] == backs[i]) {
                sameNumSet[fronts[i]] = 1;
            }
        }
        //遍历
        for (int i = 0; i < fronts.length; i++) {
            //不反转当前牌
            if (backs[i] < rst && sameNumSet[backs[i]] != 1) {
                rst = backs[i];
            }
            //反转当前牌
            if (fronts[i] < rst && sameNumSet[fronts[i]] != 1) {
                rst = fronts[i];
            }
        }

        return rst == Integer.MAX_VALUE ? 0 : rst;

    }


}
