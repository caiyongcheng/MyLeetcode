package letcode.arithmetic;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 字符串的一些算法
 *
 * @author CaiYongcheng
 * @date 2021-04-20 11:18
 **/
public class StringArithmetic {


    private static String EMPTY_STRING = "";

    public static int searchCheck(String text, String target) {
        if (null == text || null == target) {
            throw new NullPointerException("param is null");
        }
        if (text.length() < target.length()) {
            return -1;
        }
        if (EMPTY_STRING.equals(target)) {
            return 0;
        }
        return 1;
    }


    /**
     * 使用 kmp 在 文本字符串中 搜索 目标字符串
     * @param text 文本字符串
     * @param target 目标字符串
     * @return 目标字符串在文本字符串中的第一个出现位置
     */
    public static int searchByKmp(String text, String target) {
        /**
         * kmp：
         * 不匹配的时候，需要移动目标字符串。原始的搜索是向右移动一位。
         * 而kmp则是跳到next[j]位置的。即j->next[j]
         * 假设
         * text[i] != target[j] =》 text[i-j,i-1] = target[0, j-1]
         * next[j] = t 表示 target[0, t-1] = target[j-t ,j-1]
         * 如果j->next[j], 由text[i-j,i-1] = target[0, j-1]， target[0, t-1] = target[j-t ,j-1]
         * 可以得出 text[i-t,i-1] = target[j-t, j-1] = target[0, t-1]
         * 也就是直接跳过了t位的比较
         *
         * 所以算法分为两部分
         * 1 求出next数组
         * 2 用next数组求解
         *
         * 用next数组求解
         * 如果 text[i] == target[j] 那么 i++, j++
         * 否则 j = target[j]
         *
         *
         * 求next数组：next[j]表示 target[0,j-1]中，最大的前缀与后缀的相同字符串长度
         * 假设原始的 next[j]表示 target[0,j]中，最大的前缀与后缀的相同字符串长度
         * 那么最终的next[j+1]等于原始的next[j],也就是将原始next数组向右移动既是最终的next数组
         * 假设已知原始的next[j-1],求原始的next[j]
         * 假设next[j-1] = t，
         * 如果target[t] = target[j], 那么next[j] = t
         * 否则问题转化为用next数组求解。
         */
        int check = searchCheck(text, target);
        if (check < 1) {
            return check;
        }
        char[] textArr = text.toCharArray();
        char[] targetArr = target.toCharArray();
        int[] next = new int[targetArr.length];
        next[0] = -1;
        int start = -1;
        int end = 0;
        int length = targetArr.length - 1;
        while (end < length) {
            if (start == -1 || targetArr[start] == targetArr[end]) {
                ++end;
                ++start;
                next[end] = start;
            } else {
                start = next[start];
            }
        }
        int indexi = 0;
        int indexj = 0;
        while (indexi < textArr.length && indexj < targetArr.length) {
            if (indexj == -1 || textArr[indexi] == targetArr[indexj]) {
                ++indexj;
                ++indexi;
            } else {
                indexj = next[indexj];
            }
        }
        return indexj >= targetArr.length ? indexi - targetArr.length : -1;
    }


    /**
     * 使用 Sunday 在 文本字符串中 搜索 目标字符串
     * @param text 文本字符串
     * @param target 目标字符串
     * @return 目标字符串在文本字符串中的第一个出现位置
     */
    public static int searchBySunday(String text, String target) {
        int check = searchCheck(text, target);
        if (check < 1) {
            return check;
        }
        char[] textArr = text.toCharArray();
        char[] targetArr = target.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = targetArr.length - 1; i >= 0; i--) {
            if (!map.containsKey(targetArr[i])) {
                map.put(targetArr[i], i);
            }
        }
        int indexi = 0;
        int indexj = 0;
        int backLength;
        while (indexi < textArr.length && indexj < targetArr.length) {
            if (textArr[indexi] == targetArr[indexj]) {
                ++indexi;
                ++indexj;
            } else {
                indexi += targetArr.length - indexj;
                if (indexi >= textArr.length) {
                    return -1;
                }
                indexj = 0;
                backLength = map.getOrDefault(textArr[indexi], -1);
                if (backLength != -1) {
                    indexi = indexi - backLength;
                } else {
                    indexi -= targetArr.length - indexj - 1;
                }
            }
        }
        return indexj >= targetArr.length ? indexi - targetArr.length : -1;
    }


    public static void main(String[] args) {
        for (int j = 0; j < 30000; j++) {
            int length = (int) (Math.random() * 10000);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < length; i++) {
                char ch = (char) (Math.random() * 26 + 'a');
                stringBuilder.append(ch);
            }
            String string = stringBuilder.toString();
            int start = (int) (length * Math.random());
            int end = (int) (start + (length - start) * Math.random());
            String substring = string.substring(start, end);
            int index = string.indexOf(substring);
            if (index != searchByKmp(string, substring)) {
                System.out.println("no:" + j + ":searchByKmp is error");
            }
            if (index != searchBySunday(string, substring)) {
                System.out.println("no:" + j + ":searchBySunday is error");
            }
        }
    }






}
