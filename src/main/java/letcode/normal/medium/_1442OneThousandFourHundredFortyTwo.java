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

/**
 * 给你一个整数数组 arr 。  现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。  a 和 b 定义如下：
 * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1] b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k] 注意：^ 表示 按位异或 操作。
 * 请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-05-18 15:01
 **/
public class _1442OneThousandFourHundredFortyTwo {

    public int countTriplets(int[] arr) {
        /**
         * 这种情况 先求前缀和 pre 肯定没错
         * 如果 xor[i..j-1] == xor[j..k]
         * 那肯定有 xor[i..k] = xor[i..j-1] ^ xor[j..k] = 0
         * 推出 xor[0..k] = xor[0..i-1] ^ xor[i..k] = xor[0..i-1]
         * 也就是 pre[k] == pre[i-1]
         */
        int ans = 0;
        int[] preXor = new int[arr.length+1];
        preXor[0] = 0;
        preXor[1] = arr[0];
        for (int i = 1; i < preXor.length; i++) {
            preXor[i] = arr[i-1] ^ preXor[i-1];
        }
        for (int i = 1; i < preXor.length; i++) {
            for (int j = i+1; j < preXor.length; j++) {
                if (preXor[i-1] == preXor[j]) {
                    ans += j-i;
                }
            }
        }
        return ans;
    }

    /**
     * 示例 1：
     * 输入：arr = [2,3,1,6,7]
     * 输出：4
     * 解释：满足题意的三元组分别是 (0,1,2), (0,2,2), (2,3,4) 以及 (2,4,4)
     *
     * 示例 2：
     * 输入：arr = [1,1,1,1,1]
     * 输出：10
     *
     * 示例 3：
     * 输入：arr = [2,3]
     * 输出：0
     *
     * 示例 4：
     * 输入：arr = [1,3,5,7,9]
     * 输出：3
     *
     * 示例 5：
     * 输入：arr = [7,11,12,9,5,2,7,17,22]
     * 输出：8
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1442OneThousandFourHundredFortyTwo().countTriplets(
                new int[]{7,11,12,9,5,2,7,17,22}
        ));
    }


}