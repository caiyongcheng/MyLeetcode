package letcode.normal.medium;

/**
 * 2058. Find the Minimum and Maximum Number of Nodes Between Critical Points
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/find-the-minimum-and-maximum-number-of-nodes-between-critical-points/
 * <p>
 * A critical point in a linked list is defined as either a local maxima or a local minima .
 * <p>
 * A node is a local maxima if the current node has a value strictly greater than the previous node and
 * the next node.
 * <p>
 * A node is a local minima if the current node has a value strictly smaller than the previous node and
 * the next node.
 * <p>
 * Note that a node can only be a local maxima/minima if there exists both a previous node and a next
 * node.
 * <p>
 * Given a linked list head , return an array of length 2 containing [minDistance, maxDistance] where
 * minDistance is the minimum distance between any two distinct critical points and maxDistance is the
 * maximum distance between any two distinct critical points. If there are fewer than two critical
 * points, return [-1, -1] .
 * <p>
 * Example 1:
 * <p>
 * Input: head = [3,1]
 * Output: [-1,-1]
 * Explanation: There are no critical points in [3,1].
 * <p>
 * Example 2:
 * <p>
 * Input: head = [5,3,1,2,5,1,2]
 * Output: [1,3]
 * Explanation: There are three critical points:
 * - [5,3, 1 ,2,5,1,2]: The third node is a local minima because 1 is less than 3 and 2.
 * - [5,3,1,2, 5 ,1,2]: The fifth node is a local maxima because 5 is greater than 2 and 1.
 * - [5,3,1,2,5, 1 ,2]: The sixth node is a local minima because 1 is less than 5 and 2.
 * The minimum distance is between the fifth and the sixth node. minDistance = 6 - 5 = 1.
 * The maximum distance is between the third and the sixth node. maxDistance = 6 - 3 = 3.
 * <p>
 * Example 3:
 * <p>
 * Input: head = [1,3,2,2,3,2,2,2,7]
 * Output: [3,3]
 * Explanation: There are two critical points:
 * - [1, 3 ,2,2,3,2,2,2,7]: The second node is a local maxima because 3 is greater than 1 and 2.
 * - [1,3,2,2, 3 ,2,2,2,7]: The fifth node is a local maxima because 3 is greater than 2 and 2.
 * Both the minimum and maximum distances are between the second and the fifth node.
 * Thus, minDistance and maxDistance is 5 - 2 = 3.
 * Note that the last node is not considered a local maxima because it does not have a next node.
 * <p>
 * Constraints:
 * <p>
 * - The number of nodes in the list is in the range [2, 10 5 ] .
 * <p>
 * - 1 <= Node.val <= 10 5
 */

import letcode.utils.ListNode;

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
public class _2058 {

    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int idx = 1;
        int firstCriticalPointIdx = 0;
        int lastCriticalPointIdx = 0;
        int previousCriticalPointIdx = 0;

        int previousVal = head.val;
        head = head.next;

        int[] ans = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};

        while (head.next != null) {
            if ((head.val > previousVal && head.val > head.next.val)
                    || (head.val < previousVal && head.val < head.next.val)) {
                if (firstCriticalPointIdx == 0) {
                    firstCriticalPointIdx = idx;
                }
                if (previousCriticalPointIdx != 0) {
                    ans[0] =  Math.min(ans[0], idx - previousCriticalPointIdx);
                }
                previousCriticalPointIdx = idx;
                lastCriticalPointIdx = idx;
            }
            previousVal = head.val;
            head = head.next;
            idx++;
        }

        if (firstCriticalPointIdx == 0 || firstCriticalPointIdx == lastCriticalPointIdx) {
            ans[0] = -1;
            ans[1] = -1;
        } else {
            ans[1] = lastCriticalPointIdx -  firstCriticalPointIdx;
        }
        return ans;
    }
}
