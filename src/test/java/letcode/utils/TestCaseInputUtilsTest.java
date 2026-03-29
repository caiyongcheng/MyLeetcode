package letcode.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestCaseInputUtilsTest {

    @Test
    void getLongArr_parsesLargeValues() {
        assertArrayEquals(new long[]{1L, 2L, 3_000_000_000L}, TestCaseInputUtils.getLongArr("[1,2,3000000000]"));
    }

    @Test
    void resolveParameter_longPrimitive() {
        assertEquals(3_000_000_000L, TestCaseInputUtils.resolveParameter("long", "3000000000"));
    }

    @Test
    void resolveParameter_longArray() {
        long[] expected = {1L, 2L};
        assertArrayEquals(expected, (long[]) TestCaseInputUtils.resolveParameter("long[]", "[1,2]"));
    }
}
