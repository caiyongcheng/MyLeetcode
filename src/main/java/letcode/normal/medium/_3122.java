package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.*;

/**
 * There is an undirected graph of n nodes.
 * You are given a 2D array edges, where edges[i] = [ui, vi, lengthi] describes an edge between
 * node ui and node vi with a traversal time of lengthi units.
 * Additionally, you are given an array disappear, where disappear[i] denotes the time when the node i disappears
 * from the graph and you won't be able to visit it.  Notice that the graph might be disconnected and might contain
 * multiple edges.  Return the array answer, with answer[i] denoting the minimum units of time required to reach node
 * i from node 0. If node i is unreachable from node 0 then answer[i] is -1.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-07-19 15:16
 */
public class _3122 {

    public int[] minimumTime1(int n, int[][] edges, int[] disappear) {
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(new int[]{edge[1], edge[2]});
            graph[edge[1]].add(new int[]{edge[0], edge[2]});
        }

        int[] visited = new int[n];
        Arrays.fill(visited, -1);
        visited[0] = 0;
        Queue<int[]> nextQueue = new PriorityQueue<>(n, (a, b) -> a[0] - b[0]);
        nextQueue.offer(new int[]{0, 0});
        while (!nextQueue.isEmpty()) {
            int[] cur = nextQueue.poll();
            // 优先队列 + 减枝 不加就超时
            if (cur[1] > visited[cur[0]]) {
                continue;
            }
            for (int[] next : graph[cur[0]]) {
                if (cur[1] + next[1] < disappear[next[0]] && (visited[next[0]] == -1 || visited[next[0]] > cur[1] + next[1])) {
                    visited[next[0]] = cur[1] + next[1];
                    nextQueue.offer(new int[]{next[0], visited[next[0]]});
                }
            }
        }
        return visited;
    }

    public int[] minimumTime(int n, int[][] edges, int[] disappear) {
        Map<Integer, Integer>[] graph = new HashMap[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new HashMap<>();
        }

        for (int[] edge : edges) {
            Integer time = graph[edge[0]].getOrDefault(edge[1], Integer.MAX_VALUE);
            if (time > edge[2]) {
                graph[edge[0]].put(edge[1], edge[2]);
            }
            time = graph[edge[1]].getOrDefault(edge[0], Integer.MAX_VALUE);
            if (time > edge[2]) {
                graph[edge[1]].put(edge[0], edge[2]);
            }
        }

        int[] visited = new int[n];
        Arrays.fill(visited, -1);
        visited[0] = 0;
        bfs(0, 0, graph, disappear, visited);
        return visited;
    }


    private void bfs(int point, int passTime, Map<Integer, Integer>[] graph, int[] disappear, int[] visited) {
        if (visited[point] != -1 && visited[point] < passTime) {
            return;
        }
        List<Integer> nextPointList = new ArrayList<>();
        Integer nextPoint;
        Integer costTime;
        int nextNeedTime;
        for (Map.Entry<Integer, Integer> edge : graph[point].entrySet()) {
            nextPoint = edge.getKey();
            costTime = edge.getValue();
            nextNeedTime = costTime + passTime;
            if (nextNeedTime < disappear[nextPoint] && (visited[nextPoint] == -1 || visited[nextPoint] > nextNeedTime)) {
                visited[nextPoint] = nextNeedTime;
                nextPointList.add(nextPoint);
            }
        }
        for (Integer visitPoint : nextPointList) {
            bfs(visitPoint, visited[visitPoint], graph, disappear, visited);
        }
    }




}
