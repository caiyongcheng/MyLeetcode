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
 * 给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。  子数组 是数组的一段连续部分。
 *
 * 提示：
 * 1 <= nums.length <= 3 * 104
 * nums[i] 不是 0 就是 1
 * 0 <= goal <= nums.length
 *
 * @author CaiYongcheng
 * @date 2021-07-08 09:09
 **/
public class _930NineHundredThirty {


    public int numSubarraysWithSum(int[] nums, int goal) {
        /**
         * 由提示可知 我们只关心1 并不关心0
         *
         * 假设 1q 01 02 ..... 0n 1a ... 00 ... 1s ..... 01 02 ... 0m 1b
         * 其中 sum:[1a...1s] = goal => 满足条件的子数组数量 = count(Index1a - Index1q) * (Index1b - index1s)
         *
         */
        ArrayList<Integer> list = new ArrayList<>();
        int length;
        int ans = 0;
        //将-1看作 起始
        list.add(-1);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                list.add(i);
            }
        }
        //添加一个虚拟的结束
        list.add(nums.length);

        //如果goal是0 那么统计 1之间的
        if (goal == 0) {
            length = list.size();
            int now;
            for (int i = 1; i < length; i++) {
                now = list.get(i) - list.get(i - 1);
                ans = ans + (now * (now - 1) >> 1);
            }
            return ans;
        }

        length = list.size() - goal;
        --goal;
        for (int i = 1; i < length; i++) {
            ans = ans + (list.get(i) - list.get(i-1)) * (list.get(i+goal+1) - list.get(i+goal));
        }
        return ans;
    }


    /**
     * 示例 1：
     *
     * 输入：nums = [1,0,1,0,1], goal = 2
     * 输出：4
     * 解释：
     * 如下面黑体所示，有 4 个满足题目要求的子数组：
     * [1,0,1,0,1]
     * [1,0,1,0,1]
     * [1,0,1,0,1]
     * [1,0,1,0,1]
     * 示例 2：
     *
     * 输入：nums = [0,0,0,0,0], goal = 0
     * 输出：15
     *
     * [0,0,0,0,1,0,0,0,0,0,1,1,0,0,0,0,0,1,0,0]
     * 3
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/binary-subarrays-with-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _930NineHundredThirty().numSubarraysWithSum(
                new int[]{0,0,0,0,1,0,0,0,0,0,1,1,0,0,0,0,0,1,0,0},
                3
        ));
    }

}
