package letcode.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static letcode.utils.TestCaseInputUtils.*;

/**
 * 测试工具类
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-07-18 09:30
 */
@SuppressWarnings("all")
public class TestUtil {
    

    static class TestCase {

        private String inputStr = "";

        private String outputStr = "";
        
        private String explanationStr = "";

        public TestCase(String originStr) {
            String[] splitByOutput = originStr.split("Output: ");
            inputStr = splitByOutput[0].replaceAll("Input:", "");
            if (splitByOutput.length == 1) {
                return;
            }

            String[] splitByExplanation = splitByOutput[1].split("Explanation");
            outputStr = splitByExplanation[0].trim();
            if (splitByExplanation.length == 1) {
                return;
            }

            explanationStr = splitByExplanation[1];
        }
    }
    
    static class TestCaseExecutor<T> {

        private Method testMethod;

        private List<TestCase> testCaseList;
        
        private T testObj;

        public TestCaseExecutor(Class<T> testClass, String... testCaseStrArr) {
            init(testClass, testCaseStrArr);
        }

        public TestCaseExecutor(Class<T> testClass, String testCaseStr) {
            init(testClass, testCaseStr.split("Example \\d+:"));
        }
        
        public void init(Class<T> testClass, String... testCaseStrArr) {
            testMethod = getTestMethodFromClass(testClass);
            try {
                testObj = testClass.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            testCaseList = Arrays.stream(testCaseStrArr)
                    .map(str -> str.trim())
                    .filter(str -> !str.isEmpty())
                    .map(TestCase::new)
                    .collect(Collectors.toList());
        }
        
        public Method getTestMethodFromClass(Class<T> testClass) {
            return Arrays.stream(testClass.getMethods())
                    .filter(method -> Modifier.isPublic(method.getModifiers()))
                    .filter(method -> !Modifier.isStatic(method.getModifiers()))
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException(
                            String.format("type %s don't have public method!", testClass.getName())
                    ));
        }
        
        public void execute() {
            Class<?>[] parameterTypes = testMethod.getParameterTypes();
            PrintUtil.consolePrint(String.format("test class is: %s%n", testMethod.getName()), PrintUtil.YELLOW);
            PrintUtil.consolePrint(PrintUtil.PRINT_TEST_CASE_START, PrintUtil.GREEN);
            for (int time = 1; time <= this.testCaseList.size(); time++) {
                TestCase testCase = testCaseList.get(time - 1);
                PrintUtil.consolePrint(PrintUtil.PRINT_TEST_CASE_SPLIT_LINE, PrintUtil.GREEN);
                //System.out.printf(PrintUtil.PRINT_TEST_CASE_INNER_TOP);
                PrintUtil.consolePrint(String.format(PrintUtil.PRINT_TEST_CASE_INNER_START, time), PrintUtil.GREEN);
                PrintUtil.print(String.format("input: %s", testCase.inputStr), PrintUtil.BLUE);
                PrintUtil.consolePrint(PrintUtil.PRINT_TEST_CASE_INNER_SPLIT_LINE, PrintUtil.GREEN);
                PrintUtil.print(String.format("Output: %s",  testCase.outputStr), PrintUtil.CYAN);
                PrintUtil.consolePrint(PrintUtil.PRINT_TEST_CASE_INNER_SPLIT_LINE, PrintUtil.GREEN);
                PrintUtil.print(String.format("Explanation: %s", testCase.explanationStr), PrintUtil.YELLOW);
                PrintUtil.consolePrint(PrintUtil.PRINT_TEST_CASE_INNER_SPLIT_LINE, PrintUtil.GREEN);
                String[] paramsStrArr = getParamStrArr(testCase.inputStr, parameterTypes);
                Object[] params = getParams(parameterTypes, paramsStrArr);
                try {
                    PrintUtil.print(String.format("params: %s", TestCaseOutputUtils.formatObj(params)), PrintUtil.PURPLE);
                    PrintUtil.consolePrint(PrintUtil.PRINT_TEST_CASE_INNER_SPLIT_LINE, PrintUtil.GREEN);
                    Object execRst = testMethod.invoke(testObj, params);
                    String excuseResultStr = TestCaseOutputUtils.formatObj(execRst);
                    PrintUtil.print(String.format("result: %s", excuseResultStr), PrintUtil.RED);
                    PrintUtil.consolePrint(PrintUtil.PRINT_TEST_CASE_INNER_SPLIT_LINE, PrintUtil.GREEN);
                    PrintUtil.print(String.format("result conpare: %s", testCase.outputStr.equals(excuseResultStr)), "");
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                    break;
                }
                PrintUtil.consolePrint(String.format(PrintUtil.PRINT_TEST_CASE_INNER_END, time), PrintUtil.GREEN);
            }
            PrintUtil.consolePrint(PrintUtil.PRINT_TEST_CASE_SPLIT_LINE, PrintUtil.GREEN);
            PrintUtil.consolePrint(PrintUtil.PRINT_TEST_CASE_END + "\n", PrintUtil.GREEN);
        }

