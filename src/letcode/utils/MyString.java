package letcode.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Leetcode
 * MyString
 *
 * @author : CaiYongcheng
 * @date : 2020-07-15 11:09
 **/
public class MyString {

    private static Integer[] string2Int(String[] strs){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i=0; i<strs.length; ++i){
            if (Pattern.matches("^[\\-|\\+]?[0-9]+$", strs[i])){
                list.add(Integer.valueOf(strs[i]));
            }
        }
        Integer[] integers = new Integer[list.size()];
        list.toArray(integers);
        return integers;
    }

    public static Integer[] compareString(String str){
        String[] strings = str.split("[^0-9\\-\\+]+");
        //System.out.println(Arrays.toString(strings));
        Integer[] integers = string2Int(strings);
        Arrays.sort(integers);
        return integers;
    }

    public static Integer countWords(String str){
        str = str.toLowerCase();
        str = str.trim();
        if (null == str || str.length() < 1){
            return 0;
        }
        String[] strings = str.split("[^0-9a-z]+");
        if (null == strings || strings.length == 0){
            return 0;
        }
        return strings.length;
    }

    public static void main(String[] args) {
    }

}
