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
import java.util.Stack;

/**
 * 你和一群强盗准备打劫银行。给你一个下标从 0开始的整数数组security，其中security[i]是第 i天执勤警卫的数量。
 * 日子从 0开始编号。同时给你一个整数time。  
 * 如果第 i天满足以下所有条件，我们称它为一个适合打劫银行的日子：  第 i天前和后都分别至少有 time天。 
 * 第 i天前连续 time天警卫数目都是非递增的。 
 * 第 i天后连续 time天警卫数目都是非递减的。 更正式的，
 * 第 i 天是一个合适打劫银行的日子当且仅当：security[i - time] >= security[i - time + 1] >= ... >= security[i] <= ... <= security[i + time - 1] <= security[i + time].  
 * 请你返回一个数组，包含 所有 适合打劫银行的日子（下标从 0开始）。返回的日子可以 任意顺序排列。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/find-good-days-to-rob-the-bank 
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2022-03-17 11:37
 **/
public class _2100TwoThousandOneHundred {

    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        /*
        计算出连续递减的天数 连续递增的天数
         */
        int[] decrease = new int[security.length];
        int[] increase = new int[security.length];
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i < security.length; i++) {
            if (security[i] <= security[i-1]) {
                decrease[i] = decrease[i-1]+1;
            }
        }
        for (int length = security.length - 2; length > 0; length--) {
            if (security[length] <= security[length+1]) {
                increase[length] = increase[length+1] + 1;
            }
        }
        for (int i = 0; i < security.length; i++) {
            if (increase[i] >= time && decrease[i] >= time) {
                list.add(i);
            }
        }
        return list;
    }

    /**
     * 示例 1：
     *
     * 输入：security = [5,3,3,3,5,6,2], time = 2
     * 输出：[2,3]
     * 解释：
     * 第 2 天，我们有 security[0] >= security[1] >= security[2] <= security[3] <= security[4] 。
     * 第 3 天，我们有 security[1] >= security[2] >= security[3] <= security[4] <= security[5] 。
     * 没有其他日子符合这个条件，所以日子 2 和 3 是适合打劫银行的日子。
     * 示例 2：
     *
     * 输入：security = [1,1,1,1,1], time = 0
     * 输出：[0,1,2,3,4]
     * 解释：
     * 因为 time 等于 0 ，所以每一天都是适合打劫银行的日子，所以返回每一天。
     * 示例 3：
     *
     * 输入：security = [1,2,3,4,5,6], time = 2
     * 输出：[]
     * 解释：
     * 没有任何一天的前 2 天警卫数目是非递增的。
     * 所以没有适合打劫银行的日子，返回空数组。
     * 示例 4：
     *
     * 输入：security = [1], time = 5
     * 输出：[]
     * 解释：
     * 没有日子前面和后面有 5 天时间。
     * 所以没有适合打劫银行的日子，返回空数组。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-good-days-to-rob-the-bank
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(FormatUtils.formatList(new _2100TwoThousandOneHundred().goodDaysToRobBank(
                new int[]{1},
                5
        )));
    }




}
