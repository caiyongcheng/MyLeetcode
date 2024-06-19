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

package letcode.normal.difficult;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Caiyongcheng
 * @description 给你一棵由 n 个顶点组成的无向树，顶点编号从 1 到 n。青蛙从 顶点 1 开始起跳。规则如下：
 * 在一秒内，青蛙从它所在的当前顶点跳到另一个 未访问 过的顶点（如果它们直接相连）。 青蛙无法跳回已经访问过的顶点。
 * 如果青蛙可以跳到多个不同顶点，那么它跳到其中任意一个顶点上的机率都相同。
 * 如果青蛙不能跳到任何未访问过的顶点上，那么它每次跳跃都会停留在原地。
 * 无向树的边用数组 edges 描述，其中 edges[i] = [ai, bi] 意味着存在一条直接连通 ai 和 bi 两个顶点的边。
 * 返回青蛙在 t 秒后位于目标顶点 target 上的概率。与实际答案相差不超过 10-5 的结果将被视为正确答案。
 * 来源：力扣（LeetCode） 链接：<a href="https://leetcode.cn/problems/frog-position-after-t-seconds">...</a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @since 2023/5/24 9:07
 */
public class _1377 {

    /**
     * 最终概率结果
     */
    BigDecimal ansProbability = BigDecimal.ZERO;
    int times = 0;
    int target = 0;
    int[] visited;
    int[][] map;

    public double frogPosition(int n, int[][] edges, int t, int target) {
        this.visited = new int[n + 1];
        this.map = new int[n + 1][n + 1];
        this.times = t;
        this.target = target;
        for (int[] edge : edges) {
            map[edge[0]][edge[1]] = 1;
            map[edge[1]][edge[0]] = 1;
        }
        visited[1] = 1;
        dfs(1, 0, BigDecimal.ONE);
        return ansProbability.doubleValue();
    }

    /**
     * dfs计算概率和
     *
     * @param current            当前节点
     * @param currentTimes       当前时间
     * @param currentProbability 到达当前节点概率
     */
    public void dfs(int current, int currentTimes, BigDecimal currentProbability) {
        //到达规定时间 判断是否到达目标节点
        if (currentTimes == times) {
            if (current == target) {
                ansProbability = ansProbability.add(currentProbability);
            }
            return;
        }
        //计算下一秒可选择的节点数
        int totalNextCnt = 0;
        for (int next = 0; next < map[current].length; next++) {
            if (this.visited[next] == 0 && map[current][next] == 1) {
                ++totalNextCnt;
            }
        }
        //没有可选择的节点数 并且当前节点在目标节点 则满足条件
        if (totalNextCnt == 0 && current == target) {
            ansProbability = ansProbability.add(currentProbability);
        }
        //遍历下一节点
        for (int next = 0; next < map[current].length; next++) {
            if (this.visited[next] == 0 && map[current][next] == 1) {
                this.visited[next] = 1;
                dfs(next, currentTimes + 1, currentProbability.divide(BigDecimal.valueOf(totalNextCnt), 8, RoundingMode.HALF_DOWN));
                this.visited[next] = 0;
            }
        }
    }


    /**
     * 输入：n = 7, edges = {{1,2},{1,3},{1,7},{2,4},{2,6},{3,5}}, t = 2, target = 4
     * 输出：0.16666666666666666
     * 解释：上图显示了青蛙的跳跃路径。青蛙从顶点 1 起跳，第 1 秒 有 1/3 的概率跳到顶点 2 ，
     * 然后第 2 秒 有 1/2 的概率跳到顶点 4，因此青蛙在 2 秒后位于顶点 4 的概率是 1/3 * 1/2 = 1/6 = 0.16666666666666666 。
     * <p>
     * 输入：n = 7, edges = {{1,2},{1,3},{1,7},{2,4},{2,6},{3,5}}, t = 1, target = 7
     * 输出：0.3333333333333333
     * <p>
     * 7
     * {{1,2},{1,3},{1,7},{2,4},{2,6},{3,5}}
     * 20
     * 6
     * <p>
     * 3
     * {{2,1},{3,2}}
     * 1
     * 2
     * 解释：上图显示了青蛙的跳跃路径。青蛙从顶点 1 起跳，有 1/3 = 0.3333333333333333 的概率能够 1 秒 后跳到顶点 7 。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1377().frogPosition(
                3,
                new int[][]{{2, 1}, {3, 2}},
                1,
                2
        ));
    }

}
