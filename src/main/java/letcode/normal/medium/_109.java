package letcode.normal.medium;

/**
 * 109. Convert Sorted List to Binary Search Tree
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/convert-sorted-list-to-binary-search-tree/
 * <p>
 * Given the head of a singly linked list where elements are sorted in ascending order , convert it to
 * a height-balanced binary search tree .
 * <p>
 * Example 1:
 * <p>
 * Input: head = [-10,-3,0,5,9]
 * Output: [0,-3,9,-10,null,5]
 * Explanation: One possible answer is [0,-3,9,-10,null,5], which represents the shown height balanced
 * BST.
 * <p>
 * Example 2:
 * <p>
 * Input: head = []
 * Output: []
 * <p>
 * Constraints:
 * <p>
 * - The number of nodes in head is in the range [0, 2 * 10 4 ] .
 * <p>
 * - -10 5 <= Node.val <= 10 5
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {
 }
 *     ListNode(int val) {
 this.val = val;
 }
 *     ListNode(int val, ListNode next) {
 this.val = val;
 this.next = next;
 }
 *
 }
 */

import letcode.utils.ListNode;
import letcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;


public class _109 {

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        List<Integer> nums = new ArrayList<>();
        ListNode root = head;
        while (root != null) {
            nums.add(root.val);
            root = root.next;
        }
        return build(nums, 0, nums.size() - 1);
    }

    private TreeNode build(List<Integer> nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) >> 1;
        TreeNode root = new TreeNode();
        root.val = nums.get(mid);
        if (left == right) {
            return root;
        }
        root.left = build(nums, left, mid - 1);
        root.right = build(nums, mid + 1, right);
        return root;
    }



}
