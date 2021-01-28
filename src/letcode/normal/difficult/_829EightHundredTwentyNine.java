package normal.difficult;

/**
 * @program: Leetcode
 * @description: 给定一个正整数 N，试求有多少组连续正整数满足所有数字之和为 N?
 * @author: 蔡永程
 * @create: 2020-11-20 22:39
 */
public class _829EightHundredTwentyNine {

    /**
     * 示例 1:
     * 输入: 5
     * 输出: 2
     * 解释: 5 = 5 = 2 + 3，共有两组连续整数([5],[2,3])求和后为 5。
     * <p>
     * 示例 2:
     * 输入: 9
     * 输出: 3
     * 解释: 9 = 9 = 4 + 5 = 2 + 3 + 4
     * <p>
     * 示例 3:
     * 输入: 15
     * 输出: 4
     * 解释: 15 = 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/consecutive-numbers-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _829EightHundredTwentyNine().consecutiveNumbersSum(2));
    }

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