package letcode.normal.medium;

/**
 * 1111. Maximum Nesting Depth of Two Valid Parentheses Strings
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/maximum-nesting-depth-of-two-valid-parentheses-strings/
 * <p>
 * A string is a valid parentheses string (denoted VPS) if and only if it consists of "(" and ")"
 * characters only, and:
 * <p>
 * - It is the empty string, or
 * <p>
 * - It can be written as AB ( A concatenated with B ), where A and B are VPS's, or
 * <p>
 * - It can be written as (A) , where A is a VPS.
 * <p>
 * We can similarly define the nesting depth depth(S) of any VPS S as follows:
 * <p>
 * - depth("") = 0
 * <p>
 * - depth(A + B) = max(depth(A), depth(B)) , where A and B are VPS's
 * <p>
 * - depth("(" + A + ")") = 1 + depth(A) , where A is a VPS.
 * <p>
 * For example, "" , "()()" , and "()(()())" are VPS's (with nesting depths 0, 1, and 2), and ")(" and
 * "(()" are not VPS's.
 * <p>
 * Given a VPS seq , split it into two disjoint subsequences A and B , such that A and B are VPS's (and
 * A.length + B.length = seq.length ). The subsequences may not necessarily be contiguous.
 * <p>
 * For example, for the sequence 123456789 , one possible split is:
 * <p>
 * -
 * <p>
 * A = {1, 3, 5, 7, 9} ,
 * <p>
 * -
 * <p>
 * B = {2, 4, 6, 8} .
 * <p>
 * This corresponds to the output [0, 1, 0, 1, 0, 1, 0, 1, 0] where 0 indicates membership in A and 1
 * indicates membership in B .
 * <p>
 * Now choose any such A and B such that max(depth(A), depth(B)) is the minimum possible value.
 * <p>
 * Return an answer array (of length seq.length ) that encodes such a choice of A and B : answer[i] = 0
 * if seq[i] is part of A , else answer[i] = 1 . Note that even though multiple answers may exist, you
 * may return any of them.
 * <p>
 * Example 1:
 * <p>
 * Input: seq = "(()())"
 * Output: [0,1,1,1,1,0]
 * <p>
 * Example 2:
 * <p>
 * Input: seq = "()(())()"
 * Output: [0,0,0,1,1,0,1,1]
 * <p>
 * Constraints:
 * <p>
 * - 1 <= seq.size <= 10000
 */
public class _1111 {

    public int[] maxDepthAfterSplit(String seq) {
        /*
        题目要求 max(depth(A), depth(B)) is the minimum possible value.
        那么就在分配的过程中 假设 depth(A)是更大的一方 然后保证 A要大于等于B即可，如果相等，那么把(分配给A，否则分配给B
         */

        int leftA = 0;
        int leftB = 0;
        int rightA = 0;
        int rightB = 0;
        int depthA = 0;
        int depthB = 0;
        char[] charArray = seq.toCharArray();
        int[] ans = new int[charArray.length];

        for (int i = 0; i < charArray.length; i++) {

        }

        return null;

    }
}
