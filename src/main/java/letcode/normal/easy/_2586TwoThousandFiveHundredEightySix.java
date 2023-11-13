package letcode.normal.easy;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/11/7 9:18
 * description 给你一个下标从 0 开始的字符串数组 words 和两个整数：left 和 right 。
 * 如果字符串以元音字母开头并以元音字母结尾，那么该字符串就是一个 元音字符串 ，其中元音字母是 'a'、'e'、'i'、'o'、'u' 。
 * 返回 words[i] 是元音字符串的数目，其中 i 在闭区间 [left, right] 内。
 */
public class _2586TwoThousandFiveHundredEightySix {


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


    /**
     * 示例 1：
     *
     * 输入：words = ["are","amy","u"], left = 0, right = 2
     * 输出：2
     * 解释：
     * - "are" 是一个元音字符串，因为它以 'a' 开头并以 'e' 结尾。
     * - "amy" 不是元音字符串，因为它没有以元音字母结尾。
     * - "u" 是一个元音字符串，因为它以 'u' 开头并以 'u' 结尾。
     * 在上述范围中的元音字符串数目为 2 。
     * 示例 2：
     *
     * 输入：words = ["hey","aeo","mu","ooo","artro"], left = 1, right = 4
     * 输出：3
     * 解释：
     * - "aeo" 是一个元音字符串，因为它以 'a' 开头并以 'o' 结尾。
     * - "mu" 不是元音字符串，因为它没有以元音字母开头。
     * - "ooo" 是一个元音字符串，因为它以 'o' 开头并以 'o' 结尾。
     * - "artro" 是一个元音字符串，因为它以 'a' 开头并以 'o' 结尾。
     * 在上述范围中的元音字符串数目为 3 。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2586TwoThousandFiveHundredEightySix().vowelStrings(
                new String[]{"hey","aeo","mu","ooo","artro"},
                1,
                4
        ));
    }

}
