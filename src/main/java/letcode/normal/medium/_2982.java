package letcode.normal.medium;

/**
 * 给你一个仅由小写英文字母组成的字符串 s 。  如果一个字符串仅由单一字符组成，那么它被称为 特殊 字符串。例如，字符串 "abc"
 * 不是特殊字符串，而字符串 "ddd"、"zz" 和 "f" 是特殊字符串。
 * 返回在 s 中出现 至少三次 的 最长特殊子字符串 的长度，如果不存在出现至少三次的特殊子字符串，则返回 -1 。
 * 子字符串 是字符串中的一个连续 非空 字符序列。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-05-30 09:04
 */
public class _2982 {

    public int maximumLength(String s) {

        int[][][] char2Length = new int[26][3][2];
        int startIdx = 0;
        int curLen;
        char startChar = s.charAt(0);
        char curChar;
        int length = s.length();
        for (int i = 1; i < length; i++) {
            curChar = s.charAt(i);
            if (curChar != startChar) {
                curLen = i - startIdx;
                // 这里可以不保存数量 只保存长度前三的 再计算数量 速度会更快
                updateCharLength(curLen, char2Length, startChar, 1);
                updateCharLength(curLen - 1, char2Length, startChar, 2);
                updateCharLength(curLen - 2, char2Length, startChar, 3);
                startIdx = i;
                startChar = curChar;
            }
        }
        curLen = length - startIdx;
        updateCharLength(curLen, char2Length, startChar, 1);
        updateCharLength(curLen - 1, char2Length, startChar, 2);
        updateCharLength(curLen - 2, char2Length, startChar, 3);

        int maxLen = -1;
        for (int[][] lenArr : char2Length) {
            for (int[] lenAndCnt : lenArr) {
                if (lenAndCnt[0] > maxLen && lenAndCnt[0] > 0 && lenAndCnt[1] > 2) {
                    maxLen = lenAndCnt[0];
                }
            }
        }
        return maxLen;
    }

    private static void updateCharLength(int curLength, int[][][] char2Length, char startChar, int addCnt) {
        if (curLength < 1) {
            return;
        }
        startChar -= 'a';
        int i = 0;
        for (; i < char2Length[startChar].length; i++) {
            if (char2Length[startChar][i][0] == curLength) {
                char2Length[startChar][i][1] += addCnt;
                return;
            }
            if (char2Length[startChar][i][0] < curLength) {
                break;
            }
        }
        if (i >= char2Length[startChar].length) {
            return;
        }
        for (int j = char2Length[startChar].length - 2; j >= i; j--) {
            char2Length[startChar][j+1][0] = char2Length[startChar][j][0];
            char2Length[startChar][j+1][1] = char2Length[startChar][j][1];
        }
        char2Length[startChar][i][0] = curLength;
        char2Length[startChar][i][1] = addCnt;
    }

    /**
     * 示例 1：
     *
     * 输入：s = "aaaa"
     * 输出：2
     * 解释：出现三次的最长特殊子字符串是 "aa" ：子字符串 "aaaa"、"aaaa" 和 "aaaa"。
     * 可以证明最大长度是 2 。
     * 示例 2：
     *
     * 输入：s = "abcdef"
     * 输出：-1
     * 解释：不存在出现至少三次的特殊子字符串。因此返回 -1 。
     * 示例 3：
     *
     * 输入：s = "abcaba"
     * 输出：1
     * 解释：出现三次的最长特殊子字符串是 "a" ：子字符串 "abcaba"、"abcaba" 和 "abcaba"。
     * 可以证明最大长度是 1 。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2982().maximumLength(
                "cddedeedccedcedecdedcdeededdddcdddddcdeecdcddeecdc"
        ));
    }

}
