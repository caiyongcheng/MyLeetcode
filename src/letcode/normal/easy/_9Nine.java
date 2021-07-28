package letcode.easy;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * @program: StudyHTTP
 * @description: 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * @author: 蔡永程
 * @create: 2020-06-17 22:32
 */
public class _9Nine {
    /**
     * 示例 1:
     * <p>
     * 输入: 121
     * 输出: true
     * 示例2:
     * <p>
     * 输入: -121
     * 输出: false
     * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
     * 示例 3:
     * <p>
     * 输入: 10
     * 输出: false
     * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/palindrome-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static boolean isPalindrome(int x) {
        if (x < 0 || x > 9 && x % 10 == 0) {
            return false;
        }
        int y = x;
        int z = 0;
        while (y > 0) {
            z *= 10;
            z += y % 10;
            y /= 10;
        }
        return x == z;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(0));
        System.out.println(-0);
    }

}