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

import letcode.utils.FormatUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 *
 * @author CaiYongcheng
 * @date 2021-10-22 09:05
 **/
public class _229 {

    public List<Integer> majorityElement(int[] nums) {
        /*
         * 摩尔投票 求n个数中出现次数 大于 n/k 的元素[x1..xn]
         * 设 n个数中出现次数 大于 n/k 的元素 的个数为p
         * 则 k * (n/k) = n => p < k
         * 取 k - 1 个桶，对元素遍历，
         * 如果 有空桶 就放入
         * 否则 如果 当前元素等于桶里的某个元素，那么该桶值+1
         * 否则 如果 整体-1
         * 那么 [x1..xn] 最后就会剩余在桶中
         * 因为 对于 xi 而言，
         * 如果每次选择都不在桶中，则每次都可以抵消k-1元素，连自己在内一共是k个元素，而count(xi)是大于n/k的，所以
         * count(xi)*k > n，故一定会剩余。
         * 如果xi在桶中了，则xi会出现count(xi)次，xi最后不在桶中，说明被抵消了，同时也有k-1个（包括当前遍历元素）被抵消，一共k个元素。
         * 而能抵消xi，则抵消次数p >= count(xi) > n/k, 结论同上
         */
        int bucket1 = 0;
        int bucket2 = 0;
        int count1 = 0;
        int count2 = 0;
        ArrayList<Integer> ans = new ArrayList<>(2);
        for (int num : nums) {
            if (bucket1 == num && count1 > 0) {
                ++count1;
            } else if (bucket2 == num && count2 > 0) {
                ++count2;
            } else if (count1 == 0) {
                bucket1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                bucket2 = num;
                count2 = 1;
            } else {
                --count1;
                --count2;
            }
        }
        extracted(nums, bucket1, count1, ans);
        extracted(nums, bucket2, count2, ans);
        return ans;
    }

    private void extracted(int[] nums, int bucket1, int count1, ArrayList<Integer> ans) {
        if (count1 > 0) {
            count1 = 0;
            for (int num : nums) {
                if (num == bucket1) {
                    ++count1;
                }
            }
            if (count1 > nums.length / 3) {
                ans.add(bucket1);
            }
        }
    }

    /**
     * 示例1：
     * 输入：[3,2,3]
     * 输出：[3]
     * 示例 2：
     * <p>
     * 输入：nums = [1]
     * 输出：[1]
     * 示例 3：
     * <p>
     * 输入：[1,1,1,3,3,2,2,2]
     * 输出：[1,2]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/majority-element-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(FormatUtils.formatList(new _229().majorityElement(
                new int[]{1, 1, 1, 3, 3, 2, 2, 2, 3}
        )));
    }


}
