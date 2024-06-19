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

import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 中位数是 有序列表 中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 * 例如，  [2,3,4]的中位数是 3  [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * 设计一个支持以下两种操作的数据结构：  void addNum(int num) - 从数据流中添加一个整数到数据结构中。 double findMedian() - 返回目前所有元素的中位数。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/find-median-from-data-stream 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-08-27 09:10
 **/
public class _295 {


    PriorityQueue<Integer> min;
    PriorityQueue<Integer> max;

    
    /** initialize your data structure here. */
    public _295() {
        min = new PriorityQueue<>(Integer::compareTo);
        max = new PriorityQueue<>((x, y) -> y - x);
    }

    /**
     * 对于数据结构来说，里面的数只会越来越多
     * 所以中位数也只会向前移动
     * 所以中位数之前的数就没有必要保存了
     * 假设当前中位数的指针是 p1 和 p2
     * 如果原来是奇数 那么 p1 = p2， 添加一个数后 p1 不变， p2 加一
     * 如果原来是偶数 那么p2 不变 p1 加一
     * 所以随着输入量的增大 需要保存输入数据后一半的数据
     * 由于频繁增加，所以使用LinkedList
     *
     * 要求的是有序 所以思路不对了
     *
     * 1 保证 min.size == max.size || min.size == max.size + 1
     * 2 保证 min.peek > max.
     *
     * @param num
     */
    public void addNum(int num) {
        if (max.size() == 0) {
            max.add(num);
            return;
        }
        if (min.size() == 0) {
            if (num > max.peek()) {
                min.add(num);
            } else {
                min.add(max.poll());
                max.add(num);
            }
            return;
        }
        if (num <= min.peek()) {
            max.add(num);
            if (max.size() == min.size() + 2) {
                min.add(max.poll());
            }
            return;
        }
        min.add(num);
        if (max.size() == min.size() - 1) {
            max.add(min.poll());
        }
    }

    public double findMedian() {
        return ((max.size() + min.size()) & 1) == 1 ? max.peek() : (min.peek() + max.peek()) / 2.0;
    }

    /**
     * addNum(1)
     * addNum(2)
     * findMedian() -> 1.5
     * addNum(3)
     * findMedian() -> 2
     *
     * ["MedianFinder","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian"]
     * {{},{6},{},{10},{},{2},{},{6},{},{5},{},{0},{},{6},{},{3},{},{1},{},{0},{},{0},{}}
     *
     * ["MedianFinder","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMe...
     * [[],[155],[],[66],[],[114],
     * [],[0],[],[60],[],[73],[],
     * [109],[],[26],[],[154],
     * [],[0],[],[107],[],[75],
     * [],[9],[],[57],[],[53],[],
     * [6],[],[85],[],[151],[],[12],
     * [],[110],[],[64],[],[103],[],
     * [42],[],[103],[],[126],[],
     * [3],[],[88],[],[142],[],
     * [79],[],[88],[],[147],[],[47],
     * [],[134],[],[27],[],[82],[],[95],
     * [],[26],[],[124],[],[71],[],[79],[],
     * [130],[],[91],[],[131],[],[67],[],[64],
     * [],[16],[],[60],[],[156],[],[9],[],[65],
     * [],[21],[],[66],[],[49],[],[108],[],[80],
     * [],[17],[],[159],[],[24],[],[90],[],
     * [79],[],[31],[],[79],[],[113],[],[39],[],[54],[],[156],[],[139],[],[8],[],[90],[],[19],[],[10],[],[50],[],[89],[],[77],[],[83],[],[13],[],[3],[],[71],[],[52],[],[21],[],[50],[],[120],[],[159],[],[45],[],[22],[],[69],[],[144],[],[158],[],[19],[],[109],[],[52],[],[50],[],[51],[],[62],[],[20],[],[22],[],[71],[],[95],[],[47],[],[12],[],[21],[],[32],[],[17],[],[130],[],[109],[],[8],[],[61],[],[13],[],[48],[],[107],[],[14],[],[122],[],[62],[],[54],[],[70],[],[96],[],[11],[],[141],[],[129],[],[157],[],[136],[],[4...
     */
    public static void main(String[] args) {
        _295 test = new _295();
        String input = "MedianFinder,addNum,findMedian,addNum,findMedian,addNum,findMedian,addNum,findMedian,addNum,findMedian,addNum,findMedian,addNum,findMedian,addNum,findMedian,addNum,findMedian,addNum,findMedian,addNum,findMedian,addNum,findMedian,addNum,findMedian";
        String[] opts = input.split(",");
        int[][] optNum = new int[][]{{},{155},{},{66},{},{114},{},{0},{},{60},{},{73},{},{109},{},{26},{},{154},{},{0},{},{107},{},{75},{},{9},{},{57},{},{53}};
        int index = 0;
        for (String opt : opts) {
            switch (opt) {
                case "MedianFinder" : test = new _295(); break;
                case "addNum": test.addNum(optNum[index][0]); break;
                case "findMedian":
                    System.out.println(test.findMedian()); break;
                default: break;   
            }
            ++index;
        }
    }


}
