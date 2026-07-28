package letcode.normal.easy;

/**
 * 给你一个字符串 time ，格式为 hh:mm（小时：分钟），其中某几位数字被隐藏（用 ? 表示）。  有效的时间为 00:00 到 23:59 之间的所有时间，包括 00:00 和 23:59 。 
 * 替换time 中隐藏的数字，返回你可以得到的最晚有效时间。 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/latest-time-by-replacing-hidden-digits 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-07-24 11:39
 **/
public class _1736 {

    public String maximumTime(String time) {
        char[] chars = time.toCharArray();
        if (chars[0] == '?') {
            if (chars[1] >= '4' && chars[1] <='9') {
                chars[0] = '1';
            } else {
                chars[0] = '2';
            }
        }
        if (chars[1] == '?') {
            if (chars[0] != '2') {
                chars[1] = '9';
            } else {
                chars[1] = '3';
            }
        }
        if (chars[3] == '?') {
            chars[3] = '5';
        }
        if (chars[4] == '?') {
            chars[4] = '9';
        }
        return new String(chars);
    }

}
