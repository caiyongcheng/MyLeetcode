package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.List;
import java.util.PriorityQueue;

/**
 * You are given m arrays, where each array is sorted in ascending order.
 * You can pick up two integers from two different arrays (each array picks one) and calculate the distance.
 * We define the distance between two integers a and b to be their absolute difference |a - b|.
 * Return the maximum distance.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-02-19 15:50
 */
public class _624 {

    private int maxDistance(List<List<Integer>> arrays) {
        PriorityQueue<int[]> minPq = new PriorityQueue<>(
                (aArr, bArr) -> {
                    Integer a = arrays.get(aArr[0]).get(aArr[1]);
                    Integer b = arrays.get(bArr[0]).get(bArr[1]);
                    return a > b ? 1 : (a < b ? -1 : aArr[0] - bArr[0]);
                }
        );

        PriorityQueue<int[]> maxPq = new PriorityQueue<>(
                (aArr, bArr) -> {
                    Integer a = arrays.get(aArr[0]).get(aArr[1]);
                    Integer b = arrays.get(bArr[0]).get(bArr[1]);
                    return a > b ? -1 : (a < b ? 1 : bArr[0] - aArr[0]);
                }
        );

        for (int i = 0; i < arrays.size(); i++) {
            minPq.add(new int[]{i, 0});
            maxPq.add(new int[]{i, arrays.get(i).size() - 1});
        }

        int[] minIdx = minPq.poll();
        int[] maxIdx = maxPq.poll();
        if (maxIdx[0] != minIdx[0]) {
            return arrays.get(maxIdx[0]).get(maxIdx[1]) - arrays.get(minIdx[0]).get(minIdx[1]);
        }

        int[] curMaxIdx;
        int[] curMinIdx;
        while (true) {
            curMaxIdx = null;
            while (!maxPq.isEmpty()) {
                curMaxIdx = maxPq.poll();
                if (curMaxIdx[0] != minIdx[0]) {
                    break;
                }
            }
            curMinIdx = null;
            while (!minPq.isEmpty()) {
                curMinIdx = minPq.poll();
                if (curMinIdx[0] != maxIdx[0]) {
                    break;
                }
            }
            if (curMaxIdx == null && curMinIdx == null) {
                return -1;
            }
            if (curMaxIdx == null) {
                return arrays.get(maxIdx[0]).get(maxIdx[1]) - arrays.get(curMinIdx[0]).get(curMinIdx[1]);
            } else if (curMinIdx == null) {
                return arrays.get(curMaxIdx[0]).get(curMaxIdx[1]) - arrays.get(minIdx[0]).get(minIdx[1]);
            } else {
                return Math.max(
                        arrays.get(maxIdx[0]).get(maxIdx[1]) - arrays.get(curMinIdx[0]).get(curMinIdx[1]),
                        arrays.get(curMaxIdx[0]).get(curMaxIdx[1]) - arrays.get(minIdx[0]).get(minIdx[1])
                );
            }
        }
    }

    public int maxDistance2(List<List<Integer>> arrays) {
        // maxDistance有问题，因为实际上我们只需要考虑数组的极点即可，不可能会出现选择某个数组的第二大和其他数组的第二小，
        // 或者某个数组的最大和某个数组的第二小等等情况，结果只会出现在极点中，所以维护极点即可

        List<Integer> firstArr = arrays.get(0);
        int min = firstArr.get(0);
        int max = firstArr.get(firstArr.size() - 1);
        int curMin;
        int curMax;
        int ans = -1;

        int size = arrays.size();
        for (int i = 1; i < size; i++) {
            List<Integer> curList = arrays.get(i);
            curMin = curList.get(0);
            curMax = curList.get(curList.size() - 1);
            ans = Math.max(ans, Math.max(max - curMin, curMax - min));
            if (curMin < min) {
                min = curMin;
            }
            if (curMax > max) {
                max = curMax;
            }
        }
        return ans;
    }

    /**
     * Example 1:
     *
     * Input: arrays = [[1,2,3],[4,5],[1,2,3]]
     * Output: 4
     * Explanation: One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.
     * Example 2:
     *
     * Input: arrays = [[1],[1]]
     * Output: 0
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }

}
