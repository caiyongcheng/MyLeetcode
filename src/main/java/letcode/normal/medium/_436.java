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

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 给你一个区间数组 intervals ，其中intervals[i] = [starti, endi] ，且每个starti 都 不同 。
 * 区间 i 的 右侧区间 可以记作区间 j ，并满足 startj>= endi ，且 startj 最小化 。
 * 返回一个由每个区间 i 的 右侧区间 的最小起始位置组成的数组。如果某个区间 i 不存在对应的 右侧区间 ，则下标 i 处的值设为 -1 。
 * 来源：力扣（LeetCode） 链接：https://leetcode.cn/problems/find-right-interval
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2022-05-20 16:30
 **/
public class _436 {

    public int[] findRightInterval(int[][] intervals) {
        int[] ans = new int[intervals.length];
        List<Integer> sortIdx = Stream.iterate(0, i -> i + 1).limit(intervals.length)
                .sorted(Comparator.comparingInt((Integer o) -> intervals[o][0]).thenComparingInt(o -> intervals[o][1])).collect(Collectors.toList());
        int idx;
        int maxStart = intervals[sortIdx.get(intervals.length - 1)][0];
        for (int i = 0; i < sortIdx.size(); i++) {
            idx = sortIdx.get(i);
            if (intervals[idx][1] > maxStart) {
                ans[idx] = -1;
                continue;
            }
            if (i == intervals.length - 1) {
                ans[idx] = -1;
                continue;
            }
            int l = i;
            if (intervals[sortIdx.get(l)][0] >= intervals[idx][1]) {
                ans[idx] = sortIdx.get(i);
                continue;
            }
            int r = intervals.length - 1;
            int mid;
            while (l < r) {
                mid = (l + r) >>> 1;
                if (mid == l) {
                    break;
                }
                if (intervals[sortIdx.get(mid)][0] < intervals[idx][1]) {
                    l = mid;
                } else {
                    r = mid;
                }
            }
            ans[idx] = sortIdx.get(r);
        }
        return ans;
    }


    /**
     * 示例 1：
     * <p>
     * 输入：intervals = {{1,2}}
     * 输出：{-1}
     * 解释：集合中只有一个区间，所以输出-1。
     * 示例 2：
     * <p>
     * 输入：intervals = {{3,4},{2,3},{1,2}}
     * 输出：{-1,0,1}
     * 解释：对于 {3,4} ，没有满足条件的“右侧”区间。
     * 对于 {2,3} ，区间{3,4}具有最小的“右”起点;
     * 对于 {1,2} ，区间{2,3}具有最小的“右”起点。
     * 示例 3：
     * <p>
     * 输入：intervals = {{1,4},{2,3},{3,4}}
     * 输出：{-1,2,-1}
     * 解释：对于区间 {1,4} 和 {3,4} ，没有满足条件的“右侧”区间。
     * 对于 {2,3} ，区间 {3,4} 有最小的“右”起点。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/find-right-interval
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        _436 fourHundredThirtySix = new _436();
        System.out.println(FormatUtils.formatArray(fourHundredThirtySix.findRightInterval(
                //[[1,2],[2,3],[0,1],[3,4]]
                new int[][]{{1, 1}, {3, 4}}
        )));
    }


}
