package letcode.normal.easy;

/**
 * @author Caiyongcheng
 * @description 给你两个字符串数组 event1 和event2，表示发生在同一天的两个闭区间时间段事件，
 * 其中：  event1 = [startTime1, endTime1] 且 event2 = [startTime2, endTime2] 事件的时间为有效的 24 小时制且按HH:MM格式给出。
 * 当两个事件存在某个非空的交集时（即，某些时刻是两个事件都包含的），则认为出现 冲突。
 * 如果两个事件之间存在冲突，返回true；否则，返回false 。
 * 来源：力扣（LeetCode） 链接：https://leetcode.cn/problems/determine-if-two-events-have-conflict
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @since 2023/5/17 10:58
 */
public class _2446 {

    public boolean haveConflict(String[] event1, String[] event2) {
        //字符串可以直接进行比较 但是需要说明 这依赖于比较算法的实现
        int[] event1Int = parseInt(event1);
        int[] event2Int = parseInt(event2);
        return (event1Int[0] >= event2Int[0] && event1Int[0] <= event2Int[1])
                || (event2Int[0] >= event1Int[0] && event2Int[0] <= event1Int[1]);
    }

    public int[] parseInt(String[] time) {
        int[] parseRst = new int[2];
        parseRst[0] = parseDoubleDigitNum(time[0], 0) * 60 + parseDoubleDigitNum(time[0], 3);
        parseRst[1] = parseDoubleDigitNum(time[1], 0) * 60 + parseDoubleDigitNum(time[1], 3);
        return parseRst;
    }

    public int parseDoubleDigitNum(String numStr, int startIdx) {
        return (numStr.charAt(startIdx) - '0') * 10 + numStr.charAt(startIdx + 1) - '0';
    }

}
