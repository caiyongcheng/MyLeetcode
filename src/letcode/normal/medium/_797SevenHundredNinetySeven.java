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
