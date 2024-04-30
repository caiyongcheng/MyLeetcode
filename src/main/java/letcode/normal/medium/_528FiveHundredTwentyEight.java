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

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * 给定一个正整数数组w ，其中w[i]代表下标 i的权重（下标从 0 开始），请写一个函数pickIndex，它可以随机地获取下标 i，选取下标 i的概率与w[i]成正比。
 * 例如，对于 w = [1, 3]，挑选下标 0 的概率为 1 / (1 + 3)= 0.25 （即，25%），而选取下标 1 的概率为 3 / (1 + 3)= 0.75（即，75%）。
 * 也就是说，选取下标 i 的概率为 w[i] / sum(w) 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/random-pick-with-weight 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author CaiYongcheng
 * @date 2021-08-30 09:06
 **/
public class _528FiveHundredTwentyEight {

    BigDecimal[] probabilities;
    int seem;

    public _528FiveHundredTwentyEight(int[] w) {
        seem = Arrays.stream(w).sum();
        probabilities = new BigDecimal[w.length];
        probabilities[0] = new BigDecimal(w[0]).divide(new BigDecimal(seem), 10, BigDecimal.ROUND_HALF_DOWN);
        for (int i = 1; i < w.length; i++) {
            probabilities[i] = new BigDecimal(w[i]).divide(new BigDecimal(seem), 10, BigDecimal.ROUND_HALF_DOWN).add(probabilities[i-1]);
        }
    }

    public int pickIndex() {
        BigDecimal probability = new BigDecimal(System.currentTimeMillis() * Math.random() % seem / seem);
        int left = 0;
        int right = probabilities.length - 1;
        int mid = 0;
        if (probability.compareTo(probabilities[left]) <= 0) {
            return left;
        }
        while (left != right) {
            mid = (left + right) >> 1;
            if (mid == left) {
                return right;
            }
            int compare = probability.compareTo(probabilities[mid]);
            if (compare > 0) {
                left = mid;
            } else if (compare < 0) {
                right = mid;
            } else {
                return mid;
            }
        }
        return mid;
    }


    /**
     * 示例 1：
     * 输入：
     * ["Solution","pickIndex"]
     * [[[1]],[]]
     * 输出：
     * [null,0]
     * 解释：
     * Solution solution = new Solution([1]);
     * solution.pickIndex(); // 返回 0，因为数组中只有一个元素，所以唯一的选择是返回下标 0。
     *
     * 示例 2：
     * 输入：
     * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
     * [[[1,3]],[],[],[],[],[]]
     * 输出：
     * [null,1,1,1,1,0]
     * 解释：
     * Solution solution = new Solution([1, 3]);
     * solution.pickIndex(); // 返回 1，返回下标 1，返回该下标概率为 3/4 。
     * solution.pickIndex(); // 返回 1
     * solution.pickIndex(); // 返回 1
     * solution.pickIndex(); // 返回 1
     * solution.pickIndex(); // 返回 0，返回下标 0，返回该下标概率为 1/4 。
     *
     * 由于这是一个随机问题，允许多个答案，因此下列输出都可以被认为是正确的:
     * [null,1,1,1,1,0]
     * [null,1,1,1,1,1]
     * [null,1,1,1,0,0]
     * [null,1,1,1,0,1]
     * [null,1,0,1,0,0]
     * ......
     * 诸若此类。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/random-pick-with-weight
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        _528FiveHundredTwentyEight test = new _528FiveHundredTwentyEight(new int[]{
                4,2
        });
        System.out.println(test.pickIndex());
        System.out.println(test.pickIndex());
        System.out.println(test.pickIndex());
        System.out.println(test.pickIndex());
        System.out.println(test.pickIndex());
    }
    
    
}
