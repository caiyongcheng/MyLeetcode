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

import letcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 给你二叉树的根结点 root ，请你设计算法计算二叉树的 垂序遍历 序列。  对位于(row, col)的每个结点而言，其左右子结点分别位于(row + 1, col - 1)和(row + 1, col + 1) 。
 * 树的根结点位于 (0, 0) 。  二叉树的 垂序遍历 从最左边的列开始直到最右边的列结束，按列索引每一列上的所有结点，形成一个按出现位置从上到下排序的有序列表。
 * 如果同行同列上有多个结点，则按结点的值从小到大进行排序。  返回二叉树的 垂序遍历 序列。 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/vertical-order-traversal-of-a-binary-tree 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-07-31 17:21
 **/
public class _987 {

    class TreeNodeWrap {
        int val;
        int row;
        int col;

        public TreeNodeWrap(int val, int row, int col) {
            this.val = val;
            this.row = row;
            this.col = col;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        /**
         * 朴素解法
         * 保存每个节点的值以及位置 按列分组 再排序
         * 最后返回结果即可
         */
        ArrayList<List<Integer>> ans = new ArrayList<>();
        List<TreeNodeWrap> wrapList = new ArrayList<>();
        dps(wrapList, root, 0, 0);
        Map<Integer, List<TreeNodeWrap>> colMap = wrapList.stream().collect(Collectors.groupingBy(TreeNodeWrap::getCol));
        List<Integer> colList = colMap.keySet().stream().sorted(Integer::compare).collect(Collectors.toList());
        for (Integer col : colList) {
            ans.add(colMap.getOrDefault(col, new ArrayList<>()).stream().sorted(Comparator.comparingInt(TreeNodeWrap::getRow).thenComparing(TreeNodeWrap::getVal))
                    .map(TreeNodeWrap::getVal).collect(Collectors.toList()));
        }
        return ans;
    }

    public void dps(List<TreeNodeWrap> wrapList, TreeNode parent, int parentRow, int parentCol) {
        if (parent == null) {
            return;
        }
        wrapList.add(new TreeNodeWrap(parent.val, parentRow, parentCol));
        dps(wrapList, parent.left, parentRow+1, parentCol-1);
        dps(wrapList, parent.right, parentRow+1, parentCol+1);
    }
    
    
    
}
