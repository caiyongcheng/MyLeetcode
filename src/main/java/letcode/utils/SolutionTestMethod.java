package letcode.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记 {@link TestUtil} 反射执行题解时要调用的实例方法。
 * <p>
 * 在<strong>同一类上</strong>可重复使用本注解（需配合 {@link SolutionTestMethods}），表示将按声明顺序对<strong>多个</strong>方法各跑一遍全部测试用例。
 * <p>
 * <b>标注在方法上</b>：{@link #method()} 必须留空，表示使用被标注的该方法；可在多个方法上分别标注以指定多个待测方法。
 * <p>
 * <b>标注在类上</b>：必须设置 {@link #method()} 为方法名；若存在重载，须通过 {@link #paramTypes()} 指定形参类型
 * （按声明顺序，与 {@link Class#getMethod(String, Class[])} 一致，基本类型用 {@code int.class} 等）。
 * <p>
 * 若「类上」与「方法上」同时出现本注解配置，将抛异常；若类上既有可重复注解又在方法上标注，同样视为冲突。
 * <p>
 * 若类与方法上都未使用本注解，则仍按「本类中唯一符合条件的 public 实例方法」推断（与原先一致）。
 */
@Repeatable(SolutionTestMethods.class)
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SolutionTestMethod {

    /**
     * 仅在标注在<strong>类</strong>上时使用：要执行的方法名。
     * 标注在方法上时必须为默认空字符串。
     */
    String method() default "";

    /**
     * 按形参顺序列出参数类型，用于区分<strong>重载</strong>。
     * 为空时：仅当该名称下只有一个符合条件的 public 实例方法时可用，否则须显式填写。
     */
    Class<?>[] paramTypes() default {};
}
