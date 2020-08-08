package letcode.difficult;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Leetcode
 * 在二维网格 grid 上，有 4 种类型的方格：
 * 1 表示起始方格。且只有一个起始方格。
 * 2 表示结束方格，且只有一个结束方格。
 * 0 表示我们可以走过的空方格。
 * -1 表示我们无法跨越的障碍。
 * 返回在四个方向（上、下、左、右）上行走时，从起始方格到结束方格的不同路径的数目。
 * 每一个无障碍方格都要通过一次，但是一条路径中不能重复通过同一个方格。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-paths-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : CaiYongcheng
 * @date : 2020-07-14 09:53
 **/
public class _980NIneHundredEighty {

    private static int start_y = 0;
    private static int start_x = 0;
    private static int end_y = 0;
    private static int end_x = 0;

    private static int y_length;
    private static int x_length;

    private static int[][] data;
    private static boolean[][] isUse;
    private static int[][] cache;
    private static int count = 0;

    private static boolean canArrive(int y, int x){
        if ((y < 0) || (y >= y_length)
                || (x < 0) || (x >= x_length)
                || (data[y][x] == -1) || (isUse[y][x])){
            return false;
        }
        return true;
    }

    /**
     * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
     * 2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
     */
    private static int dfs(int y, int x, int nowCount){
        if (y == end_y && x == end_x){
            return count == nowCount ? 1 : 0;
        }
        if (cache[y][x] != 0){
            return cache[y][x];
        }
        if (canArrive(y-1, x)){
            isUse[y-1][x] = true;
            cache[y][x] += dfs(y-1, x, nowCount+1);
            isUse[y-1][x] = false;
        }
        if (canArrive(y+1, x)){
            isUse[y+1][x] = true;
            cache[y][x] += dfs(y+1, x, nowCount+1);
            isUse[y+1][x] = false;
        }
        if (canArrive(y, x+1)){
            isUse[y][x] = true;
            cache[y][x] += dfs(y, x+1, nowCount+1);
            isUse[y][x] = false;
        }
        if (canArrive(y, x-1)){
            isUse[y][x-1] = true;
            cache[y][x] += dfs(y, x-1, nowCount+1);
            isUse[y][x-1] = false;
        }
        return cache[y][x];
    }

    /**
     * 示例 1：
     *
     * 输入：[[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
     * 输出：2
     * 解释：我们有以下两条路径：
     * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
     * 2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
     * 示例 2：
     *
     * 输入：[[1,0,0,0],[0,0,0,0],[0,0,0,2]]
     * 输出：4
     * 解释：我们有以下四条路径：
     * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
     * 2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
     * 3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
     * 4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
     * 示例 3：
     *
     * 输入：[[0,1],[2,0]]
     * 输出：0
     * 解释：
     * 没有一条路能完全穿过每一个空的方格一次。
     * 请注意，起始和结束方格可以位于网格中的任意位置。
     * @param grid
     * @return
     */
    public static int uniquePathsIII(int[][] grid) {
        y_length = grid.length;
        x_length = grid[0].length;
        isUse = new boolean[y_length][x_length];
        cache = new int[y_length][x_length];
        data = grid;
        for (int i=0; i<y_length; ++i){
            for (int j=0; j<x_length; ++j){
                if (grid[i][j] == 1){
                    start_y = i;
                    start_x = j;
                }else if (grid[i][j] == 2){
                    end_y = i;
                    end_x = j;
                }else if (grid[i][j] == 0){
                    ++count;
                }
            }
        }
        isUse[start_y][start_x] = true;
        return dfs(start_y, start_x, 0);
    }

    public static void main(String[] args) {
/*        String str = "";
        int x = 298;
        while (x > 0){
            str = x%2 + str;
            x = x >>> 1;
        }
        System.out.println(str);
        System.out.println(1*2+1*8+1*32);

 */
        //System.out.println(uniquePathsIII(new int[][]{{1,0,0,0},{0,0,0,0},{0,0,2,-1}}));

/*      int num = (int) (Math.random()*100);
        int inNum = 0;
        System.out.println("输入1-100的数");
        Scanner sc = new Scanner(System.in);
        while (true){
            inNum = sc.nextInt();
            if (inNum == num){
                System.out.println("对了，就是"+num);
                break;
            }else if (inNum > num){
                System.out.println("大了");
            }else{
                System.out.println("小了");
            }
        }*/

        int n = (int) (Math.random()*11);
        int[] arr = new int[n];
        for (int i=0; i<n; ++i){
            arr[i] = (int) (Math.random()*30);
        }
        System.out.println(Arrays.toString(arr));
        boolean flag = true;
        for (int i=0; i<n-1&&flag; ++i){
            flag = false;
            for (int j=0; j<n-i-1; ++j){
                if (arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    flag = true;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

}
