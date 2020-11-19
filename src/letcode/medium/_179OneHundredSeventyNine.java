package medium;

/**
 * @program: Leetcode
 * @description: 给定一组非负整数 nums，重新排列它们每位数字的顺序使之组成一个最大的整数。  注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * @author: 蔡永程
 * @create: 2020-10-16 15:34
 */
public class _179OneHundredSeventyNine {


    private int[] numChars = new int[10];

    private void parseNUm(int num) {
        if (num == 0) {
            numChars[0]++;
        }
        while (num > 0) {
            numChars[num%10]++;
            num/=10;
        }
    }

    /**
     * 示例 1：
     * 输入：nums = [10,2]
     * 输出："210"
     *
     * 示例 2：
     * 输入：nums = [3,30,34,5,9]
     * 输出："9534330"
     *
     * 示例 3：
     * 输入：nums = [1]
     * 输出："1"
     *
     * 示例 4：
     * 输入：nums = [10]
     * 输出："10"
     * @param nums
     * @return
     */
    public String largestNumber(int[] nums) {
        for (int num : nums) {
            parseNUm(num);
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i=9; i>-1; --i) {
            for (int j=0; j<numChars[i]; ++j){
                stringBuffer.append(i);
            }
        }
        if (stringBuffer.length() == 0 || stringBuffer.charAt(0) == '0') {
            return "0";
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(new _179OneHundredSeventyNine().largestNumber(new int[]{
                3,30,34,5,9
        }));
    }
}