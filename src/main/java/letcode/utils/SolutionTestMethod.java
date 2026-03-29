package letcode.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记由 {@link TestUtil} 反射执行题解时要调用的实例方法。
 * <p>
 * 若类上<strong>恰好有一个</strong>带本注解的 public 非 static 方法，则优先使用该方法；
 * 若<strong>没有</strong>任何带注解的方法，则仍按「本类中唯一符合条件的 public 实例方法」推断（与原先一致）。
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SolutionTestMethod {
}
