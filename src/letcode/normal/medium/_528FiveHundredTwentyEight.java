package letcode.normal.medium;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * 给定一个正整数数组w ，其中w[i]代表下标 i的权重（下标从 0 开始），请写一个函数pickIndex，它可以随机地获取下标 i，选取下标 i的概率与w[i]成正比。
 * 例如，对于 w = [1, 3]，挑选下标 0 的概率为 1 / (1 + 3)= 0.25 （即，25%），而选取下标 1 的概率为 3 / (1 + 3)= 0.75（即，75%）。
 * 也就是说，选取下标 i 的概率为 w[i] / sum(w) 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/random-pick-with-weight 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author CaiYongcheng
 * @date 2021-08-30 09:06
 **/
public class _528FiveHundredTwentyEight {

    BigDecimal[] probabilities;
    int seem;

    public _528FiveHundredTwentyEight(int[] w) {
        seem = Arrays.stream(w).sum();
        probabilities = new BigDecimal[w.length];
        probabilities[0] = new BigDecimal(w[0]).divide(new BigDecimal(seem), 10, BigDecimal.ROUND_HALF_DOWN);
        for (int i = 1; i < w.length; i++) {
            probabilities[i] = new BigDecimal(w[i]).divide(new BigDecimal(seem), 10, BigDecimal.ROUND_HALF_DOWN).add(probabilities[i-1]);
        }
    }

    public int pickIndex() {
        BigDecimal probability = new BigDecimal(System.currentTimeMillis() * Math.random() % seem / seem);
        int left = 0;
        int right = probabilities.length - 1;
        int mid = 0;
        if (probability.compareTo(probabilities[left]) <= 0) {
            return left;
        }
        while (left != right) {
            mid = (left + right) >> 1;
            if (mid == left) {
                return right;
            }
            int compare = probability.compareTo(probabilities[mid]);
            if (compare > 0) {
                left = mid;
            } else if (compare < 0) {
                right = mid;
            } else {
                return mid;
            }
        }
        return mid;
    }


    /**
     * 示例 1：
     * 输入：
     * ["Solution","pickIndex"]
     * [[[1]],[]]
     * 输出：
     * [null,0]
     * 解释：
     * Solution solution = new Solution([1]);
     * solution.pickIndex(); // 返回 0，因为数组中只有一个元素，所以唯一的选择是返回下标 0。
     *
     * 示例 2：
     * 输入：
     * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
     * [[[1,3]],[],[],[],[],[]]
     * 输出：
     * [null,1,1,1,1,0]
     * 解释：
     * Solution solution = new Solution([1, 3]);
     * solution.pickIndex(); // 返回 1，返回下标 1，返回该下标概率为 3/4 。
     * solution.pickIndex(); // 返回 1
     * solution.pickIndex(); // 返回 1
     * solution.pickIndex(); // 返回 1
     * solution.pickIndex(); // 返回 0，返回下标 0，返回该下标概率为 1/4 。
     *
     * 由于这是一个随机问题，允许多个答案，因此下列输出都可以被认为是正确的:
     * [null,1,1,1,1,0]
     * [null,1,1,1,1,1]
     * [null,1,1,1,0,0]
     * [null,1,1,1,0,1]
     * [null,1,0,1,0,0]
     * ......
     * 诸若此类。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/random-pick-with-weight
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        _528FiveHundredTwentyEight test = new _528FiveHundredTwentyEight(new int[]{
                4,2
        });
        System.out.println(test.pickIndex());
        System.out.println(test.pickIndex());
        System.out.println(test.pickIndex());
        System.out.println(test.pickIndex());
        System.out.println(test.pickIndex());
    }
    
    
}
