package letcode.normal.easy;

/**
 * 统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。  请注意，你可以假定字符串里不包括任何不可打印的字符。
 * 示例:  输入: "Hello, my name is John" 输出: 5 解释: 这里的单词是指连续的不是空格的字符，所以 "Hello," 算作 1 个单词。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/number-of-segments-in-a-string 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-10-07 22:47
 **/
public class _434 {

    public int countSegments(String s) {
        int ans = 0;
        for (int index = 0; index < s.length(); index++) {
            if (s.charAt(index) != ' ' && (index == 0 || s.charAt(index - 1) == ' ')) {
                ++ans;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new _434().countSegments(
                "123"
        ));
    }

}
