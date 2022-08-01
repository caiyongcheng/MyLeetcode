/*
 * 版权所有（c）<2021><蔡永程>
 *
 * 反996许可证版本1.0
 *
 * 在符合下列条件的情况下，特此免费向任何得到本授权作品的副本（包括源代码、文件和/或相关内容，以
 * 下统称为“授权作品”）的个人和法人实体授权：被授权个人或法人实体有权以任何目的处置授权作品，包括
 * 但不限于使用、复制，修改，衍生利用、散布，发布和再许可：
 *
 * 1. 个人或法人实体必须在许可作品的每个再散布或衍生副本上包含以上版权声明和本许可证，不得自行修
 * 改。
 * 2. 个人或法人实体必须严格遵守与个人实际所在地或个人出生地或归化地、或法人实体注册地或经营地（
 * 以较严格者为准）的司法管辖区所有适用的与劳动和就业相关法律、法规、规则和标准。如果该司法管辖区
 * 没有此类法律、法规、规章和标准或其法律、法规、规章和标准不可执行，则个人或法人实体必须遵守国际
 * 劳工标准的核心公约。
 * 3. 个人或法人不得以任何方式诱导、暗示或强迫其全职或兼职员工或其独立承包人以口头或书面形式同意
 * 直接或间接限制、削弱或放弃其所拥有的，受相关与劳动和就业有关的法律、法规、规则和标准保护的权利
 * 或补救措施，无论该等书面或口头协议是否被该司法管辖区的法律所承认，该等个人或法人实体也不得以任
 * 何方法限制其雇员或独立承包人向版权持有人或监督许可证合规情况的有关当局报告或投诉上述违反许可证
 * 的行为的权利。
 *
 * 该授权作品是"按原样"提供，不做任何明示或暗示的保证，包括但不限于对适销性、特定用途适用性和非侵
 * 权性的保证。在任何情况下，无论是在合同诉讼、侵权诉讼或其他诉讼中，版权持有人均不承担因本软件或
 * 本软件的使用或其他交易而产生、引起或与之相关的任何索赔、损害或其他责任。
 */

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
 * @date 2022-07-19 08:51
 **/
public class _731SevenHundredThirtyOne {

    List<int[]> calenderList;

    public _731SevenHundredThirtyOne() {
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


    /**
     * MyCalendar();
     * MyCalendar.book(10, 20); // returns true
     * MyCalendar.book(50, 60); // returns true
     * MyCalendar.book(10, 40); // returns true
     * MyCalendar.book(5, 15); // returns false
     * MyCalendar.book(5, 10); // returns true
     * MyCalendar.book(25, 55); // returns true
     * 解释：
     * 前两个日程安排可以添加至日历中。 第三个日程安排会导致双重预订，但可以添加至日历中。
     * 第四个日程安排活动（5,15）不能添加至日历中，因为它会导致三重预订。
     * 第五个日程安排（5,10）可以添加至日历中，因为它未使用已经双重预订的时间10。
     * 第六个日程安排（25,55）可以添加至日历中，因为时间 [25,40] 将和第三个日程安排双重预订；
     * 时间 [40,50] 将单独预订，时间 [50,55）将和第二个日程安排双重预订。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/my-calendar-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        _731SevenHundredThirtyOne MyCalendar = new _731SevenHundredThirtyOne();
        System.out.println(MyCalendar.book(10, 20));
        System.out.println(MyCalendar.book(50, 60));
        System.out.println(MyCalendar.book(10, 40));
        System.out.println(MyCalendar.book(5, 15));
        System.out.println(MyCalendar.book(5, 10));
        System.out.println(MyCalendar.book(25, 55));
    }


}
