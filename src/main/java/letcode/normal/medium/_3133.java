package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * 给你两个整数 n 和 x 。你需要构造一个长度为 n 的 正整数 数组 nums ，对于所有 0 <= i < n - 1 ，
 * 满足 nums[i + 1] 大于 nums[i] ，并且数组 nums 中所有元素的按位 AND 运算结果为 x 。
 * 返回 nums[n - 1] 可能的 最小 值。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-08-22 10:23
 */
public class _3133 {

    public long minEnd(int n, int x) {
        /*
        and结果为x，数组内每个元素的二进制位置必须与x相等。
        然后就是把空出来的0位置对应换成1。同时保证剩余位置上，至少要存在一个0。

        更好的写法
        // 这里意思是 (64 - Long.numberOfLeadingZeros(n)) + (64 - Long.numberOfLeadingZeros(x))
        // 也就是总的，不算高位前导0二进制长度
        int bitCount = 128 - Long.numberOfLeadingZeros(n) - Long.numberOfLeadingZeros(x);
        long res = x;
        long m = n - 1;
        int j = 0;
        for (int i = 0; i < bitCount; ++i) {
            // 为0的地方需要替换
            if (((res >> i) & 1) == 0) {
                // 这一位也是0 就不用处理了
                if (((m >> j) & 1) == 1) {
                    res |= (1L << i);
                }
                j++;
            }
        }
        return res;
         */
        StringBuilder binStr = new StringBuilder(Integer.toBinaryString(x));
        String addBinStr = Integer.toBinaryString(n - 1);
        int i = addBinStr.length() - 1;
        int binStrLen = binStr.length();
        for (int j = binStrLen - 1; j > 0; j--) {
            if (binStr.charAt(j) == '0') {
                binStr.setCharAt(j, addBinStr.charAt(i));
                --i;
            }
            if (i < 0) {
                break;
            }
        }
        if (i > -1) {
            binStr.insert(0, addBinStr.substring(0, i + 1));
        }
        return Long.parseLong(binStr.toString(), 2);
    }

    /**
     * Example 1:
     *
     * Input: n = 3, x = 4
     *
     * Output: 6
     *
     * Explanation:
     *
     * nums can be [4,5,6] and its last element is 6.
     *
     * Example 2:
     *
     * Input: n = 2, x = 7
     *
     * Output: 15
     *
     * Explanation:
     *
     * nums can be [7,15] and its last element is 15.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test(_3133.class);
    }

}
