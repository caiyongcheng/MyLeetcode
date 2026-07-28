package letcode.normal.medium;

import java.util.Iterator;

/**
 * 请你设计一个迭代器，除了支持 hasNext 和 next 操作外，还支持 peek 操作。
 * 实现 PeekingIterator 类：
 * PeekingIterator(int[] nums) 使用指定整数数组 nums 初始化迭代器。
 * int next() 返回数组中的下一个元素，并将指针移动到下个元素处。
 * bool hasNext() 如果数组中存在下一个元素，返回 true ；否则，返回 false 。
 * int peek() 返回数组中的下一个元素，但 不 移动指针。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/peeking-iterator 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-10-05 23:35
 **/
public class _284 implements Iterator<Integer> {

    class _284Node {
        int val;
        _284Node next;
    }

    private _284Node root;

    public _284(Iterator<Integer> iterator) {
        // initialize any member here.
        if (iterator.hasNext()) {
            root = new _284Node();
            root.val = iterator.next();
            _284Node now = root;
            while (iterator.hasNext()) {
                now.next = new _284Node();
                now.next.val = iterator.next();
                now = now.next;
            }
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return root == null ? null : root.val;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        int val = root.val;
        root = root.next;
        return val;
    }

    @Override
    public boolean hasNext() {
        return root != null;
    }

}
