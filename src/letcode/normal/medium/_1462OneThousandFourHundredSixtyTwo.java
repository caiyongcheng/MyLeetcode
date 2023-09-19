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
import java.util.List;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/9/12 16:19
 * description 你总共需要上 numCourses 门课，课程编号依次为 0 到 numCourses-1 。
 * 你会得到一个数组 prerequisite ，其中 prerequisites[i] = [ai, bi] 表示如果你想选 bi 课程，你 必须 先选 ai 课程。
 * 有的课会有直接的先修课程，比如如果想上课程 1 ，你必须先上课程 0 ，那么会以 [0,1] 数对的形式给出先修课程数对。
 * 先决条件也可以是 间接 的。如果课程 a 是课程 b 的先决条件，课程 b 是课程 c 的先决条件，那么课程 a 就是课程 c 的先决条件。
 * 你也得到一个数组 queries ，其中 queries[j] = [uj, vj]。对于第 j 个查询，您应该回答课程 uj 是否是课程 vj 的先决条件。
 * 返回一个布尔数组 answer ，其中 answer[j] 是第 j 个查询的答案。
 * <p>
 * 2 <= numCourses <= 100
 * 0 <= prerequisites.length <= (numCourses * (numCourses - 1) / 2)
 * prerequisites[i].length == 2
 * 0 <= ai, bi <= n - 1
 * ai != bi
 * 每一对 [ai, bi] 都 不同
 * 先修课程图中没有环。
 * 1 <= queries.length <= 104
 * 0 <= ui, vi <= n - 1
 * ui != vi
 */
public class _1462OneThousandFourHundredSixtyTwo {


    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        //表示依赖于key
        int[][] map = new int[numCourses][numCourses];
        //表示该路径是否完全处理
        int[] visitArr = new int[numCourses];
        for (int[] rel : prerequisites) {
            map[rel[1]][rel[0]] = 1;
        }
        //解答的时候再处理简介关系 减少不必要的处理
        List<Boolean> ans = new ArrayList<>();
        for (int[] query : queries) {
            if (map[query[1]][query[0]] == 1) {
                ans.add(true);
                continue;
            }
            if (visitArr[query[0]] == 1) {
                ans.add(false);
                continue;
            }
            createRel(map, visitArr, query[0]);
            ans.add(map[query[1]][query[0]] == 1);
        }
        return ans;
    }

    public void createRel(int[][] map, int[] visitArr, Integer parent) {
        visitArr[parent] = 1;
        for (int child = 0; child < map.length; child++) {
            if (map[child][parent] == 0) {
                continue;
            }
            if (visitArr[child] == 1) {
                for (int childChild = 0; childChild < map.length; childChild++) {
                    if (map[childChild][child] == 1) {
                        map[childChild][parent] = 1;
                    }
                }
                continue;
            }
            createRel(map, visitArr, child);
            for (int childChild = 0; childChild < map.length; childChild++) {
                if (map[childChild][child] == 1) {
                    map[childChild][parent] = 1;
                }
            }
        }
    }


    /**
     * 输入：numCourses = 2, prerequisites = {{1,0}}, queries = {{0,1},{1,0}}
     * 输出：{false,true}
     * 解释：课程 0 不是课程 1 的先修课程，但课程 1 是课程 0 的先修课程。
     * 示例 2：
     * <p>
     * 输入：numCourses = 2, prerequisites = {}, queries = {{1,0},{0,1}}
     * 输出：{false,false}
     * 解释：没有先修课程对，所以每门课程之间是独立的。
     * <p>
     * 输入：numCourses = 3, prerequisites = {{1,2},{1,0},{2,0}}, queries = {{1,0},{1,2}}
     * 输出：{true,true}
     * <p>
     * {{2,3},{2,1},{2,0},{3,4},{3,6},{5,1},{5,0},{1,4},{1,0},{4,0},{0,6}}
     * <p>
     * {{3,0},{6,4},{5,6},{2,6},{2,3},{5,6},{4,0},{2,6},{3,5},{5,3},{1,6},{1,0},{3,5},{6,5},{2,3},{3,0},{3,4},{3,4},{2,5},{0,3},{4,0},{6,4},{5,0},{6,5},{5,6},{6,5},{1,0},{3,4},{1,5},{1,4},{3,6},{0,1},{1,2},{5,1},{5,3},{5,3},{3,4},{5,4},{5,4},{5,3}}
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(FormatUtils.formatList(new _1462OneThousandFourHundredSixtyTwo().checkIfPrerequisite(
                7,
                new int[][]{{2, 3}, {2, 1}, {2, 0}, {3, 4}, {3, 6}, {5, 1}, {5, 0}, {1, 4}, {1, 0}, {4, 0}, {0, 6}},
                new int[][]{{3, 0}, {6, 4}, {5, 6}, {2, 6}, {2, 3}, {5, 6}, {4, 0}, {2, 6}, {3, 5}, {5, 3}, {1, 6}, {1, 0}, {3, 5}, {6, 5}, {2, 3}, {3, 0}, {3, 4}, {3, 4}, {2, 5}, {0, 3}, {4, 0}, {6, 4}, {5, 0}, {6, 5}, {5, 6}, {6, 5}, {1, 0}, {3, 4}, {1, 5}, {1, 4}, {3, 6}, {0, 1}, {1, 2}, {5, 1}, {5, 3}, {5, 3}, {3, 4}, {5, 4}, {5, 4}, {5, 3}}
        )));
    }


}
