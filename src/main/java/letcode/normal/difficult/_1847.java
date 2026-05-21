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



}
