package normal.easy;

/**
 * @program: Leetcode
 * @description: 给定两个字符串 s 和 t，它们只包含小写字母。
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。  请找出在 t 中被添加的字母。
 * @author: 蔡永程
 * @create: 2020-12-18 14:44
 */
public class _389 {

    public char findTheDifference(String s, String t) {
        int sumS = 0;
        for (int index = 0; index < s.length(); index++) {
            sumS += s.charAt(index);
            sumS -= t.charAt(index);
        }
        sumS -= t.charAt(t.length() - 1);
        return (char) -sumS;
    }

}
