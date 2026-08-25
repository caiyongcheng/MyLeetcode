package letcode.normal.medium;

import org.jetbrains.annotations.NotNull;

/**
 * 3015. Count the Number of Houses at a Certain Distance I
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/count-the-number-of-houses-at-a-certain-distance-i/
 * <p>
 * You are given three positive integers n , x , and y .
 * <p>
 * In a city, there exist houses numbered 1 to n connected by n streets. There is a street connecting
 * the house numbered i with the house numbered i + 1 for all 1 <= i <= n - 1 . An additional street
 * connects the house numbered x with the house numbered y .
 * <p>
 * For each k , such that 1 <= k <= n , you need to find the number of pairs of houses (house 1 , house
 * 2 ) such that the minimum number of streets that need to be traveled to reach house 2 from house 1
 * is k .
 * <p>
 * Return a 1-indexed array result of length n where result[k] represents the total number of pairs of
 * houses such that the minimum streets required to reach one house from the other is k .
 * <p>
 * Note that x and y can be equal .
 * <p>
 * Example 1:
 * <p>
 * Input: n = 3, x = 1, y = 3
 * Output: [6,0,0]
 * Explanation: Let's look at each pair of houses:
 * - For the pair (1, 2), we can go from house 1 to house 2 directly.
 * - For the pair (2, 1), we can go from house 2 to house 1 directly.
 * - For the pair (1, 3), we can go from house 1 to house 3 directly.
 * - For the pair (3, 1), we can go from house 3 to house 1 directly.
 * - For the pair (2, 3), we can go from house 2 to house 3 directly.
 * - For the pair (3, 2), we can go from house 3 to house 2 directly.
 * <p>
 * Example 2:
 * <p>
 * Input: n = 5, x = 2, y = 4
 * Output: [10,8,2,0,0]
 * Explanation: For each distance k the pairs are:
 * - For k == 1, the pairs are (1, 2), (2, 1), (2, 3), (3, 2), (2, 4), (4, 2), (3, 4), (4, 3), (4, 5),
 * and (5, 4).
 * - For k == 2, the pairs are (1, 3), (3, 1), (1, 4), (4, 1), (2, 5), (5, 2), (3, 5), and (5, 3).
 * - For k == 3, the pairs are (1, 5), and (5, 1).
 * - For k == 4 and k == 5, there are no pairs.
 * <p>
 * Example 3:
 * <p>
 * Input: n = 4, x = 1, y = 1
 * Output: [6,4,2,0]
 * Explanation: For each distance k the pairs are:
 * - For k == 1, the pairs are (1, 2), (2, 1), (2, 3), (3, 2), (3, 4), and (4, 3).
 * - For k == 2, the pairs are (1, 3), (3, 1), (2, 4), and (4, 2).
 * - For k == 3, the pairs are (1, 4), and (4, 1).
 * - For k == 4, there are no pairs.
 * <p>
 * Constraints:
 * <p>
 * - 2 <= n <= 100
 * <p>
 * - 1 <= x, y <= n
 */
public class _3015 {

    public int[] countOfPairs(int n, int x, int y) {

        // init ans ans[i]: count of distance equal i + 1
        int[] ans = init(n);

        // ensure x < y
        if (x == y) {
            return ans;
        }
        if (x > y) {
            int temp = x;
            x = y;
            y = temp;
        }

        --x;
        --y;

        // i < j <= x < y and x < y <= i < j is meaningless
        int oldPathCost;
        int newPathCost;

        for (int i = 0; i <= x; i++) {
            for (int j = x + 1; j < n; j++) {
                oldPathCost = j - i;
                newPathCost = (x - i) + 1 + Math.abs(j - y);
                if (oldPathCost > newPathCost) {
                    ans[oldPathCost - 1] -= 2;
                    ans[newPathCost - 1] += 2;
                }
            }
        }

        for (int i = x + 1; i < y; i++) {
            for (int j = x + 1; j < n; j++) {
                oldPathCost = j - i;
                newPathCost = (i - x) + 1 + Math.abs(j - y);
                if (oldPathCost > newPathCost) {
                    ans[oldPathCost - 1] -= 2;
                    ans[newPathCost - 1] += 2;
                }
            }
        }


        return ans;

    }

    @NotNull
    private static int[] init(int n) {
        int[] ans = new int[n];
        for (int i = 0; i < ans.length - 1; i++) {
            ans[i] = (n - 1 - i) << 1;
        }
        return ans;
    }
}
