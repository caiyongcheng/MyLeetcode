package letcode.utils;

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
            TestUtil.test(targetClass);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("target class not found: " + className, e);
        }
    }
}
