package letcode.normal.difficult;

import java.util.Arrays;

/**
 * 给你一个由非负整数a1, a2, ..., an 组成的数据流输入，请你将到目前为止看到的数字总结为不相交的区间列表。
 * 实现 SummaryRanges 类：  SummaryRanges() 使用一个空数据流初始化对象。 void addNum(int val) 向数据流中加入整数 val 。
 * int[][] getIntervals() 以不相交区间[starti, endi] 的列表形式返回对数据流中整数的总结。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/data-stream-as-disjoint-intervals 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-10-09 09:10
 **/
public class _352 {

    class _352Node {
        int[] val;
        _352Node next;

        public _352Node(int val) {
            this.val = new int[]{val, val};
        }
    }


    int size;
    _352Node head;
    _352Node tail;

    public _352() {
        size = 0;
        head = new _352Node(1);
    }

    public void addNum(int val) {
        if (tail == null) {
            tail = new _352Node(val);
            head.next = tail;
            size = 1;
        }
        //找到左边最接近区间
        _352Node left = search(val);
        //最小 位于开头
        if (null == left) {
            if (val + 1 >= head.next.val[0]) {
                head.next.val[0] = val;
                return;
            }
            _352Node node = new _352Node(val);
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
            _352Node node = new _352Node(val);
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
        _352Node iter = head.next;
        for (int index = 0; index < ans.length; index++) {
            ans[index] = iter.val;
            iter = iter.next;
        }
        return ans;
    }

    private _352Node search(int val) {
        _352Node right = head.next;
        _352Node left = null;
        while (right != null && right.val[0] <= val) {
            left = right;
            right = right.next;
        }
        return left;
    }

    public static void display(int[][] arr) {
        System.out.print("[ ");
        Arrays.stream(arr).map(Arrays::toString).forEach(System.out::print);
        System.out.println(" ] ");
    }


}
