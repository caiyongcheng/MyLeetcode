package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * There is an undirected tree with n nodes labeled from 0 to n - 1, and rooted at node 0. You are given a 2D integer array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.  A node is good if all the  subtrees  rooted at its children have the same size.  Return the number of good nodes in the given tree.  A subtree of treeName is a tree consisting of a node in treeName and all of its descendants.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-11-14 11:00
 */
public class _3249 {

    public int countGoodNodes(int[][] edges) {
        List<Integer>[] map = new ArrayList[edges.length + 1];
        for (int[] edge : edges) {
            relation(edge, map, 0, 1);
            relation(edge, map, 1, 0);
        }
        return dps(map, 0, -1)[1];
    }

    private static void relation(int[] edge, List<Integer>[] map, int parentIdx, int childIdx) {
        List<Integer> childPointList = map[edge[parentIdx]];
        if (null == childPointList) {
            childPointList = new ArrayList<>();
        }
        childPointList.add(edge[childIdx]);
        map[edge[parentIdx]] = childPointList;
    }


    private int[] dps(List<Integer>[] map, int curPoint, int parentPoint) {
        List<Integer> nextPointList = map[curPoint];
        if (parentPoint != -1 && nextPointList.size() == 1) {
            return new int[]{1, 1};
        }
        int curTreePointCnt = 1;
        int childTreePointCnt = -1;
        int subTreeHasSamePointCnt = 1;
        int subGoodPointCnt = 0;
        for (Integer nextPoint : nextPointList) {
            if (nextPoint == parentPoint) {
                continue;
            }
            int[] subRes = dps(map, nextPoint, curPoint);
            curTreePointCnt += subRes[0];
            subGoodPointCnt += subRes[1];
            if (childTreePointCnt == -1) {
                childTreePointCnt = subRes[0];
            } else if (childTreePointCnt != subRes[0]) {
                subTreeHasSamePointCnt = 0;
            }
        }
        return new int[]{curTreePointCnt, subGoodPointCnt + subTreeHasSamePointCnt};
    }

    /**
     * Example 1:
     *
     * Input: edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]]
     *
     * Output: 7
     *
     * Explanation:
     *
     *
     * All of the nodes of the given tree are good.
     *
     * Example 2:
     *
     * Input: edges = [[0,1],[1,2],[2,3],[3,4],[0,5],[1,6],[2,7],[3,8]]
     *
     * Output: 6
     *
     * Explanation:
     *
     *
     * There are 6 good nodes in the given tree. They are colored in the image above.
     *
     * Example 3:
     *
     * Input: edges = [[0,1],[1,2],[1,3],[1,4],[0,5],[5,6],[6,7],[7,8],[0,9],[9,10],[9,12],[10,11]]
     *
     * Output: 12
     *
     * Explanation:
     *
     *
     * All nodes except node 9 are good.
     * @param args
     */
    public static void main(String[] args) {
        //TestUtil.test(_3249.class);
        TestUtil.test(_3249.class, "=[[2,0],[4,2],[1,2],[3,1],[5,1]]");
    }

}
