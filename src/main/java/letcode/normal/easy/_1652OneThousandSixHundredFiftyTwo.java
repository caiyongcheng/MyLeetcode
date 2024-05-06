package letcode.normal.easy;

import letcode.utils.FormatUtils;
import letcode.utils.TestCaseUtils;

/**
 * 你有一个炸弹需要拆除，时间紧迫！你的情报员会给你一个长度为 n 的 循环 数组 code 以及一个密钥 k 。
 * 为了获得正确的密码，你需要替换掉每一个数字。所有数字会 同时 被替换。  如果 k > 0 ，将第 i 个数字用 接下来 k 个数字之和替换。
 * 如果 k < 0 ，将第 i 个数字用 之前 k 个数字之和替换。 如果 k == 0 ，将第 i 个数字用 0 替换。 由于 code 是循环的，
 * code[n-1] 下一个元素是 code[0] ，且 code[0] 前一个元素是 code[n-1] 。  给你 循环 数组 code 和整数密钥 k ，请你返回解密后的结果来拆除炸弹！
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/5/6 09:02
 */
public class _1652OneThousandSixHundredFiftyTwo {

    public int[] decrypt(int[] code, int k) {

        int[] ans = new int[code.length];

        if (k == 0) {
            return ans;
        }

        int rangeSum = 0;
        int idxVar = -1;
        int len = k;
        if (k < 0) {
            len = -k;
            idxVar = -k;
        }

        for (int i = 0; i < len; i++) {
            rangeSum += code[i];
        }

        for (int i = 0; i < code.length; i++) {
            ans[(i + idxVar + ans.length) % ans.length] = rangeSum;
            rangeSum = rangeSum - code[i] + code[(i + ans.length + len) % ans.length];
        }

        return ans;



    }

    /**
     * 示例 1：
     *
     * 输入：code = [5,7,1,4], k = 3
     * 输出：[12,10,16,13]
     * 解释：每个数字都被接下来 3 个数字之和替换。解密后的密码为 [7+1+4, 1+4+5, 4+5+7, 5+7+1]。注意到数组是循环连接的。
     * 示例 2：
     * <p>
     * 输入：code = [1,2,3,4], k = 0
     * 输出：[0,0,0,0]
     * 解释：当 k 为 0 时，所有数字都被 0 替换。
     * 示例 3：
     * <p>
     * 输入：code = [2,4,9,3], k = -2
     * 输出：[12,5,6,13]
     * 解释：解密后的密码为 [3+9, 2+3, 4+2, 9+4] 。注意到数组是循环连接的。如果 k 是负数，那么和为 之前 的数字。
     * <p>
     * [10,5,7,7,3,2,10,3,6,9,1,6]
     * -4
     */
    public static void main(String[] args) {
        System.out.println(FormatUtils.formatArray(new _1652OneThousandSixHundredFiftyTwo().decrypt(
                TestCaseUtils.getIntArr("[2,4,9,3]"),
                -2
        )));
    }

}
