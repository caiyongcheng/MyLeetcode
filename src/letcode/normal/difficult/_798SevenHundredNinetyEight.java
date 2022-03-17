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

package letcode.normal.difficult;

import datastructure.utils.FormatPrintUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 给你一个数组nums，我们可以将它按一个非负整数 k 进行轮调，
 * 这样可以使数组变为[nums[k], nums[k + 1], ... nums[nums.length - 1], nums[0], nums[1], ..., nums[k-1]]的形式。
 * 此后，任何值小于或等于其索引的项都可以记作一分。  
 * 例如，数组为nums = [2,4,1,3,0]，我们按k = 2进行轮调后，它将变成[1,3,0,2,4]。这将记为 3 分，
 * 因为 1 > 0 [不计分]、3 > 1 [不计分]、0 <= 2 [计 1 分]、2 <= 3 [计 1 分]，4 <= 4 [计 1 分]。 
 * 在所有可能的轮调中，返回我们所能得到的最高分数对应的轮调下标 k 。如果有多个答案，返回满足条件的最小的下标 k 。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/smallest-rotation-with-highest-score 
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2022-03-17 14:23
 **/
public class _798SevenHundredNinetyEight {

    public int bestRotation(int[] nums) {
        /*
        论调k-1相当于论调0向后移动，此时可以看作两部分，一部分是原先的尾部，这一部分会移动到新的头部，取决于该值是不是大于0。
        另外一部分是剩余部分的索引值会变大，此时可能会有额外得分。所以需要维护一个数据结构
        同时维护一个结构，保存着当前位置的数至少要移动多少才会得分。每次需要1的链表长度就是这部分增加的结果。
        比起论调0的而言，少了nums[k-1]在k-1下标，多了nums[k-1]在0下标上的。
        此时再向后移动一位，那么相当于论调k-2，同理少了nums[k-1]在k-1下标，多了nums[k-1]在0下标上的。
        依次迭代计算，求出最小值即可。
         */
        int maxVal = 0;
        int maxCnt = 0;
        HashMap<Integer, Integer> originDiffToMove = new HashMap<>();
        //计算初始值
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= i) {
                ++maxVal;
            } else {
                originDiffToMove.put(nums[i] - i, originDiffToMove.getOrDefault(nums[i] - i, 0) + 1);
            }
        }
        //迭代计算
        int tmpVal = maxVal;
        int diffStep = 1;
        for (int i = nums.length - 1; i > 0; i--) {
            tmpVal = tmpVal - (nums[i] > (nums.length - 1) ? 0 : 1) + (nums[i] > 0 ? 0 : 1) + originDiffToMove.getOrDefault(diffStep, 0);
            originDiffToMove.remove(diffStep);
            if (nums[i] > 0) {
                originDiffToMove.put(nums[i] + diffStep, originDiffToMove.getOrDefault(nums[i] + diffStep, 0) + 1);
            }
            if ((tmpVal >= maxVal && maxCnt != 0) || tmpVal > maxVal) {
                maxVal = tmpVal;
                maxCnt = i;
            }
            ++diffStep;
        }
        return maxCnt;
    }


    /**
     * 示例 1：
     *
     * 输入：nums = [2,3,1,4,0]
     * 输出：3
     * 解释：
     * 下面列出了每个 k 的得分：
     * k = 0,  nums = [2,3,1,4,0],    score 2
     * k = 1,  nums = [3,1,4,0,2],    score 3
     * k = 2,  nums = [1,4,0,2,3],    score 3
     * k = 3,  nums = [4,0,2,3,1],    score 4
     * k = 4,  nums = [0,2,3,1,4],    score 3
     * 所以我们应当选择k = 3，得分最高。
     * 示例 2：
     *
     * 输入：nums = [1,3,0,2,4]
     * 输出：0
     * 解释：
     * nums 无论怎么变化总是有 3 分。
     * 所以我们将选择最小的 k，即 0。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/smallest-rotation-with-highest-score
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _798SevenHundredNinetyEight().bestRotation(
                new int[]{1,3,0,2,4}
        ));
        int[] ints = new int[(int) (Math.random() * 300)];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random () * ints.length);
        }
        System.out.println(FormatPrintUtils.formatArray(ints));
    }

}
