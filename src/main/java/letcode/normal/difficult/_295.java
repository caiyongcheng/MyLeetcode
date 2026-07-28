package letcode.normal.difficult;

import java.util.PriorityQueue;

/**
 * 中位数是 有序列表 中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 * 例如，  [2,3,4]的中位数是 3  [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * 设计一个支持以下两种操作的数据结构：  void addNum(int num) - 从数据流中添加一个整数到数据结构中。 double findMedian() - 返回目前所有元素的中位数。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/find-median-from-data-stream 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-08-27 09:10
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

}
