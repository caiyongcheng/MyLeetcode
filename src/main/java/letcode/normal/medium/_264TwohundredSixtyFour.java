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

import java.util.*;

/**
 * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。  丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 *
 * @author CaiYongcheng
 * @date 2021-08-09 09:08
 **/
public class _264TwohundredSixtyFour {

    public int nthUglyNumber(int n) {
        //最小堆
        int[] factors = {2, 3, 5};
        Set<Long> seen = new HashSet<Long>();
        PriorityQueue<Long> heap = new PriorityQueue<Long>();
        seen.add(1L);
        heap.offer(1L);
        int ugly = 0;
        for (int i = 0; i < n; i++) {
            long curr = heap.poll();
            ugly = (int) curr;
            for (int factor : factors) {
                long next = curr * factor;
                if (seen.add(next)) {
                    heap.offer(next);
                }
            }
        }
        return ugly;
    }

    public int nthUglyNumberForDP(int n) {
        /*
         * dp
         * 假设 dp[i]表示第i个丑数, b[i]表示第i个质因数
         * 那么 dp[i] = Math.min(dp[a]*2, dp[b]*3, dp[c]*5...)
         * a 表示 dp[a...j] * 2 时候大于 dp[i-1] 的下标
         * 按这样的关系，就可找到dp[i]，之后将对应的 dp[x] * bx进行x+1。其余的不变。
         * 则其余的依旧满足条件。
         */
        long[] uglyNumbers = new long[n+1];
        long[] factor = new long[]{2,3,5};
        long[] preVal = new long[]{2, 3, 5};
        int[] preIndex = new int[]{1, 1, 1};
        HashSet<Long> uglyNumberSet = new HashSet(Collections.singletonList(factor));
        int changeIndex;
        uglyNumbers[1] = 1;
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
                preVal[changeIndex] = uglyNumbers[preIndex[changeIndex]] * factor[changeIndex];
            } while (uglyNumberSet.contains(preVal[changeIndex]));
            uglyNumberSet.add(preVal[changeIndex]);
        }
        return (int) uglyNumbers[n];
    }

    /**
     * 示例 1：
     * 输入：n = 10
     * 输出：12
     * 解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
     *
     * 示例 2：
     * 输入：n = 1
     * 输出：1
     * 解释：1 通常被视为丑数。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/ugly-number-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _264TwohundredSixtyFour().nthUglyNumberForDP(1690));
    }


}
