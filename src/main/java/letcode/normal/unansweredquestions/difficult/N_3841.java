package letcode.normal.difficult;

import java.util.List;

/**
 * 3841. Palindromic Path Queries in a Tree
 * Difficulty: Hard
 * Link: https://leetcode.cn/problems/palindromic-path-queries-in-a-tree/
 * <p>
 * You are given an undirected tree with n nodes labeled 0 to n - 1 . This is represented by a 2D array
 * edges of length n - 1 , where edges[i] = [u i , v i ] indicates an undirected edge between nodes u i
 * and v i .
 * <p>
 * You are also given a string s of length n consisting of lowercase English letters, where s[i]
 * represents the character assigned to node i .
 * <p>
 * You are also given a string array queries , where each queries[i] is either:
 * <p>
 * - "update u i c" : Change the character at node u i to c . Formally, update s[u i ] = c .
 * <p>
 * - "query u i v i " : Determine whether the string formed by the characters on the unique path from u
 * i to v i (inclusive) can be rearranged into a palindrome .
 * <p>
 * Return a boolean array answer , where answer[j] is true if the j th query of type "query u i v i
 * "​​​​​​​ can be rearranged into a palindrome , and false otherwise.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 3, edges = [[0,1],[1,2]], s = "aac", queries = ["query 0 2","update 1 b","query 0 2"]
 * <p>
 * Output: [true,false]
 * <p>
 * Explanation:
 * <p>
 * - "query 0 2" : Path 0 &rarr; 1 &rarr; 2 gives "aac" , which can be rearranged to form "aca" , a
 * palindrome. Thus, answer[0] = true .
 * <p>
 * - "update 1 b" : Update node 1 to 'b' , now s = "abc" .
 * <p>
 * - "query 0 2" : Path characters are "abc" , which cannot be rearranged to form a palindrome. Thus,
 * answer[1] = false .
 * <p>
 * Thus, answer = [true, false] .
 * <p>
 * Example 2:
 * <p>
 * Input: n = 4, edges = [[0,1],[0,2],[0,3]], s = "abca", queries = ["query 1 2","update 0 b","query 2
 * 3","update 3 a","query 1 3"]
 * <p>
 * Output: [false,false,true]
 * <p>
 * Explanation:
 * <p>
 * - "query 1 2" : Path 1 &rarr; 0 &rarr; 2 gives "bac" , which cannot be rearranged to form a
 * palindrome. Thus, answer[0] = false .
 * <p>
 * - "update 0 b" : Update node 0 to 'b' , now s = "bbca" .
 * <p>
 * - "query 2 3" : Path 2 &rarr; 0 &rarr; 3 gives "cba" , which cannot be rearranged to form a
 * palindrome. Thus, answer[1] = false .
 * <p>
 * - "update 3 a" : Update node 3 to 'a' , s = "bbca" .
 * <p>
 * - "query 1 3" : Path 1 &rarr; 0 &rarr; 3 gives "bba" , which can be rearranged to form "bab" , a
 * palindrome. Thus, answer[2] = true .
 * <p>
 * Thus, answer = [false, false, true] .
 * <p>
 * Constraints:
 * <p>
 * - 1 <= n == s.length <= 5 * 10 4
 * <p>
 * - edges.length == n - 1
 * <p>
 * - edges[i] = [u i , v i ]
 * <p>
 * - 0 <= u i , v i <= n - 1
 * <p>
 * - s consists of lowercase English letters.
 * <p>
 * - The input is generated such that edges represents a valid tree.
 * <p>
 * - 1 <= queries.length <= 5 * 10 4 ​​​​​​​
 * <p>
 * - queries[i] = "update u i c" or
 * <p>
 * - queries[i] = "query u i v i "
 * <p>
 * - 0 <= u i , v i <= n - 1
 * <p>
 * - c is a lowercase English letter.
 */
public class N_3841 {

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
    }
}
