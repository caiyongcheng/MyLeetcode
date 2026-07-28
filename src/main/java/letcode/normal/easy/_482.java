package letcode.normal.easy;

/**
 * 有一个密钥字符串 S ，只包含字母，数字以及 '-'（破折号）。其中， N 个 '-' 将字符串分成了 N+1 组。
 * 给你一个数字 K，请你重新格式化字符串，使每个分组恰好包含 K 个字符。
 * 特别地，第一个分组包含的字符个数必须小于等于 K，
 * 但至少要包含 1 个字符。两个分组之间需要用 '-'（破折号）隔开，并且将所有的小写字母转换为大写字母。
 * 给定非空字符串 S 和数字 K，按照上面描述的规则进行格式化。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/license-key-formatting 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-10-04 12:34
 **/
public class _482 {

    public String licenseKeyFormatting(String s, int k) {
        s = s.toUpperCase();
        int length = s.length();
        int count = 0;
        StringBuilder ans = new StringBuilder();
        for (int index = 0; index < length; index++) {
            if (s.charAt(index) == '-') {
                ++count;
            }
        }
        length -= count;
        int firstGroupLenth = length % k;
        length = s.length();
        int sIndex = 0;
        for (count = 0; count < firstGroupLenth && sIndex < length; sIndex++) {
            if (s.charAt(sIndex) != '-') {
                ans.append(s.charAt(sIndex));
                ++count;
            }
        }
        if (ans.length() > 0) {
            ans.append('-');
        }
        count = 0;
        while (sIndex < length) {
            if (s.charAt(sIndex) == '-') {
                ++sIndex;
                continue;
            }
            ans.append(s.charAt(sIndex));
            ++count;
            ++sIndex;
            if (count == k) {
                count = 0;
                ans.append("-");
            }
        }
        return ans.length() == 0 ? ans.toString() : ans.substring(0, ans.length() - 1);
    }

}
