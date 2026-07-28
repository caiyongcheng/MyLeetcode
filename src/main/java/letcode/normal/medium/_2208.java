package letcode.normal.medium;

import java.math.BigDecimal;
import java.util.PriorityQueue;

/**
 * @author Caiyongcheng
 * @description 给你一个正整数数组 nums 。每一次操作中，你可以从 nums 中选择 任意 一个数并将它减小到 恰好 一半。
 * （注意，在后续操作中你可以对减半过的数继续执行操作）  请你返回将 nums 数组和 至少 减少一半的 最少 操作数
 * @since 2023/7/25 9:43
 */
public class _2208 {

    public int halveArray(int[] nums) {
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal currentSum = BigDecimal.ZERO;
        BigDecimal two = BigDecimal.valueOf(2);
        int lessOpt = 0;
        //贪心 每次选择最大的数进行缩减 所以维护一个大根堆即可
        PriorityQueue<BigDecimal> priorityQueue = new PriorityQueue<>(nums.length, (a, b) -> -a.compareTo(b));
        for (int num : nums) {
            BigDecimal numWrp = BigDecimal.valueOf(num);
            priorityQueue.add(numWrp);
            sum = sum.add(numWrp);
        }
        currentSum = sum.multiply(BigDecimal.ONE);
        while (currentSum.multiply(two).compareTo(sum) > 0) {
            BigDecimal maxNum = priorityQueue.poll().divide(two);
            currentSum = currentSum.subtract(maxNum);
            priorityQueue.add(maxNum);
            ++lessOpt;
        }
        return lessOpt;
    }


}
