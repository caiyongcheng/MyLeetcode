package datastructure.utils;

import letcode.utils.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Leetcode
 * MyString
 *
 * @author : CaiYongcheng
 * @date : 2020-07-15 11:09
 **/
public class FormatPrintUtils {

    private static Integer[] string2Int(String[] strArr) {
        ArrayList<Integer> list = new ArrayList<>();
        for (String str : strArr) {
            if (Pattern.matches("^[\\-|+]?[0-9]+$", str)) {
                list.add(Integer.valueOf(str));
            }
        }
        Integer[] integers = new Integer[list.size()];
        list.toArray(integers);
        return integers;
    }

    public static Integer[] compareString(String str) {
        String[] strings = str.split("[^0-9\\-+]+");
        Integer[] integers = string2Int(strings);
        Arrays.sort(integers);
        return integers;
    }

    /**
     * list对象的格式化toString
     * @param list 需要获取的list对象
     * @param prefix 格式化字符串前缀
     * @param suffix 格式化字符串后缀
     * @param separator 格式化字符串分隔符
     * @param <T> list类型
     * @return prefix list.get(0).toString() separator list.get(1).toString() suffix
     */
    public static<T> String formatList(List<T> list, String prefix, String suffix, String separator) {
        final StringBuilder formatStr = new StringBuilder(prefix);
        for (int i = 0; i < list.size()-1; i++) {
            formatStr.append(list.get(i).toString()).append(separator);
        }
        formatStr.append(list.get(list.size()-1).toString());
        return formatStr.append(suffix).toString();
    }

    /**
     * list对象的格式化toString 使用[与]作为前后缀，,作为分隔符
     * @param list
     * @param <T>
     * @return [list.get(0).toString(),list.get(1).toString()...]
     */
    public static<T> String formatList(List<T> list) {
        return formatList(list, "[", "]", ",");

    }

    /**
     * array
     * @param array 需要获取的array对象
     * @param prefix 格式化字符串前缀
     * @param suffix 格式化字符串后缀
     * @param separator 格式化字符串分隔符
     * @param <T> array类型
     * @return prefix array[0].toString() separator array[1].toString() suffix
     */
    public static<T> String formatArray(T[] array, String prefix, String suffix, String separator) {
        final StringBuilder formatStr = new StringBuilder(prefix);
        for (int i = 0; i < array.length-1; i++) {
            formatStr.append(array[i].toString()).append(separator);
        }
        formatStr.append(array[array.length-1].toString());
        return formatStr.append(suffix).toString();
    }

    /**
     * array 使用[与]作为前后缀，,作为分隔符
     * @param array 需要获取的array对象
     * @param <T> array类型
     * @return [array[0].toString(),array[1].toString()...]
     */
    public static<T> String formatArray(T[] array) {
        return formatArray(array, "[", "]", ",");
    }

    /**
     * array
     * @param array 需要获取的array对象
     * @param prefix 格式化字符串前缀
     * @param suffix 格式化字符串后缀
     * @param separator 格式化字符串分隔符
     * @return prefix array[0].toString() separator array[1].toString() suffix
     */
    public static String formatArray(int[] array, String prefix, String suffix, String separator) {
        final StringBuilder formatStr = new StringBuilder(prefix);
        for (int i = 0; i < array.length-1; i++) {
            formatStr.append(array[i]).append(separator);
        }
        formatStr.append(array[array.length-1]);
        return formatStr.append(suffix).toString();
    }

    /**
     * array 使用[与]作为前后缀，,作为分隔符
     * @param array 需要获取的array对象
     * @return [array[0].toString(),array[1].toString()...]
     */
    public static String formatArray(int[] array ) {
        return formatArray(array, "[", "]", ",");
    }


    /**
     * node-list
     * @param node 头节点
     * @param prefix 格式化字符串前缀
     * @param suffix 格式化字符串后缀
     * @param separator 格式化字符串分隔符
     * @return prefix array[0].toString() separator array[1].toString() suffix
     */
    public static String formatNodeLists(ListNode node, String prefix, String suffix, String separator) {
        final StringBuilder formatStr = new StringBuilder(prefix);
        while (node.next != null) {
            formatStr.append(node.val).append(separator);
        }
        formatStr.delete(formatStr.length()-separator.length(), formatStr.length());
        return formatStr.append(suffix).toString();
    }


    /**
     * node-list
     * node-list 使用[与]作为前后缀，,作为分隔符
     * @param node 头节点
     * @return [array[0].toString(),array[1].toString()...]
     */
    public static String formatNodeLists(ListNode node) {
        return formatNodeLists(node, "[", "]", ",");
    }



}
