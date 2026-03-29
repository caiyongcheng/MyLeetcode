package letcode.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 同名 public 方法重载时 {@link TestUtil#operation} 应能按参数个数解析。
 */
class TestUtilOperationTest {

    public static class OverloadStub {
        public int find(int x) {
            return x;
        }

        public int find(int x, int y) {
            return x + y;
        }
    }

    @Test
    void operation_resolvesOverloadByArity() {
        OverloadStub obj = new OverloadStub();
        String r = TestUtil.operation(obj, "[\"find\",\"find\"]", "[[1],[1,2]]");
        assertTrue(r.contains("1"));
        assertTrue(r.contains("3"));
    }
}
