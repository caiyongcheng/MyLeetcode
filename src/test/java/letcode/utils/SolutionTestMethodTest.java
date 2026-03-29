package letcode.utils;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SolutionTestMethodTest {

    public static class AnnotatedMultiPublicStub {
        @SolutionTestMethod
        public int solve(int x) {
            return x;
        }

        public void helper() {
        }
    }

    public static class DoubleAnnotatedStub {
        @SolutionTestMethod
        public int a() {
            return 1;
        }

        @SolutionTestMethod
        public int b() {
            return 2;
        }
    }

    @Test
    void executor_usesAnnotatedMethod_whenMultiplePublic() throws Exception {
        TestUtil.TestCaseExecutor<AnnotatedMultiPublicStub> ex =
                new TestUtil.TestCaseExecutor<>(
                        AnnotatedMultiPublicStub.class,
                        "Input: x = 1\nOutput: 1"
                );
        Method m = ex.getTestMethodFromClass(AnnotatedMultiPublicStub.class);
        assertEquals("solve", m.getName());
    }

    @Test
    void executor_rejects_twoAnnotatedMethods() {
        assertThrows(IllegalArgumentException.class, () ->
                new TestUtil.TestCaseExecutor<>(
                        DoubleAnnotatedStub.class,
                        "Input: x = 1\nOutput: 1"
                )
        );
    }
}
