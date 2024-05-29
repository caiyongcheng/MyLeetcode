package letcode.normal.medium;

/**
 * 给你一个仅由小写英文字母组成的字符串 s 。
 * 如果一个字符串仅由单一字符组成，那么它被称为 特殊 字符串。例如，字符串 "abc" 不是特殊字符串，而字符串 "ddd"、"zz" 和 "f" 是特殊字符串。
 * 返回在 s 中出现 至少三次 的 最长特殊子字符串 的长度，如果不存在出现至少三次的特殊子字符串，则返回 -1 。
 * 子字符串 是字符串中的一个连续 非空 字符序列。
 *
 * 3 <= s.length <= 50
 * s 仅由小写英文字母组成。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-05-29 09:35
 */
public class _2981TwoThousandNineHundredEightyOne {

    public int maximumLength(String s) {
        int[][] char2Len2Cnt = new int[26][51];
        char[] charArray = s.toCharArray();
        int startIdx = 0;
        int charIdx = 0;
        int cnt;
        for (int idx = 1; idx < charArray.length; idx++) {
            if (charArray[idx] != charArray[startIdx]) {
                charIdx = charArray[startIdx] - 'a';
                cnt = 1;
                while (startIdx < idx) {
                    char2Len2Cnt[charIdx][idx - startIdx ] += cnt;
                    ++cnt;
                    ++startIdx;
                }
            }
        }
        charIdx = charArray[startIdx] - 'a';
        cnt = 1;
        while (startIdx < charArray.length) {
            char2Len2Cnt[charIdx][charArray.length - startIdx ] += cnt;
            ++cnt;
            ++startIdx;
        }

        // 筛选统计结果
        int maxLen = -1;
        for (int[] len2Cnt : char2Len2Cnt) {
            for (int len = len2Cnt.length - 1; len >= 0; len--) {
                if (len2Cnt[len] >= 3 && len > maxLen) {
                    maxLen = len;
                    break;
                }
            }
        }
        return maxLen;
    }

    /**
     * 示例 1：
     *
     * 输入：s = "aaaa"
     * 输出：2
     * 解释：出现三次的最长特殊子字符串是 "aa" ：子字符串 "aaaa"、"aaaa" 和 "aaaa"。
     * 可以证明最大长度是 2 。
     * 示例 2：
     * 输入：s = "abcdef"
     * 输出：-1
     * 解释：不存在出现至少三次的特殊子字符串。因此返回 -1 。
     * 示例 3：
     * 输入：s = "abcaba"
     * 输出：1
     * 解释：出现三次的最长特殊子字符串是 "a" ：子字符串 "abcaba"、"abcaba" 和 "abcaba"。
     * 可以证明最大长度是 1 。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2981TwoThousandNineHundredEightyOne().maximumLength(
                "aaaa"
        ));
    }


}
