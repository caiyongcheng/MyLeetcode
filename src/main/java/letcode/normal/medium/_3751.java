package letcode.normal.medium;

import letcode.utils.SolutionTestMethod;

/**
 * 3751. Total Waviness of Numbers in Range I
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/total-waviness-of-numbers-in-range-i/
 * You are given two integers num1 and num2 representing an inclusive range [num1, num2] .
 * The waviness of a number is defined as the total count of its peaks and valleys :
 * - A digit is a peak if it is strictly greater than both of its immediate neighbors.
 * - A digit is a valley if it is strictly less than both of its immediate neighbors.
 * - The first and last digits of a number cannot be peaks or vall...
 */
@SolutionTestMethod(method = "totalWaviness")
public class _3751 {

    private int[] digital = new int[6];
    private int numDigitalLen;

    public int totalWaviness(int num1, int num2) {
        int totalWaviness = 0;
        while (num1 <= num2) {
            totalWaviness += calculateWaviness(num1);
            ++num1;
        }
        return totalWaviness;
    }

    public int calculateWaviness(int num) {

        int Waviness = 0;
        numDigitalLen = 0;

        while (num > 0) {
            digital[numDigitalLen++] = num % 10;
            num /= 10;
        }

        for (int i = 1; i < numDigitalLen - 1; i++) {
            if (digital[i] > digital[i - 1] && digital[i] > digital[i + 1]) {
                ++Waviness;
            } else if (digital[i] < digital[i - 1] && digital[i] < digital[i + 1]) {
                ++Waviness;
            }
        }
        return Waviness;
    }
}
