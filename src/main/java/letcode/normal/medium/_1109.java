package letcode.normal.medium;

import letcode.utils.TestCaseOutputUtils;

import java.util.Arrays;

/**
 * 这里有n个航班，它们分别从 1 到 n 进行编号。  
 * 有一份航班预订表bookings ，表中第i条预订记录bookings[i] = [firsti, lasti, seatsi]
 * 意味着在从 firsti到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi个座位。  
 * 请你返回一个长度为 n 的数组answer，其中 answer[i] 是航班 i 上预订的座位总数。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/corporate-flight-bookings 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 1 <= n <= 2 * 10^4
 * 1 <= bookings.length <= 2 * 10^4
 * bookings[i].length == 3
 * 1 <= firsti <= lasti <= n
 * 1 <= seatsi <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/corporate-flight-bookings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author CaiYongcheng
 * @since 2021-08-31 09:05
 **/
public class _1109 {


    public int[] corpFlightBookings(int[][] bookings, int n) {
        /*
         * 传统的穷举，最大时间复杂度 2 * 10^4 * 2 * 10^4 * 3 (两个判断 一个赋值) 可能会超时
         * 超时
         */
        int iniIndex = 0;
        Arrays.stream(bookings).forEach(ints -> {
            --ints[0];
            --ints[1];
        });
        Arrays.sort(bookings, (o1, o2) -> o1[0] < o2[0] ? -1 : o1[0] > o2[0] ? 1 : Integer.compare(o1[1], o2[1]));
        int[] ans = new int[n];
        for (int no = 0; no < ans.length; no++) {
            while (iniIndex < bookings.length && no > bookings[iniIndex][1]) {
                ++iniIndex;
            }
            for (int i = iniIndex; i < bookings.length && bookings[i][0] <= no; ++i) {
                if (bookings[i][1] >= no) {
                    ans[no] += bookings[i][2];
                }
            }
        }
        return ans;
    }


    public int[] corpFlightBookings2(int[][] bookings, int n) {
        /*
         * 差分
         */
        int[] diff = new int[n];
        for (int[] booking : bookings) {
            diff[booking[0]-1] += booking[2];
            if (booking[1] < n) {
                diff[booking[1]] -= booking[2];
            }
        }
        for (int i = 1; i < diff.length; i++) {
            diff[i] += diff[i-1];
        }
        return diff;
    }


}