        /**
         * 从输入字符串中，根据方法参数类型数组，提取对应的参数字符串数组
         *
         * @param inputStr       输入字符串
         * @param parameterTypes 方法参数类型数组
         * @return 参数字符串数组
         */
        private static String[] getParamStrArr(String inputStr, Class<?>[] parameterTypes) {
            String[] paramsStrArr = new String[parameterTypes.length];
            int j = 0;
            int length = inputStr.length();
            char ch;
            int retrieve = -1;
            int isArrParam = 0;
            int doubleQuotationCount = 0;
            StringBuilder paramStr = new StringBuilder();
            for (int i = 0; i < length; i++) {
                ch = inputStr.charAt(i);
                if (ch == '=' && (doubleQuotationCount & 1) == 0) {
                    retrieve = 0;
                } else if (ch == '[' && retrieve == 0) {
                    isArrParam++;
                } else if (ch == ']' && retrieve == 0) {
                    if (--isArrParam == 0) {
                        retrieve = 1;
                        paramStr.append(ch);
                    }
                } else if (((ch == ',') || (ch == '输' && (i + 1 < length && inputStr.charAt(i + 1) == '出')))
                        && retrieve == 0 && isArrParam == 0) {
                    retrieve = 1;
                }
                if (retrieve == 0) {
                    if (ch != '=' || (doubleQuotationCount & 1) == 1) {
                        paramStr.append(ch);
                    }
                    if (ch == '\"') {
                        doubleQuotationCount++;
                    }
                }
                if (retrieve == 1) {
                    retrieve = -1;
                    paramsStrArr[j++] = paramStr.toString();
                    paramStr.delete(0, paramStr.length());
                }
            }
            if (paramStr.length() > 0 && j < paramsStrArr.length) {
                paramsStrArr[j] = paramStr.toString();
            }
            if (j == 0 && paramStr.length() == 0) {
                paramsStrArr[0] = inputStr;
            }
            return paramsStrArr;
        }


