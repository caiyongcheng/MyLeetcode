package letcode.normal.medium;

/**
 * 给你一个整数 n ，以二进制字符串的形式返回该整数的 负二进制（base -2）表示。  注意，除非字符串就是 "0"，否则返回的字符串中不能含有前导零。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/4/28 10:58
 */
public class _1017 {

    public String baseNeg2(int n) {
        /*
        从0开始，
        奇数位i+1表示为-2^(i)
        偶数位i表示为2^(i+1)
        所以(2^i) 表示为 2^(i+1) - (2^i)
        对于二进制表示
            如果是 2^i i是奇数（从0开始），那么表示为负二进制的 2^(i+1) - (2^i) 11
            如果是 2^i i是偶数（从0开始），那么表示为负二进制的 2^(i)
         */

        /**
         *         进制转化的思想依然适用
         *         if (n == 0 || n == 1) {
         *             return String.valueOf(n);
         *         }
         *         StringBuilder res = new StringBuilder();
         *         while (n != 0) {
         *             int remainder = n & 1;
         *             res.append(remainder);
         *             n -= remainder;
         *             n /= -2;
         *         }
         *         return res.reverse().toString();
         */

        if (n == 0) {
            return "0";
        }

        int[] rstArr = new int[32];
        boolean flag = true;
        int idx = 0;
        do {
            if ((n & 1) == 1) {
                rstArr[idx]++;
                if (!flag) {
                    rstArr[idx + 1]++;
                }
            }
            flag = !flag;
            ++idx;
            n = n >> 1;
        } while (n > 0);

        int minSub;
        for (int i = 0; i < rstArr.length - 2; i++) {
            if (rstArr[i] > 1) {
                minSub = Integer.min(rstArr[i] >> 1, rstArr[i + 1]);
                rstArr[i] -= minSub << 1;
                rstArr[i+1] -= minSub;
                if (rstArr[i] > 1) {
                    minSub = rstArr[i] >> 1;
                    rstArr[i+1] += minSub;
                    rstArr[i+2] += minSub;
                    rstArr[i] -= minSub << 1;
                }
            }
        }


        StringBuilder sb = new StringBuilder();
        for (int i = rstArr.length - 1; i >= 0; i--) {
            if (rstArr[i] != 0) {
                while (i >= 0) {
                    sb.append(rstArr[i]);
                    --i;
                }
                break;
            }
        }
        return sb.toString();
    }

    /**
     * 示例 1：
     *
     * 输入：n = 2
     * 输出："110"
     * 解释：(-2)2 + (-2)1 = 2
     * 示例 2：
     *
     * 输入：n = 3
     * 输出："111"
     * 解释：(-2)2 + (-2)1 + (-2)0 = 3
     * 示例 3：
     *
     * 输入：n = 4
     * 输出："100"
     * 解释：(-2)2 = 4
     *
     * 8 + 4 + 2
     * 1110
     * 00000 + 00110 + 00100 + 11000 => 11210 =>
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1017().baseNeg2(14));
    }






}
