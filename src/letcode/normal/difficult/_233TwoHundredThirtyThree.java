package letcode.normal.difficult;

/**
 * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
 *
 * @author CaiYongcheng
 * @date 2021-08-13 09:03
 **/
public class _233TwoHundredThirtyThree {

    public int countDigitOne(int n) {
        /*
         * 假设n是p位数 最高位的count（1） = min(10^(p-1), n-10^(p-1)+1)
         * 次高位 （1） = n/10^(p-1) * 10^(p-2) + min(10^(p-2), n%10^(p-1)-10^(p-2)+1)
         * 17
         * 17 / 10 * 1 + min*(1, 17 % 10 - 1 + 1) = 2
         * 17 / 100
         * 再次高位 abcdef
         */
        int ans = 0;
        int scale = 1;
        while (scale <= n) {
            ans += n / (scale * 10) * scale + Math.max(0, Math.min(scale, n % (scale * 10) - scale + 1));
            scale *= 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new _233TwoHundredThirtyThree().countDigitOne(101));
    }

}
