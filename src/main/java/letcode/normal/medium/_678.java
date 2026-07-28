package letcode.normal.medium;


import java.util.LinkedList;
import java.util.Stack;

/**
 * 给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。
 * 有效字符串具有如下规则：
 * 任何左括号 (必须有相应的右括号 )。
 * 任何右括号 )必须有相应的左括号 (。
 * 左括号 ( 必须在对应的右括号之前 )。
 * *可以被视为单个右括号 )，或单个左括号 (，或一个空字符串。
 * 一个空字符串也被视为有效字符串。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/valid-parenthesis-string 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-09-12 20:56
 **/
public class _678 {

    public boolean checkValidString2(String s) {
        int minCount = 0, maxCount = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                minCount++;
                maxCount++;
            } else if (c == ')') {
                minCount = Math.max(minCount - 1, 0);
                maxCount--;
                if (maxCount < 0) {
                    return false;
                }
            } else {
                minCount = Math.max(minCount - 1, 0);
                maxCount++;
            }
        }
        return minCount == 0;
    }



    public boolean checkValidString1(String s) {
        /*
        少有的使用LinkedList可能更好的情景
         */
        int length = s.length();
        LinkedList<Integer> leftIdxList = new LinkedList<>();
        LinkedList<Integer> starIdxList = new LinkedList<>();
        char ch;

        for (int i = 0; i < length; i++) {
            ch = s.charAt(i);
            if (ch == '(') {
                leftIdxList.addLast(i);
            } else if (ch == ')') {
                if (leftIdxList.isEmpty()) {
                    if (starIdxList.isEmpty()) {
                        return false;
                    } else {
                        starIdxList.removeLast();
                    }
                } else {
                    leftIdxList.removeLast();
                }
            } else {
                starIdxList.addLast(i);
            }
        }

        while (!leftIdxList.isEmpty() && !starIdxList.isEmpty()) {
            if (leftIdxList.removeLast() > starIdxList.removeLast()) {
                return false;
            }
        }
        return leftIdxList.isEmpty();

    }

    public boolean checkValidString(String s) {
        //1 用栈操作 * 和 （ 入栈， 遇到）先出（ 当没有（的时候出*
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char aChar : chars) {
            if (aChar != ')') {
                stack.push(aChar);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                int index = stack.size() - 1;
                for (; index > -1; index--) {
                    if (stack.get(index) == '(') {
                        stack.remove(index);
                        break;
                    }
                }
                if (index < 0) {
                    stack.pop();
                }
            }
        }
        // 栈内 只剩下 ( 和 *
        Character[] characters = stack.toArray(new Character[]{});
        int leftCount = 0;
        for (Character character : characters) {
            if (character == '(') {
                ++leftCount;
            } else if (leftCount > 0) {
                --leftCount;
            }
        }
        return leftCount == 0;
    }

}
