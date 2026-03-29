package letcode.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记 {@link TestUtil} 反射执行题解时要调用的实例方法。
 * <p>
 * <b>标注在方法上</b>：{@link #method()} 必须留空，表示使用被标注的该方法（可多个 public 时唯一定位）。
 * <p>
 * <b>标注在类上</b>：必须设置 {@link #method()} 为方法名；若存在重载，须通过 {@link #paramTypes()} 指定形参类型
 * （按声明顺序，与 {@link Class#getMethod(String, Class[])} 一致，基本类型用 {@code int.class} 等）。
 * <p>
 * 若类与方法上都未使用本注解，则仍按「本类中唯一符合条件的 public 实例方法」推断（与原先一致）。
 */
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
