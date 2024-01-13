package letcode.normal.difficult;

import letcode.utils.TestCaseUtils;

import java.util.*;

/**
 * 给你一个数组 points 和一个整数 k 。数组中每个元素都表示二维平面上的点的坐标，并按照横坐标 x 的值从小到大排序。也就是说 points[i] = [xi, yi] ，
 * 并且在 1 <= i < j <= points.length 的前提下， xi < xj 总成立。  请你找出 yi + yj + |xi - xj| 的 最大值，其中 |xi - xj| <= k 且 1 <= i < j <= points.length。
 * 题目测试数据保证至少存在一对能够满足 |xi - xj| <= k 的点。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2023/12/27 16:53
 */
public class _1499OneThousandFourHundredNinetyNine {

    int curX;
    int curY;

    public int findMaxValueOfEquation(int[][] points, int k) {
        /*
        根据题目条件 可以直到 x坐标是有序的 意味着可以二分检索
        朴素想法是对于每个点，计算满足条件的点 求最大值
        接下来是优化 对于每个点 只向右计算 这样避免了重复计算 复杂度n^2
        优化点在于 怎么去快速的求区间内的最优值
        或者说 已知当前点的最优值，怎么快速计算出下一点的最优值
        如果我们将当前点的区间 按分数排序
        那么对于下一点来说，他的区间等于当前区间减去自己（如果包含的话），在加上新增部分。
        对于重叠的部分，对当前点与下一点的排序都是一致的。所以只需要把新增部分也加入进去，就构成了一个新的有序区间。
        也就是时间复杂度在 nlog(n)

        不需要二分 二分是无意义的
         */

        curX = points[0][0];
        curY = points[0][1];
        int rightIdx = 0;
        TreeSet<int[]> set = new TreeSet<>((a1, a2) -> (Math.abs(a2[0] - curX) + curY + a2[1]) - (Math.abs(a1[0] - curX) + curY + a1[1]));
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < points.length - 1; i++) {
            curX = points[i][0];
            curY = points[i][1];
            set.remove(points[i]);
            rightIdx = Math.max(rightIdx, i) + 1;
            while (rightIdx < points.length && points[rightIdx][0] - points[i][0] <= k) {
                set.add(points[rightIdx++]);
            }
            if (rightIdx != points.length) {
                --rightIdx;
            }
            if (!set.isEmpty()) {
                ans = Math.max(ans, getScore(set.first()));
            }
        }
        return ans;


        /*
        对于 yi + yj + |xi - xj| 来说 等价于 xj+yj + (yi - xi)
        枚举每个点范围内满足条件的 最大 (yi - xi)即可
         */
/*        int res = Integer.MIN_VALUE;
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
        for (int[] point : points) {
            int x = point[0], y = point[1];
            while (!heap.isEmpty() && x - heap.peek()[1] > k) {
                heap.poll();
            }
            if (!heap.isEmpty()) {
                res = Math.max(res, x + y - heap.peek()[0]);
            }
            heap.offer(new int[]{x - y, x});
        }
        return res;*/
    }

    public int getScore(int[] arr) {
        return Math.abs(arr[0] - curX) + curY + arr[1];
    }



    /**
     * 示例 1：
     *
     * 输入：points = [[1,3],[2,0],[5,10],[6,-10]], k = 1
     * 输出：4
     * 解释：前两个点满足 |xi - xj| <= 1 ，代入方程计算，则得到值 3 + 0 + |1 - 2| = 4 。第三个和第四个点也满足条件，得到值 10 + -10 + |5 - 6| = 1 。
     * 没有其他满足条件的点，所以返回 4 和 1 中最大的那个。
     * 示例 2：
     *
     * 输入：points = [[0,0],[3,0],[9,2]], k = 3
     * 输出：3
     * 解释：只有前两个点满足 |xi - xj| <= 3 ，代入方程后得到值 0 + 0 + |0 - 3| = 3 。
     * @param args
     *
     * [[-13,19],[-6,-16],[2,-7],[6,-13],[8,-16],[13,6],[14,-13],[20,4]]
     * 3
     * -6
     *
     * [[-18,-5],[-15,11],[-14,9],[-12,-10],[-11,5],[-6,-11],[-3,13],[4,-13],[5,-7],[9,16],[10,2],[11,14],[13,6],[19,-4]]
     * 7
     * 32
     */
    public static void main(String[] args) {
        System.out.println(new _1499OneThousandFourHundredNinetyNine().findMaxValueOfEquation(
                TestCaseUtils.get2DIntArr("[[-13,19],[-6,-16],[2,-7],[6,-13],[8,-16],[13,6],[14,-13],[20,4]]"),
                3
        ));
    }

}
