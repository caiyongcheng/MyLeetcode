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

import java.util.ArrayList;

/**
 * Leetcode
 * 给出集合[1, 2, 3, …, n]，其所有元素共有n! 种排列。
 * 按大小顺序列出所有排列情况，并一一标记，
 * 当n = 3 时,  所有排列如下：
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定n 和k，返回第k个排列。
 * 说明：
 * 给定 n 的范围是 [1,  9]。
 * 给定 k 的范围是[1,   n!]。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/permutation-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : CaiYongcheng
 * @date : 2020-07-11 18:35
 **/
public class _60 {

    /**
     * 示例 1:
     * 输入: n = 3, k = 3
     * 输出: "213"
     * <p>
     * 示例 2:
     * 输入: n = 4, k = 9
     * 输出: "2314"
     *
     * @param n
     * @param k
     * @return
     */
    public static String getPermutation(int n, int k) {
        int[] permutationList = new int[]{1, 1, 1, 2, 6, 24, 120, 720, 5040, 40320};
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            integers.add(i + 1);
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (n > 0) {
            int div = k / permutationList[n];
            k = k % permutationList[n];
            if (k == 0) {
                k = permutationList[n];
                --div;
            }
            stringBuilder.append(integers.get(div));
            integers.remove(div);
            --n;
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(getPermutation(9, 40320));
    }


}
