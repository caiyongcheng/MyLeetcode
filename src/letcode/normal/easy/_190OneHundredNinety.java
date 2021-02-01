package letcode.normal.easy;


/**
 * @program: MyLeetCode
 * @description: 颠倒给定的 32 位无符号整数的二进制位。
 * @author: 蔡永程
 * @create: 2021-02-01 09:18
 */
public class _190OneHundredNinety {

    public int reverseBits(int n) {
        int ans = 0;
        for (int i = 31; i >= 0; i--) {
            ans += n << i >>> 31 << i;
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(new _190OneHundredNinety().reverseBits(Long.valueOf("11111111111111111111111111111101",2).byteValue()));
    }

}