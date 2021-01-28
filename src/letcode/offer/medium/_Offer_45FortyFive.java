package offer.medium;

import java.util.Arrays;

/**
 * @program: Leetcode
 * @description: 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * @author: 蔡永程
 * @create: 2021-01-11 15:33
 */
public class _Offer_45FortyFive {


    /**
     * 示例 1:
     * 输入: [10,2]
     * 输出: "102"
     * 示例 2:
     * 输入: [3,30,34,5,9]
     * 输出: "3033459"
     *
     * @param args
     */
    public static void main(String[] args) {

    }

    public int compare(String x, String y) {
        final char xc = x.charAt(0);
        final char yc = y.charAt(0);
        if (xc == yc) {
            return (x + y).compareTo((y + x));
        }
        if (xc < yc) {
            return -1;
        }
        return 0;
    }

    public String minNumber(int[] nums) {
        String[] strings = new String[nums.length];
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            strings[i] = nums[i] + "";
        }
        Arrays.sort(strings, this::compare);
        for (String string : strings) {
            stringBuilder.append(string);
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.print(strings[i] + " ");
        }
        return stringBuilder.toString();
    }


}