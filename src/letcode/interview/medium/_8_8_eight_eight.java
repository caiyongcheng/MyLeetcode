package letcode.interview.medium;

import letcode.utils.FormatPrintUtils;

import java.util.HashSet;

/**
 * 有重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合。
 *
 * @author CaiYongcheng
 * @date 2021-09-01 09:17
 **/
public class _8_8_eight_eight {

    public String[] permutation(String S) {
        /*
         * 数量不大 穷举保存到set即可
         */
        int len = S.length();
        for (int i = 2; i < S.length(); i++) {
            len *= i;
        }
        HashSet<String> set = new HashSet<>(len);
        search(new StringBuilder(), S.toCharArray(), 0, set);
        return set.toArray(new String[0]);
    }

    public void search(StringBuilder str, char[] chs, int visitable, HashSet<String> set) {
        if (visitable + 1 == 2 << chs.length - 1) {
            set.add(str.toString());
            return ;
        }
        for (int index = 0; index < chs.length; index++) {
            if ((visitable & 1 << index) == 0) {
                str.append(chs[index]);
                search(str, chs, visitable | 1 << index, set);
                str.deleteCharAt(str.length()-1);
            }
        }
    }


    /**
     * 示例1:
     *  输入：S = "qqe"
     *  输出：["eqq","qeq","qqe"]
     *
     * 示例2:
     *  输入：S = "ab"
     *  输出：["ab", "ba"]
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(FormatPrintUtils.formatArray(new _8_8_eight_eight().permutation(
                "ab"
        )));
    }


}
