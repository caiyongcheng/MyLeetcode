package letcode.normal.medium;

import java.util.PriorityQueue;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/9/7 16:00
 * description 给你一个整数数组 ranks ，表示一些机械工的 能力值 。ranksi 是第 i 位机械工的能力值。能力值为 r 的机械工可以在 r * n2 分钟内修好 n 辆车。
 * 同时给你一个整数 cars ，表示总共需要修理的汽车数目。  请你返回修理所有汽车 最少 需要多少时间。  注意：所有机械工可以同时修理汽车。
 */
public class _2594 {


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


}
