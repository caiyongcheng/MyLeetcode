package letcode.normal.easy;

import letcode.utils.TestCaseInputUtils;

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

}
