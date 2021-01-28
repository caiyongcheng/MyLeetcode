package letcode.normal.medium;

import java.util.LinkedList;

/**
 * @program: Leetcode
 * @description: 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。
 * 需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 * @author: 蔡永程
 * @create: 2020-12-10 16:07
 */
public class N_316ThreeHundredSixteen {


    /**
     * 示例 1：
     * 输入：s = "bcabc"
     * 输出："abc"
     * 示例 2：
     * 输入：s = "cbacdcbc"
     * 输出："acdb"
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new N_316ThreeHundredSixteen().removeDuplicateLetters("cbacdcbc"));
    }

    public String removeDuplicateLetters(String s) {
        boolean[] isUse = new boolean[23];
        LinkedList<Character> linkedList = new LinkedList<>();
        char[] charArray = s.toCharArray();
        int index;
        int size = -1;
        StringBuilder stringBuilder = new StringBuilder();
        for (index = 0; index < charArray.length; index++) {
            isUse[charArray[index] - 'a'] = true;
        }
        for (boolean b : isUse) {
            if (b) {
                ++size;
            }
        }
        for (index = charArray.length - 1; index >= 0 && size > -1; index--) {
            if (isUse[charArray[index] - 'a']) {
                isUse[charArray[index] - 'a'] = false;
                --size;
                linkedList.add(0, charArray[index]);
            }
        }
        if (index == -1) {
            return s;
        }
        while (index > -1) {
            if (charArray[index] <= linkedList.get(0)) {
                linkedList.remove(charArray[index]);
                linkedList.add(0, charArray[index]);
            }
            --index;
        }
        for (Character character : linkedList) {
            stringBuilder.append(character);
        }
        return stringBuilder.toString();
    }


}