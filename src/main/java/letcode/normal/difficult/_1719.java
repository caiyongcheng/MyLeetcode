package letcode.normal.difficult;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个数组pairs ，其中pairs[i] = [xi, yi]，并且满足：  pairs中没有重复元素 xi < yi
 * 令ways为满足下面条件的有根树的方案数：
 * 树所包含的所有节点值都在 pairs中。
 * 一个数对[xi, yi] 出现在pairs中当且仅当xi是yi的祖先或者yi是xi的祖先。
 * 注意：构造出来的树不一定是二叉树。
 * 两棵树被视为不同的方案当存在至少一个节点在两棵树中有不同的父节点。
 * 请你返回：  如果ways == 0，返回0。 如果ways == 1，返回 1。 如果ways > 1，返回2。
 * 一棵 有根树指的是只有一个根节点的树，所有边都是从根往外的方向。
 * 我们称从根到一个节点路径上的任意一个节点（除去节点本身）都是该节点的 祖先。根节点没有祖先。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/number-of-ways-to-reconstruct-a-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-02-16 09:37
 **/
public class _1719 {

    public int checkWays(int[][] pairs) {
        /*
         *  一个数对[xi, yi] 出现在pairs中当且仅当xi是yi的祖先或者yi是xi的祖先。 这句话的叙述很奇怪
         *  它实际上表达的意思是，如果能构造出一棵树，那么书中节点与他的祖先节点，后代节点所构成的数对都会出现在pairs中
         *
         *  解法来自 https://leetcode-cn.com/problems/number-of-ways-to-reconstruct-a-tree/solution/onmde-luan-gao-zuo-fa-by-weak-chicken-y2mv/
         *
         */
        int size = 501;
        int ans = 1;
        int[] pre = new int[size];
        int[] nums = new int[size];
        boolean[] vis = new boolean[size];
        List<int[]> list = new ArrayList<>();
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            edges.add(new ArrayList<>());
        }
        for (int[] pair : pairs) {
            int u = pair[0];
            int v = pair[1];
            nums[u]++;
            nums[v]++;
            edges.get(u).add(v);
            edges.get(v).add(u);
            pre[u] = pre[v] = -1;
        }
        for (int i = 0; i < size; i++) {
            if (nums[i] == 0) {
                continue;
            }
            list.add(new int[]{i, nums[i]});
        }
        int sz = list.size();
        list.sort((a, b) -> (b[1] - a[1]));
        if (nums[list.get(0)[0]] != sz - 1) {
            return 0;
        }
        for (int[] ints : list) {
            int u = ints[0];
            int len = edges.get(u).size();
            for (int j = 0; j < len; j++) {
                int v = edges.get(u).get(j);
                if (nums[v] == nums[u]) {
                    ans = 2;
                }
                if (!vis[v]) {
                    if (pre[u] != pre[v]) {
                        return 0;
                    }
                    pre[v] = u;
                }
                vis[u] = true;
            }
        }
        return ans;
    }

}
