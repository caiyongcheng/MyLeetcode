/*
 * 版权所有（c）<2021><蔡永程>
 *
 * 反996许可证版本1.0
 *
 * 在符合下列条件的情况下，特此免费向任何得到本授权作品的副本（包括源代码、文件和/或相关内容，以
 * 下统称为“授权作品”）的个人和法人实体授权：被授权个人或法人实体有权以任何目的处置授权作品，包括
 * 但不限于使用、复制，修改，衍生利用、散布，发布和再许可：
 *
 * 1. 个人或法人实体必须在许可作品的每个再散布或衍生副本上包含以上版权声明和本许可证，不得自行修
 * 改。
 * 2. 个人或法人实体必须严格遵守与个人实际所在地或个人出生地或归化地、或法人实体注册地或经营地（
 * 以较严格者为准）的司法管辖区所有适用的与劳动和就业相关法律、法规、规则和标准。如果该司法管辖区
 * 没有此类法律、法规、规章和标准或其法律、法规、规章和标准不可执行，则个人或法人实体必须遵守国际
 * 劳工标准的核心公约。
 * 3. 个人或法人不得以任何方式诱导、暗示或强迫其全职或兼职员工或其独立承包人以口头或书面形式同意
 * 直接或间接限制、削弱或放弃其所拥有的，受相关与劳动和就业有关的法律、法规、规则和标准保护的权利
 * 或补救措施，无论该等书面或口头协议是否被该司法管辖区的法律所承认，该等个人或法人实体也不得以任
 * 何方法限制其雇员或独立承包人向版权持有人或监督许可证合规情况的有关当局报告或投诉上述违反许可证
 * 的行为的权利。
 *
 * 该授权作品是"按原样"提供，不做任何明示或暗示的保证，包括但不限于对适销性、特定用途适用性和非侵
 * 权性的保证。在任何情况下，无论是在合同诉讼、侵权诉讼或其他诉讼中，版权持有人均不承担因本软件或
 * 本软件的使用或其他交易而产生、引起或与之相关的任何索赔、损害或其他责任。
 */

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
     * 链接：https:leetcode-cn.com/problems/super-ugly-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _313().nthSuperUglyNumber(
                12, new int[]{2,7,13,19}
        ));
    }

}
