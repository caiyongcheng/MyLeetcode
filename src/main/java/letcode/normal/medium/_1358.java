package letcode.normal.medium;

/**
 * 1358. Number of Substrings Containing All Three Characters
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/number-of-substrings-containing-all-three-characters/
 * Given a string s consisting only of characters a , b and c .
 * Return the number of substrings containing at least one occurrence of all these characters a , b and c .
 */
public class _1358 {

    public int numberOfSubstrings(String s) {
        int count = 0;
        int[] char2Cnt = new int[3];

        int length = s.length();
        int limitIdx = 0;
        for (int i = 0; i < length; i++) {
            while ((char2Cnt[0] < 1 || char2Cnt[1] < 1 || char2Cnt[2] < 1) && limitIdx < length) {
                char2Cnt[s.charAt(limitIdx) - 'a']++;
                ++limitIdx;
            }
            if (char2Cnt[0] < 1 || char2Cnt[1] < 1 || char2Cnt[2] < 1) {
                return count;
            }
            count += (length - limitIdx + 1);
            char2Cnt[s.charAt(i) - 'a']--;
        }
        return count;

    }
}
