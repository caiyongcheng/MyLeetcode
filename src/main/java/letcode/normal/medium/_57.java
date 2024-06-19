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

/**
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。  在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *
 * @author CaiYongcheng
 * @date 2021-09-17 14:42
 **/
public class _57 {


    public int[][] insert(int[][] intervals, int[] newInterval) {
        /*
        可以和采取和56一样的做法
        考虑到题目给出的是无重叠的
        也就是 对于 interval 由 interval[index][1] < interval[index+1][0]
        所以对于 终点小于newInterval[0]的并无影响，先二分排除了
        剩下的部分中，找出起始点小于等于 newInterval[1] 的部分，只有这一部分才会收到影响
        合并这一部分与newInterval即可

        可以归纳成 找到 newInterval 起点在的intervals[left] 与 终点在的 intervals[right]。
        将其归纳合并即可。
         */
        if (intervals.length == 0) {
            return new int[][]{{newInterval[0], newInterval[1]}};
        }
        int[][] ans;
        int left = binarySearch(intervals, newInterval[0]);
        int right = binarySearch(intervals, newInterval[1]);
        int start;
        start = left;
        while (left <= right && left < intervals.length &&
                intervals[left][0] <= newInterval[1] && newInterval[0] <= intervals[left][1]) {
            newInterval[0] = Math.min(intervals[left][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[left][1], newInterval[1]);
            ++left;
        }
        ans = new int[intervals.length + start - left + 1][2];
        int i = 0;
        for (; i < start; ++i) {
            ans[i] = intervals[i];
        }
        ans[i++] = newInterval;
        for (; i < ans.length; ++i) {
            ans[i] = intervals[left++];
        }
        return ans;
    }


    public int binarySearch(int[][] intervals, int start) {
        if (intervals[0][1] >= start) {
            return 0;
        }
        if (intervals[intervals.length - 1][1] < start) {
            return intervals.length;
        }
        int left = 0;
        int right = intervals.length;
        int mid;
        while (right > left) {
            mid = (right + left) >> 1;
            if (mid == left) {
                break;
            }
            if (intervals[mid][1] == start) {
                return mid;
            } else if (intervals[mid][1] > start) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }


    /**
     * 示例1：
     * 输入：intervals = {{1,3},{6,9}}, newInterval = {2,5}
     * <p>
     * 输出：{{1,5},{6,9}}
     * <p>
     * 示例 2：
     * 输入：intervals = {{1,2},{3,5},{6,7},{8,10},{12,16}}, newInterval = {4,8}
     * 输出：{{1,2},{3,10},{12,16}}
     * 解释：这是因为新的区间 {4,8} 与 {3,5},{6,7},{8,10}重叠。
     * <p>
     * 示例 3：
     * 输入：intervals = {}, newInterval = {5,7}
     * 输出：{{5,7}}
     * <p>
     * 示例 4：
     * 输入：intervals = {{1,5}}, newInterval = {2,3}
     * 输出：{{1,5}}
     * <p>
     * 示例 5：
     * 输入：intervals = {{1,5}}, newInterval = {2,7}
     * 输出：{{1,7}}
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/insert-interval
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        int[][] insert = new _57().insert(
                new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}},
                new int[]{0, 77777}
        );
        for (int[] ints : insert) {
            System.out.println(FormatUtils.formatArray(ints));
        }
    }

}
