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

import letcode.utils.TreeNode;

import java.util.*;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/9/6 9:50
 * description 给你一个有根节点 root 的二叉树，返回它 最深的叶节点的最近公共祖先 。
 * 回想一下：  叶节点 是二叉树中没有子节点的节点 树的根节点的 深度 为 0，如果某一节点的深度为 d，
 * 那它的子节点的深度就是 d+1 如果我们假定 A 是一组节点 S 的 最近公共祖先，S 中的每个节点都在以 A 为根节点的子树中，
 * 且 A 的深度达到此条件下可能的最大值。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中的节点数将在 [1, 1000] 的范围内。
 * 0 <= Node.val <= 1000
 * 每个节点的值都是 独一无二 的。
 */
public class _1123OneThousandOneHundredTwentyThree {

    Map<Integer, TreeNode> id2TreeNode;
    Map<Integer, Integer> id2TreeHigh;
    Map<Integer, Integer> id2ParentId;

    int[] empty = new int[]{-1, -1};


    int maxHigh = 0;

    public TreeNode lcaDeepestLeaves2(TreeNode root) {
        /**
         * 如果根节点x 的左子树l高和右子树r高一致 那么加上x构成的树即满足条件 也就是答案是x
         * 如果两边字数不一样高 那么高的字数就满足答案 不停的递归分解子问题 即可得到最终答案
         */
        id2TreeNode = new HashMap<>(128);
        return id2TreeNode.get(search2(root, 0)[0]);
    }


    public int[] search2(TreeNode root, int high) {
        id2TreeNode.put(root.val, root);
        if (Objects.isNull(root.right) && Objects.isNull(root.left)) {
            return new int[]{root.val, high};
        }
        int[] leftAns = Objects.isNull(root.left) ? empty : search2(root.left, high + 1);
        int[] rightAns = Objects.isNull(root.right) ? empty : search2(root.right, high + 1);
        return leftAns[1] == rightAns[1]
                ? new int[]{root.val, leftAns[1]}
                : (leftAns[1] > rightAns[1] ? leftAns : rightAns);
    }


    public TreeNode lcaDeepestLeaves(TreeNode root) {
        /*
        根据题目提示 可以使用节点值作为节点id
        遍历树 保存子节点树高 以及父节点id 依次向上回溯即可
         */
        id2TreeNode = new HashMap<>(128);
        id2TreeHigh = new HashMap<>(128);
        id2ParentId = new HashMap<>(128);
        search(root, 0);

        //找出最深叶子节点
        return findDeepNode();
    }

    private void search(TreeNode treeNode, int high) {
        id2TreeNode.put(treeNode.val, treeNode);
        if (Objects.nonNull(treeNode.left)) {
            id2ParentId.put(treeNode.left.val, treeNode.val);
            search(treeNode.left, high + 1);
        }
        if (Objects.nonNull(treeNode.right)) {
            id2ParentId.put(treeNode.right.val, treeNode.val);
            search(treeNode.right, high + 1);
        }
        if (Objects.isNull(treeNode.left) && Objects.isNull(treeNode.right) && high >= maxHigh) {
            if (high > maxHigh) {
                id2TreeHigh.clear();
                maxHigh = high;
            }
            id2TreeHigh.put(treeNode.val, high);
        }
    }


    private TreeNode findDeepNode() {
        List<Integer> deepNodeIdList = new ArrayList<>(id2TreeHigh.keySet());
        //只有一个最深节点
        if (deepNodeIdList.size() == 1) {
            return id2TreeNode.get(deepNodeIdList.get(0));
        }
        //所有节点依次迭代 直到父节点相等为止
        while (true) {
            //哨兵 减少判断
            boolean same = true;
            int preNodeParentId = 0;
            for (int i = 0; i < deepNodeIdList.size(); i++) {
                Integer parentId = id2ParentId.get(deepNodeIdList.get(i));
                same = same && (preNodeParentId == 0 || parentId == preNodeParentId);
                deepNodeIdList.set(i, parentId);
                preNodeParentId = parentId;
            }
            if (same) {
                return id2TreeNode.get(preNodeParentId);
            }
        }
    }


    /**
     * 输入：root = [3,5,1,6,2,0,8,null,null,7,4]
     * 输出：[2,7,4]
     * 解释：我们返回值为 2 的节点，在图中用黄色标记。
     * 在图中用蓝色标记的是树的最深的节点。
     * 注意，节点 6、0 和 8 也是叶节点，但是它们的深度是 2 ，而节点 7 和 4 的深度是 3 。
     * 示例 2：
     * <p>
     * 输入：root = [1]
     * 输出：[1]
     * 解释：根节点是树中最深的节点，它是它本身的最近公共祖先。
     * 示例 3：
     * <p>
     * 输入：root = [0,1,3,null,2]
     * 输出：[2]
     * 解释：树中最深的叶节点是 2 ，最近公共祖先是它自己。
     *
     * @param args
     */
    public static void main(String[] args) {
        TreeNode root = TreeNode.createUseLeetCode(new Integer[]{0, 1, 3, null, 2});
        System.out.println(new _1123OneThousandOneHundredTwentyThree().lcaDeepestLeaves(root).val);
        System.out.println(new _1123OneThousandOneHundredTwentyThree().lcaDeepestLeaves2(root).val);
    }


}
