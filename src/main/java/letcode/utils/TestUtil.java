package letcode.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
public class TestUtil {


    static class TestCase {

        private final String inputStr;

        private String outputStr = "";
        
        private String explanationStr = "";

        public TestCase(String originStr) {
            String[] splitByOutput = originStr.split("(Output: )|(输出：)");
            inputStr = splitByOutput[0].replaceAll("(Input:)|(输入：)", "");
            if (splitByOutput.length == 1) {
                return;
            }

            String[] splitByExplanation = splitByOutput[1].split("(Explanation)|(解释：)");
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
        
        private List<T> testObjList;

        public TestCaseExecutor(Class<T> testClass, String... testCaseStrArr) {
            init(testClass, testCaseStrArr);
        }

        public TestCaseExecutor(Class<T> testClass, String testCaseStr) {
            init(testClass, testCaseStr.split("(Example \\d+:)|(示例 \\d+：)"));
        }
        
        public void init(Class<T> testClass, String... testCaseStrArr) {
            testMethod = getTestMethodFromClass(testClass);
            testObjList = Arrays.stream(testCaseStrArr)
                    .map(str -> {
                        try {
                            return testClass.getConstructor().newInstance();
                        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                                 NoSuchMethodException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .collect(Collectors.toList());
            testCaseList = Arrays.stream(testCaseStrArr)
                    .map(String::trim)
                    .filter(str -> !str.isEmpty())
                    .map(TestCase::new)
                    .collect(Collectors.toList());
        }
        
        public Method getTestMethodFromClass(Class<T> testClass) {
            List<Method> methodList = Arrays.stream(testClass.getMethods())
                    .filter(method -> Modifier.isPublic(method.getModifiers()))
                    .filter(method -> !Modifier.isStatic(method.getModifiers()))
                    .collect(Collectors.toList());
            if (methodList.size() == 9) {
                throw new IllegalArgumentException(String.format("type %s don't have public method!", testClass.getName()));
            }
            if (methodList.size() > 10) {
                throw new IllegalArgumentException(String.format("type %s have more than one public method!", testClass.getName()));
            }
            return methodList.get(0);
        }
        
        public void execute() {
            Type[] genericParameterTypes = testMethod.getGenericParameterTypes();
            PrintUtil.consolePrint(String.format("test class is: %s%n", testMethod.getDeclaringClass().getSimpleName()),
                    PrintUtil.YELLOW);
            PrintUtil.consolePrint(String.format("test method is: %s%n", testMethod.getName()), PrintUtil.YELLOW);
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
                String[] paramsStrArr = getParamStrArr(testCase.inputStr, genericParameterTypes);
                Object[] params = getParams(genericParameterTypes, paramsStrArr);
                try {
                    PrintUtil.print(String.format("params: %s", TestCaseOutputUtils.formatObj(params)), PrintUtil.PURPLE);
                    PrintUtil.consolePrint(PrintUtil.PRINT_TEST_CASE_INNER_SPLIT_LINE, PrintUtil.GREEN);
                    Object execRst = testMethod.invoke(testObjList.get(time - 1), params);
                    String excuseResultStr = TestCaseOutputUtils.formatObj(execRst);
                    PrintUtil.print(String.format("result: %s", excuseResultStr), PrintUtil.RED);
                    PrintUtil.consolePrint(PrintUtil.PRINT_TEST_CASE_INNER_SPLIT_LINE, PrintUtil.GREEN);
                    PrintUtil.print(
                            String.format(
                                    "result compare: %s",
                                    testCase.outputStr.equals(excuseResultStr) || (
                                            resultTypeIsStringType()
                                                    && testCase.outputStr.replaceAll("\"", "").equals(excuseResultStr)
                                    )
                            ),
                            ""
                    );
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
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
        private static String[] getParamStrArr(String inputStr, Type[] parameterTypes) {
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
        public static Object[] getParams(Type[] typeArr, String[] paramStrArr) {
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
                params[i] = TestCaseInputUtils.getParam(typeArr[i].getTypeName(), paramStrArr[i]);
            }
            return params;
        }

        /**
         * 判断returnType是不是String类型或者是String类型的数据
         * @return returnType是不是String类型或者是String类型的数据
         */
        private boolean resultTypeIsStringType() {
            Class<?> returnType = testMethod.getReturnType();
            return returnType == String.class || (returnType.isArray() && returnType.getComponentType() == String.class);
        }


    }

    static class PrintUtil {
        private static final String PRINT_TEST_CASE_START = "================================================================== start ====================================================================\n";
        private static final String PRINT_TEST_CASE_END = "================================================================== end ======================================================================";
        private static final String PRINT_TEST_CASE_SPLIT_LINE = "||                                                                                                                                         ||\n";
        private static final String PRINT_TEST_CASE_INNER_SPLIT_LINE = "||\t |                                                                                                                                |\t   ||\n";
        private static final String PRINT_TEST_CASE_INNER_START = "||\t ·----------------------------------------------------------- %02d[start] ----------------------------------------------------------·    ||\n";
        private static final String PRINT_TEST_CASE_INNER_END = "||\t ·----------------------------------------------------------- %02d[end] ------------------------------------------------------------·\t   ||\n";
        private static final String PRINT_TEST_CASE_INNER_PREFIX = "||   | ";
        private static final String PRINT_TEST_CASE_INNER_SUFFIX = " |    ||";

        private static final String RESET = "\u001B[0m";
        private static final String RED = "\u001B[31m";
        private static final String GREEN = "\u001B[32m";
        private static final String YELLOW = "\u001B[33m";
        private static final String BLUE = "\u001B[34m";
        private static final String PURPLE = "\u001B[35m";
        private static final String CYAN = "\u001B[36m";


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
            System.out.printf("%s%s%s",fontColor, str, RESET);
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
     */
    public static <T> void testUseTestFile(Class<T> targetClass) {
        new TestCaseExecutor<>(targetClass, TestCaseInputUtils.getTestInputFromTestFile(targetClass)).execute();
    }

    /**
     * 测试目标类的方法
     */
    public static <T> void test() {
        test(TestCaseInputUtils.getTestTargetClass());
    }


    /**
     * 测试目标类的方法
     *
     * @param targetClass 目标类
     */
    public static <T> void test(Class<T> targetClass) {
        if (Objects.isNull(targetClass)) {
            throw new IllegalArgumentException("targetClass must not null");
        }
        try {
            URI uri = Objects.requireNonNull(targetClass.getResource(targetClass.getSimpleName() + ".class")).toURI();
            String path = uri.getPath();
            path = path.replaceAll("file:/", "");
            path = path.replaceAll("/target/classes/", "/src/main/java/");
            path = path.replaceAll(".class", ".java");
            String inputStr = getTestInputFromClassFileMainMethod(path);
            if (inputStr.isEmpty()) {
                TestUtil.testUseTestFile(targetClass);
            } else {
                TestUtil.test(targetClass, inputStr);
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 测试目标类的方法
     *
     * @param inputStr 输入字符串，按输入进行划分
     */
    public static <T> void test(String inputStr) {
        test(TestCaseInputUtils.getTestTargetClass(), inputStr);
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
    public static <T> void testBatch(Class<T> targetClass, String... inputStrArr) {
        if (Objects.isNull(inputStrArr) || inputStrArr.length == 0) {
            inputStrArr = new String[]{TestCaseInputUtils.getTestInputFromTestFile()};
        }
        new TestCaseExecutor<>(targetClass, inputStrArr).execute();
    }





}
