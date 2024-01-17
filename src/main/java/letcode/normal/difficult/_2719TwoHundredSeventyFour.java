package letcode.normal.difficult;

/**
 * 给你两个数字字符串 num1 和 num2 ，以及两个整数 max_sum 和 min_sum 。如果一个整数 x 满足以下条件，我们称它是一个好整数：
 * num1 <= x <= num2 min_sum <= digit_sum(x) <= max_sum. 请你返回好整数的数目。答案可能很大，请返回答案对 109 + 7 取余后的结果。
 * 注意，digit_sum(x) 表示 x 各位数字之和。
 *
 * 提示：
 *
 * 1 <= num1 <= num2 <= 10^22
 * 1 <= min_sum <= max_sum <= 400
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/1/17 17:57
 */
public class _2719TwoHundredSeventyFour {

    public int count(String num1, String num2, int min_sum, int max_sum) {
        /*
        明显 根据数字规模 是无法进行暴力的
        考虑动态规划

        对于 10^i范围(nums1 <= 10^i <= nums2) 而言, 他的各位数字之和是一个集合si
        对于 10^i+1范围(nums1 <= 10^i+1 <= nums2) 而言, 他的各位数字之和可以由si得到
        对 si中的每部分别加上1-9即可得到si+1

        接下来需要考虑问题 边界取值是nums2

         */

    }

}
