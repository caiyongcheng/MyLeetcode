package letcode.normal.medium;

import java.util.Arrays;

/**
 * 3016. Minimum Number of Pushes to Type Word II
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/minimum-number-of-pushes-to-type-word-ii/
 * <p>
 * You are given a string word containing lowercase English letters.
 * <p>
 * Telephone keypads have keys mapped with distinct collections of lowercase English letters, which can
 * be used to form words by pushing them. For example, the key 2 is mapped with ["a","b","c"] , we need
 * to push the key one time to type "a" , two times to type "b" , and three times to type "c" .
 * <p>
 * It is allowed to remap the keys numbered 2 to 9 to distinct collections of letters. The keys can be
 * remapped to any amount of letters, but each letter must be mapped to exactly one key. You need to
 * find the minimum number of times the keys will be pushed to type the string word .
 * <p>
 * Return the minimum number of pushes needed to type word after remapping the keys .
 * <p>
 * An example mapping of letters to keys on a telephone keypad is given below. Note that 1 , * , # ,
 * and 0 do not map to any letters.
 * <p>
 * Example 1:
 * <p>
 * Input: word = "abcde"
 * Output: 5
 * Explanation: The remapped keypad given in the image provides the minimum cost.
 * "a" -> one push on key 2
 * "b" -> one push on key 3
 * "c" -> one push on key 4
 * "d" -> one push on key 5
 * "e" -> one push on key 6
 * Total cost is 1 + 1 + 1 + 1 + 1 = 5.
 * It can be shown that no other mapping can provide a lower cost.
 * <p>
 * Example 2:
 * <p>
 * Input: word = "xyzxyzxyzxyz"
 * Output: 12
 * Explanation: The remapped keypad given in the image provides the minimum cost.
 * "x" -> one push on key 2
 * "y" -> one push on key 3
 * "z" -> one push on key 4
 * Total cost is 1 * 4 + 1 * 4 + 1 * 4 = 12
 * It can be shown that no other mapping can provide a lower cost.
 * Note that the key 9 is not mapped to any letter: it is not necessary to map letters to every key,
 * but to map all the letters.
 * <p>
 * Example 3:
 * <p>
 * Input: word = "aabbccddeeffgghhiiiiii"
 * Output: 24
 * Explanation: The remapped keypad given in the image provides the minimum cost.
 * "a" -> one push on key 2
 * "b" -> one push on key 3
 * "c" -> one push on key 4
 * "d" -> one push on key 5
 * "e" -> one push on key 6
 * "f" -> one push on key 7
 * "g" -> one push on key 8
 * "h" -> two pushes on key 9
 * "i" -> one push on key 9
 * Total cost is 1 * 2 + 1 * 2 + 1 * 2 + 1 * 2 + 1 * 2 + 1 * 2 + 1 * 2 + 2 * 2 + 6 * 1 = 24.
 * It can be shown that no other mapping can provide a lower cost.
 * <p>
 * Constraints:
 * <p>
 * - 1 <= word.length <= 10 5
 * <p>
 * - word consists of lowercase English letters.
 */
public class _3016 {

    public int minimumPushes(String word) {
        Integer[] char2Cnt = new Integer[26];
        Arrays.fill(char2Cnt, 0);
        for (char ch : word.toCharArray()) {
            char2Cnt[ch - 'a']++;
        }

        Arrays.sort(char2Cnt, (i, j) -> j - i);
        int ans = 0;

        for (int i = 0; i < char2Cnt.length; i++) {
            ans += (i / 8 + 1) * char2Cnt[i];
        }

        return ans;
    }
}
