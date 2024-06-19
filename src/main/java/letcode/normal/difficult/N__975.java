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

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

/**
 * @program: MyLeetcode
 * @description: 给定一个整数数组 A，你可以从某一起始索引出发，跳跃一定次数。
 * 在你跳跃的过程中，第 1、3、5... 次跳跃称为奇数跳跃，而第 2、4、6... 次跳跃称为偶数跳跃。  
 * 你可以按以下方式从索引 i向后跳转到索引 j（其中 i < j）：
 * 在进行奇数跳跃时（如，第1，3，5... 次跳跃），你将会跳到索引 j，使得 A[i] <=A[j]，A[j] 是可能的最小值。
 * 如果存在多个这样的索引 j，你只能跳到满足要求的最小索引 j 上。
 * 在进行偶数跳跃时（如，第2，4，6... 次跳跃），你将会跳到索引j，使得 A[i] >= A[j]，A[j] 是可能的最大值。
 * 如果存在多个这样的索引 j，你只能跳到满足要求的最小索引 j上。 
 * （对于某些索引 i，可能无法进行合乎要求的跳跃。） 如果从某一索引开始跳跃一定次数（可能是 0 次或多次），就可以到达数组的末尾（索引 A.length - 1），那么该索引就会被认为是好的起始索引。
 * 返回好的起始索引的数量。  来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/odd-even-jump 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.difficult
 * @author: 6JSh5rC456iL
 * @date: 2021-04-06 09:25
 **/
public class N__975 {



    public int oddEvenJumps(int[] arr) {
        //计算出每个位置的下一个奇数跳和偶数跳位置
        //
        return 0;
    }


    /**
     * 示例 1：
     * 输入：[10,13,12,14,15]
     * 输出：2
     * 解释：
     * 从起始索引 i = 0 出发，我们可以跳到 i = 2，（因为 A[2] 是 A[1]，A[2]，A[3]，A[4] 中大于或等于 A[0] 的最小值），然后我们就无法继续跳下去了。
     * 从起始索引 i = 1 和 i = 2 出发，我们可以跳到 i = 3，然后我们就无法继续跳下去了。
     * 从起始索引 i = 3 出发，我们可以跳到 i = 4，到达数组末尾。
     * 从起始索引 i = 4 出发，我们已经到达数组末尾。
     * 总之，我们可以从 2 个不同的起始索引（i = 3, i = 4）出发，通过一定数量的跳跃到达数组末尾。
     *
     * 示例2：
     * 输入：[2,3,1,1,4]
     * 输出：3
     * 解释：
     * 从起始索引 i=0 出发，我们依次可以跳到 i = 1，i = 2，i = 3：
     * 在我们的第一次跳跃（奇数）中，我们先跳到 i = 1，因为 A[1] 是（A[1]，A[2]，A[3]，A[4]）中大于或等于 A[0] 的最小值。
     * 在我们的第二次跳跃（偶数）中，我们从 i = 1 跳到 i = 2，因为 A[2] 是（A[2]，A[3]，A[4]）中小于或等于 A[1] 的最大值。A[3] 也是最大的值，但 2 是一个较小的索引，所以我们只能跳到 i = 2，而不能跳到 i = 3。
     * 在我们的第三次跳跃（奇数）中，我们从 i = 2 跳到 i = 3，因为 A[3] 是（A[3]，A[4]）中大于或等于 A[2] 的最小值。
     * 我们不能从 i = 3 跳到 i = 4，所以起始索引 i = 0 不是好的起始索引。
     *
     * 类似地，我们可以推断：
     * 从起始索引 i = 1 出发， 我们跳到 i = 4，这样我们就到达数组末尾。
     * 从起始索引 i = 2 出发， 我们跳到 i = 3，然后我们就不能再跳了。
     * 从起始索引 i = 3 出发， 我们跳到 i = 4，这样我们就到达数组末尾。
     * 从起始索引 i = 4 出发，我们已经到达数组末尾。
     * 总之，我们可以从 3 个不同的起始索引（i = 1, i = 3, i = 4）出发，通过一定数量的跳跃到达数组末尾。
     * 
     * 示例 3：
     * 输入：[5,1,3,4,2]
     * 输出：3
     * 解释：
     * 我们可以从起始索引 1，2，4 出发到达数组末尾。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/odd-even-jump
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {

    }

}