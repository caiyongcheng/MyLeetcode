/*
 * 版权所有（c）<2021><蔡永程>
 *
 * 反996许可证版本1.0
 *
 * 在符合下列条件的情况下，特此免费向任何得到本授权作品的副本（包括源代码、文件和/或相关内容，以
 * 下统称为“授权作品”）的个人和法人实体授权：被授权个人或法人实体有权以任何目的处置授权作品，包括
 * 但不限于使用、复制，修改，衍生利用、散布，发布和再许可：
 *
 * 1. 个人或法人实体必须在许可作品的每个再散布或衍生副本上包含以上版权声明和本许可证，不得自行修
 * 改。
 * 2. 个人或法人实体必须严格遵守与个人实际所在地或个人出生地或归化地、或法人实体注册地或经营地（
 * 以较严格者为准）的司法管辖区所有适用的与劳动和就业相关法律、法规、规则和标准。如果该司法管辖区
 * 没有此类法律、法规、规章和标准或其法律、法规、规章和标准不可执行，则个人或法人实体必须遵守国际
 * 劳工标准的核心公约。
 * 3. 个人或法人不得以任何方式诱导、暗示或强迫其全职或兼职员工或其独立承包人以口头或书面形式同意
 * 直接或间接限制、削弱或放弃其所拥有的，受相关与劳动和就业有关的法律、法规、规则和标准保护的权利
 * 或补救措施，无论该等书面或口头协议是否被该司法管辖区的法律所承认，该等个人或法人实体也不得以任
 * 何方法限制其雇员或独立承包人向版权持有人或监督许可证合规情况的有关当局报告或投诉上述违反许可证
 * 的行为的权利。
 *
 * 该授权作品是"按原样"提供，不做任何明示或暗示的保证，包括但不限于对适销性、特定用途适用性和非侵
 * 权性的保证。在任何情况下，无论是在合同诉讼、侵权诉讼或其他诉讼中，版权持有人均不承担因本软件或
 * 本软件的使用或其他交易而产生、引起或与之相关的任何索赔、损害或其他责任。
 */

package letcode.normal.difficult;

import java.util.Arrays;

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
 * @since : 2020-07-14 09:53
 **/
public class _980 {

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

    private static boolean canArrive(int y, int x) {
        if ((y < 0) || (y >= y_length)
                || (x < 0) || (x >= x_length)
                || (data[y][x] == -1) || (isUse[y][x])) {
            return false;
        }
        return true;
    }

    /**
     * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
     * 2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
     */
    private static int dfs(int y, int x, int nowCount) {
        if (y == end_y && x == end_x) {
            return count == nowCount ? 1 : 0;
        }
        if (cache[y][x] != 0) {
            return cache[y][x];
        }
        if (canArrive(y - 1, x)) {
            isUse[y - 1][x] = true;
            cache[y][x] += dfs(y - 1, x, nowCount + 1);
            isUse[y - 1][x] = false;
        }
        if (canArrive(y + 1, x)) {
            isUse[y + 1][x] = true;
            cache[y][x] += dfs(y + 1, x, nowCount + 1);
            isUse[y + 1][x] = false;
        }
        if (canArrive(y, x + 1)) {
            isUse[y][x] = true;
            cache[y][x] += dfs(y, x + 1, nowCount + 1);
            isUse[y][x] = false;
        }
        if (canArrive(y, x - 1)) {
            isUse[y][x - 1] = true;
            cache[y][x] += dfs(y, x - 1, nowCount + 1);
            isUse[y][x - 1] = false;
        }
        return cache[y][x];
    }

    /**
     * 示例 1：
     * <p>
     * 输入：[[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
     * 输出：2
     * 解释：我们有以下两条路径：
     * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
     * 2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
     * 示例 2：
     * <p>
     * 输入：[[1,0,0,0],[0,0,0,0],[0,0,0,2]]
     * 输出：4
     * 解释：我们有以下四条路径：
     * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
     * 2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
     * 3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
     * 4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
     * 示例 3：
     * <p>
     * 输入：[[0,1],[2,0]]
     * 输出：0
     * 解释：
     * 没有一条路能完全穿过每一个空的方格一次。
     * 请注意，起始和结束方格可以位于网格中的任意位置。
     *
     * @param grid
     * @return
     */
    public static int uniquePathsIII(int[][] grid) {
        y_length = grid.length;
        x_length = grid[0].length;
        isUse = new boolean[y_length][x_length];
        cache = new int[y_length][x_length];
        data = grid;
        for (int i = 0; i < y_length; ++i) {
            for (int j = 0; j < x_length; ++j) {
                if (grid[i][j] == 1) {
                    start_y = i;
                    start_x = j;
                } else if (grid[i][j] == 2) {
                    end_y = i;
                    end_x = j;
                } else if (grid[i][j] == 0) {
                    ++count;
                }
            }
        }
        isUse[start_y][start_x] = true;
        return dfs(start_y, start_x, 0);
    }

    public static void main(String[] args) {

        int n = (int) (Math.random() * 11);
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = (int) (Math.random() * 30);
        }
        System.out.println(Arrays.toString(arr));
        boolean flag = true;
        for (int i = 0; i < n - 1 && flag; ++i) {
            flag = false;
            for (int j = 0; j < n - i - 1; ++j) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

}
