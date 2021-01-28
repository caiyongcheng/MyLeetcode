package letcode.easy;

/**
 * StudyHTTP
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。
 * 如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。
 * 如果不存在最后一个单词，请返回 0 。
 * 说明：一个单词是指仅由字母组成、不包含任何空格字符的 最大子字符串。
 *
 * @author : CaiYongcheng
 * @date : 2020-06-24 15:01
 **/
public class _58FiftyEight {

    /**
     * 示例:
     * 输入: "Hello World"
     * 输出: 5
     *
     * @param s
     * @return
     */
    public static int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int i = 0, j = 0;
        int n = s.length();
        int res = 0;
        for (; i < n; ++i) {
            if (s.charAt(i) != ' ') {
                //此处需注意，条件不满足的情况是j==n||s[j] == ‘ ’
                // 所以长度是j-i
                for (j = i; j < n && s.charAt(j) != ' '; ++j) ;
                res = j - i;
                i = j;
            }
        }
        return res;
    }

    public static int lengthOfLastWord2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int i = s.length() - 1, j = 0;
        int n = s.length();
        int res = 0;
        for (; i > -1; --i) {
            if (s.charAt(i) != ' ') {
                for (j = i; j > -1 && s.charAt(j) != ' '; --j) ;
                return i - j;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord2("Hello World"));
    }


}
