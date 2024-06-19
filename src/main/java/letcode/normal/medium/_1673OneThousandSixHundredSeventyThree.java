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

import java.util.Stack;

/**
 * @program: MyLeetcode
 * @description: 给你一个整数数组 nums 和一个正整数 k ，返回长度为 k 且最具 竞争力 的 nums 子序列。  
 * 数组的子序列是从数组中删除一些元素（可能不删除元素）得到的序列。  
 * 在子序列a 和子序列b 第一个不相同的位置上，如果a中的数字小于 b 中对应的数字，那么我们称子序列 a 比子序列 b（相同长度下）更具 竞争力 。 
 * 例如，[1,3,4] 比 [1,3,5] 更具竞争力，在第一个不相同的位置，也就是最后一个位置上，4 小于 5 。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/find-the-most-competitive-subsequence 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @date: 2021-03-29 15:22
 **/
public class _1673OneThousandSixHundredSeventyThree {

    public int[] mostCompetitive(int[] nums, int k) {
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[k];
        for (int i = 0; i < nums.length; i++) {
            if (nums.length - i + stack.size() <= k) {
                stack.push(nums[i]);
            } else {
                while (!stack.empty() && nums.length - i + stack.size() > k && stack.peek() > nums[i]) {
                    stack.pop();
                }
                if (stack.size() < k) {
                    stack.push(nums[i]);
                }
            }
        }
        while (!stack.empty()) {
            ans[--k] = stack.pop();
        }
        return ans;
    }

    public int[] mostCompetitiveOptimize(int[] nums, int k) {
        int curCnt = 0;
        int[] ans = new int[k];
        for (int i = 0; i < nums.length; i++) {
            while (curCnt > 0 && nums.length - i + curCnt > k && ans[curCnt - 1] > nums[i]) {
                --curCnt;
            }
            if (curCnt < k) {
                ans[curCnt++] = nums[i];
            }
        }
        return ans;
    }

    /**
     * 示例 1：
     * 输入：nums = [3,5,2,6], k = 2
     * 输出：[2,6]
     * 解释：在所有可能的子序列集合 {[3,5], [3,2], [3,6], [5,2], [5,6], [2,6]} 中，[2,6] 最具竞争力。
     *
     * 示例 2：
     * 输入：nums = [2,4,3,3,5,4,9,6], k = 4
     * 输出：[2,3,3,4]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-the-most-competitive-subsequence
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(FormatUtils.formatArray(new _1673OneThousandSixHundredSeventyThree().mostCompetitive(
                new int[]{3,5,2,6},
                2
        )));
    }

}
