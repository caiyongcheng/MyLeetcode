package letcode.utils;



import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("C:\\Users\\10761\\Desktop\\1.txt")));
        Scanner scanner = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();
        String lineStr ;
        int index = 0;
        while (true) {
            scanner.next();
            for (int i = 0; i < 1000; i++) {
                lineStr = bufferedReader.readLine();
                if (lineStr != null && lineStr.length() > 0) {
                    stringBuilder.append(lineStr);
                }
            }
            ++index;
            System.out.println(stringBuilder);
            System.out.println("=================" + index + "===========");
            stringBuilder.delete(0, stringBuilder.length());
        }
    }



}
