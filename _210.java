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

import datastructure.utils.FormatPrintUtils;

import java.util.LinkedList;
import java.util.Queue;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi]
 * indicates that you must take course bi first if you want to take course ai.  For example,
 * the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses.
 * If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
 *
 * @author CaiYongcheng
 * @date 2021-12-23 10:57
 **/
public class _210 {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //看作有向图 拓扑排序即可
        int[][] grap = new int[numCourses][numCourses];
        int[] ans = new int[numCourses];
        int index = 0;
        for (int[] prerequisite : prerequisites) {
            grap[prerequisite[1]][prerequisite[0]] = 1;
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        boolean isStart = false;
        for (int col = 0; col < grap.length; col++) {
            isStart = true;
            for (int row = 0; row < grap.length; row++) {
                if (grap[row][col] == 1) {
                    isStart = false;
                    break;
                }
            }
            if (isStart) {
                queue.add(col);
            }
        }
        while (!queue.isEmpty()) {
            Integer start = queue.poll();
            for (int next = 0; next < grap[start].length; next++) {
                if (grap[start][next] == 1) {
                    isStart = true;
                    for (int row = 0; row < grap.length; row++) {
                        if (row != start && grap[row][next] == 1) {
                            isStart = false;
                            break;
                        }
                    }
                    if (isStart) {
                        queue.add(next);
                    }
                }
                grap[start][next] = 0;
            }
            ans[index++] = start;
        }
        for (int[] ints : grap) {
            for (int anInt : ints) {
                if (anInt == 1) {
                    return new int[]{};
                }
            }
        }
        return ans;
    }


    /**
     * Example 1:
     * <p>
     * Input: numCourses = 2, prerequisites = {{1,0}}
     * Output: {0,1}
     * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is {0,1}.
     * Example 2:
     * <p>
     * Input: numCourses = 4, prerequisites = {{1,0},{2,0},{3,1},{3,2}}
     * Output: {0,2,1,3}
     * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
     * So one correct course order is {0,1,2,3}. Another correct ordering is {0,2,1,3}.
     * Example 3:
     * <p>
     * Input: numCourses = 1, prerequisites = {}
     * Output: {0]
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(FormatPrintUtils.formatArray(new _210().findOrder(
                4,
                new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}
        )));
    }


}
