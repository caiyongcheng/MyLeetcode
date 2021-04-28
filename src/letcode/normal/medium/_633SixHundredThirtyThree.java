package letcode.normal.medium;

/**
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。
 *
 * @author CaiYongcheng
 * @date 2021-04-28 09:47
 **/
public class _633SixHundredThirtyThree {


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

    /**
     * 示例 1：
     *
     * 输入：c = 5
     * 输出：true
     * 解释：1 * 1 + 2 * 2 = 5
     * 示例 2：
     *
     * 输入：c = 3
     * 输出：false
     * 示例 3：
     *
     * 输入：c = 4
     * 输出：true
     * 示例 4：
     *
     * 输入：c = 2
     * 输出：true
     * 示例 5：
     *
     * 输入：c = 1
     * 输出：true
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sum-of-square-numbers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _633SixHundredThirtyThree().judgeSquareSum(1));
    }

}
