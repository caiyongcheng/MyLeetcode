package letcode.utils;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/12/12 9:38
 * description 测试用例工具类
 */
public class TestCaseUtils {

    /**
     * 将字符串转化为对应的二维整形数组
     * @param inputStr 输入字符串 形式类似 "[[1,6,1],[3,10,2],[10,12,3],[11,12,2],[12,15,2],[13,18,1]]"
     * @return 对应的二维整形数组
     */
    public static int[][] get2DIntArr(String inputStr) {
        String separator = ",";
        return get2DArr(
                inputStr,
                separator,
                strArr -> Arrays.stream(strArr.split(separator)).map(String::trim).map(Integer::parseInt).mapToInt(Integer::intValue).toArray(),
                new int[0][0]
        );
    }

    /**
     * 将字符串转化为对应的二维字符串数组
     * @param inputStr 输入字符串 形式类似 "[[1,6,1],[3,10,2],[10,12,3],[11,12,2],[12,15,2],[13,18,1]]"
     * @return 对应的二维字符串数组
     */
    public static String[][] get2DStrArr(String inputStr) {
        String separator = ",";
        return get2DArr(
                inputStr,
                separator,
                strArr -> Arrays.stream(strArr.split(separator)).peek(String::trim).collect(Collectors.toList()).toArray(new String[0]),
                new String[0][0]
        );
    }

    /**
     * 将字符串转为对应的二维List
     * @param inputStr 输入字符串 类似 [[...],[...],[...]]
     * @param separator 字符串中分割数组的分隔符
     * @param mapFun 一维数组字符串到一维数组实例的转化器
     * @return 字符串对应的二维数组
     * @param <T> 数组类型[]
     */
    public static<T> List<List<T>> get2DList(String inputStr, String separator, Function<String, T> mapFun) {
        inputStr = inputStr.substring(1, inputStr.length() - 1).replaceAll("[\\[|\\]]", " ");
        return Arrays.stream(inputStr.split(" " + separator + " ")).map(mapFun).map(Arrays::asList).collect(Collectors.toList());
    }


    /**
     * 将字符串转为对应的二维数组
     * @param inputStr 输入字符串 类似 [[...],[...],[...]]
     * @param separator 字符串中分割数组的分隔符
     * @param mapFun 一维数组字符串到一维数组实例的转化器
     * @param arr 二维数组 空数组即可 用于list的toArray方法
     * @return 字符串对应的二维数组
     * @param <T> 数组类型[]
     */
    public static<T> T[] get2DArr(String inputStr, String separator, Function<String, T> mapFun, T[] arr) {
        inputStr = inputStr.substring(1, inputStr.length() - 1).replaceAll("[\\[|\\]]", " ");
        return Arrays.stream(inputStr.split(" " + separator + " ")).map(mapFun).collect(Collectors.toList()).toArray(arr);
    }

    /**
     * 将输入数组字符串转为字符串数组，保留输入中的"
     * @param inputStr 数组字符串 例如 "[\"SmallestInfiniteSet\", \"addBack\", \"popSmallest\", \"popSmallest\",
     *                \"popSmallest\", \"addBack\", \"popSmallest\", \"popSmallest\", \"popSmallest\"]"
     * @return 字符串数组 例如 ["\"SmallestInfiniteSet\""...]
     */
    public static String[] getArr(String inputStr) {
        return getArr(inputStr, ",", String::trim, new String[0]);
    }

    /**
     * 将输入数组字符串转为字符串数组，不保留输入中的"
     * @param inputStr 数组字符串 例如 "[\"SmallestInfiniteSet\", \"addBack\", \"popSmallest\", \"popSmallest\",
     *                 \"popSmallest\", \"addBack\", \"popSmallest\", \"popSmallest\", \"popSmallest\"]"
     * @return 字符串数组 例如 ["SmallestInfiniteSet"...]
     */
    public static String[] getArrIgnoreDoubleQuote(String inputStr) {
        return getArr(inputStr, ",", s -> s.trim().replaceAll("\"", ""), new String[0]);
    }

    /**
     * 将输入数组字符串转为Integer数组
     * @param inputStr 数组字符串 例如 "["1", "2", 3]"
     * @return Integer数组 [1,2,3]
     */
    public static Integer[] getIntegerArr(String inputStr) {
        return getArr(inputStr, ",", str -> Integer.parseInt(str.replaceAll("\"", "").trim()), new Integer[0]);
    }

