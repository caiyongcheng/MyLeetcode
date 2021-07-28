package letcode.normal.medium;

/**
 * @program: Leetcode
 * @description: 给你两个字符串a 和b，它们长度相同。请你选择一个下标，将两个字符串都在相同的下标 分割开。
 * 由a可以得到两个字符串：aprefix和asuffix，
 * 满足a = aprefix + asuffix，同理，由b 可以得到两个字符串bprefix 和bsuffix，
 * 满足b = bprefix + bsuffix。请你判断aprefix + bsuffix 或者bprefix + asuffix能否构成回文串。
 * 当你将一个字符串s分割成sprefix 和ssuffix时，ssuffix 或者sprefix 可以为空。
 * 比方说，s = "abc"那么"" + "abc"，"a" + "bc"，"ab" + "c"和"abc" + ""都是合法分割。
 * 如果 能构成回文字符串 ，那么请返回true，否则返回false。
 * 请注意，x + y表示连接字符串x 和y。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/split-two-strings-to-make-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-12-31 09:21
 */
public class _1616OneThousandOneHundredSixteen {

    private static boolean isPlalindromeStr(String str) {
        if (null == str || str.length() < 2) {
            return true;
        }
        int lindex = 0;
        int rindex = str.length() - 1;
        while (lindex <= rindex) {
            if (str.charAt(lindex) != str.charAt(rindex)) {
                return false;
            }
            ++lindex;
            --rindex;
        }
        return true;
    }

    private static boolean isPlalindromeMergeStr(String preStr, String lastStr) {
        int lindex = 0;
        int rindex = preStr.length() - 1;
        while (lindex <= rindex) ;
        return true;
    }


}