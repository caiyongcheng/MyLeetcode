package letcode.normal.easy;

/**
 * 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。  给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
 *
 * @author CaiYongcheng
 * @since 2021-06-29 14:40
 **/
public class _461 {



    public int hammingDistance(int x, int y) {
        int xor = x ^ y;
        int ans = 0;
        while (xor > 0) {
            if ((xor & 1) == 1) {
                ans++;
            }
            xor >>>= 1;
        }
        return ans;
    }

}
