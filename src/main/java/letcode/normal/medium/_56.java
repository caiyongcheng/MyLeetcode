package letcode.normal.medium;

import letcode.utils.TestCaseOutputUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，
 * 并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/merge-intervals 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-09-17 14:11
 **/
public class _56 {

    public int[][] merge(int[][] intervals) {
        //按起始点，终点排序即可,一次合并即可
        Arrays.sort(intervals, Comparator.comparingInt(arr -> arr[0]));
        LinkedList<int[]> list = new LinkedList<>();
        int[] lastInterval;
        list.add(intervals[0]);
        for (int index = 1; index < intervals.length; index++) {
            lastInterval = list.getLast();
            if (lastInterval[1] >= intervals[index][0]) {
                lastInterval[1] = Math.max(lastInterval[1], intervals[index][1]);
            } else {
                list.add(intervals[index]);
            }
        }
        return list.toArray(new int[0][0]);
    }

}
