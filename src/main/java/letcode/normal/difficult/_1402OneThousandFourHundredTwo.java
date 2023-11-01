package letcode.normal.difficult;

import java.util.Arrays;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/10/23 10:58
 * description 一个厨师收集了他 n 道菜的满意程度 satisfaction ，这个厨师做出每道菜的时间都是 1 单位时间。
 * 一道菜的 「 like-time 系数 」定义为烹饪这道菜结束的时间（包含之前每道菜所花费的时间）乘以这道菜的满意程度，也就是 time[i]*satisfaction[i] 。
 * 返回厨师在准备了一定数量的菜肴后可以获得的最大 like-time 系数 总和。
 * 你可以按 任意 顺序安排做菜的顺序，你也可以选择放弃做某些菜来获得更大的总和。
 */
public class _1402OneThousandFourHundredTwo {

    public int maxSatisfaction(int[] satisfaction) {
        /*
        排序 先计算正数 贪心
        然后按从小到达的顺序不停的在首部插入负数
        如果插入负数后 like-time 系数 不增加 那么就停止 也就是当前数列的和+该负数 <= 0
        因为此时再插入负数 原序列整体向右移动一位 整体值会变小 再加上插入的负数 值会更小
         */
        Arrays.sort(satisfaction);
        // 一道菜都不做
        if (satisfaction[satisfaction.length - 1] <= 0) {
            return 0;
        }
        // 其实可以省略 把底下循环的两次操作拆出来即可 省略不省略对时间复杂度影响不大
        int firstPosNumIdx = findFirstPosNumIdx(satisfaction);
        int ans = 0;
        int curSum = 0;
        for (int posNumIdx = firstPosNumIdx; posNumIdx < satisfaction.length; posNumIdx++) {
            curSum += satisfaction[posNumIdx];
            ans += satisfaction[posNumIdx] * (posNumIdx - firstPosNumIdx + 1);
        }
        for (int negNumIdx = firstPosNumIdx - 1; negNumIdx >= 0; negNumIdx--) {
            if (satisfaction[negNumIdx] + curSum > 0) {
                curSum += satisfaction[negNumIdx];
                ans += curSum;
            } else {
                return ans;
            }
        }
        return ans;
    }


    public int findFirstPosNumIdx(int[] satisfaction) {
        if (satisfaction[0] >= 0) {
            return 0;
        }
        int left = 0;
        int right = satisfaction.length - 1;
        int mid;
        while (right > left) {
            mid = (right + left) >>> 1;
            if (mid == left) {
                return right;
            }
            if (satisfaction[mid] >= 0) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }


    /**
     * 示例 1：
     *
     * 输入：satisfaction = new int []{-1,-8,0,5,-9}
     * 输出：14
     * 解释：去掉第二道和最后一道菜，最大的 like-time 系数和为 (-1*1 + 0*2 + 5*3 = 14) 。每道菜都需要花费 1 单位时间完成。
     * 示例 2：
     *
     * 输入：satisfaction = new int []{4,3,2}
     * 输出：20
     * 解释：可以按照任意顺序做菜 (2*1 + 3*2 + 4*3 = 20)
     * 示例 3：
     *
     * 输入：satisfaction = new int []{-1,-4,-5}
     * 输出：0
     * 解释：大家都不喜欢这些菜，所以不做任何菜就可以获得最大的 like-time 系数。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1402OneThousandFourHundredTwo().maxSatisfaction(
                new int []{-1,-4,-5}
        ));
    }

}
