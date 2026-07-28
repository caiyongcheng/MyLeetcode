package letcode.normal.medium;

import java.util.Arrays;
import java.util.List;

/**
 * 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
 *
 * @author CaiYongcheng
 * @since 2022-01-18 09:06
 **/
public class _539 {


    public int findMinDifference(List<String> timePoints) {
        if (timePoints.size() > 24 * 60) {
            return 0;
        }
        timePoints.sort(String::compareTo);
        int ans = 24 * 60;
        String firstTime = timePoints.get(0);
        String currentTime;
        int pre = (firstTime.charAt(0) - '0') * 600 + (firstTime.charAt(1) - '0') * 60
                + (firstTime.charAt(3) - '0') * 10 + (firstTime.charAt(4) - '0');
        int current = 0;
        for (int i = 1; i < timePoints.size(); i++) {
            currentTime = timePoints.get(i);
            current = (currentTime.charAt(0) - '0') * 600 + (currentTime.charAt(1) - '0') * 60
                    + (currentTime.charAt(3) - '0') * 10 + (currentTime.charAt(4) - '0');
            if (current - pre < ans) {
                ans = current - pre;
            }
            pre = current;
        }
        currentTime = timePoints.get(0);
        current = (currentTime.charAt(0) - '0') * 600 + (currentTime.charAt(1) - '0') * 60
                + (currentTime.charAt(3) - '0') * 10 + (currentTime.charAt(4) - '0') + 24 * 60;
        return Math.min(current - pre, ans);
    }


}
