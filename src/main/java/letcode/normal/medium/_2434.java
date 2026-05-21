package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.ArrayDeque;

/**
 * You are given a string s and a robot that currently holds an empty string t.
 * Apply one of the following operations until s and t are both empty:  Remove the first character of a string s and give it to the robot.
 * The robot will append this character to the string t.
 * Remove the last character of a string t and give it to the robot. The robot will write this character on paper.
 * Return the lexicographically smallest string that can be written on the paper.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-06-06 10:04
 */
public class _2434 {

    public String robotWithString(String s) {
        int length = s.length();
        int[] suffixArr = new int[length];
        char minCh = 'z';
        char curCh;
        for (int i = length - 1; i > -1; i--) {
            curCh = s.charAt(i);
            if (curCh < minCh) {
                minCh = curCh;
            }
            suffixArr[i] = minCh;
        }

        ArrayDeque<Character> stack = new ArrayDeque<>();
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < length; i++) {
            while (!stack.isEmpty() && stack.peek() <= suffixArr[i]) {
                ans.append(stack.pop());
            }
            stack.push(s.charAt(i));
        }

        while (!stack.isEmpty()) {
            ans.append(stack.pop());
        }
        return ans.toString();
    }

}
