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

package letcode.competition;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author CaiYongcheng
 * @date 2021-08-15 10:26
 **/
public class Two20210815 {

    final int MOD = 1000000000 + 7;

    public int numOfStrings(String[] patterns, String word) {
        int ans = 0;
        for (String pattern : patterns) {
            if (word.contains(pattern)) {
                ++ans;
            }
        }
        return ans;
    }


    public int[] rearrangeArray(int[] nums) {
        Arrays.sort(nums);
        int[] ans = new int[nums.length];
        int index = 0;
        for (int i = 0; i < ans.length; i+=2) {
            ans[i] = nums[index];
            ++index;
        }
        for (int i = 1; i < ans.length; i+=2) {
            ans[i] = nums[index];
            ++index;
        }
        return ans;
    }

    public int minNonZeroProduct(int p) {
        if (p == 1) {
            return 1;
        }
        long limit = (1L << p) - 2;
        long count = (limit + 1) >> 1;
        return (int) ((mul(limit % MOD, count) * ((limit + 1) % MOD)) % MOD);
    }

    public long mul(long fac, long size) {
        if (size <= 1) {
            return fac % MOD;
        }
        long mul = mul(fac, size >> 1);
        return (((mul * mul) % MOD) * ((size & 1) == 1 ? fac : 1)) % MOD;
    }


    public int latestDayToCross(int row, int col, int[][] cells) {
        int[] path = new int[row + 1];
        int left = 0;
        int right = cells.length - 1;
        int mid;
        int[][] land = new int[row+1][col+1];
        int[][] visitable = new int[row + 1][col + 1];
        int isFill = 1;
        row = 1;
        col = 1;
        while (left < right) {
            for (int[] ints : visitable) {
                Arrays.fill(ints, 0);
            }
            mid = (left + right) >> 1;
            if (mid == left) {
                break;
            }
            fillAndClear(land, cells, left, mid, isFill);
            if (dfs(row, col, path, land, visitable)) {
                left = mid;
                isFill = 1;
            } else {
                right = mid;
                isFill = 0;
            }
        }
        for (int[] ints : visitable) {
            Arrays.fill(ints, 0);
        }
        fillAndClear(land, cells, 0, cells.length-1, 1);
        if (dfs(1,1,path,land, visitable)) {
            return cells.length;
        }
        return left + 1;
    }

    public void fillAndClear(int[][] now, int[][] cells, int start, int end, int isFill) {
        while (start <= end) {
            now[cells[start][0]][cells[start][1]] = isFill;
            ++start;
        }
    }

    public boolean dfs(int row, int col, int[] path, int[][] land, int[][] visitable) {
        if (row < 1 || row >= path.length || col < 1 || col >= land[0].length) {
            return false;
        }
        if (visitable[row][col] == 1) {
            return false;
        }

        if (land[row][col] == 1 && row == 1) {
            visitable[row][col] = 1;
            return dfs(row, col+1, path, land, visitable) || dfs(row, col-1, path, land, visitable);
        }
        if (land[row][col] == 1) {
            return false;
        }
        if (row == land[0].length - 1) {
            return land[row][col] != 1;
        }
        visitable[row][col] = 1;
        path[row] = col;
        return dfs(row+1, col, path, land, visitable) || dfs(row, col+1, path, land, visitable) || dfs(row-1, col, path, land, visitable) || dfs(row, col-1, path, land, visitable);
    }


    /**
     * row = 2, col = 2, cells = {{1,1},{2,1},{1,2},{2,2}}      2
     * row = 2, col = 2, cells = {{1,1},{1,2},{2,1},{2,2}}      1
     * row = 3, col = 3, cells = {{1,2},{2,1},{3,3},{2,2},{1,1},{1,3},{2,3},{3,2},{3,1}}   3
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new Two20210815().latestDayToCross(
                3,
                3,
                new int[][]{{1,2},{2,1},{3,3},{2,2},{1,1},{1,3},{2,3},{3,2},{3,1}}
        ));
    }

}
