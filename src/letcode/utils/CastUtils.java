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


    public static<T> List<List<T>> array2List(T[][] arr) {
        ArrayList<List<T>> lists = new ArrayList<>(arr.length);
        for (T[] ts : arr) {
            ArrayList<T> ts1 = new ArrayList<>(ts.length);
            ts1.addAll(Arrays.asList(ts));
            lists.add(ts1);
        }
        return lists;
    }


    public static void main(String[] args) {
        try {
            BigDecimal bigDecimal = new BigDecimal("-0.00");
            bigDecimal.subtract(new BigDecimal("1"));
            System.out.println(bigDecimal);
            FileReader fileReader = new FileReader(new File("C:\\Users\\10761\\Desktop\\chis3-medicalins.log"));
            char[] lineData = new char[1024];
            boolean isNum = false;
            int total = 0;
            int count = 0;
            int singleNum = 0;
            while (fileReader.read(lineData) > 0) {
                String lineStr = new String(lineData);
                int index = lineStr.lastIndexOf("门诊获取报销信息医保请求耗时info=");
                if (index > -1) {
                    index += 19;
                    while (lineData[index] >= '0' && lineData[index] <= '9') {
                        singleNum *= 10;
                        singleNum += lineData[index] -'0';
                        ++index;
                    }
                    ++count;
                    total+=singleNum;
                    singleNum = 0;
                }
            }
            System.out.println(total);
            System.out.println(total*1.0/count);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
