package letcode.normal.medium;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/**
 * @program: MyLeetcode
 * @description: 编写一段程序来查找第 n 个超级丑数。  
 * 超级丑数是指其所有质因数都是长度为 k 的质数列表 primes 中的正整数。
 * 是任何给定primes的超级丑数。 
 * 给定primes中的数字以升序排列。
 * 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000 。
 * 第n个超级丑数确保在 32 位有符整数范围内。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/super-ugly-number 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @date: 2021-03-15 10:52
 **/
public class _313ThreeHundredThirteen {


    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] uglyNumbers = new int[n+1];
        int[] preVal = Arrays.copyOf(primes, primes.length);
        int[] preIndex = new int[n];
        HashSet<Integer> uglyNumberSet = new HashSet();
        int changeIndex;
        uglyNumbers[1] = 1;
        uglyNumberSet.add(1);
        Arrays.fill(preIndex, 1);
        for (int i = 2; i <= n; i++) {
            changeIndex = 0;
            for (int j = 1; j < preVal.length; j++) {
                if (preVal[j] < preVal[changeIndex]) {
                    changeIndex = j;
                }
            }
            uglyNumbers[i] = preVal[changeIndex];
            uglyNumberSet.remove(uglyNumbers[i]);
            do {
                ++preIndex[changeIndex];
                preVal[changeIndex] = uglyNumbers[preIndex[changeIndex]] * primes[changeIndex];
            } while (uglyNumberSet.contains(preVal[changeIndex]));
            uglyNumberSet.add(preVal[changeIndex]);
        }
        return uglyNumbers[n];
    }

    /**
     * 示例 1：
     * 输入：n = 12, primes = [2,7,13,19]
     * 输出：32
     * 解释：给定长度为 4 的质数数组 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。
     *
     * 示例 2：
     * 输入：n = 1, primes = [2,3,5]
     * 输出：1
     * 解释：1 不含质因数，因此它的所有质因数都在质数数组 primes = [2,3,5] 中。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/super-ugly-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _313ThreeHundredThirteen().nthSuperUglyNumber(
                12, new int[]{2,7,13,19}
        ));
    }

}
