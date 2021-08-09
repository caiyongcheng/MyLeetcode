package letcode.normal.medium;

import letcode.utils.FormatPrintUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 在有向图中，以某个节点为起始节点，从该点出发，每一步沿着图中的一条有向边行走。如果到达的节点是终点（即它没有连出的有向边），则停止。 
 * 对于一个起始节点，如果从该节点出发，无论每一步选择沿哪条有向边行走，最后必然在有限步内到达终点，则将该起始节点称作是 安全 的。  
 * 返回一个由图中所有安全的起始节点组成的数组作为答案。答案数组中的元素应当按 升序 排列。  
 * 该有向图有 n 个节点，按 0 到 n - 1 编号，其中 n 是graph的节点数。图以下述形式给出：graph[i] 是编号 j 节点的一个列表，满足 (i, j) 是图的一条有向边。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/find-eventual-safe-states 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-08-05 09:11
 **/
public class _802EightHundredTwo {

    public List<Integer> eventualSafeNodes(int[][] graph) {
        /*
        安全节点的定义 ：
        起始节点，如果从该节点出发，无论每一步选择沿哪条有向边行走，最后必然在有限步内到达终点，则将该起始节点称作是安全的。
        也就是从某一个起始节点开始 如果成环了 那么该节点就是 不安全的起始节点
         */
        HashSet<Integer> passPath = new HashSet<>(graph.length);
        HashSet<Integer> circlePoints = new HashSet<>(graph.length);
        HashSet<Integer> unCirclePoint = new HashSet<>(graph.length);
        ArrayList<Integer> safeStartPoints = new ArrayList<>();
        //循环遍历
        for (int startPoint = 0; startPoint < graph.length; ++startPoint) {
            passPath.clear();
            if (unCirclePoint.contains(startPoint)) {
                safeStartPoints.add(startPoint);
                continue;
            }
            if (!circlePoints.contains(startPoint) &&
                    bfs(startPoint, graph, circlePoints, unCirclePoint, passPath)) {
                safeStartPoints.add(startPoint);
            }
        }
        return safeStartPoints;
    }

    public boolean bfs(int nowPoint, int[][] graph, HashSet<Integer> circlePoints, HashSet<Integer> unCirclePoint,
                       HashSet<Integer> passPath) {
        passPath.add(nowPoint);
        for (int nextPoint : graph[nowPoint]) {
            if (circlePoints.contains(nextPoint) || passPath.contains(nextPoint)) {
                circlePoints.addAll(passPath);
                return false;
            }
            if (unCirclePoint.contains(nextPoint)) {
                continue;
            }
            if (!bfs(nextPoint, graph, circlePoints, unCirclePoint, passPath)) {
                return false;
            } else {
                passPath.remove(nextPoint);
            }
        }
        unCirclePoint.add(nowPoint);
        return true;
    }


    /**
     * 输入：graph = {{1,2},{2,3},{5},{0},{5},{},{}}
     * 输出：{2,4,5,6}
     * 解释：示意图如上。
     * 
     * 示例 2：
     * 输入：graph = {{1,2,3,4},{1,2},{3,4},{0,4},{}}
     * 输出：{4}
     *
     * 输入：
     * {{},{0,2,3,4},{3},{4},{}}
     * 输出：
     * {0,2,3,4}
     * 预期结果：
     * {0,1,2,3,4}
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-eventual-safe-states
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(FormatPrintUtils.formatList(new _802EightHundredTwo().eventualSafeNodes(
                new int[][]{{},{0,2,3,4},{3},{4},{}}
        )));
    }

}
