package letcode.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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
                strArr -> Arrays.stream(strArr.split(separator)).map(String::trim).collect(Collectors.toList()).toArray(new String[0]),
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
        return getArr(inputStr, ",", s -> s.replaceAll("\"", "").trim(), new String[0]);
    }

    /**
     * 将输入数组字符串转为字符串List，保留输入中的"
     * @param inputStr 数组字符串 例如 "[\"SmallestInfiniteSet\", \"addBack\", \"popSmallest\", \"popSmallest\",
     *                \"popSmallest\", \"addBack\", \"popSmallest\", \"popSmallest\", \"popSmallest\"]"
     * @return 字符串数组 例如 ["\"SmallestInfiniteSet\""...]
     */
    public static List<String> getList(String inputStr) {
        return Arrays.stream(getArr(inputStr, ",", s -> s.replaceAll("\"", "").trim(), new String[0])).collect(Collectors.toList());
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
        return getArr(
                inputStr.trim(),
                ",",
                str -> {
                    if ("NULL".equalsIgnoreCase(str)) {
                        return null;
                    }
                    return Integer.parseInt(str.replaceAll("\"", "").trim());
                },
                new Integer[0]
        );
    }

    /**
     * 将输入数组字符串转为Character数组
     * @param inputStr 数组字符串 例如 "["1", "2", 3]"
     * @return Integer数组 ['1','2','3']
     */
    public static Character[] getCharacterArr(String inputStr) {
        return getArr(
                inputStr.trim(),
                ",",
                str -> {
                    if ("NULL".equalsIgnoreCase(str)) {
                        return null;
                    }
                    return str.replaceAll("\"", "").trim().charAt(0);
                },
                new Character[0]
        );
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
     * 将输入数组字符串转为int数组
     * @param inputStr 数组字符串 例如 "["1", "2", 3]"
     * @return Integer数组 [1,2,3]
     */
    public static char[] getCharArr(String inputStr) {
        Character[] characterArr = getCharacterArr(inputStr);
        char[] charArr = new char[characterArr.length];
        for (int i = 0; i < characterArr.length; i++) {
            charArr[i] = characterArr[i];
        }
        return charArr;
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
        if (inputStr.trim().isEmpty()) {
            return arr;
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
            throw new IllegalArgumentException(String.format("type array's length[%d] not equal paramStr array's length[%d]",
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
     * @param debug 是否打印调试语句
     * @return 操作结果数组字符串
     */
    public static String operation(Object obj, String operationStr, String paramsStr, boolean debug) {
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
                Object[] params = getParams(method.getParameterTypes(), paramsArr[i]);
                ans[i] = String.valueOf(method.invoke(obj, params));
                if (debug) {
                    System.out.printf(
                            "method: %s, params: %s, call result: %s. \n",
                            method.getName(), Arrays.toString(params), ans[i]
                    );
                }
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            System.err.printf("operation method has error: %s \n", e.getMessage());
        }
        return Arrays.toString(ans);
    }

    /**
     * 让指定的对象，执行给定的操作
     * @param obj 执行的对象
     * @param operationStr 操作列表 方法数组字符串
     * @param paramsStr 对应的参数列表 参数数组字符串
     * @return 操作结果数组字符串
     */
    public static String operation(Object obj, String operationStr, String paramsStr) {
        return operation(obj, operationStr, paramsStr, false);
    }

    public static String getStringFromFile(String fileName) {
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            StringBuilder str = new StringBuilder();
            String lineStr;
            while (true) {
                lineStr = bufferedReader.readLine();
                if (lineStr == null || lineStr.isEmpty()) {
                    break;
                }
                str.append(lineStr);
            }
            return str.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static int[] createRandomIntArr(int arrLength, int floor, int ceil) {
        int[] randomArr = new int[arrLength];
        int dist = ceil - floor;
        try {
            for (int i = 0; i < randomArr.length; i++) {
                randomArr[i] = (int) (SecureRandom.getInstanceStrong().nextDouble() * dist) + floor;
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return randomArr;
    }

    public static int[] createRandomIntArr(int arrLength) {
        int[] randomArr = new int[arrLength];
        try {
            for (int i = 0; i < randomArr.length; i++) {
                randomArr[i] = SecureRandom.getInstanceStrong().nextInt(Integer.MAX_VALUE);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return randomArr;
    }







}
