package letcode.normal.difficult;

import letcode.utils.TestUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * You are given an array of integers nums. Perform the following steps:
 * Find any two adjacent numbers in nums that are non-coprime.
 * If no such numbers are found, stop the process. Otherwise,
 * delete the two numbers and replace them with their LCM (Least Common Multiple).
 * Repeat this process as long as you keep finding two adjacent non-coprime numbers.
 * Return the final modified array.
 * It can be shown that replacing adjacent non-coprime numbers in any arbitrary order will lead to the same result.
 * The test cases are generated such that the values in the final array are less than or equal to 108.
 * Two values x and y are non-coprime if GCD(x, y) > 1 where GCD(x, y) is the Greatest Common Divisor of x and y.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-09-16 09:28
 */
public class _2197 {

    public List<Integer> replaceNonCoprimes(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        stack.push(nums[0]);
        int curNum;
        int stackPeek;
        int gcd;
        for (int i = 1; i < nums.length; i++) {
            curNum = nums[i];
            stackPeek = stack.peek();
            gcd = gcd(stackPeek, curNum);
            while (gcd != 1) {
                stack.pop();
                curNum = curNum / gcd * stackPeek;
                if (stack.isEmpty()) {
                    break;
                }
                stackPeek = stack.peek();
                gcd = gcd(stackPeek, curNum);
            }
            stack.push(curNum);
        }

        return new ArrayList<>(stack);
    }

    private int gcd(int x, int y) {
        if (x < y) {
            return gcd(y, x);
        }

        int tmp;
        while (y != 0) {
            tmp = x;
            x = y;
            y = tmp % y;
        }
        return x;
    }

}
