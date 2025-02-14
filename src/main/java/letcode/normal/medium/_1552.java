package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.Arrays;

/**
 * In the universe Earth C-137, Rick discovered a special form of magnetic force between two
 * balls if they are put in his new invented basket. Rick has n empty baskets, the ith basket is at position[i],
 * Morty has m balls and needs to distribute the balls into the baskets such that the minimum magnetic
 * force between any two balls is maximum.  Rick stated that magnetic force between two different balls at
 * positions x and y is |x - y|.  Given the integer array position and the integer m. Return the required force.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-02-14 14:08
 */
public class _1552 {

    public int maxDistance(int[] position, int m) {
        /*
        要最小值最大 那么就是要求尽可能平均
        如果答案是 ans，那么意味着可以找到一种排列方法，使得满足答案，
        那么此时比ans小的排列方式我们也可以构造出来，但是大于ans的排列方式可能无法构造出来
        那么可以才用二分的方法寻找答案
         */

        Arrays.sort(position);
        if (m == 2) {
            return position[position.length - 1] - position[0];
        }

        int right = position[position.length - 1] - position[0] / m;
        int left = 0;
        int mid;
        while (true) {
            mid = (right + left) >> 1;
            if (mid == left) {
                return left;
            }
            if (check(position, m, mid)) {
                left = mid;
            } else {
                right = mid;
            }
        }
    }

    private boolean check(int[] position, int m, int positionInterval) {
        int lastIdx = 0;
        int nextIdx;
        for (int i = 1; i < m; i++) {
            // 这里使不使用二分，性能上差的应该不会很多
            for (nextIdx = lastIdx;
                 nextIdx < position.length && position[nextIdx] - position[lastIdx] < positionInterval;
                 nextIdx++);
            if (nextIdx == position.length) {
                return false;
            }
            lastIdx = nextIdx;
        }
        return true;
    }

    /**
     * Input: position = [1,2,3,4,7], m = 3
     * Output: 3
     * Explanation: Distributing the 3 balls into baskets 1, 4 and 7 will make the magnetic force between ball pairs [3, 3, 6]. The minimum magnetic force is 3. We cannot achieve a larger minimum magnetic force than 3.
     * Example 2:
     *
     * Input: position = [5,4,3,2,1,1000000000], m = 2
     * Output: 999999999
     * Explanation: We can use baskets 1 and 1000000000.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }

}
