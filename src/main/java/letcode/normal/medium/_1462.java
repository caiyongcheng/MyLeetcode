package letcode.normal.medium;

import letcode.utils.TestCaseOutputUtils;

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
public class _1462 {


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


}
