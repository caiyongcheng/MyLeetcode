package letcode.normal.unansweredquestions.difficult;

import letcode.utils.SolutionTestMethod;

/**
 * 3753. Total Waviness of Numbers in Range II
 * Difficulty: Hard
 * Link: https://leetcode.cn/problems/total-waviness-of-numbers-in-range-ii/
 * You are given two integers num1 and num2 representing an inclusive range [num1, num2] .
 * The waviness of a number is defined as the total count of its peaks and valleys :
 * - A digit is a peak if it is strictly greater than both of its immediate neighbors.
 * - A digit is a valley if it is strictly less than both of its immediate neighbors.
 * - The first and last digits of a number cannot be peaks or vall...
 */
@SolutionTestMethod(method = "totalWaviness")
public class _3753 {

    public int totalWaviness(int num1, int num2) {
        int totalWaviness = 0;

        if (num1 < 100) {
            num1 = 100;
        }

        while (num1 <= num2) {
            totalWaviness += calculateWaviness(num1);
            ++num1;
        }
        return totalWaviness;
    }

    public int calculateWaviness(int num) {

        int Waviness = 0;

        int firstNum = num % 10;
        num /= 10;
        int secondNum = num % 10;
        num /= 10;
        int thirdNum = num % 10;


        while (num > 0) {
            num /= 10;
            if (secondNum > firstNum && secondNum > thirdNum) {
                ++Waviness;
            } else if (secondNum < firstNum && secondNum < thirdNum) {
                ++Waviness;
            }
            firstNum = secondNum;
            secondNum = thirdNum;
            thirdNum = num % 10;
        }

        return Waviness;
    }
}
