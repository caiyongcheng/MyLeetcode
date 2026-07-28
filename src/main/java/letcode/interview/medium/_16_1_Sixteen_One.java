package letcode.interview.medium;

import letcode.utils.TestCaseOutputUtils;

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

}
