package letcode.interview.medium;

import letcode.utils.FormatPrintUtils;

/**
 * @program: MyLeetCode
 * @description: 编写一个函数，不用临时变量，直接交换numbers = [a, b]中a与b的值
 * @author: 蔡永程
 * @create: 2021-02-04 14:49
 */
public class _16_1_Sixteen_One {


    public int[] swapNumbers(int[] numbers) {
        numbers[0] ^= numbers[1];
        numbers[1] ^= numbers[0];
        numbers[0] ^= numbers[1];

        return numbers;
    }

    /**
     * 输入: numbers = [1,2]
     * 输出: [2,1]
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(FormatPrintUtils.formatArray(new _16_1_Sixteen_One().swapNumbers(new int[]{1,2})));
    }

}