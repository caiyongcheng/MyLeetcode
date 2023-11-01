package letcode.normal.medium;

import java.util.Arrays;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/10/25 9:01
 * description n 的 惩罚数 定义为所有满足以下条件 i 的数的平方和：  1 <= i <= n
 * i * i 的十进制表示的字符串可以分割成若干连续子字符串，且这些子字符串对应的整数值之和等于 i 。
 */
public class _2698TwoThousandSixHundredNinetyEight {

    private static int curNum = 0;

    private static int[] ansArr = new int[1001];

    private static int[] table = new int[]{1,9,10,36,45,55,82,91,99,100,235,297,369,370,379,414,657,675,703,756,792,909,918,945,964,990,991,999,1000};
    private static int[] tableSum = new int[]{1, 82, 182, 1478, 3503, 6528, 13252, 21533, 31334, 41334, 96559, 184768, 320929, 457829, 601470, 772866, 1204515, 1660140, 2154349, 2725885, 3353149, 4179430, 5022154, 5915179, 6844475, 7824575, 8806656, 9804657, 10804657};

    static {
        ansArr[1] = 1;
    }

    public int punishmentNumber2(int n) {
        for (int i = 0; i < table.length; i++) {
            if (n < table[i]) {
                return tableSum[i-1];
            }
        }
        return tableSum[tableSum.length - 1];
    }

    public int punishmentNumber(int n) {
        // 我以为我够狠了 没想到那些人直接打表
        // i的平方和任意切割除9的余数 和 平方和的余数 相等 还要和i除9的余数相等
        if (n <= curNum) {
            return ansArr[n];
        }
        int pow;
        for (int i = curNum + 1; i <= n; ++i) {
            pow = i * i;
            if (pow % 9 == i % 9 && find(i, pow, getNumLen(pow))) {
                ansArr[i] = ansArr[i-1] + i * i;
            } else {
                ansArr[i] = ansArr[i-1];
            }
        }
        return ansArr[n];
    }


    public boolean find(int target, int residue, int length) {
        if (length == 1) {
            return target - residue == 0;
        }
        if (target - residue == 0) {
            return true;
        }
        int limit = 10;
        int mod;
        while (limit < residue) {
            --length;
            mod = residue % limit;
            if (mod > target) {
                break;
            }
            if (find(target - mod, residue / limit, length)) {
                return true;
            }
            limit *= 10;
        }
        return false;
    }

    public int getNumLen(int num) {
        if (num < 10) {
            return 1;
        }
        if (num < 100) {
            return 2;
        }
        if (num < 1000) {
            return 3;
        }
        if (num < 10000) {
            return 4;
        }
        if (num < 100000) {
            return 5;
        }
        if (num < 1000000) {
            return 6;
        }
        return 0;
    }

    /**
     * 示例 1：
     *
     * 输入：n = 10
     * 输出：182
     * 解释：总共有 3 个整数 i 满足要求：
     * - 1 ，因为 1 * 1 = 1
     * - 9 ，因为 9 * 9 = 81 ，且 81 可以分割成 8 + 1 。
     * - 10 ，因为 10 * 10 = 100 ，且 100 可以分割成 10 + 0 。
     * 因此，10 的惩罚数为 1 + 81 + 100 = 182
     * 示例 2：
     *
     * 输入：n = 37
     * 输出：1478
     * 解释：总共有 4 个整数 i 满足要求：
     * - 1 ，因为 1 * 1 = 1
     * - 9 ，因为 9 * 9 = 81 ，且 81 可以分割成 8 + 1 。
     * - 10 ，因为 10 * 10 = 100 ，且 100 可以分割成 10 + 0 。
     * - 36 ，因为 36 * 36 = 1296 ，且 1296 可以分割成 1 + 29 + 6 。
     * 因此，37 的惩罚数为 1 + 81 + 100 + 1296 = 1478
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2698TwoThousandSixHundredNinetyEight().punishmentNumber2(10));
    }


}
