package letcode.normal.medium;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @program: MyLeetcode
 * @description: 编写一段程序来查找第 n 个超级丑数。  
 * 超级丑数是指其所有质因数都是长度为 k 的质数列表 primes 中的正整数。
 * 是任何给定primes的超级丑数。 
 * 给定primes中的数字以升序排列。
 * 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000 。
 * 第n个超级丑数确保在 32 位有符整数范围内。  
 * 来源：力扣（LeetCode） 链接：https:leetcode-cn.com/problems/super-ugly-number 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @since: 2021-03-15 10:52
 **/
public class _313 {

    /**
     * Note also that the biased state contains the age bits normally
     *     contained in the object header. Large increases in scavenge
     *     times were seen when these bits were absent and an arbitrary age
     *     assigned to all biased objects, because they tended to consume a
     *     significant fraction of the eden semispaces and were not
     *     promoted promptly, causing an increase in the amount of copying
     *     performed. The runtime system aligns all JavaThread* pointers to
     *     a very large value (currently 128 bytes (32bVM) or 256 bytes (64bVM))
     *     to make room for the age bits & the epoch bits (used in support of
     *     biased locking), and for the CMS "freeness" bit in the 64bVM (+COOPs).
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] uglyNumbers = new int[n + 1];
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

}
