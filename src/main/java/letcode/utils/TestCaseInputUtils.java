package letcode.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/12/12 9:38
 * description 测试用例输入工具类
 */
public class TestCaseInputUtils {

    public static final String TEST_CASE_FILE_PATH_TEMPLATE = "src/main/resources/TestCase%s.txt";

    public static final String DEFAULT_SEPARATOR = "\\,";

    /**
     * 将字符串转化为对应的二维整形数组
     * @param inputStr 输入字符串 形式类似 "[[1,6,1],[3,10,2],[10,12,3],[11,12,2],[12,15,2],[13,18,1]]"
     * @return 对应的二维整形数组
     */
    public static int[][] get2DimensionIntArr(String inputStr) {
        return (int[][]) getArr(inputStr, int.class);
    }

    /**
     * 将字符串转化为对应的二维字符串数组
     * @param inputStr 输入字符串 形式类似 "[[1,6,1],[3,10,2],[10,12,3],[11,12,2],[12,15,2],[13,18,1]]"
     * @return 对应的二维字符串数组
     */
    public static String[][] get2DimensionStrArr(String inputStr) {
        return (String[][]) getArr(inputStr, String.class);
    }

    /**
     * 将字符串转为对应的二维List
     * @param inputStr 输入字符串 类似 [[...],[...],[...]]
     * @param separator 字符串中分割数组的分隔符
     * @param mapFun 一维数组字符串到一维数组实例的转化器
     * @return 字符串对应的二维数组
     * @param <T> 数组类型[]
     */
    public static<T> List<List<T>> get2DimensionList(String inputStr, String separator, Function<String, T[]> mapFun) {
        inputStr = inputStr.substring(1, inputStr.length() - 1).replaceAll("[\\[|\\]]", " ");
        return Arrays.stream(inputStr.split(" " + separator + " ")).map(mapFun).map(Arrays::asList).collect(Collectors.toList());
    }

    /**
     * 将字符串转为对应的字符串二维List
     * @param inputStr 输入字符串 类似 [[...],[...],[...]]
     * @param separator 字符串中分割数组的分隔符
     * @return 字符串对应的二维List
     */
    public static List<List<String>> get2DimensionStrList(String inputStr, String separator) {
        return get2DimensionList(inputStr, separator, TestCaseInputUtils::getStrArr);
    }

