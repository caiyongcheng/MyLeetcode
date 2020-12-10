package letcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Leetcode
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 2 abc
 * 3 def
 * 4 ghi
 * 5 jkl
 * 6 mno
 * 7 pqrs
 * 8 tuv
 * 9 wxyz
 * @author : CaiYongcheng
 * @date : 2020-06-27 14:42
 **/
public class _17Seventeen {

    static char[][] mapper =  {
        {},
        {},
        {'a','b','c'},
        {'d','e','f'},
        {'g','h','i'},
        {'j','k','l'},
        {'m','n','o'},
        {'p','q','r','s'},
        {'t','u','v'},
        {'w','x','y','z'}
    };

    static char[] dig;
    static StringBuilder stringBuilder = new StringBuilder();
    static List<String> list = new ArrayList<String>();

    public static List<String> letterCombinations(int n) {
        if(n == dig.length){
            list.add(stringBuilder.toString());
            return null;
        }
        int index = dig[n] - '0';
        for(int i=0; i<mapper[index].length; ++i){
            stringBuilder.append(mapper[index][i]);
            letterCombinations(n+1);
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }
        return list;
    }

    /**
     * 输入："23"
     * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     * @param digits
     * @return
     */
    public static List<String> letterCombinations(String digits) {
        dig = digits.toCharArray();
        if(digits==null || digits.length() == 0){
            list.add("");
            return list;
        }
        return letterCombinations(0);
    }

    public static void main(String[] args) {
        List<String> strings = letterCombinations("23");
        for (String string : strings) {
            System.out.println(string);
        }
    }
}
