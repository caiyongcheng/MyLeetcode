package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 1344. Angle Between Hands of a Clock
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/angle-between-hands-of-a-clock/
 * Given two numbers, hour and minutes , return the smaller angle (in degrees) formed between the hour and the minute hand .
 * Answers within 10 -5 of the actual value will be accepted as correct.
 */
public class _1344 {

    public double angleClock(int hour, int minutes) {
        BigDecimal minuteAngleFrom0 = BigDecimal.valueOf(minutes * 6L);
        BigDecimal hourAngleFrom0 = BigDecimal.valueOf(hour % 12 * 30L)
                .add(BigDecimal.valueOf(minutes).divide(BigDecimal.valueOf(2L), 1, RoundingMode.HALF_UP));

        BigDecimal betweenAngle = minuteAngleFrom0.subtract(hourAngleFrom0).abs();
        return betweenAngle.compareTo(BigDecimal.valueOf(180)) > 0
                ? new BigDecimal(360).subtract(betweenAngle).setScale(5, RoundingMode.HALF_UP).doubleValue()
                : betweenAngle.setScale(5, RoundingMode.HALF_UP).doubleValue();
    }

    public static void main(String[] args) {
        TestUtil.test("=1,=57");
    }
}
