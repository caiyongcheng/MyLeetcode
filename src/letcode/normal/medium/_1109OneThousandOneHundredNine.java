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

import java.util.*;

/**
 * 这里有n个航班，它们分别从 1 到 n 进行编号。  
 * 有一份航班预订表bookings ，表中第i条预订记录bookings[i] = [firsti, lasti, seatsi]
 * 意味着在从 firsti到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi个座位。  
 * 请你返回一个长度为 n 的数组answer，其中 answer[i] 是航班 i 上预订的座位总数。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/corporate-flight-bookings 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 1 <= n <= 2 * 10^4
 * 1 <= bookings.length <= 2 * 10^4
 * bookings[i].length == 3
 * 1 <= firsti <= lasti <= n
 * 1 <= seatsi <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/corporate-flight-bookings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author CaiYongcheng
 * @date 2021-08-31 09:05
 **/
public class _1109OneThousandOneHundredNine {


    public int[] corpFlightBookings(int[][] bookings, int n) {
        /*
         * 传统的穷举，最大时间复杂度 2 * 10^4 * 2 * 10^4 * 3 (两个判断 一个赋值) 可能会超时
         * 超时
         */
        int iniIndex = 0;
        Arrays.stream(bookings).forEach(ints -> {
            --ints[0];
            --ints[1];
        });
        Arrays.sort(bookings, (o1, o2) -> o1[0] < o2[0] ? -1 : o1[0] > o2[0] ? 1 : Integer.compare(o1[1], o2[1]));
        int[] ans = new int[n];
        for (int no = 0; no < ans.length; no++) {
            while (iniIndex < bookings.length && no > bookings[iniIndex][1]) {
                ++iniIndex;
            }
            for (int i = iniIndex; i < bookings.length && bookings[i][0] <= no; ++i) {
                if (bookings[i][1] >= no) {
                    ans[no] += bookings[i][2];
                }
            }
        }
        return ans;
    }


    public int[] corpFlightBookings2(int[][] bookings, int n) {
        /*
         * 差分
         */
        int[] diff = new int[n];
        for (int[] booking : bookings) {
            diff[booking[0]-1] += booking[2];
            if (booking[1] < n) {
                diff[booking[1]] -= booking[2];
            }
        }
        for (int i = 1; i < diff.length; i++) {
            diff[i] += diff[i-1];
        }
        return diff;
    }


    /**
     * 示例 1：
     *
     * 输入：bookings = {{1,2,10},{2,3,20},{2,5,25}}, n = 5
     * 输出：{10,55,45,25,25}
     * 解释：
     * 航班编号        1   2   3   4   5
     * 预订记录 1 ：   10  10
     * 预订记录 2 ：       20  20
     * 预订记录 3 ：       25  25  25  25
     * 总座位数：      10  55  45  25  25
     * 因此，answer = {10,55,45,25,25}
     * 示例 2：
     *
     * 输入：bookings = {{1,2,10},{2,2,15}}, n = 2
     * 输出：{10,25}
     * 解释：
     * 航班编号        1   2
     * 预订记录 1 ：   10  10
     * 预订记录 2 ：       15
     * 总座位数：      10  25
     * 因此，answer = {10,25}
     * 
     * 
     * {{2,3,30},{2,3,45},{2,3,15},{1,3,15}}
     * 4
     * 
     * 
     * {{2,2,50},{1,1,35},{3,3,40},{1,4,50}}
     * 4
     * 
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/corporate-flight-bookings
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(FormatUtils.formatArray(
                new _1109OneThousandOneHundredNine().corpFlightBookings2(
                        new int[][]{{1, 2, 10}, {2, 3, 20}, {2, 5, 25}},
                        5
                )
        ));
    }


}
