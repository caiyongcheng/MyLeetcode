package letcode.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static letcode.utils.TestCaseInputUtils.*;

/**
 * 测试工具类：题解方法解析见 {@link SolutionMethodResolver}，示例字符串与参数转换见 {@link TestCaseArgumentCodec}。
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
            originStr = originStr.replaceAll("<p>", "");
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

        private Class<T> testClass;

        private List<Method> testMethods;

        private List<TestCase> testCaseList;

        public TestCaseExecutor(Class<T> testClass, String... testCaseStrArr) {
            init(testClass, testCaseStrArr);
        }

        public TestCaseExecutor(Class<T> testClass, String testCaseStr) {
            init(testClass, testCaseStr.split("(Example \\d+:)|(示例 \\d+：)"));
        }
        
        public void init(Class<T> testClass, String... testCaseStrArr) {
            this.testClass = testClass;
            testMethods = SolutionMethodResolver.resolveSolutionMethods(testClass);
            testCaseList = Arrays.stream(testCaseStrArr)
                    .map(String::trim)
                    .filter(str -> !str.isEmpty())
                    .map(TestCase::new)
                    .collect(Collectors.toList());
        }

        /**
         * 返回本题要跑的全部题解方法（顺序：类上可重复注解顺序，或方法上注解的声明顺序）。
         */
        public List<Method> getTestMethodsFromClass(Class<T> testClass) {
            return SolutionMethodResolver.resolveSolutionMethods(testClass);
        }

        public Method getTestMethodFromClass(Class<T> testClass) {
            return SolutionMethodResolver.resolveSingleSolutionMethod(testClass);
        }

        /**
         * 每个题解方法单独一套实例：与用例数相同，彼此不与其它题解方法共享引用。
         */
        private List<T> newTestObjectsOnePerCase() {
            List<T> list = new ArrayList<>(testCaseList.size());
            for (int i = 0; i < testCaseList.size(); i++) {
                try {
                    list.add(testClass.getConstructor().newInstance());
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
            return list;
        }
        
        public void execute() {
            Method first = testMethods.get(0);
            PrintUtil.consolePrint(String.format("test class is: %s%n", first.getDeclaringClass().getSimpleName()),
                    PrintUtil.YELLOW);
            for (int mi = 0; mi < testMethods.size(); mi++) {
                Method testMethod = testMethods.get(mi);
                List<T> testObjList = newTestObjectsOnePerCase();
                if (testMethods.size() > 1) {
                    PrintUtil.consolePrint(String.format(
                            "---------- solution targets [%d/%d]: %s ----------%n",
                            mi + 1,
                            testMethods.size(),
                            testMethod.getName()
                    ), PrintUtil.YELLOW);
                }
                PrintUtil.consolePrint(String.format("test method is: %s%n", testMethod.getName()), PrintUtil.YELLOW);
                PrintUtil.consolePrint(PrintUtil.PRINT_TEST_CASE_START, PrintUtil.GREEN);
                runExamplesForMethod(testMethod, testObjList);
                PrintUtil.consolePrint(PrintUtil.PRINT_TEST_CASE_SPLIT_LINE, PrintUtil.GREEN);
                PrintUtil.consolePrint(PrintUtil.PRINT_TEST_CASE_END + "\n", PrintUtil.GREEN);
            }
        }

        private void runExamplesForMethod(Method testMethod, List<T> testObjList) {
            Type[] genericParameterTypes = testMethod.getGenericParameterTypes();
            for (int time = 1; time <= this.testCaseList.size(); time++) {
                TestCase testCase = testCaseList.get(time - 1);
                PrintUtil.consolePrint(PrintUtil.PRINT_TEST_CASE_SPLIT_LINE, PrintUtil.GREEN);
                PrintUtil.consolePrint(String.format(PrintUtil.PRINT_TEST_CASE_INNER_START, time), PrintUtil.GREEN);
                PrintUtil.print(String.format("input: %s", testCase.inputStr), PrintUtil.BLUE);
                PrintUtil.consolePrint(PrintUtil.PRINT_TEST_CASE_INNER_SPLIT_LINE, PrintUtil.GREEN);
                PrintUtil.print(String.format("Output: %s", testCase.outputStr), PrintUtil.CYAN);
                PrintUtil.consolePrint(PrintUtil.PRINT_TEST_CASE_INNER_SPLIT_LINE, PrintUtil.GREEN);
                PrintUtil.print(String.format("Explanation: %s", testCase.explanationStr), PrintUtil.YELLOW);
                PrintUtil.consolePrint(PrintUtil.PRINT_TEST_CASE_INNER_SPLIT_LINE, PrintUtil.GREEN);
                String[] paramsStrArr = TestCaseArgumentCodec.splitInputToParamStrings(testCase.inputStr, genericParameterTypes);
                Object[] params = TestCaseArgumentCodec.buildParams(genericParameterTypes, paramsStrArr);
                try {
                    PrintUtil.print(String.format("params: %s", TestCaseOutputUtils.formatObj(params)), PrintUtil.PURPLE);
                    PrintUtil.consolePrint(PrintUtil.PRINT_TEST_CASE_INNER_SPLIT_LINE, PrintUtil.GREEN);
                    long startNanoTime = System.nanoTime();
                    Object execRst = testMethod.invoke(testObjList.get(time - 1), params);
                    long endNanoTime = System.nanoTime();
                    String actualResultStr = TestCaseOutputUtils.formatObj(execRst);
                    PrintUtil.print(String.format("result: %s", actualResultStr), PrintUtil.RED);
                    PrintUtil.consolePrint(PrintUtil.PRINT_TEST_CASE_INNER_SPLIT_LINE, PrintUtil.GREEN);
                    PrintUtil.print(
                            String.format(
                                    "result compare: %s",
                                    testCase.outputStr.equals(actualResultStr) || (
                                            TestCaseArgumentCodec.returnTypeComparesLikePlainString(testMethod)
                                                    && testCase.outputStr.replaceAll("\"", "").equals(actualResultStr)
                                    )
                            ),
                            PrintUtil.LIGHT_GREEN
                    );
                    PrintUtil.consolePrint(PrintUtil.PRINT_TEST_CASE_INNER_SPLIT_LINE, PrintUtil.GREEN);
                    PrintUtil.print(
                            String.format(
                                    "time-consuming execution : %s nanosecond, %s milliseconds, %s second",
                                    endNanoTime - startNanoTime,
                                    BigDecimal.valueOf(endNanoTime - startNanoTime).divide(BigDecimal.valueOf(1000000), 6, RoundingMode.HALF_UP),
                                    BigDecimal.valueOf(endNanoTime - startNanoTime).divide(BigDecimal.valueOf(1000000000), 6, RoundingMode.HALF_UP)
                            ),
                            PrintUtil.LIGHT_YELLOW
                    );
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                PrintUtil.consolePrint(String.format(PrintUtil.PRINT_TEST_CASE_INNER_END, time), PrintUtil.GREEN);
            }
        }

        /**
         * 将字符串数组转变为给定类型的参数（供 {@link TestUtil#operation} 等复用）。
         */
        public static Object[] getParams(Type[] typeArr, String[] paramStrArr) {
            return TestCaseArgumentCodec.buildParams(typeArr, paramStrArr);
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

        private static final String WHITE = "\u001B[37m";
        private static final String GRAY = "\u001B[90m";
        private static final String LIGHT_RED = "\u001B[91m";
        private static final String LIGHT_GREEN = "\u001B[92m";
        private static final String LIGHT_YELLOW = "\u001B[93m";
        private static final String LIGHT_BLUE = "\u001B[94m";
        private static final String LIGHT_PURPLE = "\u001B[95m";
        private static final String LIGHT_CYAN = "\u001B[96m";

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
        Map<String, List<Method>> methodsByName =
                Arrays.stream(methodArr).collect(Collectors.groupingBy(Method::getName));
        String[] operationArr = getStrArrIgnoreDoubleQuote(operationStr);
        String[][] paramsArr = get2DimensionStrArr(paramsStr);
        String[] ans = new String[paramsArr.length];
        try {
            for (int i = 0; i < ans.length; i++) {
                List<Method> candidates = methodsByName.get(operationArr[i]);
                if (candidates == null || candidates.isEmpty()) {
                    ans[i] = null;
                    continue;
                }
                Method method = selectOperationMethod(candidates, paramsArr[i]);
                method.setAccessible(true);
                Object[] params = TestCaseExecutor.getParams(method.getGenericParameterTypes(), paramsArr[i]);
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
     * 在同名方法中按参数个数解析重载；多个同 arity 时尝试 {@link TestCaseExecutor#getParams} 直至成功。
     */
    private static Method selectOperationMethod(List<Method> candidates, String[] paramStrings) {
        int arity = paramStrings.length;
        List<Method> arityMatch = candidates.stream()
                .filter(m -> m.getGenericParameterTypes().length == arity)
                .collect(Collectors.toList());
        if (arityMatch.isEmpty()) {
            throw new IllegalArgumentException(String.format(
                    "No overload with arity %d for name %s, candidates=%s, params=%s",
                    arity,
                    candidates.get(0).getName(),
                    candidates.stream().map(Method::toString).collect(Collectors.toList()),
                    Arrays.toString(paramStrings)));
        }
        if (arityMatch.size() == 1) {
            return arityMatch.get(0);
        }
        for (Method m : arityMatch) {
            try {
                TestCaseExecutor.getParams(m.getGenericParameterTypes(), paramStrings);
                return m;
            } catch (RuntimeException ignored) {
                // try next overload
            }
        }
        throw new IllegalArgumentException(String.format(
                "Cannot resolve overload among %s for params %s",
                arityMatch.stream().map(Method::toString).collect(Collectors.toList()),
                Arrays.toString(paramStrings)));
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
