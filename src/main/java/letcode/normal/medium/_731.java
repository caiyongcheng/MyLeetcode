package letcode.normal.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现一个 MyCalendar 类来存放你的日程安排。如果要添加的时间内不会导致三重预订时，则可以存储这个新的日程安排。
 * MyCalendar 有一个 book(int start, int end)方法。
 * 它意味着在 start 到 end 时间内增加一个日程安排，注意，这里的时间是半开区间，即 [start, end), 实数x 的范围为，
 * start <= x < end。  当三个日程安排有一些时间上的交叉时（例如三个日程安排都在同一时间内），就会产生三重预订。
 * 每次调用 MyCalendar.book方法时，如果可以将日程安排成功添加到日历中而不会导致三重预订，返回 true。否则，
 * 返回 false 并且不要将该日程安排添加到日历中。
 * 请按照以下步骤调用MyCalendar 类: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 * 来源：力扣（LeetCode） 链接：https://leetcode.cn/problems/my-calendar-ii 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-07-19 08:51
 **/
public class _731 {

    List<int[]> calenderList;

    public _731() {
        calenderList = new ArrayList<>(1024);
    }

    public boolean book(int start, int end) {
        /*
         * 最朴素的暴力 可以使用二分进行优化
         */
        if (calenderList.isEmpty()) {
            calenderList.add(new int[]{start, end});
            return true;
        }
        List<int[]> overlapList = new ArrayList<>();
        for (int[] range : calenderList) {
            int[] overlapRange = getOverlapRange(start, end, range[0], range[1]);
            if (overlapRange != null) {
                overlapList.add(overlapRange);
            }
        }
        if (overlapList.isEmpty()) {
            calenderList.add(new int[]{start, end});
            return true;
        }
        int[] leftOverlapRange;
        int[] rightOverlapRange;
        for (int i = 0; i < overlapList.size(); i++) {
            leftOverlapRange = overlapList.get(i);
            for (int j = i + 1; j < overlapList.size(); j++) {
                rightOverlapRange = overlapList.get(j);
                if (getOverlapRange(leftOverlapRange[0], leftOverlapRange[1],
                        rightOverlapRange[0], rightOverlapRange[1]) != null) {
                    return false;
                }
            }
        }
        calenderList.add(new int[]{start, end});
        return true;
    }


    private int[] getOverlapRange(int leftStart, int leftEnd, int rightStart, int rightEnd) {
        if (leftStart > rightStart) {
            return getOverlapRange(rightStart, rightEnd, leftStart, leftEnd);
        }
        if (leftStart == rightStart) {
            return new int[]{leftStart, Math.min(leftEnd, rightEnd)};
        }
        if (leftEnd <= rightStart) {
            return null;
        }
        if (leftEnd > rightEnd) {
            return new int[]{rightStart, rightEnd};
        }
        return new int[]{rightStart, leftEnd};
    }


}
