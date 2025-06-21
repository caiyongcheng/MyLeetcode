package letcode.normal.easy;

import letcode.utils.TestUtil;

/**
 * You are given a string array words and a string s, where words[i] and s comprise only of lowercase English letters.
 * Return the number of strings in words that are a prefix of s.  A prefix of a string is a substring that occurs at
 * the beginning of the string. A substring is a contiguous sequence of characters within a string.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-03-24 15:31
 */
public class _2255 {

    public int countPrefixes(String[] words, String s) {
/*        int ans = 0;
        int sLen = s.length();
        for (String word : words) {
            int wLen = word.length();
            if (sLen >= wLen) {
                int i;
                for (i = 0; i < wLen; i++) {
                    if (word.charAt(i) != s.charAt(i)) {
                        break;
                    }
                }
                if (i >= wLen) {
                    ans++;
                }
            }
        }
        return ans;*/
/*        return (int) Arrays.asList(words)
                .stream()
                .parallel()
                .filter(s::startsWith)
                .count();*/
        int ans = 0;
        for (String word : words) {
            if (s.startsWith(word)) {
                ans++;
            }
        }
        return ans;
    }

    /**
     * Example 1:
     *
     * Input: words = ["a","b","c","ab","bc","abc"], s = "abc"
     * Output: 3
     * Explanation:
     * The strings in words which are a prefix of s = "abc" are:
     * "a", "ab", and "abc".
     * Thus the number of strings in words which are a prefix of s is 3.
     * Example 2:
     *
     * Input: words = ["a","a"], s = "aa"
     * Output: 2
     * Explanation:
     * Both of the strings are a prefix of s.
     * Note that the same string can occur multiple times in words, and it should be counted each time.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }


}
