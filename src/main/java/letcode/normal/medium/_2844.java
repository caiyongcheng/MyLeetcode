package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * You are given a 0-indexed string num representing a non-negative integer.
 * In one operation, you can pick any digit of num and delete it.
 * Note that if you delete all the digits of num, num becomes 0.
 * Return the minimum number of operations required to make num special.
 * An integer x is considered special if it is divisible by 25.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-07-26 10:54
 */
public class _2844 {

    public int minimumOperations(String num) {
        /*
         * 其实这种做法冗余了
         * 因为从后往前 组合的数哪个先出现 因为删除的元素的是组合数的位置到字符串末尾的长度-2，即只保留2个
         * 所以从后往前，越早出现越好
         */
        return Integer.min(
                Integer.min(reduceToDivisibleBy25('0', '0', num), reduceToDivisibleBy25('5', '0', num)),
                Integer.min(reduceToDivisibleBy25('2', '5', num), reduceToDivisibleBy25('7', '5', num))
        );
    }

    private int reduceToDivisibleBy25(char start, char end, String num) {
        int deleteCount = 0;
        int length = num.length();

        int endIdx = indexOf(end, num, length);

        if (endIdx == -1) {
            return num.length();
        }
        deleteCount += length - endIdx - 1;

        int startIdx = indexOf(start, num, endIdx);
        if (startIdx == -1) {
            return num.length() - (start == '0' && end == '0' ? 1 : 0);
        }
        deleteCount += endIdx - startIdx - 1;
        return deleteCount;
    }

    private static int indexOf(char end, String num, int length) {
        int endIdx;
        for (endIdx = length - 1; endIdx >= 0; endIdx--) {
            if (num.charAt(endIdx) == end) {
                break;
            }
        }
        return endIdx;
    }

}
