package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.Arrays;

/**
 * You are given a 0-indexed integer array buses of length n, where buses[i] represents
 * the departure time of the ith bus. You are also given a 0-indexed integer array passengers
 * of length m, where passengers[j] represents the arrival time of the jth passenger.
 * All bus departure times are unique. All passenger arrival times are unique.
 * You are given an integer capacity, which represents the maximum number of passengers that can get on each bus.
 * When a passenger arrives, they will wait in line for the next available bus.
 * You can get on a bus that departs at x minutes if you arrive at y minutes where y <= x,
 * and the bus is not full. Passengers with the earliest arrival times get on the bus first.
 * More formally when a bus arrives, either:  If capacity or fewer passengers are waiting for a bus,
 * they will all get on the bus, or The capacity passengers with the earliest arrival times will get
 * on the bus. Return the latest time you may arrive at the bus station to catch a bus.
 * You cannot arrive at the same time as another passenger.  Note: The arrays buses and passengers
 * are not necessarily sorted.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-09-22 17:01
 */
public class _2332 {

    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        // 先排序 然后依次决定需要上哪辆公交车
        Arrays.sort(buses);
        Arrays.sort(passengers);

        int left = 0;
        int right;
        int temp;
        int ans = -1;
        for (int bus : buses) {
            if (left >= passengers.length) {
                ans = buses[buses.length - 1];
                break;
            }
            if (passengers[left] > bus) {
                ans = bus;
                continue;
            }
            // 找到当前公交车能坐的最大乘客
            for (right = left;
                 right < passengers.length
                         && passengers[right] <= bus
                         && right - left + 1 <= capacity;
                 right++);
            --right;
            // 找到满足条件的插队时间
            // 没有坐满的情况 并且最晚乘客不等于发车时间的情况下
            if (right - left + 1 < capacity && passengers[right] != bus) {
                ans = bus;
            } else {
                for (temp = right; temp >= left; temp--) {
                    if (temp == 0 || passengers[temp] - 1 != passengers[temp - 1]) {
                        ans = passengers[temp] - 1;
                        break;
                    }
                }
            }
            left = right + 1;
        }

        // 乘客都无法上车的情况
        if (ans == -1) {
            ans = buses[buses.length - 1];
        }
        return ans;
    }

    /**
     * Example 1:
     *
     * Input: buses = [10,20], passengers = [2,17,18,19], capacity = 2
     * Output: 16
     * Explanation: Suppose you arrive at time 16.
     * At time 10, the first bus departs with the 0th passenger.
     * At time 20, the second bus departs with you and the 1st passenger.
     * Note that you may not arrive at the same time as another passenger, which is why you must arrive before the 1st passenger to catch the bus.
     * Example 2:
     *
     * Input: buses = [20,30,10], passengers = [19,13,26,4,25,11,21], capacity = 2
     * Output: 20
     * Explanation: Suppose you arrive at time 20.
     * At time 10, the first bus departs with the 3rd passenger.
     * At time 20, the second bus departs with the 5th and 1st passengers.
     * At time 30, the third bus departs with the 0th passenger and you.
     * Notice if you had arrived any later, then the 6th passenger would have taken your seat on the third bus.
     * @param args
     */
    public static void main(String[] args) {
        //TestUtil.test(_2332.class);
        //TestUtil.test(_2332.class, "=[2,3],=[4,2],=1");
        TestUtil.test(_2332.class, "=[3,2],=[2],=2");
    }

}