    /**
     * 将字符串转为对应的字符串二维List
     * @param inputStr 输入字符串 类似 [[...],[...],[...]]
     * @return 字符串对应的二维List
     */
    public static List<List<String>> get2DimensionStrList(String inputStr) {
        return get2DimensionStrList(inputStr, ",");
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
    public static<T> T[] get2DimensionArr(String inputStr, String separator, Function<String, T> mapFun, T[] arr) {
        return (T[]) getArr(
                inputStr,
                separator,
                mapFun,
                arr.getClass().getComponentType()
        );
    }

    /**
     * 将输入数组字符串转为字符串数组，保留输入中的"
     * @param inputStr 数组字符串 例如 "[\"SmallestInfiniteSet\", \"addBack\", \"popSmallest\", \"popSmallest\",
     *                \"popSmallest\", \"addBack\", \"popSmallest\", \"popSmallest\", \"popSmallest\"]"
     * @return 字符串数组 例如 ["\"SmallestInfiniteSet\""...]
     */
    public static String[] getStrArr(String inputStr) {
        return getStrArr(inputStr, ",", s -> s.replaceAll("\"", "").trim(), new String[0]);
    }

    /**
     * 将输入数组字符串转为字符串List，保留输入中的"
     * @param inputStr 数组字符串 例如 "[\"SmallestInfiniteSet\", \"addBack\", \"popSmallest\", \"popSmallest\",
     *                \"popSmallest\", \"addBack\", \"popSmallest\", \"popSmallest\", \"popSmallest\"]"
     * @return 字符串数组 例如 ["\"SmallestInfiniteSet\""...]
     */
    public static List<String> getStrList(String inputStr) {
        return Arrays.stream(getStrArr(inputStr, ",", s -> s.replaceAll("\"", "").trim(), new String[0])).collect(Collectors.toList());
    }

    /**
     * 将输入数组字符串转为字符串数组，不保留输入中的"
     * @param inputStr 数组字符串 例如 "[\"SmallestInfiniteSet\", \"addBack\", \"popSmallest\", \"popSmallest\",
     *                 \"popSmallest\", \"addBack\", \"popSmallest\", \"popSmallest\", \"popSmallest\"]"
     * @return 字符串数组 例如 ["SmallestInfiniteSet"...]
     */
    public static String[] getStrArrIgnoreDoubleQuote(String inputStr) {
        return getStrArr(inputStr, ",", s -> s.trim().replaceAll("\"", ""), new String[0]);
    }

    /**
     * 将输入数组字符串转为Integer数组
     * @param inputStr 数组字符串 例如 "["1", "2", 3]"
     * @return Integer数组 [1,2,3]
     */
    public static Integer[] getIntegerArr(String inputStr) {
        return getStrArr(
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
        return getStrArr(
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
                getStrArr(
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

    public static char[][] get2DCharArr(String inputStr) {
        return get2DimensionArr(
                inputStr,
                ",",
                TestCaseInputUtils::getCharArr,
                new char[0][0]
        );
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
    public static<T> T[] getStrArr(String inputStr, String separator, Function<String, T> mapFun, T[] arr) {
        return (T[]) getArr(
                inputStr,
                separator,
                mapFun,
                arr.getClass().getComponentType()
        );
    }

    /**
     * 将字符串转化为对应的数组 使用默认分隔符和默认解析方法
     * @param inputStr 输入字符串 形式类似 "[[1,6,1],[3,10,2],[10,12,3],[11,12,2],[12,15,2],[13,18,1]]"
     * @param clazz 数组的基础类型
     * @return 对应的数组
     */
    public static<T> Object getArr(String inputStr, Class<T> clazz) {
        return getArr(
                inputStr,
                DEFAULT_SEPARATOR,
                str -> ((T) resolveParameter(clazz.getName(), str)),
                clazz
        );
    }

    /**
     * 将字符串转化为对应的数组
     * @param inputStr 输入字符串 形式类似 "[[1,6,1],[3,10,2],[10,12,3],[11,12,2],[12,15,2],[13,18,1]]"
     * @param separator 分隔符
     * @param mapFunc 字符串怎么解析为数组基础类型的实现 如果为空 则使用resolveParameter方法
     * @param clazz 数组的基础类型
     * @return 对应的数组
     */

    public static Object getArr(String inputStr, String separator, Function<String, ?> mapFunc, Class<?> clazz) {
        // 1 计算维度
        int dimension = 0;
        int length = inputStr.length();
        for (int i = 0; i < length; i++) {
            if (inputStr.charAt(i) != '[') {
                break;
            }
            ++dimension;
        }

        // 2 非数组 直接返回
        if (dimension == 0) {
            return resolveParameter(clazz.getName(), inputStr);
        }

        // 3 数组的话 计算正确维度
        // [[1,6,1],[3,10,2],[10,12,3],[11,12,2],[12,15,2],[13,18,1]]
        int[] dimensionArr = new int[dimension];
        StringBuilder arrStartStr = new StringBuilder(separator);
        StringBuilder arrEndStr = new StringBuilder("]");
        for (int i = dimension - 1; i > -1; i--) {
            dimensionArr[i] = inputStr.substring(
                    0, inputStr.indexOf(arrEndStr.toString())).split(arrStartStr.toString()
            ).length;
            arrStartStr.append("\\[");
            arrEndStr.append("]");
        }
        // 3.1 计算每个维度对应的元素数量 用于计算下标
        int[] elementCountArr = new int[dimension];
        elementCountArr[dimension - 1] = 1;
        for (int i = elementCountArr.length - 2; i >= 0; i--) {
            elementCountArr[i] = elementCountArr[i + 1] * dimensionArr[i + 1];
        }

        // 3.2 构造对应维度数组
        Object arr = Array.newInstance(clazz, dimensionArr);
        // 3.3 获取输入转化后的结果
        Object[] plantItemArr = Arrays.stream(
                inputStr.replaceAll("\\[", "").replaceAll("]", "").split(separator)
        ).map(mapFunc).toArray();
        // 3.4 设置值
        Object wrp;
        for (int i = 0; i < plantItemArr.length; i++) {
            wrp = arr;
            int idx = i;
            for (int i1 = 0; i1 < elementCountArr.length - 1; i1++) {
                wrp = Array.get(wrp, idx / elementCountArr[i1]);
                idx %= elementCountArr[i1];
            }
            Array.set(wrp, idx % dimensionArr[dimension - 1], plantItemArr[i]);
        }
        return arr;
    }

    /**
     * 将字符串转为对应的参数对象
     * @param paramType 参数类型
     * @param paramsStr 参数字符串
     * @return 参数对象
     */
    public static Object resolveParameter(String paramType, String paramsStr) {
        if (paramType.startsWith("java.util.List<")) {
            paramType = paramType.substring("java.util.List<".length(), paramType.length() - 1);
            paramsStr = paramsStr.substring(1, paramsStr.length() - 1);
            List<Integer> splitIndexFromParamsStr = getSplitIndexFromParamsStr(paramsStr);
            // 这里没有处理类型和参数不匹配的情况 暂时不考虑处理 因为leetcode的测试用例都是正确的格式
            if (splitIndexFromParamsStr.isEmpty()) {
                String finalParamType = paramType;
                return Arrays.stream(paramsStr.split(","))
                        .map(str -> resolveParameter(finalParamType, str))
                        .collect(Collectors.toList());
            } else {
                List<Object> list = new ArrayList<>();
                int startIdx = 0;
                for (Integer splitIndex : splitIndexFromParamsStr) {
                    if (paramsStr.charAt(startIdx) == ',') {
                        ++startIdx;
                    }
                    list.add(resolveParameter(paramType, paramsStr.substring(startIdx, splitIndex + 1)));
                    startIdx = splitIndex + 1;
                }
                return list;
            }
        } else if (paramType.contains("int[][]")) {
            return get2DimensionIntArr(paramsStr);
        } else if (paramType.contains("int[]")) {
            return getIntArr(paramsStr);
        } else if (paramType.contains("Integer[]")) {
            return getIntegerArr(paramsStr);
        } else if (paramType.contains("char[]")) {
            return getCharArr(paramsStr);
        } else if (paramType.contains("Integer") || paramType.contains("int")) {
            return Integer.parseInt(paramsStr);
        } else if (paramType.contains("long[][]")) {
            return get2DimensionArr(
                    paramsStr,
                    ",",
                    strArr -> Arrays.stream(strArr.split(","))
                            .map(String::trim)
                            .map(Long::parseLong)
                            .mapToLong(Long::intValue).toArray(),
                    new int[0][0]
            );
        } else if (paramType.contains("long[]")) {
            return getIntArr(paramsStr);
        } else if (paramType.contains("Long[]")) {
            return getIntegerArr(paramsStr);
        } else if (paramType.contains("Long") || paramType.contains("long")) {
            return Integer.parseInt(paramsStr);
        } else if (paramType.contains("Character") || paramType.contains("char")) {
            return paramsStr.charAt(0);
        } else if (paramType.contains("String[")) {
            return getStrArr(paramsStr);
        } else if (paramType.contains("String")) {
            return paramsStr;
        } else if (paramType.contains("TreeNode")) {
            return new TreeNode(getIntegerArr(paramsStr));
        } else if (paramType.contains("ListNode")) {
            return new ListNode(getIntArr(paramsStr));
        }
        throw new IllegalArgumentException(String.format(
                "type %s is not supported, param string: %s", paramType, paramsStr
        ));
    }

    public static String getTestInputFromTestFile(String fileName) {
        return getTestInputFromTestFile(fileName, "");
    }

    public static String getTestInputFromTestFile(String fileName, String lineSeparator) {
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            StringBuilder str = new StringBuilder();
            String lineStr;
            while (true) {
                lineStr = bufferedReader.readLine();
                if (lineStr == null ) {
                    break;
                }
                str.append(lineStr);
                str.append(lineSeparator);
            }
            return str.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getTestInputFromTestFile() {
        return getTestInputFromTestFile(String.format(TEST_CASE_FILE_PATH_TEMPLATE, ""));
    }

    public static String getTestInputFromTestFile(Class<?> clazz) {
        return getTestInputFromTestFile(String.format(TEST_CASE_FILE_PATH_TEMPLATE, clazz.getSimpleName()));
    }

    public static String getTestInputFromClassFileMainMethod(String fileName) {
        StringBuilder str = new StringBuilder();
        String lineStr;
        String trimLineStr;
        int inComment = 0;
        try (BufferedReader bufferedReader = Files.newBufferedReader(new File(fileName).toPath())) {
            while (true) {
                lineStr = bufferedReader.readLine();
                if (lineStr == null) {
                    break;
                }
                if (lineStr.isEmpty()) {
                    continue;
                }
                trimLineStr = lineStr.trim();
                if (trimLineStr.startsWith("/*")) {
                    inComment = 1;
                } else if (trimLineStr.endsWith("*/")) {
                    inComment = 0;
                } else if (inComment == 0 && trimLineStr.startsWith("//")) {
                    str.append(trimLineStr.substring(2));
                } else if (inComment != 0) {
                    if (trimLineStr.startsWith("*")) {
                        trimLineStr = trimLineStr.substring(1);
                    }
                    if (trimLineStr.matches("\\s+@param\\s*args[.\\s]*")) {
                        continue;
                    }
                    if (trimLineStr.matches("<p>")) {
                        continue;
                    }
                    str.append(trimLineStr);
                } else if (trimLineStr.startsWith("public static void main")) {
                    break;
                } else {
                    str.delete(0, str.length());
                }
            }
            return str.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getClassName(String fileName) {
        String lineStr;
        String packageUrl = "";
        try (BufferedReader bufferedReader = Files.newBufferedReader(new File(fileName).toPath())) {
            while (true) {
                lineStr = bufferedReader.readLine();
                if (lineStr == null) {
                    break;
                }
                if (lineStr.isEmpty()) {
                    continue;
                }
                if (lineStr.matches("\\s*package.+")) {
                    lineStr = lineStr
                            .replaceAll("package\\s*(.+)\\s*;", "$1")
                            .replaceAll("\\s", "");
                    packageUrl = lineStr;
                } else if (lineStr.matches("\\s*public\\s*class\\s*.+\\s*[\n{]")) {
                    lineStr = lineStr.replaceAll("\\s*public\\s*class\\s*([A-Za-z0-9$_]+)\\s*[\n{]", "$1");
                    return packageUrl.isEmpty()
                            ? lineStr.substring(0, lineStr.indexOf(" "))
                            : packageUrl + "." + lineStr;
                }
            }
            return null;
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

    static List<Integer> getSplitIndexFromParamsStr(String paramsStr) {
        int openCnt = 0;
        int length = paramsStr.length();
        char ch;
        List<Integer> strSplitIndexList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            ch = paramsStr.charAt(i);
            if (ch == '[') {
                openCnt++;
            } else if (ch == ']') {
                --openCnt;
                if (openCnt == 0) {
                    strSplitIndexList.add(i);
                }
            }
        }
        return strSplitIndexList;
    }


    /**
     * 获取测试目标类
     * @return 测试目标类
     */
    public static Class<?> getTestTargetClass() {
        // 获取方法堆栈
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        // 保证是main方法是因为，main方法一般情况下是stackTrace的最后一个元素，而这里获取的是最后一个元素
        // 实际上，正确的逻辑是找到本方法的堆栈，再下一个就是调用方法的信息，但是代码中都是在main方法中调用的，所以这里直接判断main方法
        if (!Objects.equals(stackTrace[stackTrace.length - 1].getMethodName(), "main")) {
            throw new RuntimeException("test method must be called in main method");
        }
        Class<?> testTargetClass;
        try {
            testTargetClass = Class.forName(stackTrace[stackTrace.length - 1].getClassName());
        } catch (ClassNotFoundException e) {
            System.err.print("get test class failed, cause:" + e.getMessage());
            throw new RuntimeException(e);
        }
        return testTargetClass;
    }

}
