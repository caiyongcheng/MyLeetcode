package letcode.utils;

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
