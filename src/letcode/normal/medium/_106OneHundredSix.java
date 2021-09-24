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

import java.util.HashMap;

/**
 * 根据一棵树的中序遍历与后序遍历构造二叉树。  注意: 你可以假设树中没有重复的元素。
 *
 * @author CaiYongcheng
 * @date 2021-09-23 09:04
 **/
public class _106OneHundredSix {

    private int[] inorder;
    private int[] postorder;
    private HashMap<Integer, Integer> valToInx;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        /*
         * 同105题
         * 后续遍历中后取出的是根 根据中序遍历判断是根的左节点还是右节点
         * 所以中序遍历要转化成 值->下标的形式
         */
        valToInx = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            valToInx.put(inorder[i], i);
        }
        this.inorder = inorder;
        this.postorder = postorder;
        return createTree(0, inorder.length - 1, 0, postorder.length - 1);
    }


    public TreeNode createTree(int il, int ir, int pl, int pr) {
        if (ir == il) {
            return new TreeNode(inorder[ir]);
        }
        if (ir < il) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[pr]);
        //后序遍历中 最后一个是根节点 根据根节点 在 中序遍历中 划分左右子树
        Integer inx = valToInx.get(postorder[pr]);
        root.left = createTree(il, inx - 1, pl, inx + pl - il - 1);
        root.right = createTree(inx + 1, ir, inx + pl - il, pr - 1);
        return root;
    }

    /**
     * 例如，给出
     * <p>
     * 中序遍历 inorder = [9,3,15,20,7]
     * 后序遍历 postorder = [9,15,7,20,3]
     * 返回如下的二叉树：
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(FormatUtils.formatList(TreeNode.inOrder(root)));
        System.out.println(FormatUtils.formatList(TreeNode.postOrder(root)));
        TreeNode treeNode = new _106OneHundredSix().buildTree(
                new int[]{9, 3, 15, 20, 7},
                new int[]{9, 15, 7, 20, 3}
        );
        System.out.println(FormatUtils.formatList(TreeNode.inOrder(treeNode)));
        System.out.println(FormatUtils.formatList(TreeNode.postOrder(treeNode)));
    }

}
