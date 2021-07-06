package letcode.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: MyLeetcode
 * @description: 类型转化工具
 * @packagename: letcode.utils
 * @author: 6JSh5rC456iL
 * @date: 2021-04-14 10:37
 **/
public class CastUtils {


    /**
     * 二维数组转list
     * @param arr 二维数组
     * @param <T> 数组类型
     * @return 对应类型的二维list
     */
    public static<T> List<List<T>> array2List(T[][] arr) {
        ArrayList<List<T>> lists = new ArrayList<>(arr.length);
        for (T[] ts : arr) {
            ArrayList<T> ts1 = new ArrayList<>(ts.length);
            ts1.addAll(Arrays.asList(ts));
            lists.add(ts1);
        }
        return lists;
    }






}
