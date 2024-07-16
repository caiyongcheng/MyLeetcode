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

package letcode.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Leetcode
 * MyString
 *
 * @author : CaiYongcheng
 * @since : 2020-07-15 11:09
 **/
public class FormatUtils {

    private static final String NULL_STRING = "!null object!";


    /**
     * list对象的格式化toString
     *
     * @param list      需要获取的list对象
     * @param prefix    格式化字符串前缀
     * @param suffix    格式化字符串后缀
     * @param separator 格式化字符串分隔符
     * @param <T>       list类型
     * @return prefix list.get(0).toString() separator list.get(1).toString() suffix
     */
    public static <T> String formatList(List<T> list, String prefix, String suffix, String separator) {
        if (list == null || list.isEmpty()) {
            return prefix + suffix;
        }
        final StringBuilder formatStr = new StringBuilder(prefix);
        for (int i = 0; i < list.size() - 1; i++) {
            formatStr.append(list.get(i) == null ? NULL_STRING : list.get(i).toString()).append(separator);
        }
        formatStr.append(list.get(list.size() - 1) == null ? NULL_STRING : list.get(list.size() - 1).toString());
        return formatStr.append(suffix).toString();
    }

    /**
     * list对象的格式化toString
     *
     * @param list      需要获取的list对象
     * @param prefix    格式化字符串前缀
     * @param suffix    格式化字符串后缀
     * @param separator 格式化字符串分隔符
     * @param <T>       list类型
     * @return prefix list.get(0).toString() separator list.get(1).toString() suffix
     */
    public static <T> String formatNestList(List<? extends List<T>> list, String prefix, String suffix, String separator) {
        if (list == null || list.isEmpty()) {
            return prefix + suffix;
        }
        final StringBuilder formatStr = new StringBuilder(prefix);
        for (int i = 0; i < list.size() - 1; i++) {
            formatStr.append(list.get(i) == null ? NULL_STRING : formatList(list.get(i))).append(separator);
        }
        formatStr.append(list.get(list.size() - 1) == null ? NULL_STRING : formatList(list.get(list.size() - 1)));
        return formatStr.append(suffix).toString();
    }

    /**
     * list对象的格式化toString 使用[与]作为前后缀，,作为分隔符
     *
     * @param list 需要格式化输出对象
     * @param <T> list类型
     * @return [list.get(0).toString(), list.get(1).toString()...]
     */
    public static <T> String formatList(List<T> list) {
        return formatList(list, "[", "]", ",");
    }


    /**
     * list对象的格式化toString
     *
     * @param list 需要获取的list对象
     * @param <T>  list类型
     * @return prefix list.get(0).toString() separator list.get(1).toString() suffix
     */
    public static <T> String formatNestList(List<? extends List<T>> list) {
        return formatNestList(list, "[", "]", ",");
    }

    /**
     * array
     *
     * @param array     需要获取的array对象
     * @param prefix    格式化字符串前缀
     * @param suffix    格式化字符串后缀
     * @param separator 格式化字符串分隔符
     * @param <T>       array类型
     * @return prefix array[0].toString() separator array[1].toString() suffix
     */
    public static <T> String formatArray(T[] array, String prefix, String suffix, String separator) {
        if (array == null || array.length == 0) {
            return prefix + suffix;
        }
        final StringBuilder formatStr = new StringBuilder(prefix);
        for (int i = 0; i < array.length - 1; i++) {
            formatStr.append(array[i] == null ? NULL_STRING : array[i].toString()).append(separator);
        }
        formatStr.append(array[array.length - 1] == null ? NULL_STRING : array[array.length - 1].toString());
        return formatStr.append(suffix).toString();
    }

    /**
     * array 使用[与]作为前后缀，,作为分隔符
     *
     * @param array 需要获取的array对象
     * @param <T>   array类型
     * @return [array[0].toString(), array[1].toString()...]
     */
    public static <T> String formatArray(T[] array) {
        return formatArray(array, "[", "]", ",");
    }

    /**
     * array
     *
     * @param array     需要获取的array对象
     * @param prefix    格式化字符串前缀
     * @param suffix    格式化字符串后缀
     * @param separator 格式化字符串分隔符
     * @return prefix array[0].toString() separator array[1].toString() suffix
     */
    public static String formatArray(int[] array, String prefix, String suffix, String separator) {
        final StringBuilder formatStr = new StringBuilder(prefix);
        for (int i = 0; i < array.length - 1; i++) {
            formatStr.append(array[i]).append(separator);
        }
        if (array.length > 0) {
            formatStr.append(array[array.length - 1]);
        }
        return formatStr.append(suffix).toString();
    }

    /**
     * array 使用[与]作为前后缀，,作为分隔符
     *
     * @param array 需要获取的array对象
     * @return [array[0].toString(), array[1].toString()...]
     */
    public static String formatArray(int[] array) {
        return formatArray(array, "[", "]", ",");
    }

    /**
     * array 使用[与]作为前后缀，,作为分隔符
     *
     * @param array 需要获取的array对象
     * @return [array[0].toString(), array[1].toString()...]
     */
    public static String format2DArray(int[][] array, String prefix, String suffix, String separator) {
        return prefix
                + Arrays.stream(array).map(FormatUtils::formatArray).collect(Collectors.joining(separator))
                + suffix;
    }

    /**
     * array 使用[与]作为前后缀，,作为分隔符
     *
     * @param array 需要获取的array对象
     * @return [array[0].toString(), array[1].toString()...]
     */
    public static String format2DArray(int[][] array) {
        return format2DArray(array, "[", "]", ",");
    }


    /**
     * node-list
     *
     * @param node      头节点
     * @param prefix    格式化字符串前缀
     * @param suffix    格式化字符串后缀
     * @param separator 格式化字符串分隔符
     * @return prefix array[0].toString() separator array[1].toString() suffix
     */
    public static String formatNodeLists(ListNode node, String prefix, String suffix, String separator) {
        final StringBuilder formatStr = new StringBuilder(prefix);
        while (node.next != null) {
            formatStr.append(node.val).append(separator);
        }
        formatStr.delete(formatStr.length() - separator.length(), formatStr.length());
        return formatStr.append(suffix).toString();
    }


    /**
     * node-list
     * node-list 使用[与]作为前后缀，,作为分隔符
     *
     * @param node 头节点
     * @return [array[0].toString(), array[1].toString()...]
     */
    public static String formatNodeLists(ListNode node) {
        return formatNodeLists(node, "[", "]", ",");
    }


    @SuppressWarnings("unchecked")
    public static<T> String formatObj(Object execRst) {
        if (Objects.isNull(execRst)) {
            return "null";
        }
        if (execRst instanceof List) {
            return "[" + ((List<?>) execRst).stream().map(FormatUtils::formatObj).collect(Collectors.joining(",")) + "]";
        }
        if (execRst instanceof int[]) {
            return formatArray((int[]) execRst);
        }
        if (execRst.getClass().isArray()) {
            return "[" + Arrays.stream((T[]) execRst).map(FormatUtils::formatObj).collect(Collectors.joining(",")) + "]";
        }
        return execRst.toString();
    }


}
