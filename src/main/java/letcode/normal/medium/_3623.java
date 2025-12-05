package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given a 2D integer array points, where points[i] = [xi, yi] represents
 * the coordinates of the ith point on the Cartesian plane.
 * A horizontal trapezoid is a convex quadrilateral with at least one pair of horizontal sides
 * (i.e. parallel to the x-axis). Two lines are parallel if and only if they have the same slope.
 * Return the number of unique horizontal trapezoids that can be formed by choosing any four distinct points from points.
 * Since the answer may be very large, return it modulo 109 + 7.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-12-05 17:21
 */
public class _3623 {

    static int MOD = 1_000_000_000 + 7;

    public int countTrapezoids(int[][] points) {
        Map<Integer, Integer> yIdx2CntMap = new HashMap<>();
        for (int[] point : points) {
            yIdx2CntMap.put(point[1], yIdx2CntMap.getOrDefault(point[1], 0) + 1);
        }

        long ans = 0;
        long topEdgeSum = 0;
        long curEdgeCnt;
        for (Integer curEdgePointCnt : yIdx2CntMap.values()) {
            curEdgeCnt = ((long) curEdgePointCnt) * (curEdgePointCnt - 1) >> 1;
            ans = (ans + curEdgeCnt * topEdgeSum) % MOD;
            topEdgeSum += curEdgeCnt;
        }
        return (int) ans;

        /*
        long[] edgeCntArr = new long[yIdx2CntMap.size()];
        int i = 0;
        long sumEdgeCnt = 0;
        for (Integer value : yIdx2CntMap.values()) {
            edgeCntArr[i] = ((long) value * (value - 1) / 2) % MOD;
            sumEdgeCnt = (sumEdgeCnt + edgeCntArr[i++]) % MOD;
        }

        long ans = 0;
        for (long curEdgeCnt : edgeCntArr) {
            ans = (ans + (sumEdgeCnt - curEdgeCnt + MOD) * curEdgeCnt % MOD) % MOD;
        }
        return (int) (ans / 1);
        (int) (ans / 1) 这里是有问题的 因为正确的逻辑是 计算完所有的，再 >> 1去除重复的，再MOD，
        而这里是先计算了MOD的再>>1
        */
    }


    /**
     * Example 1:
     *
     * Input: points = [[1,0],[2,0],[3,0],[2,2],[3,2]]
     *
     * Output: 3
     *
     * Explanation:
     *
     *
     *
     * There are three distinct ways to pick four points that form a horizontal trapezoid:
     *
     * Using points [1,0], [2,0], [3,2], and [2,2].
     * Using points [2,0], [3,0], [3,2], and [2,2].
     * Using points [1,0], [3,0], [3,2], and [2,2].
     * Example 2:
     *
     * Input: points = [[0,0],[1,0],[0,1],[2,1]]
     *
     * Output: 1
     *
     * Explanation:
     *
     *
     *
     * There is only one horizontal trapezoid that can be formed.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }

}
