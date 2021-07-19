package letcode.normal.difficult;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。给你所有建筑物的位置和高度，请返回由这些建筑物形成的 天际线 。
 * 每个建筑物的几何信息由数组 buildings 表示，其中三元组 buildings[i] = [lefti, righti, heighti] 表示：  lefti 是第 i 座建筑物左边缘的 x 坐标。
 * righti 是第 i 座建筑物右边缘的 x 坐标。 heighti 是第 i 座建筑物的高度。 天际线 应该表示为由 “关键点” 组成的列表，格式 [[x1,y1],[x2,y2],...] ，并按 x 坐标 进行 排序 。
 * 关键点是水平线段的左端点。列表中最后一个点是最右侧建筑物的终点，y 坐标始终为 0 ，仅用于标记天际线的终点。此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。
 * 注意：输出天际线中不得有连续的相同高度的水平线。例如 [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 是不正确的答案；三条高度为 5 的线应该在最终输出中合并为一个：[...[2 3], [4 5], [12 7], ...]
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/the-skyline-problem 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-07-13 09:10
 **/
public class _218TwoHundredEighteen {



    public List<List<Integer>> getSkyline(int[][] buildings) {
        /**
         * 首先 将建筑是否 重叠 划分为 几部分
         * 最终结果 = 每一部分的天际线 + 每一部分右侧（x，0）的点
         * 问题 1 怎么划分部分 每一部分 从左向右 判断右侧建筑与左侧是否重叠 是的话表明是同一建筑
         * 问题 2 怎么判断部分的关键点 该部分建筑的关键点是水平线的左端点
         *                          模拟 假设有根线段向下压 线段与部分建筑重合的水平线左端点就是关键点
         *                          最后加上部分建筑的右端点
         */

        //排序
        Arrays.sort(buildings, (o1, o2) -> {
            if (o1[0] < o2[0]) {
                return 1;
            }
            if (o1[0] > o2[0]) {
                return -1;
            }
            if (o1[1] < o2[1]) {
                return 1;
            }
            if (o1[1] > o2[1]) {
                return 1;
            }
            return 0;
        });
        //最终结果
        List<List<Integer>> ans = new ArrayList<>();
        //部分建筑
        List<int[]> partBuilding = null;
        //部分建筑的左右端点
        int left = -1, right = 0;
        //循环遍历 划分部分
        for (int[] building : buildings) {
            if (building[0] > left) {
                //如果有前一个部分，处理前一个部分
                if (partBuilding != null && !partBuilding.isEmpty()) {
                    ans.add(getPartSky(partBuilding, left, right));
                }
                //建立新部分
                partBuilding = new ArrayList<>();
                partBuilding.add(building);
                left = building[0];
                right = building[1];
                continue;
            }
            //加入当前部分
            partBuilding.add(building);
            if (building[1] > right) {
                right = building[1];
            }
        }
        //处理最后一部分
        if (partBuilding != null && !partBuilding.isEmpty()) {
            ans.add(getPartSky(partBuilding, left, right));
        }
        return ans;
    }

    public List<Integer> getPartSky(List<int[]> partBuilding, int left, int right) {
        //按高度分组排序
        Map<Integer, List<int[]>> map = partBuilding.stream().collect(Collectors.groupingBy(o -> o[2]));
        List<Integer> sortKey = map.keySet().stream().sorted(Comparator.comparingInt(o -> o))
                .collect(Collectors.toList());
        for (int i = sortKey.size() - 1; i >= 0; i--) {
            List<int[]> buildingByHigh = map.get(sortKey.get(i));
        }

        //从高到低遍历
        for (int i = partBuilding.size() - 1; i >= 0; i--) {

        }
        return null;
    }


}
