package letcode.normal.easy;

import letcode.utils.TestCaseInputUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个字符串数组 words 和一个字符 separator ，请你按 separator 拆分 words 中的每个字符串。
 * 返回一个由拆分后的新字符串组成的字符串数组，不包括空字符串 。  注意  separator 用于决定拆分发生的位置，但它不包含在结果字符串中。
 * 拆分可能形成两个以上的字符串。 结果字符串必须保持初始相同的先后顺序。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/1/24 17:53
 */
public class _2788 {

    public List<String> splitWordsBySeparator(List<String> words, char separator) {
        List<String> ans = new ArrayList<>(words.size() << 1);
        for (String word : words) {
            split(ans, word, separator);
        }
        return ans;
    }

    public void split(List<String> list, String str, char separator) {
        int len = str.length();
        StringBuilder sb = new StringBuilder();
        char ch;
        for (int i = 0; i < len; i++) {
            ch = str.charAt(i);
            if (ch == separator) {
                if (sb.length() > 0) {
                    list.add(sb.toString());
                    sb.delete(0, sb.length());
                }
                continue;
            }
            sb.append(ch);
        }
        if (sb.length() > 0) {
            list.add(sb.toString());
            sb.delete(0, sb.length());
        }
    }

}
