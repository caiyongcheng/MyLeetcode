package letcode.normal.medium;

import java.util.Stack;

/**
 * @program: MyLeetcode
 * @description: 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 * 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/remove-duplicate-letters 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @date: 2021-03-24 16:16
 **/
public class _1081OneThousandEightyOne {

    public String removeDuplicateLetters(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        int[] nums = new int[26];
        int[] set = new int[26];
        int index;
        int aIndex;
        for (char aChar : chars) {
            nums[aChar-'a']++;
        }
        for (char aChar : chars) {
            aIndex = aChar-'a';
            --nums[aIndex];
            if (set[aIndex] == 1) {
                continue;
            }
            while (!stack.empty() && stack.peek() > aChar && nums[stack.peek()-'a'] > 0) {
                index = stack.pop() - 'a';
                set[index] = 0;
            }
            if (!stack.empty() && stack.peek() <= aChar
                    || stack.empty() || set[aIndex] == 0) {
                stack.push(aChar);
                set[aChar-'a'] = 1;
            }
        }
        char[] characters = new char[stack.size()];
        for (int i = characters.length - 1; i >= 0; i--) {
            characters[i] = stack.pop();
        }
        return String.valueOf(characters);
    }

    /**
     * 示例 1：
     * 输入：s = "bcabc"
     * 输出："abc"
     *
     * 示例 2：
     * 输入：s = "cbacdcbc"
     * 输出："acdb"
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1081OneThousandEightyOne().removeDuplicateLetters("edebbed"));
    }

}
