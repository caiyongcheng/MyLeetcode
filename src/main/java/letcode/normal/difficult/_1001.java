package letcode.normal.difficult;

import datastructure.utils.FormatPrintUtils;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 在大小为 n x n 的网格 grid 上，每个单元格都有一盏灯，最初灯都处于 关闭 状态。  给你一个由灯的位置组成的二维数组lamps ，
 * 其中 lamps[i] = [rowi, coli] 表示 打开 位于 grid[rowi][coli] 的灯。
 * 即便同一盏灯可能在 lamps 中多次列出，不会影响这盏灯处于 打开 状态。
 * 当一盏灯处于打开状态，它将会照亮 自身所在单元格 以及同一 行 、同一 列 和两条 对角线 上的 所有其他单元格 。
 * 另给你一个二维数组 queries ，其中 queries[j] = [rowj, colj] 。
 * 对于第 j 个查询，如果单元格 [rowj, colj] 是被照亮的，则查询结果为 1 ，否则为 0 。
 * 在第 j 次查询之后 [按照查询的顺序] ，关闭 位于单元格 grid[rowj][colj]
 * 上及相邻 8 个方向上（与单元格 grid[rowi][coli] 共享角或边）的任何灯。
 * 返回一个整数数组 ans 作为答案， ans[j] 应等于第 j 次查询queries[j]的结果，1 表示照亮，0 表示未照亮。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/grid-illumination 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-02-08 14:20
 **/
public class _1001 {

    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        /*
        1 维护一个set，表示哪些位置的是灯源。
        2 最开始根据数组打开首先打开所有灯 被多次照亮的地方需要记录亮度
        3 依次关灯，关灯可能会影响其他地方照明 所以需要记上
        */
        int[] ans = new int[queries.length];
        //分别表示行、列、对角线（正对角线用斜率表示，也就是行-列值不变，反对角线和不变）被照亮的地方
        HashMap<Integer, Integer> rowMap = new HashMap<>();
        HashMap<Integer, Integer> colMap = new HashMap<>();
        HashMap<Integer, Integer> lDiagonalMap = new HashMap<>();
        HashMap<Integer, Integer> rDiagonalMap = new HashMap<>();
        //存放光源
        HashSet<Long> lightSource = new HashSet<>();
        for (int[] lamp : lamps) {
            if (!lightSource.add(hash(lamp[0], lamp[1]))) {
                continue;
            }
            rowMap.put(lamp[0], rowMap.getOrDefault(lamp[0], 0) + 1);
            colMap.put(lamp[1], colMap.getOrDefault(lamp[1], 0) + 1);
            lDiagonalMap.put(lamp[0] - lamp[1], lDiagonalMap.getOrDefault(lamp[0] - lamp[1], 0) + 1);
            rDiagonalMap.put(lamp[0] + lamp[1], rDiagonalMap.getOrDefault(lamp[0] + lamp[1], 0) + 1);
        }
        //查询
        int row, col;
        for (int i = 0; i < queries.length; i++) {
            row = queries[i][0];
            col = queries[i][1];
            if (rowMap.containsKey(row)) {
                ans[i] = 1;
            } else if (colMap.containsKey(col)) {
                ans[i] = 1;
            } else if (lDiagonalMap.containsKey(row - col)) {
                ans[i] = 1;
            } else if (rDiagonalMap.containsKey(row + col)) {
                ans[i] = 1;
            }
            //关灯
            for (int r = row - 1; r <= row + 1; r++) {
                for (int c = col - 1; c <= col + 1; c++) {
                    if (r < 0 || c < 0 || r >= n || c >= n || !lightSource.remove(hash(r, c))) {
                        continue;
                    }
                    decrement(rowMap, r);
                    decrement(colMap, c);
                    decrement(lDiagonalMap, r - c);
                    decrement(rDiagonalMap, r + c);
                }
            }
        }
        return ans;
    }

    public long hash(int x, int y) {
        return (long) x + ((long) y << 32);
    }

    public void decrement(HashMap<Integer, Integer> map, Integer key) {
        Integer value = map.getOrDefault(key, 0);
        if (value == 0) {
            return;
        }
        if (value == 1) {
            map.remove(key);
        } else {
            map.put(key, value - 1);
        }
    }

}
