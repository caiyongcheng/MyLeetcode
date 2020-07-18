package letcode.medium;

/**
 * Leetcode
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。  机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。  现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？  来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/unique-paths-ii 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : CaiYongcheng
 * @date : 2020-07-14 09:35
 **/
public class _63SixtyThree {

    private static int[][] cache;
    
    private static int[][] data;
    
    private static int dfs(int y, int x){
        if (cache[y][x] != 0){
            return cache[y][x];
        }
        if ((y < cache.length-1)&&(data[y+1][x] != 1)){
            cache[y][x] += dfs(y+1, x);
        }
        if ((x < cache[0].length-1)&&(data[y][x+1] != 1)){
            cache[y][x] += dfs(y, x+1);
        }
        return cache[y][x];
    }

    /**
     * 示例 1:
     *
     * 输入:
     * [
     *   [0,0,0],
     *   [0,1,0],
     *   [0,0,0]
     * ]
     * 输出: 2
     * 解释:
     * 3x3 网格的正中间有一个障碍物。
     * 从左上角到右下角一共有 2 条不同的路径：
     * 1. 向右 -> 向右 -> 向下 -> 向下
     * 2. 向下 -> 向下 -> 向右 -> 向右
     * @param obstacleGrid
     * @return
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1 ||
        obstacleGrid[obstacleGrid.length-1][obstacleGrid[0].length-1] == 1){
            return 0;
        }
        data = obstacleGrid;
        cache = new int[obstacleGrid.length][obstacleGrid[0].length];
        cache[obstacleGrid.length-1][obstacleGrid[0].length-1] = 1;
        return dfs(0, 0);
    }

    public static void main(String[] args) {
        System.out.println(uniquePathsWithObstacles(new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        }));
    }
}
