package letcode.normal.medium;

import java.util.*;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/12/3 22:35
 * description 车上最初有 capacity 个空座位。车 只能 向一个方向行驶（也就是说，不允许掉头或改变方向）
 * 给定整数 capacity 和一个数组 trips ,  trip[i] = [numPassengersi, fromi, toi] 表示第 i 次旅行有 numPassengersi 乘客，
 * 接他们和放他们的位置分别是 fromi 和 toi 。这些位置是从汽车的初始位置向东的公里数。
 * 当且仅当你可以在所有给定的行程中接送所有乘客时，返回 true，否则请返回 false。
 */
public class _1094OneThousandNinetyFour {

    public boolean carPooling(int[][] trips, int capacity) {
/*        Map<Integer, Integer> changeMap = new HashMap<>(trips.length * 2);
        for (int[] trip : trips) {
            changeMap.put(trip[1], changeMap.getOrDefault(trip[1], 0) + trip[0]);
            changeMap.put(trip[2], changeMap.getOrDefault(trip[2], 0) - trip[0]);
        }
        int curCap = 0;
        Iterator<Map.Entry<Integer, Integer>> it = changeMap.entrySet().stream().sorted(Comparator.comparingInt(Map.Entry::getKey)).iterator();
        while (it.hasNext()) {
            curCap += it.next().getValue();
            if (curCap > capacity) {
                return false;
            }
        }*/

        // 根据数据量 优化
        int[] change = new int[1001];
        int farthest = 0;
        for (int[] trip : trips) {
            change[trip[1]] += trip[0];
            change[trip[2]] -= trip[0];
            farthest = Integer.max(farthest, trip[2]+1);
        }
        int curCap = 0;
        for (int i = 0; i < farthest; i++) {
            curCap += change[i];
            if (curCap > capacity) {
                return false;
            }
        }
        return true;
    }

}
