package letcode.normal.difficult;

/**
 * StudyHTTP
 * 给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。
 * 说明:  s可能为空，且只包含从a-z的小写字母。
 * p可能为空，且只包含从a-z的小写字母，以及字符.和*。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/regular-expression-matching 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : CaiYongcheng
 * @since : 2020-06-22 15:29
 **/
public class _10 {

    private char[] chars;
    private char[] pattem;


    public boolean isMatch(String s, String p) {
        chars = s.toCharArray();
        pattem = p.toCharArray();
        return isMatch(0, 0);
    }

    public boolean isMatch(int indexC, int indexP) {
        if (indexP >= pattem.length) {
            return indexC >= chars.length;
        }
        if (indexC >= chars.length) {
            if (((pattem.length - indexP) & 1) == 1) {
                return false;
            }
            while (indexP + 1 < pattem.length) {
                if (pattem[indexP + 1] != '*') {
                    return false;
                }
                indexP += 2;
            }
            return true;
        }
        if (indexP + 1 < pattem.length && pattem[indexP + 1] == '*') {
            if (pattem[indexP] == '.' || pattem[indexP] == chars[indexC]) {
                return isMatch(indexC + 1, indexP + 2) || isMatch(indexC + 1, indexP) || isMatch(indexC, indexP + 2);
            } else {
                return isMatch(indexC, indexP + 2);
            }
        }
        return (pattem[indexP] == '.' || pattem[indexP] == chars[indexC]) && isMatch(indexC + 1, indexP + 1);
    }


}
