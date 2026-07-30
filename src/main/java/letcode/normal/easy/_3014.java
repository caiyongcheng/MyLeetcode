package letcode.normal.easy;

import java.util.Arrays;

/**
 * 3014. Minimum Number of Pushes to Type Word I
 * Difficulty: Easy
 * Link: https://leetcode.cn/problems/minimum-number-of-pushes-to-type-word-i/
 * <p>
 * You are given a string word containing distinct lowercase English letters.
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
 * Input: word = "xycdefghij"
 * Output: 12
 * Explanation: The remapped keypad given in the image provides the minimum cost.
 * "x" -> one push on key 2
 * "y" -> two pushes on key 2
 * "c" -> one push on key 3
 * "d" -> two pushes on key 3
 * "e" -> one push on key 4
 * "f" -> one push on key 5
 * "g" -> one push on key 6
 * "h" -> one push on key 7
 * "i" -> one push on key 8
 * "j" -> one push on key 9
 * Total cost is 1 + 2 + 1 + 2 + 1 + 1 + 1 + 1 + 1 + 1 = 12.
 * It can be shown that no other mapping can provide a lower cost.
 * <p>
 * Constraints:
 * <p>
 * - 1 <= word.length <= 26
 * <p>
 * - word consists of lowercase English letters.
 * <p>
 * - All letters in word are distinct.
 */
public class _3014 {

    public int minimumPushes(String word) {
        Integer[] char2Cnt = new Integer[26];
        Arrays.fill(char2Cnt, 0);
        for (char ch : word.toCharArray()) {
            char2Cnt[ch - 'a']++;
        }

        Arrays.sort(char2Cnt, (i, j) -> j - i);
        int ans = 0;
        int cost = 1;

        for (int i = 0; i < char2Cnt.length; i++) {
            ans += (i / 8 + 1) * char2Cnt[i];
        }

        return ans;

    }
}
