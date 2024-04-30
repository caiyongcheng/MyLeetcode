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

import letcode.utils.FormatUtils;

import java.util.ArrayList;
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
        System.out.println(FormatUtils.formatList(new _802EightHundredTwo().eventualSafeNodes(
                new int[][]{{}, {0, 2, 3, 4}, {3}, {4}, {}}
        )));
    }

}
