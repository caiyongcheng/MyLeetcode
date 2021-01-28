package letcode.easy;

import java.util.Arrays;

/**
 * StudyHTTP
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/plus-one
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : CaiYongcheng
 * @date : 2020-06-25 14:13
 **/
public class _66Sixtysix {

    /**
     * 示例 1:
     * 输入: [1,2,3]
     * 输出: [1,2,4]
     * 解释: 输入数组表示数字 123。
     * <p>
     * 示例 2:
     * 输入: [4,3,2,1]
     * 输出: [4,3,2,2]
     * 解释: 输入数组表示数字 4321。
     *
     * @param digits
     * @return
     */
    public static int[] plusOne(int[] digits) {
        int n = digits.length - 1;
        ++digits[n];
        while (n > 0) {
            if (digits[n] > 9) {
                digits[n] = digits[n] % 10;
                ++digits[n - 1];
            }
            --n;
        }
        if (digits[0] > 9) {
            n = digits.length + 1;
            int[] ints = new int[n];
            ints[0] = 1;
            ints[1] = digits[0] % 10;
            for (int i = 2; i < n; ++i) {
                ints[i] = digits[i - 1];
            }
            return ints;
        }
        return digits;
    }

    public static void main(String[] args) {
        int[] ints = {9, 9, 9};
        System.out.println(Arrays.toString(plusOne(ints)));
    }

}
