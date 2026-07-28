package letcode.normal.medium;

import java.util.Arrays;
import java.util.List;

/**
 * 给你一个字符串 s 和一个字符串数组 dictionary 作为字典，找出并返回字典中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。
 * 如果答案不止一个，返回长度最长且字典序最小的字符串。如果答案不存在，则返回空字符串。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-09-14 09:03
 **/
public class _524 {

    public String findLongestWord(String s, List<String> dictionary) {
        char[] chars = s.toCharArray();
        String ans = "";
        for (int i = 0; i < dictionary.size(); i++) {
            String ns = dictionary.get(i);
            char[] comparable = ns.toCharArray();
            int index = 0;
            int cIndex = 0;
            while (cIndex < comparable.length) {
                while (index < chars.length) {
                    if (chars[index] == comparable[cIndex]) {
                        ++index;
                        ++cIndex;
                        break;
                    }
                    ++index;
                }
                if (index >= chars.length) {
                    break;
                }
            }
            if (cIndex >= comparable.length) {
                if (comparable.length > ans.length()) {
                    ans = ns;
                } else if (comparable.length == ans.length() && ans.compareTo(ns) > 0) {
                    ans = ns;
                }
            }
        }
        return ans;
    }


}
