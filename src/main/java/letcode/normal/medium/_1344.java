package letcode.normal.medium;

/**
 * 1344. Angle Between Hands of a Clock
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/angle-between-hands-of-a-clock/
 * Given two numbers, hour and minutes , return the smaller angle (in degrees) formed between the hour and the minute hand .
 * Answers within 10 -5 of the actual value will be accepted as correct.
 */
public class _1344 {

    public double angleClock(int hour, int minutes) {
        int minuteAngle = minutes * 6;
        double hourAngle = hour * 30 + minutes * 0.5;
        double diffAngle = Math.abs(hourAngle - minuteAngle);
        return diffAngle > 180 ? 360 - diffAngle : diffAngle;
    }

}