        /**
         * 将字符串数组转变为给定类型的参数
         *
         * @param typeArr     参数类型
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
                paramStrArr[i] = paramStrArr[i].replaceAll("\"", "").trim();
                if ("int".equals(typeArr[i].getName()) || typeArr[i] == Integer.class) {
                    params[i] = Integer.parseInt(paramStrArr[i]);
                    continue;
                }
                if ("String".equals(typeArr[i].getName()) || typeArr[i] == String.class) {
                    params[i] = paramStrArr[i];
                    continue;
                }
                if ("long".equals(typeArr[i].getName()) || typeArr[i] == Long.class) {
                    params[i] = Long.parseLong(paramStrArr[i]);
                    continue;
                }
                if ("boolean".equals(typeArr[i].getName()) || typeArr[i] == Boolean.class) {
                    params[i] = Boolean.parseBoolean(paramStrArr[i]);
                    continue;
                }
                if ("char".equals(typeArr[i].getName()) || typeArr[i] == Character.class) {
                    params[i] = paramStrArr[i].charAt(0);
                    continue;
                }
                if ("double".equals(typeArr[i].getName()) || typeArr[i] == Double.class) {
                    params[i] = Double.parseDouble(paramStrArr[i]);
                    continue;
                }
                if ("float".equals(typeArr[i].getName()) || typeArr[i] == Float.class) {
                    params[i] = Float.parseFloat(paramStrArr[i]);
                    continue;
                }
                if ("byte".equals(typeArr[i].getName()) || typeArr[i] == Byte.class) {
                    params[i] = Byte.parseByte(paramStrArr[i]);
                    continue;
                }
                if ("short".equals(typeArr[i].getName()) || typeArr[i] == Short.class) {
                    params[i] = Short.parseShort(paramStrArr[i]);
                    continue;
                }
                if ("char[]".equals(typeArr[i].getName()) || typeArr[i] == char[].class) {
                    params[i] = getCharArr(paramStrArr[i]);
                    continue;
                }
                if ("int[]".equals(typeArr[i].getName()) || typeArr[i] == int[].class) {
                    params[i] = getIntArr(paramStrArr[i]);
                    continue;
                }
                if ("[[I".equals(typeArr[i].getName()) || typeArr[i] == int[][].class) {
                    params[i] = get2DIntArr(paramStrArr[i]);
                    continue;
                }
                if ("Integer[]".equals(typeArr[i].getName()) || typeArr[i] == Integer[].class) {
                    params[i] = getIntegerArr(paramStrArr[i]);
                    continue;
                }
                if ("String[]".equals(typeArr[i].getName()) || typeArr[i] == String[].class) {
                    params[i] = getStrArr(paramStrArr[i]);
                    continue;
                }
                if ("[[Ljava.lang.String;".equals(typeArr[i].getName()) || typeArr[i] == String[][].class) {
                    params[i] = get2DStrArr(paramStrArr[i]);
                    continue;
                }
                if (typeArr[i] == List.class) {
                    switch (typeArr[i].getName()) {
                        case "List":
                        case "List<String>":
                            params[i] = getStrList(paramStrArr[i]);
                            continue;
                        case "List<Integer>":
                            params[i] = getIntegerList(paramStrArr[i]);
                            continue;
                        case "List<List<String>>":
                            params[i] = get2DStrList(paramStrArr[i]);
                            continue;
                        case "List<List<Integer>>":
                            params[i] = get2DList(paramStrArr[i], ",", TestCaseInputUtils::getIntegerArr);
                            continue;
                    }
                }
                throw new IllegalArgumentException(String.format(
                        "type %s is not supported, param string: %s",
                        typeArr[i].getName(),
                        paramStrArr[i]
                ));
            }
            return params;
        }


    }

    static class PrintUtil {
        private static final String PRINT_TEST_CASE_START = "================================================================== start ====================================================================\n";
        private static final String PRINT_TEST_CASE_END = "================================================================== end ======================================================================";
        private static final String PRINT_TEST_CASE_SPLIT_LINE = "||                                                                                                                                         ||\n";
        private static final String PRINT_TEST_CASE_INNER_SPLIT_LINE = "||\t |                                                                                                                                |\t   ||\n";
        private static final String PRINT_TEST_CASE_INNER_START = "||\t ·----------------------------------------------------------- %02d[start] ----------------------------------------------------------·    ||\n";
        private static final String PRINT_TEST_CASE_INNER_END = "||\t ·----------------------------------------------------------- %02d[end] ------------------------------------------------------------·\t   ||\n";
        private static final String PRINT_TEST_CASE_INNER_TOP = "||\t----------------------------------------------------------------------------------------------------------------------------------\t   ||\n";
        private static final String PRINT_TEST_CASE_INNER_PREFIX = "||   | ";
        private static final String PRINT_TEST_CASE_INNER_SUFFIX = " |    ||";

        private static final String RESET = "\u001B[0m";
        private static final String RED = "\u001B[31m";
        private static final String GREEN = "\u001B[32m";
        private static final String YELLOW = "\u001B[33m";
        private static final String BLUE = "\u001B[34m";
        private static final String PURPLE = "\u001B[35m";
        private static final String CYAN = "\u001B[36m";
        private static final String WHITE = "\u001B[37m";



        /**
         * 打印测试结果的语句
         *
         * @param str 打印语句
         */
        public static void print(String str, String fontColor) {
            int lineWidth = getWidth(PrintUtil.PRINT_TEST_CASE_START);
            int prefixWidth = getWidth(PrintUtil.PRINT_TEST_CASE_INNER_PREFIX);
            int suffixWidth = getWidth(PrintUtil.PRINT_TEST_CASE_INNER_SUFFIX);
            int printStrOneLineWidth = lineWidth - prefixWidth - suffixWidth - 2;
            int curWidth = 0;

            consolePrint(PrintUtil.PRINT_TEST_CASE_INNER_PREFIX, GREEN);
            String[] wordArr = str.split("\\s+");
            for (String word : wordArr) {
                int width = getWidth(word) + 1;
                if (curWidth + width > printStrOneLineWidth) {
                    while (curWidth <= printStrOneLineWidth) {
                        consolePrint(" ", fontColor);
                        ++curWidth;
                    }
                    consolePrint(PrintUtil.PRINT_TEST_CASE_INNER_SUFFIX + "\n", GREEN);
                    consolePrint(PrintUtil.PRINT_TEST_CASE_INNER_PREFIX, GREEN);
                    curWidth = 0;
                }
                consolePrint(word, fontColor);
                consolePrint(" ", fontColor);
                curWidth += width;
            }
            int tLen = curWidth;
            while (tLen <= printStrOneLineWidth) {
                consolePrint(" ", fontColor);
                ++tLen;
            }
            if (curWidth < printStrOneLineWidth) {
                consolePrint(PrintUtil.PRINT_TEST_CASE_INNER_SUFFIX + "\n", GREEN);
            }
        }