    /**
     * 将输入数组字符串转为List
     * @param inputStr 数组字符串 例如 "["1", "2", 3]"
     * @return Integer数组 [1,2,3]
     */
    public static List<Integer> getIntegerList(String inputStr) {
        return Arrays.asList(
                getArr(
                        inputStr,
                        ",",
                        str -> Integer.parseInt(str.replaceAll("\"", "").trim()),
                        new Integer[0]
                )
        );
    }

    /**
     * 将输入数组字符串转为int数组
     * @param inputStr 数组字符串 例如 "["1", "2", 3]"
     * @return Integer数组 [1,2,3]
     */
    public static int[] getIntArr(String inputStr) {
        Integer[] integerArr = getIntegerArr(inputStr);
        int[] intArr = new int[integerArr.length];
        for (int i = 0; i < integerArr.length; i++) {
            intArr[i] = integerArr[i];
        }
        return intArr;
    }

    /**
     * 将输入字符串转化为数组
     * @param inputStr 数组字符串
     * @param separator 分割符
     * @param mapFun 映射函数
     * @param arr 目标数组 用于list.toArray 方法
     * @return 数组
     * @param <T> 目标数组类型
     */
    public static<T> T[] getArr(String inputStr, String separator, Function<String, T> mapFun, T[] arr) {
        if (inputStr.startsWith("[")) {
            inputStr = inputStr.substring(1);
        }
        if (inputStr.endsWith("]")) {
            inputStr = inputStr.substring(0, inputStr.length() - 1);
        }
        return Arrays.stream(inputStr.split(separator)).map(mapFun).collect(Collectors.toList()).toArray(arr);
    }


    /**
     * 将字符串数组转变为给定类型的参数
     * @param typeArr 参数类型
     * @param paramStrArr 字符串参数
     * @return 转化后结果
     */
    public static Object[] getParams(Class<?>[] typeArr, String[] paramStrArr) {
        if (typeArr == null || typeArr.length == 0 || paramStrArr == null || paramStrArr.length == 0) {
            return new Object[0];
        }
        if (typeArr.length != paramStrArr.length) {
            throw new IllegalArgumentException(String.format("typeArr's length[%d] not equal paramStrArr's length[%d]",
                    typeArr.length, paramStrArr.length));
        }
        Object[] params = new Object[typeArr.length];
        for (int i = 0; i < typeArr.length; i++) {
            if ("int".equals(typeArr[i].getName()) || typeArr[i] == Integer.class) {
                params[i] = Integer.parseInt(paramStrArr[i].replaceAll("\"", "").trim());
                continue;
            }
            if ("String".equals(typeArr[i].getName()) || typeArr[i] == String.class) {
                params[i] = paramStrArr[i];
                continue;
            }
            if ("long".equals(typeArr[i].getName()) || typeArr[i] == Long.class) {
                params[i] = paramStrArr[i];
                continue;
            }
            if ("boolean".equals(typeArr[i].getName()) || typeArr[i] == Boolean.class) {
                params[i] = paramStrArr[i];
            }
        }
        return params;
    }


    /**
     * 让指定的对象，执行给定的操作
     * @param obj 执行的对象
     * @param operationStr 操作列表 方法数组字符串
     * @param paramsStr 对应的参数列表 参数数组字符串
     * @return 操作结果数组字符串
     */
    public static String operation(Object obj, String operationStr, String paramsStr) {
        Method[] methodArr = obj.getClass().getDeclaredMethods();
        Map<String, Method> methodName2Method =
                Arrays.stream(methodArr).collect(Collectors.toMap(Method::getName, Function.identity()));
        String[] operationArr = getArrIgnoreDoubleQuote(operationStr);
        String[][] paramsArr = get2DStrArr(paramsStr);
        String[] ans = new String[paramsArr.length];
        try {
            for (int i = 0; i < ans.length; i++) {
                Method method = methodName2Method.get(operationArr[i]);
                // 构造函数 忽略
                if (method == null) {
                    ans[i] = null;
                    continue;
                }
                ans[i] = String.valueOf(method.invoke(obj, getParams(method.getParameterTypes(), paramsArr[i])));
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return Arrays.toString(ans);
    }

    public static String getStringFromFile(String fileName) {
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            StringBuilder str = new StringBuilder();
            String lineStr;
            while (true) {
                lineStr = bufferedReader.readLine();
                if (lineStr == null || lineStr.length() == 0) {
                    break;
                }
                str.append(lineStr);
            }
            return str.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }









}
