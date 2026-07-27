package letcode.normal.medium;

/**
 * 43. Multiply Strings
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/multiply-strings/
 * <p>
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and
 * num2 , also represented as a string.
 * <p>
 * Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.
 * <p>
 * Example 1:
 * <p>
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 * <p>
 * Example 2:
 * <p>
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 * <p>
 * Constraints:
 * <p>
 * - 1 <= num1.length, num2.length <= 200
 * <p>
 * - num1 and num2 consist of digits only.
 * <p>
 * - Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 */
public class _43 {

    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        char[] cArr1 = num1.toCharArray();
        char[] cArr2 = num2.toCharArray();
        int[] resultArr = new int[cArr1.length + cArr2.length];

        for (int i = cArr1.length - 1; i >= 0; i--) {
            for (int j = cArr2.length - 1; j >= 0; j--) {
                resultArr[cArr1.length - i + cArr2.length - j - 2] += (cArr1[i] - '0') * (cArr2[j] - '0');
            }
        }

        for (int i = 0; i < resultArr.length - 1; i++) {
            resultArr[i + 1] += resultArr[i] / 10;
            resultArr[i] %= 10;
        }

        StringBuilder sb = new StringBuilder();
        int digitIdx = resultArr.length - 1;
        while (digitIdx >= 0 && resultArr[digitIdx] == 0) {
            --digitIdx;
        }
        while (digitIdx >= 0) {
            sb.append(resultArr[digitIdx--]);
        }
        return sb.toString();

    }
}
