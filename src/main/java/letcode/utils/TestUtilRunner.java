package letcode.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * IDEA 插件运行入口：插件传入当前题解类名后，由这里统一交给 TestUtil 执行。
 */
public class TestUtilRunner {

    public static void main(String[] args) {
        if (args == null || args.length == 0 || args[0] == null || args[0].trim().isEmpty()) {
            throw new IllegalArgumentException("target class name args[0] is required");
        }
        String className = args[0].trim();
        try {
            // 运行配置只能传字符串参数，这里反射加载当前题解类。
            Class<?> targetClass = Class.forName(className);
            String input = parseInput(args);
            if (input == null || input.isEmpty()) {
                TestUtil.test(targetClass);
            } else {
                TestUtil.test(targetClass, input);
            }
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("target class not found: " + className, e);
        }
    }

    private static String parseInput(String[] args) {
        if (args.length < 2) {
            return null;
        }
        if (args.length >= 3 && "--base64".equals(args[1])) {
            // Base64 避免测试用例中的空格、换行、引号被 IDEA 运行参数拆分。
            byte[] bytes = Base64.getDecoder().decode(args[2]);
            return new String(bytes, StandardCharsets.UTF_8);
        }
        return args[1];
    }
}
