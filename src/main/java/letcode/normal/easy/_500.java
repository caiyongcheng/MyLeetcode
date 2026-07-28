package letcode.normal.easy;

import letcode.utils.TestCaseOutputUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 给你一个字符串数组 words ，只返回可以使用在 美式键盘 同一行的字母打印出来的单词。
 * 键盘如下图所示。
 * 美式键盘 中：
 * 第一行由字符 "qwertyuiop" 组成。
 * 第二行由字符 "asdfghjkl" 组成。
 * 第三行由字符 "zxcvbnm" 组成。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/keyboard-row 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-11-01 09:46
 **/
public class _500 {

    static HashMap<Character, Integer> cache;

    static {
        cache = new HashMap<>();
        String[] strings = new String[]{"qwertyuiop", "asdfghjkl", "zxcvbnm"};
        int length;
        char ch;
        for (int row = 0; row < strings.length; row++) {
            length = strings[row].length();
            for (int index = 0; index < length; index++) {
                ch = strings[row].charAt(index);
                cache.put(ch, row);
                cache.put((char) (ch - 32), row);
            }
        }
    }

    public String[] findWords(String[] words) {
        ArrayList<String> ans = new ArrayList<>();
        int length;
        boolean onlyRow = true;
        for (String word : words) {
            Integer row = cache.get(word.charAt(0));
            onlyRow = true;
            length = word.length();
            for (int index = 0; index < length; index++) {
                if (cache.get(word.charAt(index)).intValue() != row) {
                    onlyRow = false;
                    break;
                }
            }
            if (onlyRow) {
                ans.add(word);
            }
        }
        return ans.toArray(new String[0]);
    }

}
