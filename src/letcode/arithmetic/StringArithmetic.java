/*
 * 版权所有（c）<2021><蔡永程>
 *
 * 反996许可证版本1.0
 *
 * 在符合下列条件的情况下，特此免费向任何得到本授权作品的副本（包括源代码、文件和/或相关内容，以
 * 下统称为“授权作品”）的个人和法人实体授权：被授权个人或法人实体有权以任何目的处置授权作品，包括
 * 但不限于使用、复制，修改，衍生利用、散布，发布和再许可：
 *
 * 1. 个人或法人实体必须在许可作品的每个再散布或衍生副本上包含以上版权声明和本许可证，不得自行修
 * 改。
 * 2. 个人或法人实体必须严格遵守与个人实际所在地或个人出生地或归化地、或法人实体注册地或经营地（
 * 以较严格者为准）的司法管辖区所有适用的与劳动和就业相关法律、法规、规则和标准。如果该司法管辖区
 * 没有此类法律、法规、规章和标准或其法律、法规、规章和标准不可执行，则个人或法人实体必须遵守国际
 * 劳工标准的核心公约。
 * 3. 个人或法人不得以任何方式诱导、暗示或强迫其全职或兼职员工或其独立承包人以口头或书面形式同意
 * 直接或间接限制、削弱或放弃其所拥有的，受相关与劳动和就业有关的法律、法规、规则和标准保护的权利
 * 或补救措施，无论该等书面或口头协议是否被该司法管辖区的法律所承认，该等个人或法人实体也不得以任
 * 何方法限制其雇员或独立承包人向版权持有人或监督许可证合规情况的有关当局报告或投诉上述违反许可证
 * 的行为的权利。
 *
 * 该授权作品是"按原样"提供，不做任何明示或暗示的保证，包括但不限于对适销性、特定用途适用性和非侵
 * 权性的保证。在任何情况下，无论是在合同诉讼、侵权诉讼或其他诉讼中，版权持有人均不承担因本软件或
 * 本软件的使用或其他交易而产生、引起或与之相关的任何索赔、损害或其他责任。
 */

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
        int avgJDK = 0;
        int avgSunday = 0;
        int avgKmp = 0;
        int indexJDK;
        int indexSunday;
        int indexKmp;
        long startTime;
        for (int j = 0; j < 300; j++) {
            int length = (int) (Math.random() * 1000000);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < length; i++) {
                char ch = (char) (Math.random() * 26 + 'a');
                stringBuilder.append(ch);
            }
            String string = stringBuilder.toString();
            int start = (int) (length * Math.random());
            int end = (int) (start + (length - start) * Math.random());
            String substring = string.substring(start, end);

            startTime = System.nanoTime();
            indexJDK = string.indexOf(substring);
            avgJDK += System.nanoTime() - startTime;

            startTime = System.nanoTime();
            indexKmp = searchByKmp(string, substring);
            avgKmp += System.nanoTime() - startTime;

            startTime = System.nanoTime();
            indexSunday = searchBySunday(string, substring);
            avgSunday += System.nanoTime() - startTime;


            if (indexJDK != indexKmp) {
                System.out.println("no:" + j + ":searchByKmp is error");
            }
            if (indexJDK != indexSunday) {
                System.out.println("no:" + j + ":searchBySunday is error");
            }
        }
        System.out.println(avgJDK/300.0);
        System.out.println(avgKmp/300.0);
        System.out.println(avgSunday/300.0);
    }






}
