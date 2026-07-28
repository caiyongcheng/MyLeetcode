package letcode.normal.medium;

import letcode.utils.TestCaseOutputUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 给你一个区间数组 intervals ，其中intervals[i] = [starti, endi] ，且每个starti 都 不同 。
 * 区间 i 的 右侧区间 可以记作区间 j ，并满足 startj>= endi ，且 startj 最小化 。
 * 返回一个由每个区间 i 的 右侧区间 的最小起始位置组成的数组。如果某个区间 i 不存在对应的 右侧区间 ，则下标 i 处的值设为 -1 。
 * 来源：力扣（LeetCode） 链接：https://leetcode.cn/problems/find-right-interval
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-05-20 16:30
 **/
public class _436 {

    public int[] findRightInterval(int[][] intervals) {
        int[] ans = new int[intervals.length];
        List<Integer> sortIdx = Stream.iterate(0, i -> i + 1).limit(intervals.length)
                .sorted(Comparator.comparingInt((Integer o) -> intervals[o][0]).thenComparingInt(o -> intervals[o][1])).collect(Collectors.toList());
        int idx;
        int maxStart = intervals[sortIdx.get(intervals.length - 1)][0];
        for (int i = 0; i < sortIdx.size(); i++) {
            idx = sortIdx.get(i);
            if (intervals[idx][1] > maxStart) {
                ans[idx] = -1;
                continue;
            }
            if (i == intervals.length - 1) {
                ans[idx] = -1;
                continue;
            }
            int l = i;
            if (intervals[sortIdx.get(l)][0] >= intervals[idx][1]) {
                ans[idx] = sortIdx.get(i);
                continue;
            }
            int r = intervals.length - 1;
            int mid;
            while (l < r) {
                mid = (l + r) >>> 1;
                if (mid == l) {
                    break;
                }
                if (intervals[sortIdx.get(mid)][0] < intervals[idx][1]) {
                    l = mid;
                } else {
                    r = mid;
                }
            }
            ans[idx] = sortIdx.get(r);
        }
        return ans;
    }


}
