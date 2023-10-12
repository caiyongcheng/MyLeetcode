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

import java.util.PriorityQueue;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/9/7 16:00
 * description 给你一个整数数组 ranks ，表示一些机械工的 能力值 。ranksi 是第 i 位机械工的能力值。能力值为 r 的机械工可以在 r * n2 分钟内修好 n 辆车。
 * 同时给你一个整数 cars ，表示总共需要修理的汽车数目。  请你返回修理所有汽车 最少 需要多少时间。  注意：所有机械工可以同时修理汽车。
 */
public class _2594TwoThousandFiveHundredNinetyFour {


    public static class CombinationData implements Comparable<CombinationData> {
        long capacity;

        long carCnt;

        public CombinationData(long capacity) {
            this.capacity = capacity;
            this.carCnt = 0;
        }

        public long cost() {
            return (carCnt + 1) * (carCnt + 1) * capacity;
        }

        @Override
        public int compareTo(CombinationData o) {
            return -Long.compare(o.cost(), cost());
        }
    }


    public long repairCars2(int[] ranks, int cars) {
        /*
        对时间进行二分 只要时间内完成的数量 大于等于目标数量说明改时间内可以完成 因为时间是整数 所以可以二分出上下边界
         */
        if (cars == 0) {
            return 0;
        }
        long left = 0;
        long right = Long.MAX_VALUE;
        long mid;
        while (true) {
            mid = (left + right) >> 1;
            if (left == mid) {
                return right;
            }
            if (culMaintainCnt(ranks, mid) >= cars) {
                right = mid;
            } else {
                left = mid;
            }
        }
    }

    public long culMaintainCnt(int[] ranks, long time) {
        long rst = 0;
        for (int rank : ranks) {
            rst += Math.sqrt((double) time / rank);
        }
        return rst;
    }


    public long repairCars(int[] ranks, int cars) {
        /*
        贪心 成本越低的优先分配 时间复杂度 O(nlogn)
         */
        long rst = 0;
        PriorityQueue<CombinationData> priorityQueue = new PriorityQueue<>(ranks.length);
        for (int rank : ranks) {
            priorityQueue.add(new CombinationData(rank));
        }
        while (cars > 0) {
            CombinationData poll = priorityQueue.poll();
            poll.carCnt++;
            rst = Math.max(rst, poll.carCnt * poll.carCnt * poll.capacity);
            priorityQueue.add(poll);
            --cars;
        }
        return rst;
    }


    /**
     * 示例 1：
     * <p>
     * 输入：ranks = [4,2,3,1], cars = 10
     * 输出：16
     * 解释：
     * - 第一位机械工修 2 辆车，需要 4 * 2 * 2 = 16 分钟。
     * - 第二位机械工修 2 辆车，需要 2 * 2 * 2 = 8 分钟。
     * - 第三位机械工修 2 辆车，需要 3 * 2 * 2 = 12 分钟。
     * - 第四位机械工修 4 辆车，需要 1 * 4 * 4 = 16 分钟。
     * 16 分钟是修理完所有车需要的最少时间。
     * 示例 2：
     * <p>
     * 输入：ranks = [5,1,8], cars = 6
     * 输出：16
     * 解释：
     * - 第一位机械工修 1 辆车，需要 5 * 1 * 1 = 5 分钟。
     * - 第二位机械工修 4 辆车，需要 1 * 4 * 4 = 16 分钟。
     * - 第三位机械工修 1 辆车，需要 8 * 1 * 1 = 8 分钟。
     * 16 分钟时修理完所有车需要的最少时间。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2594TwoThousandFiveHundredNinetyFour().repairCars(
                new int[]{31, 31, 5, 19, 19, 10, 31, 18, 19, 3, 16, 20, 4, 16, 2, 25, 10, 16, 23, 18, 21, 23, 28, 6, 7, 29, 11, 11, 19, 20, 24, 19, 26, 12, 29, 29, 1, 14, 17, 26, 24, 7, 11, 28, 22, 14, 31, 12, 3, 19, 16, 26, 11},
                736185
        ));
        System.out.println(new _2594TwoThousandFiveHundredNinetyFour().repairCars2(
                new int[]{31, 31, 5, 19, 19, 10, 31, 18, 19, 3, 16, 20, 4, 16, 2, 25, 10, 16, 23, 18, 21, 23, 28, 6, 7, 29, 11, 11, 19, 20, 24, 19, 26, 12, 29, 29, 1, 14, 17, 26, 24, 7, 11, 28, 22, 14, 31, 12, 3, 19, 16, 26, 11},
                736185
        ));
    }


}