        public static void consolePrint(String str, String fontColor) {
            System.out.print(String.format("%s%s%s",fontColor, str, RESET));
        }

        /**
         * 获取字符的宽度
         *
         * @param str 字符串
         * @return 宽度
         */
        public static int getWidth(String str) {
            int width = 0;
            for (int i = 0; i < str.length(); ) {
                int codepoint = str.codePointAt(i);
                int charCount = Character.charCount(codepoint);
                i += charCount;
                width += getWidth(codepoint);
            }
            return width;
        }

        /**
         * 获取字符的宽度
         *
         * @param codepoint 字符
         * @return 宽度
         */
        private static int getWidth(int codepoint) {
            if (Character.UnicodeBlock.of(codepoint) == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                    || Character.UnicodeBlock.of(codepoint) == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                    || Character.UnicodeBlock.of(codepoint) == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                    || Character.UnicodeBlock.of(codepoint) == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                    || Character.UnicodeBlock.of(codepoint) == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT) {
                // CJK characters are typically full-width.
                return 2;
            } else {
                return 1;
            }
        }
    }

    /**
     * 让指定的对象，执行给定的操作
     *
     * @param obj          执行的对象
     * @param operationStr 操作列表 方法数组字符串
     * @param paramsStr    对应的参数列表 参数数组字符串
     * @param debug        是否打印调试语句
     * @return 操作结果数组字符串
     */
    public static String operation(Object obj, String operationStr, String paramsStr, boolean debug) {
        Method[] methodArr = obj.getClass().getDeclaredMethods();
        Map<String, Method> methodName2Method =
                Arrays.stream(methodArr).collect(Collectors.toMap(Method::getName, Function.identity()));
        String[] operationArr = getStrArrIgnoreDoubleQuote(operationStr);
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
                Object[] params = TestCaseExecutor.getParams(method.getParameterTypes(), paramsArr[i]);
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
     *
     * @param obj          执行的对象
     * @param operationStr 操作列表 方法数组字符串
     * @param paramsStr    对应的参数列表 参数数组字符串
     * @return 操作结果数组字符串
     */
    public static String operation(Object obj, String operationStr, String paramsStr) {
        return operation(obj, operationStr, paramsStr, false);
    }

    /**
     * 测试目标类的方法
     *
     * @param targetClass 目标类
     * @param inputStr 输入字符串，按输入进行划分
     */
    public static <T> void test(Class<T> targetClass, String inputStr) {
        new TestCaseExecutor<>(targetClass, inputStr).execute();
    }

    /**
     * 测试目标类的方法
     *
     * @param targetClass 目标类
     * @param inputStrArr 输入字符串，按输入进行划分
     */
    public static <T> void test(Class<T> targetClass, String... inputStrArr) {
        new TestCaseExecutor<>(targetClass, inputStrArr).execute();
    }





}
