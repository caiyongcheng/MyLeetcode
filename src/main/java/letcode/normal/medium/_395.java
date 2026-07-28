package letcode.normal.medium;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @program: Leetcode
 * @description: 找到给定字符串（由小写字符组成）中的最长子串 T ， 要求 T 中的每一字符出现次数都不少于 k
 * 。输出 T 的长度。
 * @author: 蔡永程
 * @create: 2021-01-07 10:55
 */
public class _395 {

    public char[] chars;

    public int longestSubstring(int left, int right, int k) {
        if (left > right) {
            return 0;
        }
        int[] letterCoount = new int[26];
        final HashSet<Character> separators = new HashSet<>();
        for (int l = left; l <= right; ++l) {
            letterCoount[chars[l] - 'a']++;
        }
        for (int i = 0; i < letterCoount.length; i++) {
            if (letterCoount[i] > 0 && letterCoount[i] < k) {
                separators.add((char) ('a' + i));
            }
        }
        if (separators.isEmpty()) {
            return chars.length;
        }
        Arrays.fill(letterCoount, 0);
        int maxLenth = 0;
        int nowLength = 0;
        boolean isMatchCondition;
        for (; left <= right + 1; ++left) {
            if (separators.contains(chars[left])) {
                if (nowLength == 0) {
                    continue;
                }
                if (nowLength <= maxLenth) {
                    continue;
                }
                isMatchCondition = true;
                for (int i = 0; i < 26; ++i) {
                    if (letterCoount[i] != 0 && letterCoount[i] < k) {
                        isMatchCondition = false;
                        break;
                    }
                }
                if (!isMatchCondition) {
                    nowLength = longestSubstring(left - nowLength, left, k);
                }
                if (nowLength > maxLenth) {
                    maxLenth = nowLength;
                }
                Arrays.fill(letterCoount, 0);
                nowLength = 0;
            } else {
                ++nowLength;
                letterCoount[chars[left] - 'a']++;
            }
        }
        return maxLenth;
    }

    public int longestSubstring(String s, int k) {
        chars = s.toCharArray();
        return longestSubstring(0, s.length() - 1, k);
    }

}
