package letcode.normal.easy;

/**
 * 给你一个字符串 s ，仅反转字符串中的所有元音字母，并返回结果字符串。  元音字母包括 'a'、'e'、'i'、'o'、'u'，且可能以大小写两种形式出现。
 *
 * @author CaiYongcheng
 * @date 2021-08-19 14:31
 **/
public class _345ThreeHundredFortyFive {

    public String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            while (left < right && notVowel(chars[left])) {
                ++left;
            }
            while (left < right && notVowel(chars[right])) {
                --right;
            }
            if (right > left) {
                char temporary = chars[right];
                chars[right] = chars[left];
                chars[left] = temporary;
                ++left;
                --right;
            }
        }
        return new String(chars);
    }


    public boolean notVowel(char letter) {
        return !(letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u'
                    || letter == 'A' || letter == 'E' || letter == 'I' || letter == 'O' || letter == 'U');
    }

    /**
     * 例 1：
     * 输入：s = "hello"
     * 输出："holle"
     *
     * 示例 2：
     * 输入：s = "leetcode"
     * 输出："leotcede"
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _345ThreeHundredFortyFive().reverseVowels("leetcode"));
    }


}
