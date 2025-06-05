package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.Arrays;

/**
 * You are given two strings of the same length s1 and s2 and a string baseStr.
 * We say s1[i] and s2[i] are equivalent characters.  For example, if s1 = "abc" and s2 = "cde", then we have 'a' == 'c',
 * 'b' == 'd', and 'c' == 'e'. Equivalent characters follow the usual rules of any equivalence relation:
 * Reflexivity: 'a' == 'a'.
 * Symmetry: 'a' == 'b' implies 'b' == 'a'.
 * Transitivity: 'a' == 'b' and 'b' == 'c' implies 'a' == 'c'.
 * For example, given the equivalency information from s1 = "abc" and s2 = "cde",
 * "acd" and "aab" are equivalent strings of baseStr = "eed",
 * and "aab" is the lexicographically smallest equivalent string of baseStr.
 * Return the lexicographically smallest equivalent string of baseStr by using the equivalency information from s1 and s2.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-06-05 16:34
 */
public class _1061 {

    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        int[] unionFind = new int[26];
        for (int i = 0; i < unionFind.length; i++) {
            unionFind[i] = i;
        }

        int length = s1.length();
        char ch1, ch2;
        for (int i = 0; i < length; i++) {
            ch1 = s1.charAt(i);
            ch2 = s2.charAt(i);
            union(unionFind, ch1 - 'a', ch2 - 'a');
        }

        StringBuilder res = new StringBuilder();
        int retLength = baseStr.length();
        for (int i = 0; i < retLength; i++) {
            res.append((char) (find(unionFind, baseStr.charAt(i) - 'a') + 'a'));
        }
        return res.toString();
    }

    private void union(int[] unionFind, int i, int j) {
        int iRoot = find(unionFind, i);
        int jRoot = find(unionFind, j);
        if (iRoot > jRoot) {
            unionFind[iRoot] = jRoot;
        } else {
            unionFind[jRoot] = iRoot;
        }
    }

    private int find(int[] unionFind, int i) {
        if (unionFind[i] != i) {
            unionFind[i] = find(unionFind, unionFind[i]);
        }
        return unionFind[i];
    }

    private String smallestEquivalentString1(String s1, String s2, String baseStr) {
        /*
        这是使用图的做法
         */
        int[][] map = new int[26][26];
        int length = s1.length();
        char ch1, ch2;
        for (int i = 0; i < length; i++) {
            ch1 = s1.charAt(i);
            ch2 = s2.charAt(i);
            map[ch1 - 'a'][ch2 - 'a'] = 1;
            map[ch2 - 'a'][ch1 - 'a'] = 1;
        }

        StringBuilder res = new StringBuilder();
        int resultLen = baseStr.length();
        int[] visited = new int[26];
        int[] cache = new int[26];
        Arrays.fill(cache, -1);
        int chIdx;
        for (int i = 0; i < resultLen; i++) {
            chIdx = baseStr.charAt(i) - 'a';
            cache[chIdx] = (dfs(map, baseStr.charAt(i) - 'a', visited, cache));
            res.append((char) (cache[chIdx] + 'a'));
            Arrays.fill(visited, 0);
        }
        return res.toString();
    }


    private int dfs(int[][] map, int index, int[] visited, int[] cache) {
        visited[index] = 1;
        if (cache[index] != -1) {
            return cache[index];
        }
        int res = index;
        for (int i = 25; i > -1; i--) {
            if (map[index][i] == 1 && visited[i] == 0) {
                res = Math.min(res, dfs(map, i, visited, cache));
                map[index][res] = 1;
                map[res][index] = 1;
            }
        }
        return res;
    }

    /**
     * Example 1:
     *
     * Input: s1 = "parker", s2 = "morris", baseStr = "parser"
     * Output: "makkek"
     * Explanation: Based on the equivalency information in s1 and s2, we can group their characters as [m,p], [a,o], [k,r,s], [e,i].
     * The characters in each group are equivalent and sorted in lexicographical order.
     * So the answer is "makkek".
     * Example 2:
     *
     * Input: s1 = "hello", s2 = "world", baseStr = "hold"
     * Output: "hdld"
     * Explanation: Based on the equivalency information in s1 and s2, we can group their characters as [h,w], [d,e,o], [l,r].
     * So only the second letter 'o' in baseStr is changed to 'd', the answer is "hdld".
     * Example 3:
     *
     * Input: s1 = "leetcode", s2 = "programs", baseStr = "sourcecode"
     * Output: "aauaaaaada"
     * Explanation: We group the equivalent characters in s1 and s2 as [a,o,e,r,s,c], [l,p], [g,t] and [d,m], thus all letters in baseStr except 'u' and 'd' are transformed to 'a', the answer is "aauaaaaada".
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }

}
