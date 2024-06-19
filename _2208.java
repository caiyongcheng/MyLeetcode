/*
 * 版权所有（c）<2021><蔡永程>
 *
 * 反996许可证版本1.0
 *
 * 在符合下列条件的情况下，特此免费向任何得到本授权作品的副本（包括源代码、文件和/或相关内容，以
 * 下统称为“授权作品”）的个人和法人实体授权：被授权个人或法人实体有权以任何目的处置授权作品，包括
 * 但不限于使用、复制，修改，衍生利用、散布，发布和再许可：
 *
 * 1. 个人或法人实体必须在许可作品的每个再散布或衍生副本上包含以上版权声明和本许可证，不得自行修
 * 改。
 * 2. 个人或法人实体必须严格遵守与个人实际所在地或个人出生地或归化地、或法人实体注册地或经营地（
 * 以较严格者为准）的司法管辖区所有适用的与劳动和就业相关法律、法规、规则和标准。如果该司法管辖区
 * 没有此类法律、法规、规章和标准或其法律、法规、规章和标准不可执行，则个人或法人实体必须遵守国际
 * 劳工标准的核心公约。
 * 3. 个人或法人不得以任何方式诱导、暗示或强迫其全职或兼职员工或其独立承包人以口头或书面形式同意
 * 直接或间接限制、削弱或放弃其所拥有的，受相关与劳动和就业有关的法律、法规、规则和标准保护的权利
 * 或补救措施，无论该等书面或口头协议是否被该司法管辖区的法律所承认，该等个人或法人实体也不得以任
 * 何方法限制其雇员或独立承包人向版权持有人或监督许可证合规情况的有关当局报告或投诉上述违反许可证
 * 的行为的权利。
 *
 * 该授权作品是"按原样"提供，不做任何明示或暗示的保证，包括但不限于对适销性、特定用途适用性和非侵
 * 权性的保证。在任何情况下，无论是在合同诉讼、侵权诉讼或其他诉讼中，版权持有人均不承担因本软件或
 * 本软件的使用或其他交易而产生、引起或与之相关的任何索赔、损害或其他责任。
 */

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


    /**
     * 输入：nums = [5,19,8,1]
     * 输出：3
     * 解释：初始 nums 的和为 5 + 19 + 8 + 1 = 33 。
     * 以下是将数组和减少至少一半的一种方法：
     * 选择数字 19 并减小为 9.5 。
     * 选择数字 9.5 并减小为 4.75 。
     * 选择数字 8 并减小为 4 。
     * 最终数组为 [5, 4.75, 4, 1] ，和为 5 + 4.75 + 4 + 1 = 14.75 。
     * nums 的和减小了 33 - 14.75 = 18.25 ，减小的部分超过了初始数组和的一半，18.25 >= 33/2 = 16.5 。
     * 我们需要 3 个操作实现题目要求，所以返回 3 。
     * 可以证明，无法通过少于 3 个操作使数组和减少至少一半。
     * <p>
     * 输入：nums = [3,8,20]
     * 输出：3
     * 解释：初始 nums 的和为 3 + 8 + 20 = 31 。
     * 以下是将数组和减少至少一半的一种方法：
     * 选择数字 20 并减小为 10 。
     * 选择数字 10 并减小为 5 。
     * 选择数字 3 并减小为 1.5 。
     * 最终数组为 [1.5, 8, 5] ，和为 1.5 + 8 + 5 = 14.5 。
     * nums 的和减小了 31 - 14.5 = 16.5 ，减小的部分超过了初始数组和的一半， 16.5 >= 31/2 = 16.5 。
     * 我们需要 3 个操作实现题目要求，所以返回 3 。
     * 可以证明，无法通过少于 3 个操作使数组和减少至少一半。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2208().halveArray(
                new int[]{3, 8, 20}
        ));
    }


}
