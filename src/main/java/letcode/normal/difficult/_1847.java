package letcode.normal.difficult;

import letcode.utils.TestUtil;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * There is a hotel with n rooms. The rooms are represented by a 2D integer array rooms
 * where rooms[i] = [roomIdi, sizei] denotes that there is a room with room number roomIdi and size equal to sizei.
 * Each roomIdi is guaranteed to be unique.
 * You are also given k queries in a 2D array queries where queries[j] = [preferredj, minSizej].
 * The answer to the jth query is the room number id of a room such that:  The room has a size of at least minSizej,
 * and abs(id - preferredj) is minimized, where abs(x) is the absolute value of x.
 * If there is a tie in the absolute difference, then use the room with the smallest such id.
 * If there is no such room, the answer is -1.
 * Return an array answer of length k where answer[j] contains the answer to the jth query.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-12-16 10:02
 */
public class _1847 {

    public int[] closestRoom(int[][] rooms, int[][] queries) {
        // 数据先排序
        // 因为是写题 就不用Comparator的相关实现了 慢
        Arrays.sort(rooms, (a, b) -> a[1] - b[1]);
        Integer[] queryIdxArr = new Integer[queries.length];
        for (int i = 0; i < queries.length; i++) {
            queryIdxArr[i] = i;
        }
        Arrays.sort(queryIdxArr, (i, j) -> queries[j][1] - queries[i][1]);

        // 保存大于等于当前查询的size的roomId
        TreeSet<Integer> idSet = new TreeSet<>();
        int end = rooms.length - 1;

        int[] ans = new int[queries.length];
        Arrays.fill(ans, -1);
        for (Integer idx : queryIdxArr) {
            while (end >= 0 && rooms[end][1] >= queries[idx][1]) {
                idSet.add(rooms[end--][0]);
            }

            // 查询大于queries[cur]的最小roomsId以及大于queries[cur]的最小roomsId
            Integer higher = idSet.ceiling(queries[idx][0]);
            Integer lower = idSet.floor(queries[idx][0]);
            int dist = Integer.MAX_VALUE;
            if (higher != null && higher - queries[idx][0] < dist) {
                dist = higher - queries[idx][0];
                ans[idx] = higher;
            }
            if (lower != null && queries[idx][0] - lower <= dist) {
                ans[idx] = lower;
            }
        }
        return ans;
    }



    /**
     * Example 1:
     * Input: rooms = [[2,2],[1,2],[3,2]], queries = [[3,1],[3,3],[5,2]]
     * Output: [3,-1,3]
     * Explanation: The answers to the queries are as follows:
     * Query = [3,1]: Room number 3 is the closest as abs(3 - 3) = 0, and its size of 2 is at least 1. The answer is 3.
     * Query = [3,3]: There are no rooms with a size of at least 3, so the answer is -1.
     * Query = [5,2]: Room number 3 is the closest as abs(3 - 5) = 2, and its size of 2 is at least 2. The answer is 3.
     * Example 2:
     * Input: rooms = [[1,4],[2,3],[3,5],[4,1],[5,2]], queries = [[2,3],[2,4],[2,5]]
     * Output: [2,1,3]
     * Explanation: The answers to the queries are as follows:
     * Query = [2,3]: Room number 2 is the closest as abs(2 - 2) = 0, and its size of 3 is at least 3. The answer is 2.
     * Query = [2,4]: Room numbers 1 and 3 both have sizes of at least 4. The answer is 1 since it is smaller.
     * Query = [2,5]: Room number 3 is the only room with a size of at least 5. The answer is 3.
     * @param args args
     */
    public static void main(String[] args) {
        TestUtil.test(_1847.class);
    }

}
