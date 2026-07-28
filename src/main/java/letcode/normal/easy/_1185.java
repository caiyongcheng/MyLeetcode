package letcode.normal.easy;

/**
 * 给你一个日期，请你设计一个算法来判断它是对应一周中的哪一天。  输入为三个整数：day、month 和 year，分别表示日、月、年。
 * 您返回的结果必须是这几个值中的一个 {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/day-of-the-week 著作权归领扣网络所有。商业转载请联系官方授权，
 * 非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-01-04 09:07
 **/
public class _1185 {

    static String[] DAYS = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    static int[] MONTHS = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public String dayOfTheWeek(int day, int month, int year) {
        int betweenDays = 0;
        int startYear = 1970;
        int startMonth = 1;
        while (startYear < year) {
            if (startYear % 400 == 0 || (startYear % 100 != 0 && startYear % 4 == 0)) {
                betweenDays += 366;
            } else {
                betweenDays += 365;
            }
            ++startYear;
        }
        if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)) {
            MONTHS[2] = 29;
        } else {
            MONTHS[2] = 28;
        }
        while (startMonth < month) {
            betweenDays += MONTHS[startMonth++];
        }
        betweenDays += day;
        return DAYS[(3 + betweenDays) % 7];
    }

}
