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

import letcode.utils.FormatUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 给你一个由非负整数a1, a2, ..., an 组成的数据流输入，请你将到目前为止看到的数字总结为不相交的区间列表。
 * 实现 SummaryRanges 类：  SummaryRanges() 使用一个空数据流初始化对象。 void addNum(int val) 向数据流中加入整数 val 。
 * int[][] getIntervals() 以不相交区间[starti, endi] 的列表形式返回对数据流中整数的总结。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/data-stream-as-disjoint-intervals 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-10-09 09:10
 **/
public class _352 {

    class _352 {
        int[] val;
        _352 next;

        public _352(int val) {
            this.val = new int[]{val, val};
        }
    }


    int size;
    _352 head;
    _352 tail;

    public _352() {
        size = 0;
        head = new _352(1);
    }

    public void addNum(int val) {
        if (tail == null) {
            tail = new _352(val);
            head.next = tail;
            size = 1;
        }
        //找到左边最接近区间
        _352 left = search(val);
        //最小 位于开头
        if (null == left) {
            if (val + 1 >= head.next.val[0]) {
                head.next.val[0] = val;
                return;
            }
            _352 node = new _352(val);
            node.next = head.next;
            head.next = node;
            ++size;
            return;
        }
        if (left.val[1] >= val) {
            return;
        }
        if (left.val[1] + 1 == val) {
            left.val[1] = val;
        } else {
            _352 node = new _352(val);
            node.next = left.next;
            left.next = node;
            ++size;
            left = node;
        }
        if (left.next == null) {
            return;
        }
        if (left.val[1] + 1 >= left.next.val[0]) {
            left.val[1] = left.next.val[1];
            left.next = left.next.next;
            --size;
        }
    }

    public int[][] getIntervals() {
        int[][] ans = new int[size][];
        _352 iter = head.next;
        for (int index = 0; index < ans.length; index++) {
            ans[index] = iter.val;
            iter = iter.next;
        }
        return ans;
    }

    private _352 search(int val) {
        _352 right = head.next;
        _352 left = null;
        while (right != null && right.val[0] <= val) {
            left = right;
            right = right.next;
        }
        return left;
    }

    /**
     * 输入：
     * ["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals"]
     * [[], [1], [], [3], [], [7], [], [2], [], [6], []]
     * 输出：
     * [null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]], null, [[1, 3], [7, 7]], null, [[1, 3], [6, 7]]]
     * <p>
     * 解释：
     * SummaryRanges summaryRanges = new SummaryRanges();
     * summaryRanges.addNum(1);      // arr = [1]
     * summaryRanges.getIntervals(); // 返回 [[1, 1]]
     * summaryRanges.addNum(3);      // arr = [1, 3]
     * summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3]]
     * summaryRanges.addNum(7);      // arr = [1, 3, 7]
     * summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3], [7, 7]]
     * summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
     * summaryRanges.getIntervals(); // 返回 [[1, 3], [7, 7]]
     * summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
     * summaryRanges.getIntervals(); // 返回 [[1, 3], [6, 7]]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/data-stream-as-disjoint-intervals
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        _352 test = new _352();
        test.addNum(1);
        display(test.getIntervals());
        test.addNum(3);
        display(test.getIntervals());
        test.addNum(7);
        display(test.getIntervals());
        test.addNum(2);
        display(test.getIntervals());
        test.addNum(6);
        display(test.getIntervals());
    }


    public static void display(int[][] arr) {
        System.out.print("[ ");
        Arrays.stream(arr).map(Arrays::toString).forEach(System.out::print);
        System.out.println(" ] ");
    }


}
