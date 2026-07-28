package letcode.normal.easy;

import letcode.utils.TestCaseOutputUtils;

/**
 * @program: MyLeetcode
 * @description: 「无零整数」是十进制表示中 不含任何 0的正整数。
 * 给你一个整数n，请你返回一个 由两个整数组成的列表 [A, B]，满足：
 * A 和 B都是无零整数 A + B = n 题目数据保证至少有一个有效的解决方案。
 * 如果存在多个有效解决方案，你可以返回其中任意一个。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/convert-integer-to-the-sum-of-two-no-zero-integers 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.easy
 * @author: 6JSh5rC456iL
 * @since: 2021-03-03 17:21
 **/
public class _1317 {


    public int[] getNoZeroIntegers(int n) {
        int[] ans = new int[2];
        int exp = 1;
        int modolNum = 0;
        while (n > 9) {
            modolNum = n % 10;
            if (modolNum < 2) {
                modolNum += 10;
                n -= 10;
                ans[0] += 2 * exp;
                ans[1] += (modolNum-2) * exp;
            }else {
                ans[0] += exp;
                ans[1] += (modolNum-1) * exp;
            }
            exp *= 10;
            n /= 10;
        }
        if (n > 0) {
            ans[0] += exp;
            ans[1] += (n-1) * exp;
        }
        return ans;
    }

}
