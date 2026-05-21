package letcode.normal.medium;

import letcode.utils.TestCaseInputUtils;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2024/1/13 21:17
 * description 给你一个字符串 s 和一个整数 repeatLimit ，用 s 中的字符构造一个新字符串 repeatLimitedString ，
 * 使任何字母 连续 出现的次数都不超过 repeatLimit 次。你不必使用 s 中的全部字符。  返回 字典序最大的 repeatLimitedString 。
 * 如果在字符串 a 和 b 不同的第一个位置，字符串 a 中的字母在字母表中出现时间比字符串 b 对应的字母晚，则认为字符串 a 比字符串 b 字典序更大 。
 * 如果字符串中前 min(a.length, b.length) 个字符都相同，那么较长的字符串字典序更大。
 */
public class _2182 {

    public String repeatLimitedString(String s, int repeatLimit) {
        /*
         * 贪心 每次选择最大的可以拼接字符即可
         */
        int[] charArr = new int[26];
        int len = s.length();
        int seqLen = 0;
        char lastCh = ' ';
        char ch = 'z';
        int oiLen = -1;
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < len; i++) {
            charArr[s.charAt(i) - 'a']++;
        }
        while (oiLen != ans.length()) {
            ch = 'z';
            oiLen = ans.length();
            for (int j = 25; j >= 0; j--, ch--) {
                if (charArr[j] > 0 && (lastCh != ch || seqLen + 1 <= repeatLimit)) {
                    ans.append(ch);
                    if (lastCh != ch) {
                        seqLen = 1;
                    } else {
                        ++seqLen;
                    }
                    lastCh = ch;
                    charArr[j]--;
                    break;
                }
            }
        }
        return ans.toString();
    }


}
