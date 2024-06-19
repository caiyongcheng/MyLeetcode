package letcode.normal.difficult;

/**
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * 注意：  对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2023/12/26 14:42
 */
public class _76 {

    public String minWindow(String s, String t) {
        /**
         * 按开头位置 搜索子串 再进行判断
         * 判断的时候进行剪枝 一但某个长度满足了条件 就不再向后进行
         * 等到下一个位置的时候 如果去掉上一个开头字符串 还能够满足条件 那么上一个的长度-1就是这个长度
         * 然后继续跳到下一个位置 一直到不满足为止 不满足的时候就向后扩展直到满足为止 这样就可以包含所有结果了
         * 有点像动态硅规划的意思
         */

        // t字符串需要满足的条件
        int[] targetArr = new int[52];
        int tLen = t.length();
        for (int i = 0; i < tLen; i++) {
            if (t.charAt(i) < 'a') {
                targetArr[t.charAt(i) - 'A']++;
            } else {
                targetArr[t.charAt(i) - 'a' + 26]++;
            }
        }
        // 表示需要几种字符
        int diff = 0;
        for (int target : targetArr) {
            diff += target > 0 ? 1 : 0;
        }
        int[] curArr = new int[52];
        int sLen = s.length();
        int startIdx = 0;
        int endIdx = 0;
        int ansStartIdx = 0;
        int ansEndIdx = 0;
        for (int i = 0; i < sLen; i++) {
            if (s.charAt(i) < 'a') {
                if (++curArr[s.charAt(i) - 'A'] == targetArr[s.charAt(i) - 'A']) {
                    --diff;
                }
            } else if (++curArr[s.charAt(i) - 'a' + 26] == targetArr[s.charAt(i) - 'a' + 26]) {
                --diff;
            }
            if (diff == 0) {
                ansStartIdx= 0;
                ansEndIdx = i;
                endIdx = i;
                break;
            }
        }
        if (diff != 0) {
            return "";
        }
        for (int i = 1; i < sLen; i++) {
            startIdx = i;
            if (s.charAt(i-1) < 'a') {
                if (--curArr[s.charAt(i-1) - 'A'] == targetArr[s.charAt(i-1) - 'A'] - 1) {
                    ++diff;
                }
            } else if (--curArr[s.charAt(i-1) - 'a' + 26] == targetArr[s.charAt(i-1) - 'a' + 26] - 1) {
                ++diff;
            }
            if (diff == 0) {
                if (endIdx - startIdx < ansEndIdx - ansStartIdx) {
                    ansEndIdx = endIdx;
                    ansStartIdx = startIdx;
                }
                startIdx = i;
            } else {
                ++endIdx;
                while (endIdx < sLen) {
                    if (s.charAt(endIdx) < 'a') {
                        if (++curArr[s.charAt(endIdx) - 'A'] == targetArr[s.charAt(endIdx) - 'A']) {
                            --diff;
                        }
                    } else if (++curArr[s.charAt(endIdx) - 'a' + 26] == targetArr[s.charAt(endIdx) - 'a' + 26]) {
                        --diff;
                    }
                    if (diff == 0) {
                        break;
                    }
                    ++endIdx;
                }
                if (endIdx >= sLen) {
                    break;
                }
                if (endIdx - startIdx < ansEndIdx - ansStartIdx) {
                    ansEndIdx = endIdx;
                    ansStartIdx = startIdx;
                }
            }
            if (endIdx >= sLen) {
                break;
            }
        }
        return s.substring(ansStartIdx, ansEndIdx + 1);
    }


    /**
     * 示例 1：
     *
     * 输入：s = "ADOBECODEBANC", t = "ABC"
     * 输出："BANC"
     * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
     * 示例 2：
     *
     * 输入：s = "a", t = "a"
     * 输出："a"
     * 解释：整个字符串 s 是最小覆盖子串。
     * 示例 3:
     *
     * 输入: s = "a", t = "aa"
     * 输出: ""
     * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
     * 因此没有符合条件的子字符串，返回空字符串。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _76().minWindow(
                "a",
                "aa"
        ));
    }
}
