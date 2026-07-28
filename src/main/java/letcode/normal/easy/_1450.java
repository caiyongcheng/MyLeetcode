package letcode.normal.easy;

/**
 * @author 蔡永程
 * @since 2022/8/19 8:51
 */
public class _1450 {

    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int rst = 0;
        for (int i = 0; i < startTime.length; i++) {
            if (startTime[i] <= queryTime && endTime[i] >= queryTime) {
                ++rst;
            }
        }
        return rst;
    }

}
