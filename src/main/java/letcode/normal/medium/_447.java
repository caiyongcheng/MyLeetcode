package letcode.normal.medium;
import java.util.Arrays;

/**
 * 给定平面上n 对 互不相同 的点points ，其中 points[i] = [xi, yi] 。回旋镖 是由点(i, j, k) 表示的元组 ，其中i和j之间的距离和i和k之间的距离相等（需要考虑元组的顺序）。  返回平面上所有回旋镖的数量。  来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/number-of-boomerangs 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-09-13 10:05
 **/
public class _447 {

    public int numberOfBoomerangs(int[][] points) {
        int ans = 0;
        long[][] distance = new long[points.length][points.length];
        for (int start = 0; start < distance.length; start++) {
            for (int end = 0; end < distance[start].length; end++) {
                distance[start][end] = start > end
                        ? distance[end][start]
                        : start == end
                        ? 0
                        : computeDistance(points[start], points[end]);
            }
        }
        for (long[] dist : distance) {
            Arrays.sort(dist);
        }
        for (long[] longs : distance) {
            for (int i = 0; i < longs.length; ) {
                int j = i;
                for (; j < longs.length && longs[i] == longs[j]; j++) {
                }
                ans += (j - i) * (j - i - 1);
                i = j;
            }
        }
        return ans;
    }

    public long computeDistance(int[] start, int[] end) {
        long xDist = start[0] - end[0];
        long yDist = start[1] - end[1];
        return xDist * xDist + yDist * yDist;
    }

}
