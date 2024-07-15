package letcode.normal.medium;

import letcode.utils.TestCaseUtils;

/**
 * 给定字符串列表 strs ，返回其中 最长的特殊序列 的长度。如果最长特殊序列不存在，返回 -1 。
 * 特殊序列 定义如下：该序列为某字符串 独有的子序列（即不能是其他字符串的子序列）。
 * s 的 子序列可以通过删去字符串 s 中的某些字符实现。  例如，"abc" 是 "aebdc" 的子序列，因为您可以删除"aebdc"中的下划线字符来得到 "abc" 。
 * "aebdc"的子序列还包括"aebdc"、 "aeb" 和 "" (空字符串)。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-06-17 11:38
 */
public class _522 {

    public int findLUSlength(String[] strs) {
        int ans = -1;
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() <= ans) {
                continue;
            }
            boolean flag = true;
            for (int j = 0; j < strs.length; j++) {
                if (i == j) {
                    continue;
                }
                if (isSubSequence(strs[i], strs[j])) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ans = Math.max(ans, strs[i].length());
            }
        }

        return ans;

    }


    public boolean isSubSequence(String s, String t) {
        int sIdx = 0;
        int tIdx = 0;
        while (sIdx < s.length() && tIdx < t.length()) {
            if (s.charAt(sIdx) == t.charAt(tIdx)) {
                sIdx++;
            }
            tIdx++;
        }
        return sIdx == s.length();
    }





    /**
     * 示例 1：
     *
     * 输入: strs = ["aba","cdc","eae"]
     * 输出: 3
     * 示例 2:
     *
     * 输入: strs = ["aaa","aaa","aa"]
     * 输出: -1
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _522().findLUSlength(
                TestCaseUtils.getStrArr("[\"aba\",\"cdc\",\"eae\"]")
        ));
    }

}
