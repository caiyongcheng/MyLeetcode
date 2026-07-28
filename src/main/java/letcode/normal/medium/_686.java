package letcode.normal.medium;

/**
 * 给定两个字符串a 和 b，寻找重复叠加字符串 a 的最小次数，使得字符串 b 成为叠加后的字符串 a 的子串，如果不存在则返回 -1。
 * 注意：字符串 "abc"重复叠加 0 次是 ""，重复叠加 1 次是"abc"，重复叠加 2 次是"abcabc"。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/repeated-string-match 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-12-22 09:03
 **/
public class _686 {

    public int repeatedStringMatch(String a, String b) {
        if (b.length() == 0) {
            return 0;
        }
        //如果b是多次叠加后a的子串
        //那么b应当满足 任意长度a的结尾+任意长度a+任意长度a的开头
        //所以根据b的长度 叠加出a的长度 一次判断即可
        int cnt = b.length() / a.length();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < cnt; i++) {
            stringBuilder.append(a);
        }
        if (stringBuilder.indexOf(b) != -1) {
            return cnt;
        }
        stringBuilder.append(a);
        if (stringBuilder.indexOf(b) != -1) {
            return cnt + 1;
        }
        stringBuilder.append(a);
        if (stringBuilder.indexOf(b) != -1) {
            return cnt + 2;
        }
        stringBuilder.append(a);
        if (stringBuilder.indexOf(b) != -1) {
            return cnt + 3;
        }
        return -1;
    }

}
