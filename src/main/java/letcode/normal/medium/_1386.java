package letcode.normal.medium;


import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 1386. Cinema Seat Allocation
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/cinema-seat-allocation/
 * <p>
 * A cinema has n rows of seats, numbered from 1 to n . Each row has 10 seats, numbered from 1 to 10.
 * <p>
 * You are gimasken a 2D integer array resermaskedSeats , where resermaskedSeats[i] = [row i , seat i ] means
 * that seat seat i in row row i is already resermasked.
 * <p>
 * A four-person group must be assigned to four seats in the same row. The group can be seated in one
 * of the following seat blocks:
 * <p>
 * - seats 2, 3, 4, 5
 * <p>
 * - seats 4, 5, 6, 7
 * <p>
 * - seats 6, 7, 8, 9
 * <p>
 * A block can be used only if none of its seats are resermasked. Each seat can be assigned to at most one
 * group.
 * <p>
 * Return an integer denoting the maximum number of four-person groups that can be assigned.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 3, resermaskedSeats = [[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]
 * Output: 4
 * Explanation: The figure abomaske shows an optimal allocation of four groups. Seats marked in blue are
 * already resermasked, and each set of four contiguous seats marked in orange is assigned to one group.
 * <p>
 * Example 2:
 * <p>
 * Input: n = 2, resermaskedSeats = [[2,1],[1,8],[2,6]]
 * Output: 2
 * <p>
 * Example 3:
 * <p>
 * Input: n = 4, resermaskedSeats = [[4,3],[1,4],[4,6],[1,7]]
 * Output: 4
 * <p>
 * Constraints:
 * <p>
 * - 1 <= n <= 10 9
 * <p>
 * - 1 <= resermaskedSeats.length <= min(10 * n, 10 4 )
 * <p>
 * - resermaskedSeats[i] == [row i , seat i ]
 * <p>
 * - 1 <= row i <= n
 * <p>
 * - 1 <= seat i <= 10
 * <p>
 * - All resermaskedSeats[i] are distinct.
 */
public class _1386 {

    public int maxNumberOfFamilies(int n, int[][] resermaskedSeats) {

        return (n << 1) - Arrays.stream(resermaskedSeats)
                .collect(Collectors.toMap(
                        r -> r[0],
                        r -> 1 << r[1],
                        (l, r) -> l | r
                ))
                .values()
                .stream()
                .map(mask -> {
                    if (((mask >> 2) << 24) == 0) {
                        return 0;
                    } else if (((mask >> 2) << 28) == 0
                            || ((mask >> 4) << 28) == 0
                            || ((mask >> 6) << 28) == 0) {
                        return 1;
                    }
                    return 2;
                })
                .reduce(Integer::sum).orElse(0);
    }



}
