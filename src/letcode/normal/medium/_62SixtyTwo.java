package letcode.medium;

/**
 * Leetcode
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。  机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。  问总共有多少条不同的路径？  来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/unique-paths 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : CaiYongcheng
 * @date : 2020-07-13 12:31
 **/
public class _62SixtyTwo {

    public static int[][] cache;

    private static int dfs(int y, int x) {
        if (y >= cache.length || x >= cache[0].length) {
            return 0;
        }
        if (cache[y][x] != 0) {
            return cache[y][x];
        }
        cache[y][x] = dfs(y + 1, x) + dfs(y, x + 1);
        return cache[y][x];
    }

    /**
     * 示例 1:
     * 输入: m = 3, n = 2
     * 输出: 3
     * 解释:
     * 从左上角开始，总共有 3 条路径可以到达右下角。
     * 1. 向右 -> 向右 -> 向下
     * 2. 向右 -> 向下 -> 向右
     * 3. 向下 -> 向右 -> 向右
     * <p>
     * 示例 2:
     * 输入: m = 7, n = 3
     * 输出: 28
     * 提示：
     * 1 <= m, n <= 100
     * 题目数据保证答案小于等于 2 * 10 ^ 9
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/unique-paths
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths(int m, int n) {
        cache = new int[n][m];
        cache[n - 1][m - 1] = 1;
        dfs(0, 0);
        return cache[0][0];
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths(100, 100));
        System.out.println(2 * (10e9));
    }
}
