package letcode.normal.easy;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/10/26 9:01
 * description 给你一个整数 num ，返回 num 中能整除 num 的数位的数目。  如果满足 nums % val == 0 ，则认为整数 val 可以整除 nums 。
 */
public class _2520 {

    public int countDigits(int num) {
        int ans = 0;
        int nu = num;
        while (num > 0) {
            if (nu % (num % 10) == 0) {
                ++ans;
            }
            num /= 10;
        }
        return ans;
    }

}
