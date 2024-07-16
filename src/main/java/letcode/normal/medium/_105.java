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
import letcode.utils.TreeNode;

/**
 * 给定一棵树的前序遍历 preorder 与中序遍历  inorder。请构造二叉树并返回其根节点。
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder和inorder均无重复元素
 * inorder均出现在preorder preorder保证为二叉树的前序遍历序列
 * inorder保证为二叉树的中序遍历序列
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-09-18 09:20
 **/
public class _105 {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        /*
        前序遍历是 根 左 右 也即是先根节点再子节点
        中序遍历是 左 根 右 根据前序遍历拿出根节点，根据中序遍历判断是左节点 还是右节点
         */
        int[] valToIndex = new int[6001];
        for (int index = 0; index < inorder.length; index++) {
            valToIndex[inorder[index] + 3000] = index;
        }
        TreeNode root = new TreeNode(preorder[0]);
        TreeNode child;
        TreeNode parent = root;
        for (int index = 1; index < preorder.length; index++) {
            child = new TreeNode(preorder[index]);
            //左节点
            if (valToIndex[child.val + 3000] + 1 == valToIndex[parent.val + 3000]) {
                parent.left = child;
                parent = child;
                continue;
            }
            // 表明child 不是 parent的子节点 需要从root节点开始找父节点
            parent = root;
            while (parent != child) {
                if (valToIndex[child.val + 3000] < valToIndex[parent.val + 3000]) {
                    if (parent.left == null) {
                        parent.left = child;
                        parent = child;
                    } else {
                        parent = parent.left;
                    }
                } else {
                    if (parent.right == null) {
                        parent.right = child;
                        parent = child;
                    } else {
                        parent = parent.right;
                    }
                }
            }
        }
        return root;
    }


    /**
     * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
     * Output: [3,9,20,null,null,15,7]
     * <p>
     * 示例 2:
     * Input: preorder = [-1], inorder = [-1]
     * Output: [-1]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(-1);
        System.out.println(FormatUtils.formatList(TreeNode.preOrder(root)));
        System.out.println(FormatUtils.formatList(TreeNode.inOrder(root)));
        TreeNode treeNode = new _105().buildTree(
                new int[]{-1},
                new int[]{-1}
        );
        System.out.println(FormatUtils.formatList(TreeNode.preOrder(treeNode)));
        System.out.println(FormatUtils.formatList(TreeNode.inOrder(treeNode)));
    }

}
