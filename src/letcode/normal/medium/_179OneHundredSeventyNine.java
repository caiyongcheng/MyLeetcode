package letcode.normal.medium;

import java.util.Arrays;

/**
 * @program: Leetcode
 * @description: 给定一组非负整数 nums，重新排列它们每位数字的顺序使之组成一个最大的整数。
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * @author: 蔡永程
 * @create: 2020-10-16 15:34
 */
public class _179OneHundredSeventyNine {


    public static void main(String[] args) {
        System.out.println(new _179OneHundredSeventyNine().largestNumber(new int[]{
                3, 30, 34, 5, 9
        }));
    }

    /**
     * 示例 1：
     * 输入：nums = [10,2]
     * 输出："210"
     * <p>
     * 示例 2：
     * 输入：nums = [3,30,34,5,9]
     * 输出："9534330"
     * <p>
     * 示例 3：
     * 输入：nums = [1]
     * 输出："1"
     * <p>
     * 示例 4：
     * 输入：nums = [10]
     * 输出："10"
     *
     * @param nums
     * @return
     */
    public String largestNumber(int[] nums) {
        Integer[] nums1 = new Integer[nums.length];
        for (int index = 0; index < nums.length; index++) {
            nums1[index] = nums[index];
        }
        Arrays.sort(nums1, (o1, o2) -> {
            if (o1.intValue() == o2.intValue()) {
                return 0;
            }
            String astr = o1 + "";
            String bstr = o2 + "";
            int index = 0;
            int minLength = Math.min(astr.length(), bstr.length());
            while (index < minLength) {
                if (astr.charAt(index) > bstr.charAt(index)) {
                    return -1;
                } else if (astr.charAt(index) < bstr.charAt(index)) {
                    return 1;
                }
                ++index;
            }
            return index >= astr.length() ? -1 : 1;
        });
        final StringBuilder stringBuilder = new StringBuilder();
        for (Integer integer : nums1) {
            stringBuilder.append(integer);
        }
        return stringBuilder.toString();
    }
}