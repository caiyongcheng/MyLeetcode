package letcode.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/12/12 9:38
 * description 测试用例输入的转化工具
 */
public class TestCaseInputUtils {

    public static int[][] get2DArr(String inputStr) {
        return get2DArr(inputStr, ",");
    }


    public static int[][] get2DArr(String inputStr, String separator) {
        inputStr = inputStr.substring(1, inputStr.length() - 1).replaceAll("[\\[|\\]]", " ");
        String[] split = inputStr.split(" " + separator + " ");
        int[][] twoDArr = new int[split.length][];
        for (int i = 0; i < twoDArr.length; i++) {
            String[] numArr = split[i].split(separator);
            twoDArr[i] = new int[numArr.length];
            for (int j = 0; j < twoDArr[i].length; j++) {
                twoDArr[i][j] = Integer.parseInt(numArr[j].trim());
            }
        }
        return twoDArr;
    }





}
