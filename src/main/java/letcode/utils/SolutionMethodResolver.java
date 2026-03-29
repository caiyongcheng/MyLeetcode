package letcode.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 根据 {@link SolutionTestMethod} 与类结构解析待执行的题解 {@link Method} 列表。
 */
public final class SolutionMethodResolver {

    private SolutionMethodResolver() {
    }

    /**
     * 解析本题要跑的全部题解方法（顺序：类上可重复注解顺序，或方法上注解的声明顺序）。
     */
    public static List<Method> resolveSolutionMethods(Class<?> testClass) {
        List<Method> methodAnnotated = Arrays.stream(testClass.getDeclaredMethods())
                .filter(m -> m.getAnnotation(SolutionTestMethod.class) != null)
                .collect(Collectors.toList());
        for (Method m : methodAnnotated) {
            SolutionTestMethod meta = m.getAnnotation(SolutionTestMethod.class);
            if (!Modifier.isPublic(m.getModifiers()) || Modifier.isStatic(m.getModifiers())) {
                throw new IllegalArgumentException(String.format(
                        "@SolutionTestMethod must be on a public non-static method: %s.%s",
                        testClass.getSimpleName(), m.getName()));
            }
            if (meta != null && !meta.method().isEmpty()) {
                throw new IllegalArgumentException(String.format(
                        "@SolutionTestMethod on method %s must leave method() empty; use class-level to specify name",
                        m.getName()));
            }
        }

        SolutionTestMethod[] classAnnos = testClass.getAnnotationsByType(SolutionTestMethod.class);

        if (!methodAnnotated.isEmpty() && classAnnos.length > 0) {
            throw new IllegalArgumentException(String.format(
                    "type %s cannot mix @SolutionTestMethod on class and on methods at the same time",
                    testClass.getName()));
        }

        if (!methodAnnotated.isEmpty()) {
            return methodAnnotated;
        }

        if (classAnnos.length > 0) {
            List<Method> resolved = new ArrayList<>(classAnnos.length);
            for (SolutionTestMethod classAnno : classAnnos) {
                resolved.add(resolveMethodFromClassAnnotation(testClass, classAnno));
            }
            return resolved;
        }

        List<Method> methods = Arrays.stream(testClass.getMethods())
                .filter(m -> Modifier.isPublic(m.getModifiers()))
                .filter(m -> !Modifier.isStatic(m.getModifiers()))
                .filter(m -> m.getDeclaringClass() == testClass)
                .filter(m -> !isPublicObjectMethodSignature(m))
                .collect(Collectors.toList());
        if (methods.isEmpty()) {
            throw new IllegalArgumentException(String.format(
                    "type %s has no public non-static method declared in this class (excluding Object overrides). "
                            + "Use @SolutionTestMethod on class or method, or expose a single such method.",
                    testClass.getName()));
        }
        if (methods.size() > 1) {
            throw new IllegalArgumentException(String.format(
                    "type %s has more than one public non-static method: %s. "
                            + "Use @SolutionTestMethod on class (method + paramTypes) or on the solution method.",
                    testClass.getName(),
                    methods.stream().map(Method::getName).collect(Collectors.toList())));
        }
        return Collections.singletonList(methods.get(0));
    }

    /**
     * 恰好一个题解方法时使用；否则抛异常。
     */
    public static Method resolveSingleSolutionMethod(Class<?> testClass) {
        List<Method> list = resolveSolutionMethods(testClass);
        if (list.size() != 1) {
            throw new IllegalArgumentException(String.format(
                    "expected exactly one solution method, found %d; use resolveSolutionMethods() for multiple",
                    list.size()));
        }
        return list.get(0);
    }

    private static Method resolveMethodFromClassAnnotation(Class<?> testClass, SolutionTestMethod classAnno) {
        String methodName = classAnno.method();
        if (methodName == null || methodName.isEmpty()) {
            throw new IllegalArgumentException(
                    "@SolutionTestMethod on class requires non-empty method() to select the solution method");
        }
        Class<?>[] paramTypes = classAnno.paramTypes();
        if (paramTypes != null && paramTypes.length > 0) {
            try {
                Method m = testClass.getMethod(methodName, paramTypes);
                if (m.getDeclaringClass() != testClass) {
                    throw new IllegalArgumentException(String.format(
                            "method %s must be declared in %s, not inherited",
                            methodName, testClass.getName()));
                }
                if (Modifier.isStatic(m.getModifiers())) {
                    throw new IllegalArgumentException("solution method must not be static: " + methodName);
                }
                return m;
            } catch (NoSuchMethodException e) {
                throw new IllegalArgumentException(String.format(
                        "no method %s with paramTypes %s on %s",
                        methodName, Arrays.toString(paramTypes), testClass.getName()), e);
            }
        }
        List<Method> sameName = Arrays.stream(testClass.getDeclaredMethods())
                .filter(m -> Modifier.isPublic(m.getModifiers()))
                .filter(m -> !Modifier.isStatic(m.getModifiers()))
                .filter(m -> m.getName().equals(methodName))
                .collect(Collectors.toList());
        if (sameName.size() == 1) {
            return sameName.get(0);
        }
        if (sameName.isEmpty()) {
            throw new IllegalArgumentException(String.format(
                    "no public instance method named %s on %s", methodName, testClass.getName()));
        }
        throw new IllegalArgumentException(String.format(
                "multiple overloads named %s on %s; set paramTypes in @SolutionTestMethod",
                methodName, testClass.getName()));
    }

    /**
     * java.lang.Object 上的 public 实例方法（含签名）；子类中与之同签名的 override（如 toString）应排除，否则会与题解方法并列。
     */
    static boolean isPublicObjectMethodSignature(Method m) {
        try {
            Object.class.getMethod(m.getName(), m.getParameterTypes());
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }
}
