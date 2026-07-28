package normal.difficult;

/**
 * @program: Leetcode
 * @description: 给定一个正整数 N，试求有多少组连续正整数满足所有数字之和为 N?
 * @author: 蔡永程
 * @create: 2020-11-20 22:39
 */
public class _829 {

    public int consecutiveNumbersSum(int N) {
        int count = 0;
        N *= 2;
        for (int x = 1; x * x < N; ++x) {
            if (N % x == 0) {
                int bx = (N / x - x + 1);
                if (bx % 2 == 0 && bx > 1) {
                    ++count;
                }
            }
        }
        return count;
    }

}
