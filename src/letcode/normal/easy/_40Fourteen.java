package letcode.easy;

/**
 * @program: StudyHTTP
 * @description: 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * @author: 蔡永程
 * @create: 2020-06-17 23:05
 */
public class _40Fourteen {

    /**
     * 示例 1:
     * <p>
     * 输入: ["flower","flow","flight"]
     * 输出: "fl"
     * 示例 2:
     * <p>
     * 输入: ["dog","racecar","car"]
     * 输出: ""
     * 解释: 输入不存在公共前缀。
     *
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        if (strs.length == 1) return strs[0];
        StringBuilder stringBuilder = new StringBuilder();
        int x = 0, y = 0, length = 0;
        int minlength1 = Integer.MAX_VALUE;
        int minlength2 = Integer.MAX_VALUE;
        int i = 0, j = 0;
        int strlength = 0;
        char ch = 'a';
        for (i = 0; i < strs.length; ++i) {
            length = strs[i].length();
            if (length <= minlength1) {
                minlength2 = minlength1;
                minlength1 = length;
                y = x;
                x = i;
            } else if (length <= minlength2) {
                minlength2 = length;
                y = i;
            }
        }
        for (i = 0; i < minlength1; ++i) {
            ch = strs[x].charAt(i);
            if (ch == strs[y].charAt(i)) {
                stringBuilder.append(ch);
            } else {
                break;
            }
        }
        strlength = stringBuilder.length();
        for (i = 0; i < strs.length; ++i) {
            if (i == x || i == y) continue;
            if (strlength <= 0) return "";
            for (j = strlength - 1; j > -1; --j) {
                ch = stringBuilder.charAt(j);
                if (strs[i].charAt(j) != ch) {
                    strlength -= 1;
                }
            }
        }
        return stringBuilder.substring(0, strlength).toString();
    }

    public static void main(String[] args) {
        String[] strings = {"dog", "racecar", "car"};
        System.out.println(longestCommonPrefix(strings));
    }
}