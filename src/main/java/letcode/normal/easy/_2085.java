package letcode.normal.easy;

import letcode.utils.TestCaseUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你两个字符串数组 words1 和 words2 ，请你返回在两个字符串数组中 都恰好出现一次 的字符串的数目。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/1/12 09:02
 */
public class _2085 {

    public int countWords(String[] words1, String[] words2) {
        Set<String> set1 = getAppearOnceStrSet(words1);
        Set<String> set2 = getAppearOnceStrSet(words2);
        int ans = 0;
        for (String word : set1) {
            if (set2.contains(word)) {
                ans++;
            }
        }
        return ans;
    }

    public Set<String> getAppearOnceStrSet(String[] words) {
        Set<String> appearOnceStrSet = new HashSet<>();
        Set<String> appearSet = new HashSet<>();
        for (String word : words) {
            if (appearSet.contains(word)) {
                appearOnceStrSet.remove(word);
            } else {
                appearOnceStrSet.add(word);
                appearSet.add(word);
            }
        }
        return appearOnceStrSet;
    }

    /**
     * 示例 1：
     *
     * 输入：words1 = ["leetcode","is","amazing","as","is"], words2 = ["amazing","leetcode","is"]
     * 输出：2
     * 解释：
     * - "leetcode" 在两个数组中都恰好出现一次，计入答案。
     * - "amazing" 在两个数组中都恰好出现一次，计入答案。
     * - "is" 在两个数组中都出现过，但在 words1 中出现了 2 次，不计入答案。
     * - "as" 在 words1 中出现了一次，但是在 words2 中没有出现过，不计入答案。
     * 所以，有 2 个字符串在两个数组中都恰好出现了一次。
     * 示例 2：
     *
     * 输入：words1 = ["b","bb","bbb"], words2 = ["a","aa","aaa"]
     * 输出：0
     * 解释：没有字符串在两个数组中都恰好出现一次。
     * 示例 3：
     *
     * 输入：words1 = ["a","ab"], words2 = ["a","a","a","ab"]
     * 输出：1
     * 解释：唯一在两个数组中都出现一次的字符串是 "ab" 。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2085().countWords(
                TestCaseUtils.getStrArr("[\"a\",\"ab\"]"),
                TestCaseUtils.getStrArr("[\"a\",\"a\",\"a\",\"ab\"]")
        ));
    }

}
