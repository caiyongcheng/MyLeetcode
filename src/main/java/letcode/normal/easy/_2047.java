package letcode.normal.easy;

/**
 * 句子仅由小写字母（'a' 到 'z'）、数字（'0' 到 '9'）、连字符（'-'）、标点符号（'!'、'.' 和 ','）以及空格（' '）组成。
 * 每个句子可以根据空格分解成 一个或者多个 token ，这些 token 之间由一个或者多个空格 ' ' 分隔。
 * 如果一个 token 同时满足下述条件，则认为这个 token 是一个有效单词：  仅由小写字母、连字符和/或标点（不含数字）。
 * 至多一个 连字符 '-' 。如果存在，连字符两侧应当都存在小写字母（"a-b" 是一个有效单词，但 "-ab" 和 "ab-" 不是有效单词）。
 * 至多一个 标点符号。如果存在，标点符号应当位于 token 的 末尾 。
 * 这里给出几个有效单词的例子："a-b."、"afad"、"ba-c"、"a!" 和 "!" 。
 * 给你一个字符串 sentence ，请你找出并返回 sentence 中 有效单词的数目 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/number-of-valid-words-in-a-sentence 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-01-27 17:38
 **/
public class _2047 {

    String statement;

    public int countValidWords(String sentence) {
        if (sentence.length() == 1) {
            return sentence.charAt(0) == ' ' || (sentence.charAt(0) >= '0' && sentence.charAt(0) <= '9') || sentence.charAt(0) == '-' ? 0 : 1;
        }
        statement = sentence;
        int length = statement.length();
        int start = -1;
        int ans = 0;
        for (int i = 0; i < length; i++) {
            if (sentence.charAt(i) == ' ' || i == length - 1) {
                if (start != -1 && check(start, i == length - 1 ? length - 1 : i - 1)) {
                    ++ans;
                }
                start = -1;
                continue;
            }
            if (start == -1) {
                start = i;
            }
        }
        return ans;
    }

    public boolean check(int start, int end) {
        int punctuationCount = 0;
        int hyphenCount = 0;
        char ch;
        for (int index = start; index <= end; ++index) {
            ch = statement.charAt(index);
            if (ch >= '0' && ch <= '9') {
                return false;
            }
            if (ch == '!' || ch == '.' || ch == ',') {
                if (index != end) {
                    return false;
                }
                if (punctuationCount > 0) {
                    return false;
                }
                ++punctuationCount;
            }
            if (ch == '-') {
                if (hyphenCount > 0) {
                    return false;
                }
                ++hyphenCount;
                if (index - 1 < start || index + 1 > end) {
                    return false;
                }
                ch = statement.charAt(index - 1);
                if (ch > 'z' || ch < 'a') {
                    return false;
                }
                ch = statement.charAt(index + 1);
                if (ch > 'z' || ch < 'a') {
                    return false;
                }
            }
        }
        return true;
    }

}
