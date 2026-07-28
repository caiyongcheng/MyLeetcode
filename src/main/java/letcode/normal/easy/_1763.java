package letcode.normal.easy;

import java.util.HashMap;

/**
 * 当一个字符串 s包含的每一种字母的大写和小写形式 同时出现在 s中，就称这个字符串s是 美好 字符串。
 * 比方说，"abABB"是美好字符串，因为'A' 和'a'同时出现了，且'B' 和'b'也同时出现了。
 * 然而，"abA"不是美好字符串因为'b'出现了，而'B'没有出现。
 * 给你一个字符串s，请你返回s最长的美好子字符串。如果有多个答案，请你返回最早出现的一个。
 * 如果不存在美好子字符串，请你返回一个空字符串。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/longest-nice-substring 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-02-01 22:49
 **/
public class _1763 {

    public String longestNiceSubstring(String s) {
        /*
        1 肯定用hash存储每个字母出现的次数
        2 依次遍历即可
         */
        HashMap<Character, Integer> hash = new HashMap<>();
        int length = s.length();
        int maxSubLen = 0;
        int index = -1;
        char ch;
        for (int i = 0; i < length; i++) {
            ch = s.charAt(i);
            if (ch > 'Z') {
                hash.put(ch, hash.getOrDefault(ch, 0) + 1);
            } else {
                ch += 32;
                hash.put(ch, hash.getOrDefault(ch, 0) - 1);
            }
            //if (hash.put())
        }
/*        for (int startIndex = 0; length - startIndex + 1 > maxSubLen; ++maxSubLen) {

        }*/
        return index == -1 ? "" : s.substring(index, index + maxSubLen);
    }


    public static void main(String[] args) {
        System.out.println(((int) 'a'));
    }

}
