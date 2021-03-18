package letcode.normal.medium;

import java.util.TreeMap;

/**
 * @program: MyLeetcode
 * @description: 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。  若可行，输出任意可行的结果。若不可行，返回空字符串。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @date: 2021-03-18 09:09
 **/
public class _767SevenHundredSixtySeven {

    public String reorganizeString(String S) {
        char[] chars = S.toCharArray();
        char[] ans = new char[S.length()];
        int[] ints = new int[26];
        for (char aChar : chars) {
            ints[aChar-'a']++;
        }
        int l = (S.length() + 1) / 2;
        int ai;
        int index = 0;
        for (int i = 0; i < ints.length; i++) {
            if (ints[i] > l) {
                return "";
            }
            if (ints[i] == l && (ans.length & 1) == 1) {
                for (int j=0; j<ans.length; j+=2) {
                    ans[j] = (char) ('a' + i);
                }
                ints[i] = 0;
                break;
            }
        }
        if (ans[0] != 0) {
            index = 1;
            for (ai = 0; ai < ints.length; ai++) {
                while (ints[ai] > 0) {
                    if (index < ans.length) {
                        ans[index] = (char) ('a' + ai);
                        index += 2;
                    } else {
                        break;
                    }
                    --ints[ai];
                }
            }
            return String.valueOf(ans);
        }
        for (ai = 0; ai < ints.length; ai++) {
            while (ints[ai] > 0) {
                if (index < ans.length) {
                    ans[index] = (char) ('a' + ai);
                    index += 2;
                } else {
                    break;
                }
                --ints[ai];
            }
            if (index >= ans.length) {
                break;
            }
        }
        index = 1;
        for (; ai < ints.length; ai++) {
            while (ints[ai] > 0) {
                if (index < ans.length) {
                    ans[index] = (char) ('a' + ai);
                    index += 2;
                } else {
                    break;
                }
                --ints[ai];
            }
        }
        return String.valueOf(ans);
    }

    /**
     * 示例 1:
     * 输入: S = "aab"
     * 输出: "aba"
     * 示例 2:
     * 输入: S = "aaab"
     * 输出: ""
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _767SevenHundredSixtySeven().reorganizeString("aaab"));
    }

}
