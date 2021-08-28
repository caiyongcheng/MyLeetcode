package letcode.normal.medium;

import letcode.utils.FormatPrintUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个有n个节点的 有向无环图（DAG），
 * 请你找出所有从节点 0到节点 n-1的路径并输出（不要求按特定顺序）  
 * 二维数组的第 i 个数组中的单元都表示有向图中 i 号节点所能到达的下一些节点，空就是没有下一个结点了。  
 * 译者注：有向图是有方向的，即规定了 a→b 你就不能从 b→a 。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/all-paths-from-source-to-target 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-08-25 09:38
 **/
public class _797SevenHundredNinetySeven {

    private List<List<Integer>> allPath;
    int[][] map;
    int[] visitable;
    int endPoint;

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        allPath = new ArrayList<>(0);
        int length = graph.length;
        map = new int[length][length];
        visitable = new int[length];
        endPoint = length - 1;
        for (int point = 0; point < graph.length; point++) {
            for (int nextPoint : graph[point]) {
                map[point][nextPoint] = 1;
            }
        }
        List<Integer> path = new ArrayList<>();
        path.add(0);
        visitable[0] = 1;
        dfs(path, 0);
        return allPath;
    }


    public void dfs(List<Integer> nowPath, int nowPoint) {
        if (nowPoint == endPoint) {
            allPath.add(new ArrayList<>(nowPath));
            return;
        }
        for (int nextPoint = 0; nextPoint < map[nowPoint].length; nextPoint++) {
            if (map[nowPoint][nextPoint] == 1 && visitable[nextPoint] == 0) {
                nowPath.add(nextPoint);
                visitable[nextPoint] = 1;
                dfs(nowPath, nextPoint);
                nowPath.remove(Integer.valueOf(nextPoint));
                visitable[nextPoint] = 0;
            }
        }
    }

    /**
     * 输入：graph = {{1,2},{3},{3},{}}
     * 输出：{{0,1,3},{0,2,3}}
     * 解释：有两条路径 0 -> 1 -> 3 和 0 -> 2 -> 3
     * 
     * 示例 2：
     * 输入：graph = {{4,3,1},{3,2,4},{3},{4},{}}
     * 输出：{{0,4},{0,3,4},{0,1,3,4},{0,1,2,3,4},{0,1,4}}
     * 
     * 示例 3：
     * 输入：graph = {{1},{}}
     * 输出：{{0,1}}
     * 
     * 示例 4：
     * 输入：graph = {{1,2,3},{2},{3},{}}
     * 输出：{{0,1,2,3},{0,2,3},{0,3}}
     * 
     * 示例 5：
     * 输入：graph = {{1,3},{2},{3},{}}
     * 输出：{{0,1,2,3},{0,3}}
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/all-paths-from-source-to-target
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        List<List<Integer>> lists = new _797SevenHundredNinetySeven().allPathsSourceTarget(
                new int[][]{{1,3},{2},{3},{}}
        );
        for (List<Integer> list : lists) {
            System.out.println(FormatPrintUtils.formatList(list));
        }
    }

}
