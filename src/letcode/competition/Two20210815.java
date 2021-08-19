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
