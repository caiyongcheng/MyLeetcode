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

import java.util.Stack;

/**
 * @program: MyLeetcode
 * @description: 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * @packagename: letcode.normal.difficult
 * @author: 6JSh5rC456iL
 * @date: 2021-03-30 15:20
 **/
public class _42FourHundredTwenty {


    public int trap(int[] height) {
        if (height.length < 3) {
            return 0;
        }
        int index;
        int minHeightIndex = 0;
        int minHeight = 0;
        int ans = 0;
        int[] record = new int[height.length];
        record[height.length-1] = height.length-1;
        for (int i = record.length - 2; i >= 0; i--) {
            record[i] = height[record[i+1]] > height[i+1] ? record[i+1] : i+1;
        }
        for (index = 0; index < height.length; index++) {
            if (height[index] != 0) {
                minHeight = height[index];
                minHeightIndex = index;
                break;
            }
        }
        for (; index < height.length; ++index) {
            if (height[index] >= minHeight) {
                minHeightIndex = index;
                minHeight = height[minHeightIndex];
                if (minHeight > height[record[minHeightIndex]]) {
                    minHeightIndex = record[minHeightIndex];
                    minHeight = height[minHeightIndex];
                }
            } else {
                ans += minHeight - height[index];
            }
        }
        return ans;
    }


    /**
     * 示例 1：
     * \              \
     * \              \
     * \              \
     * \    =~~~===~= \
     * \  =~==~====== \
     * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出：6
     * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
     * 示例 2：
     *
     * 输入：height = [4,2,0,3,2,5]
     * 输出：9
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/trapping-rain-water
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args){
        System.out.println( new _42FourHundredTwenty().trap(
                new int[]{6,8,5,0,0,6,5}
                ));
    }

}
