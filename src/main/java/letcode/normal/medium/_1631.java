package letcode.normal.medium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/12/11 17:50
 * description 你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row, col) 的高度。
 * 一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
 * 一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。  请你返回从左上角走到右下角的最小 体力消耗值 。
 */
public class _1631 {


    public int minimumEffortPath(int[][] heights) {
        int total = heights.length * heights[0].length;
        int[] moveArr = new int[]{-1, 1, heights[0].length, -heights[0].length};
        Map<Integer, Integer> costMap = new HashMap<>(total);
        Queue<Integer> changeSet = new LinkedList<>();
        changeSet.add(0);
        costMap.put(0, 0);
        int nextPoint;
        int sourceCost = 0;
        int newCost = 0;
        while (changeSet.size() > 0) {
            Integer point = changeSet.remove();
            for (int move : moveArr) {
                nextPoint = point + move;
                if (nextPoint < 0 || nextPoint >= total) {
                    continue;
                }
                sourceCost = costMap.getOrDefault(nextPoint, 10000000);
                newCost = Math.abs(
                        heights[point / heights[0].length][point % heights[0].length] - heights[nextPoint / heights[0].length][nextPoint % heights[0].length]
                );
                if (newCost < sourceCost) {
                    changeSet.add(nextPoint);
                    costMap.put(nextPoint, newCost);
                }
            }
        }
        return costMap.get(total - 1);
    }

}
