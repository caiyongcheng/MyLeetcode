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

}
