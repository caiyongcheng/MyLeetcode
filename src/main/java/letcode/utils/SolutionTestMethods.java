package letcode.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 容器注解：在类上可写多个 {@link SolutionTestMethod}（JDK 可重复注解机制）。
 *
 * @see SolutionTestMethod
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SolutionTestMethods {
    SolutionTestMethod[] value();
}
