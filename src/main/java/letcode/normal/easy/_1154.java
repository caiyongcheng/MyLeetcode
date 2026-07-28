package letcode.normal.easy;

/**
 * 给你一个字符串date ，按 YYYY-MM-DD 格式表示一个 现行公元纪年法 日期。请你计算并返回该日期是当年的第几天。
 * 通常情况下，我们认为 1 月 1 日是每年的第 1 天，1 月 2 日是每年的第 2 天，依此类推。每个月的天数与现行公元纪年法（格里高利历）一致。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/day-of-the-year 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-12-21 08:59
 **/
public class _1154 {

    private static final int[] MONTH = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int dayOfYear(String date) {
        //获取月份 天
        int month = (date.charAt(5) - '0') * 10 + (date.charAt(6) - '0');
        int day = (date.charAt(8) - '0') * 10 + (date.charAt(9) - '0');
        if (month == 1) {
            return day;
        }
        if (month == 2) {
            return MONTH[1] + day;
        }
        //判断是不是闰年
        int year = (date.charAt(0) - '0') * 1000 + (date.charAt(1) - '0') * 100
                + (date.charAt(2) - '0') * 10 + (date.charAt(3) - '0');
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            MONTH[2] = 29;
        } else {
            MONTH[2] = 28;
        }
        for (int i = 0; i < month; i++) {
            day += MONTH[i];
        }
        return day;
    }

}
