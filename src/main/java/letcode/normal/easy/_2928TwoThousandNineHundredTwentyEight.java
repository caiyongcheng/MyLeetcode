package letcode.normal.easy;

/**
 * 请你将 n 颗糖果分给 3 位小朋友，确保没有任何小朋友得到超过 limit 颗糖果，请你返回满足此条件下的 总方案数 。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-06-03 15:07
 */
public class _2928TwoThousandNineHundredTwentyEight {

    public int distributeCandies(int n, int limit) {
        /*
        基于组合排列的做法
            public int distributeCandies(int n, int limit) {
                return cal(n + 2) - 3 * cal(n - limit + 1) + 3 * cal(n - (limit + 1) * 2 + 2) - cal(n - 3 * (limit + 1) + 2);
            }

            public int cal(int x) {
                if (x < 0) {
                    return 0;
                }
                return x * (x - 1) / 2;
            }
         */
        if (limit * 3 < n) {
            return 0;
        }
        if (limit > n) {
            limit = n;
        }
        int min = Math.max(n - (limit << 1), 0);
        int ans = 0;
        for (int i = limit; i >= min; i--) {
            if (n - i > limit << 1) {
                break;
            }
            if (n - i < limit) {
                ans += n - i + 1;
            } else {
                ans += 1 + limit - (n - i - limit);
            }
        }
        return ans;
    }

    /**
     * 示例 1：
     * <p>
     * 输入：n = 5, limit = 2
     * 输出：3
     * 解释：总共有 3 种方法分配 5 颗糖果，且每位小朋友的糖果数不超过 2 ：(1, 2, 2) ，(2, 1, 2) 和 (2, 2, 1) 。
     * 示例 2：
     * <p>
     * 输入：n = 3, limit = 3
     * 输出：10
     * 解释：总共有 10 种方法分配 3 颗糖果，且每位小朋友的糖果数不超过 3 ：(0, 0, 3) ，(0, 1, 2) ，(0, 2, 1) ，(0, 3, 0) ，(1, 0, 2) ，(1, 1, 1) ，(1, 2, 0) ，(2, 0, 1) ，(2, 1, 0) 和 (3, 0, 0) 。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2928TwoThousandNineHundredTwentyEight().distributeCandies(
                50, 50
        ));
    }

}
