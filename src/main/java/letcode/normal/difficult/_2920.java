package letcode.normal.difficult;

import letcode.utils.TestUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* There exists an undirected tree rooted at node 0 with n nodes labeled from 0 to n - 1.
 * You are given a 2D integer array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an
 * edge between nodes ai and bi in the tree. You are also given a 0-indexed array coins of size n where coins[i]
 * indicates the number of coins in the vertex i, and an integer k.  Starting from the root, you have to collect all
 * the coins such that the coins at a node can only be collected if the coins of its ancestors have been already
 * collected.  Coins at nodei can be collected in one of the following ways:  Collect all the coins, but you will
 * get coins[i] - k points. If coins[i] - k is negative then you will lose abs(coins[i] - k) points. Collect all
 * the coins, but you will get floor(coins[i] / 2) points. If this way is used, then for all the nodej present in
 * the subtree of nodei, coins[j] will get reduced to floor(coins[j] / 2). Return the maximum points you can get
 * after collecting the coins from all the tree nodes.
* @version  1.0.0
* @author   蔡永程  
* @since    2025-02-10 10:44
*/
public class _2920 {

    public int maximumPoints(int[][] edges, int[] coins, int k) {
        /*
            1、如果当前节点 >= 2k, 那么显然第一种选择更具有优势
            2、根据数据规模，金币最大值不超过10000，那么进行14次操作2后，节点及其子节点，金币数量就都是0了，此时选择操作1结果为负数，选择
               操作2结果都是0，故此时直接返回0即可。
         */
        int[][] cache = new int[edges.length + 1][14];
        for (int[] pointCache : cache) {
            Arrays.fill(pointCache, -1);
        }
        // 构造树
        List<Integer>[] tree = new ArrayList[edges.length + 1];
        for (int[] edge : edges) {
            if (tree[edge[0]] == null) {
                tree[edge[0]] = new ArrayList<>();
            }
            tree[edge[0]].add(edge[1]);
            if (tree[edge[1]] == null) {
                tree[edge[1]] = new ArrayList<>();
            }
            tree[edge[1]].add(edge[0]);
        }
        dfs(tree, cache, 0, -1, coins, k, 0);
        int ans = 0;
        for (int i = 0; i < cache[0].length; i++) {
            ans = Math.max(ans, cache[0][i]);
        }
        return ans;
    }

    private int dfs(List<Integer>[] tree, int[][] cache, int curPoint, int parentPoint, int[] coins, int k, int rate) {
        if (rate >= 14) {
            return 0;
        }
        if (cache[curPoint][rate] != -1) {
            return cache[curPoint][rate];
        }

        int curPointCoin = coins[curPoint] >> rate;

        int useOneOptGetCoins = curPointCoin - k;
        for (int nextPoint : tree[curPoint]) {
            if (nextPoint == parentPoint) {
                continue;
            }
            useOneOptGetCoins += dfs(tree, cache, nextPoint, curPoint, coins, k, rate);
        }
        if (curPointCoin >= k << 1) {
            cache[curPoint][rate] = useOneOptGetCoins;
            return useOneOptGetCoins;
        }

        // 考虑第二种方法，获取当前金币的二分之一，并递归获取子树金币
        int useTwoOptGetCoins = curPointCoin >> 1;
        for (int nextPoint : tree[curPoint]) {
            if (nextPoint == parentPoint) {
                continue;
            }
            useTwoOptGetCoins += dfs(tree, cache, nextPoint, curPoint, coins, k, rate + 1);
        }

        int maxCoins = Math.max(useOneOptGetCoins, useTwoOptGetCoins);
        cache[curPoint][rate] = maxCoins;
        return maxCoins;
    }


}
