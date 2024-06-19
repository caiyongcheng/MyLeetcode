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
 * @date 2022-02-16 09:37
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
