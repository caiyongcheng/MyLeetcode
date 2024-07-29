package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * You are given a string s and an integer k.
 * Define a function distance(s1, s2) between two strings s1 and s2 of the same length n as:
 * The sum of the minimum distance between s1[i] and s2[i]
 * when the characters from 'a' to 'z' are placed in a cyclic order, for all i in the range [0, n - 1].
 * For example, distance("ab", "cd") == 4, and distance("a", "z") == 1.
 * You can change any letter of s to any other lowercase English letter, any number of times.
 * Return a string denoting the  lexicographically smallest
 * string t you can get after some changes, such that distance(s, t) <= k.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-07-29 10:48
 */
public class _3106 {



    public String getSmallestString(String s, int k) {
        char[] charArray = s.toCharArray();
        int distance;
        for (int i = 0; i < charArray.length; i++) {
            distance = Integer.min(charArray[i] - 'a', 26 - charArray[i] + 'a');
            if (distance <= k) {
                k -= distance;
                charArray[i] = 'a';
            } else {
                charArray[i] -= k;
                return new String(charArray);
            }
        }
        return new String(charArray);
    }


    /**
     * Example 1:
     *
     * Input: s = "zbbz", k = 3
     *
     * Output: "aaaz"
     *
     * Explanation:
     *
     * Change s to "aaaz". The distance between "zbbz" and "aaaz" is equal to k = 3.
     *
     * Example 2:
     *
     * Input: s = "xaxcd", k = 4
     *
     * Output: "aawcd"
     *
     * Explanation:
     *
     * The distance between "xaxcd" and "aawcd" is equal to k = 4.
     *
     * Example 3:
     *
     * Input: s = "lol", k = 0
     *
     * Output: "lol"
     *
     * Explanation:
     *
     * It's impossible to change any character as k = 0.
     *
     * 示例 1：
     *
     * 输入：s = "zbbz", k = 3
     * 输出："aaaz"
     * 解释：在这个例子中，可以执行以下操作：
     * 将 s[0] 改为 'a' ，s 变为 "abbz" 。
     * 将 s[1] 改为 'a' ，s 变为 "aabz" 。
     * 将 s[2] 改为 'a' ，s 变为 "aaaz" 。
     * "zbbz" 和 "aaaz" 之间的距离等于 k = 3 。
     * 可以证明 "aaaz" 是在任意次操作后能够得到的字典序最小的字符串。
     * 因此，答案是 "aaaz" 。
     * 示例 2：
     *
     * 输入：s = "xaxcd", k = 4
     * 输出："aawcd"
     * 解释：在这个例子中，可以执行以下操作：
     * 将 s[0] 改为 'a' ，s 变为 "aaxcd" 。
     * 将 s[2] 改为 'w' ，s 变为 "aawcd" 。
     * "xaxcd" 和 "aawcd" 之间的距离等于 k = 4 。
     * 可以证明 "aawcd" 是在任意次操作后能够得到的字典序最小的字符串。
     * 因此，答案是 "aawcd" 。
     * 示例 3：
     *
     * 输入：s = "lol", k = 0
     * 输出："lol"
     * 解释：在这个例子中，k = 0，更改任何字符都会使得距离大于 0 。
     * 因此，答案是 "lol" 。
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.testWithTestClassFile(_3106.class);
    }

}
