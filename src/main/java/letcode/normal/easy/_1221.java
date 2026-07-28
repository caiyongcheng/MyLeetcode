package letcode.normal.easy;

/**
 * 在一个 平衡字符串 中，'L' 和 'R' 字符的数量是相同的。  给你一个平衡字符串s，请你将它分割成尽可能多的平衡字符串。
 * 注意：分割得到的每个字符串都必须是平衡字符串。
 * 返回可以通过分割得到的平衡字符串的 最大数量 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/split-a-string-in-balanced-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-09-07 09:02
 **/
public class _1221 {


    public int balancedStringSplit(String s) {
        int count = 0;
        int ans = 0;
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            ans += aChar == 'R' ? 1 : -1;
            count += ans == 0 ? 1 : 0;
        }
        return count;
    }

}
