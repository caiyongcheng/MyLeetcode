package letcode.normal.difficult;

import java.util.*;

/**
 * 给你一个整数数组arr，你一开始在数组的第一个元素处（下标为 0）。
 * 每一步，你可以从下标i跳到下标：
 * i + 1满足：i + 1 < arr.length
 * i - 1满足：i - 1 >= 0 j
 * 满足：arr[i] == arr[j]且i != j 请你返回到达数组最后一个元素的下标处所需的最少操作次数。
 * 注意：任何时候你都不能跳到数组外面。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/jump-game-iv
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-01-21 09:02
 **/
public class _1345 {

    public int minJumps(int[] arr) {
        /*
        将其看作一个无项无权图 求的是起点到终点的最短路径
        采用BFS的传统做法的话 复杂度是O（V+E），根据本题描述可以得出最大复杂度是O(V^2) 会超时
        主要原因是因为值相同的点会构成一个稠密的子图（相同值的点可以相互访问，最坏情况下所有节点的值都相等，由a^2+b^2 < (a+b)^2得知）
        所以稠密子图只需要访问一次既可（通过相同值点的形式），因为BFS每次遍历，步长逐步增加。所以对于点t而言，访问到他的最快方式只能是通过
        左右相邻点（指的数组中）或者是第一个相同值点。
         */
        Map<Integer, List<Integer>> sameIndex = new HashMap<>();
        Set<Integer> visit = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        int[] nowData;
        int nowIndex;
        int nowStep;
        for (int index = 0; index < arr.length; index++) {
            sameIndex.putIfAbsent(arr[index], new ArrayList<>());
            sameIndex.get(arr[index]).add(index);
        }
        queue.offer(new int[]{0, 0});
        while (!queue.isEmpty()) {
            nowData = queue.poll();
            nowIndex = nowData[0];
            nowStep = nowData[1];
            //到达终点
            if (nowIndex == arr.length - 1) {
                return nowStep;
            }
            //表示为已访问
            visit.add(nowIndex);
            ++nowStep;
            //第一次进入稠密子图
            if (sameIndex.containsKey(arr[nowIndex])) {
                for (Integer subMapIndex : sameIndex.get(arr[nowIndex])) {
                    if (!visit.contains(subMapIndex)) {
                        queue.offer(new int[]{subMapIndex, nowStep});
                    }
                }
                sameIndex.remove(arr[nowIndex]);
            }
            //访问相邻点
            if (nowIndex + 1 < arr.length && !visit.contains(nowIndex + 1)) {
                queue.offer(new int[]{nowIndex + 1, nowStep});
            }
            if (nowIndex - 1 > -1 && !visit.contains(nowIndex - 1)) {
                queue.offer(new int[]{nowIndex - 1, nowStep});
            }
        }
        return arr.length;
    }

}
