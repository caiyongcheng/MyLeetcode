package normal.medium;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @program: Leetcode
 * @description: 找到给定字符串（由小写字符组成）中的最长子串 T ， 要求 T 中的每一字符出现次数都不少于 k
 * 。输出 T 的长度。
 * @author: 蔡永程
 * @create: 2021-01-07 10:55
 */
public class _395ThreeHundredNinetyFive {

    public char[] chars;

    public int longestSubstring(int left, int right, int k) {
        if (left > right) {
            return 0;
        }
        int[] letterCoount = new int[26];
        final HashSet<Character> separators = new HashSet<>();
        for (int l = left; l <= right; ++l) {
            letterCoount[chars[l]-'a']++;
        }
        for (int i = 0; i < letterCoount.length; i++) {
            if (letterCoount[i] > 0 && letterCoount[i] < k) {
                separators.add((char) ('a'+i));
            }
        }
        if (separators.isEmpty()) {
            return chars.length;
        }
        Arrays.fill(letterCoount, 0);
        int maxLenth = 0;
        int nowLength = 0;
        boolean isMatchCondition;
        for (; left <= right + 1; ++left){
            if (separators.contains(chars[left])) {
                if (nowLength == 0) {
                    continue;
                }
                if (nowLength <= maxLenth) {
                    continue;
                }
                isMatchCondition = true;
                for (int i=0; i<26; ++i) {
                    if (letterCoount[i] != 0 && letterCoount[i] < k) {
                        isMatchCondition = false;
                        break;
                    }
                }
                if (!isMatchCondition) {
                    nowLength = longestSubstring(left-nowLength, left, k);
                }
                if (nowLength > maxLenth) {
                    maxLenth = nowLength;
                }
                Arrays.fill(letterCoount, 0);
                nowLength = 0;
            }else{
                ++nowLength;
                letterCoount[chars[left]-'a']++;
            }
        }
        return maxLenth;
    }

    public int longestSubstring(String s, int k) {
        chars = s.toCharArray();
        return longestSubstring(0,s.length()-1, k);
    }

    /**
     * 示例 1:
     * 输入:
     * s = "aaabb", k = 3
     * 输出:
     * 3
     * 最长子串为 "aaa" ，其中 'a' 重复了 3 次。
     * 示例 2:
     * 输入:
     * s = "ababbc", k = 2
     * 输出:
     * 5
     * 最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _395ThreeHundredNinetyFive().longestSubstring(
                "aaaaaaaaabbbcccccddddd",
                5
        ));
    }

}