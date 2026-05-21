package letcode.normal.easy;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/11/7 9:18
 * description 给你一个下标从 0 开始的字符串数组 words 和两个整数：left 和 right 。
 * 如果字符串以元音字母开头并以元音字母结尾，那么该字符串就是一个 元音字符串 ，其中元音字母是 'a'、'e'、'i'、'o'、'u' 。
 * 返回 words[i] 是元音字符串的数目，其中 i 在闭区间 [left, right] 内。
 */
public class _2586 {


    public int vowelStrings(String[] words, int left, int right) {
        int cnt = 0;
        while (left <= right) {
            if (isVowel(words[left].charAt(0)) && isVowel(words[left].charAt(words[left].length() - 1))) {
                ++cnt;
            }
            ++left;
        }
        return cnt;
    }


    public boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' ||ch == 'i' ||ch == 'o' ||ch == 'u' ||
                ch == 'A' || ch == 'E' ||ch == 'I' ||ch == 'O' ||ch == 'U';
    }


}
