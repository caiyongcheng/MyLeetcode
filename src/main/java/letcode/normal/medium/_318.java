package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/11/6 9:09
 * description 给你一个字符串数组 words ，找出并返回 length(words[i]) * length(words[j]) 的最大值，并且这两个单词不含有公共字母。如果不存在这样的两个单词，返回 0 。
 */
public class _318 {



    public int maxProduct(String[] words) {
        Map<Integer, Integer> charStructure2Str = new HashMap<>();
        int len;
        int charStructure = 0;
        for (String word : words) {
            len = word.length();
            charStructure = 0;
            for (int i = 0; i < len; i++) {
                charStructure |= 1 << word.charAt(i)-'a';
            }
            Integer strLen = charStructure2Str.get(charStructure);
            if (strLen == null || strLen < len) {
                charStructure2Str.put(charStructure, len);
            }
        }
        int maxLen = 0;
        int[] charStructureArr = new int[charStructure2Str.size()];
        int[] lenArr = new int[charStructure2Str.size()];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : charStructure2Str.entrySet()) {
            charStructureArr[i] = entry.getKey();
            lenArr[i++] = entry.getValue();
        }
        for (i = 0; i < charStructureArr.length; i++) {
            for (int j = i + 1; j < charStructureArr.length; j++) {
                if ((charStructureArr[i] & charStructureArr[j]) == 0 && lenArr[i] * lenArr[j] > maxLen) {
                    maxLen = lenArr[i] * lenArr[j];
                }
            }
        }
        return maxLen;
    }


    /**
     * 示例 1：
     *
     * 输入：words = ["abcw","baz","foo","bar","xtfn","abcdef"]
     * 输出：16
     * 解释：这两个单词为 "abcw", "xtfn"。
     * 示例 2：
     *
     * 输入：words = ["a","ab","abc","d","cd","bcd","abcd"]
     * 输出：4
     * 解释：这两个单词为 "ab", "cd"。
     * 示例 3：
     *
     * 输入：words = ["a","aa","aaa","aaaa"]
     * 输出：0
     * 解释：不存在这样的两个单词。
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test(_318.class);
    }



}
