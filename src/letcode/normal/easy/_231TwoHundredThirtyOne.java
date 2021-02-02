package letcode.normal.easy;

/**
 * @program: MyLeetCode
 * @description: 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
 * @author: 蔡永程
 * @create: 2021-02-02 09:18
 */
public class _231TwoHundredThirtyOne {

    public boolean isPowerOfTwo(int n) {
        int px = 1;
        while (px <= n && px > 0) {
            if (n == px) {
                return true;
            }
            px = px << 1;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new _231TwoHundredThirtyOne().isPowerOfTwo(Integer.MAX_VALUE));
    }

}