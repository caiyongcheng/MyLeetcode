package letcode.normal.easy;

import letcode.utils.TestCaseInputUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你一个下标从 0 开始的数组 words ，数组中包含 互不相同 的字符串。  如果字符串 words[i] 与字符串 words[j] 满足以下条件，
 * 我们称它们可以匹配：  字符串 words[i] 等于 words[j] 的反转字符串。
 * 0 <= i < j < words.length 请你返回数组 words 中的 最大 匹配数目。  注意，每个字符串最多匹配一次。
 *
 * 提示：
 *
 * 1 <= words.length <= 50
 * words[i].length == 2
 * words 包含的字符串互不相同。
 * words[i] 只包含小写英文字母。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/1/17 17:43
 */
public class _2744 {

    public int maximumNumberOfStringPairs(String[] words) {
        /*
        如果加上条件 words[i].length == 2 可以选择更好的hashcode方法
        例如word.charAt(0) * 300 + word.charAt(1) 将hashset换成array即可
         */
        Set<String> strSet = new HashSet<>();
        String reverseStr;
        for (String word : words) {
            reverseStr = reverse(word);
            if (reverseStr.equals(word)) {
                continue;
            }
            strSet.add(reverseStr);
        }
        int ans = 0;
        for (String word : words) {
            if (strSet.contains(word)) {
                ++ans;
            }
        }
        return ans >>> 1;
    }

    public String reverse(String str) {
        StringBuilder sb = new StringBuilder();
        int len = str.length();
        for (int i = len - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    /**
     * 示例 1：
     *
     * 输入：words = ["cd","ac","dc","ca","zz"]
     * 输出：2
     * 解释：在此示例中，我们可以通过以下方式匹配 2 对字符串：
     * - 我们将第 0 个字符串与第 2 个字符串匹配，因为 word[0] 的反转字符串是 "dc" 并且等于 words[2]。
     * - 我们将第 1 个字符串与第 3 个字符串匹配，因为 word[1] 的反转字符串是 "ca" 并且等于 words[3]。
     * 可以证明最多匹配数目是 2 。
     * 示例 2：
     *
     * 输入：words = ["ab","ba","cc"]
     * 输出：1
     * 解释：在此示例中，我们可以通过以下方式匹配 1 对字符串：
     * - 我们将第 0 个字符串与第 1 个字符串匹配，因为 words[1] 的反转字符串 "ab" 与 words[0] 相等。
     * 可以证明最多匹配数目是 1 。
     * 示例 3：
     *
     * 输入：words = ["aa","ab"]
     * 输出：0
     * 解释：这个例子中，无法匹配任何字符串。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2744().maximumNumberOfStringPairs(
                TestCaseInputUtils.getStrArr("[\"aa\",\"ab\"]")
        ));
    }

}
