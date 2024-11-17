package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given a positive integer n.  A binary string x is valid if all
 * substrings  of x of length 2 contain at least one "1".
 * Return all valid strings with length n, in any order.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-10-30 09:36
 */
public class _3211 {

    static List<String>[] len2StrList;

    static {
        len2StrList = new ArrayList[11];

        len2StrList[0] = new ArrayList<>(0);

        len2StrList[1] = new ArrayList<>(0);
        len2StrList[1].add("0");
        len2StrList[1].add("1");

        len2StrList[2] = new ArrayList<>();
        len2StrList[2].add("01");
        len2StrList[2].add("10");
        len2StrList[2].add("11");

        for (int i = 3; i < len2StrList.length; i++) {
            len2StrList[i] = new ArrayList<>();
            for (String s : len2StrList[i - 1]) {
                len2StrList[i].add("1" + s);
                if (s.charAt(0) == '1') {
                    len2StrList[i].add("0" + s);
                }
            }
        }
    }

    public List<String> validStrings(int n) {
        return len2StrList[n];
    }


    /**
     * Example 1:
     *
     * Input: n = 3
     *
     * Output: ["010","011","101","110","111"]
     *
     * Explanation:
     *
     * The valid strings of length 3 are: "010", "011", "101", "110", and "111".
     *
     * Example 2:
     *
     * Input: n = 1
     *
     * Output: ["0","1"]
     *
     * Explanation:
     *
     * The valid strings of length 1 are: "0" and "1".
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test(_3211.class);
    }

}
