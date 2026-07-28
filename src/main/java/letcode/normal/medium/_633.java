package letcode.normal.medium;

/**
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。
 *
 * @author CaiYongcheng
 * @since 2021-04-28 09:47
 **/
public class _633 {


    public boolean judgeSquareSum(int c) {
        int limit = (int) (Math.sqrt(c) + 1);
        int k;
        for (int i = 0; i < limit; i++) {
            k = (int) Math.sqrt(c - i * i);
            if (k*k + i*i == c) {
                return true;
            }
        }
        return false;
    }

}
