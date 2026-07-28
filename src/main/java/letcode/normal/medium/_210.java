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
 * @since 2021-12-23 10:57
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


}
