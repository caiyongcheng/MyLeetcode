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
 * @date 2021-07-31 17:21
 **/
public class _987NineHundredAndEightySeven {

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
