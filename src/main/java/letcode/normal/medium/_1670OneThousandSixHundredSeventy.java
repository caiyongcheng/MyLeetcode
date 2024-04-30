package letcode.normal.medium;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/11/28 9:13
 * description 请你设计一个队列，支持在前，中，后三个位置的 push 和 pop 操作。
 * 请你完成 FrontMiddleBack 类：  FrontMiddleBack() 初始化队列。
 * void pushFront(int val) 将 val 添加到队列的 最前面 。
 * void pushMiddle(int val) 将 val 添加到队列的 正中间 。
 * void pushBack(int val) 将 val 添加到队里的 最后面 。
 * int popFront() 将 最前面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * int popMiddle() 将 正中间 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * int popBack() 将 最后面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * 请注意当有 两个 中间位置的时候，选择靠前面的位置进行操作。
 * 比方说：  将 6 添加到 [1, 2, 3, 4, 5] 的中间位置，结果数组为 [1, 2, 6, 3, 4, 5] 。
 * 从 [1, 2, 3, 4, 5, 6] 的中间位置弹出元素，返回 3 ，数组变为 [1, 2, 4, 5, 6] 。
 */
public class _1670OneThousandSixHundredSeventy {

    private class InnerNode {
        int val;

        InnerNode next;

        InnerNode pre;

        public InnerNode(int val, InnerNode pre, InnerNode next) {
            this.val = val;
            this.next = next;
            this.pre = pre;
        }

    }

    InnerNode head;

    InnerNode tail;

    InnerNode mid;

    int len;

    public _1670OneThousandSixHundredSeventy() {
        head = new InnerNode(0, null, null);
        tail = new InnerNode(0, null, null);
        mid = head;
        head.next = tail;
        tail.pre = head;
        len = 2;
    }

    public void pushFront(int val) {
        InnerNode innerNode = new InnerNode(val, head, head.next);
        head.next.pre = innerNode;
        head.next = innerNode;
        if (len >> 1 == (len + 1) >> 1) {
            mid = mid.next;
        }
        len++;
    }

    public void pushMiddle(int val) {
        // 1 2 3 4
        if ((len & 1) == 1) {
            InnerNode innerNode = new InnerNode(val, mid.pre, mid);
            mid.pre.next = innerNode;
            mid.pre = innerNode;
            mid = mid.pre;
        } else {
            InnerNode innerNode = new InnerNode(val, mid, mid.next);
            mid.next.pre = innerNode;
            mid.next = innerNode;
            mid = mid.next;
        }
        ++len;
    }

    public void pushBack(int val) {
        InnerNode innerNode = new InnerNode(val, tail.pre, tail);
        tail.pre.next = innerNode;
        tail.pre = innerNode;
        if (len >> 1 == (len + 1) >> 1) {
            mid = mid.next;
        }
        len++;
    }

    public int popFront() {
        if (len == 2) {
            return -1;
        }
        int delVal = head.next.val;
        head.next = head.next.next;
        head.next.pre = head;
        if ((len & 1) == 0) {
            mid = mid.next;
        }
        --len;
        return delVal;
    }

    public int popMiddle() {
        if (len == 2) {
            return -1;
        }
        int val = mid.val;
        if ((len & 1) == 1) {
            mid = mid.pre;
            mid.next = mid.next.next;
            mid.next.pre = mid;
        } else {
            mid = mid.next;
            mid.pre.pre.next = mid;
            mid.pre = mid.pre.pre;
        }
        --len;
        return val;
    }

    public int popBack() {
        // 1 2 3 4
        if (len == 2) {
            return -1;
        }
        int delVal = tail.pre.val;
        tail.pre.pre.next = tail;
        tail.pre = tail.pre.pre;
        if ((len & 1) == 1) {
            mid = mid.pre;
        }
        --len;
        return delVal;
    }

    /**
     * {"pushFront","pushMiddle","pushMiddle","pushFront","pushFront","pushMiddle","popMiddle","popMiddle","pushMiddle","pushMiddle","popFront"}
     * {{888438],{772690],{375192],{411268],{885613},{508187},{},{},{111498},{296008},{}}
     * 输出：
     * [null, null, null, null, null, 1, 3, 4, 2, -1]
     *
     * 解释：
     * FrontMiddleBackQueue q = new FrontMiddleBackQueue();
     * q.pushFront(1);   // [1]
     * q.pushBack(2);    // [1, 2]
     * q.pushMiddle(3);  // [1, 3, 2]
     * q.pushMiddle(4);  // [1, 4, 3, 2]
     * q.popFront();     // 返回 1 -> [4, 3, 2]
     * q.popMiddle();    // 返回 3 -> [4, 2]
     * q.popMiddle();    // 返回 4 -> [2]
     * q.popBack();      // 返回 2 -> []
     * q.popFront();     // 返回 -1 -> [] （队列为空）
     * @param args
     */
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String[] optArr = {
                "pushFront", "pushBack", "pushMiddle", "pushMiddle", "popFront", "popMiddle", "popMiddle", "popBack", "popFront"
        };
        Object[][] data = {{1}, {2}, {3}, {4}, {}, {}, {}, {}, {}};

        _1670OneThousandSixHundredSeventy check = new _1670OneThousandSixHundredSeventy();
        Class<? extends _1670OneThousandSixHundredSeventy> checkClass = check.getClass();
        Map<String, Method> name2Method = Arrays.stream(checkClass.getMethods()).filter(method -> method.getName().startsWith("p")).collect(Collectors.toMap(
                Method::getName,
                Function.identity()
        ));
        for (int i = 0; i < optArr.length; i++) {
            display(check);
            Method method = name2Method.get(optArr[i]);
            System.out.println(method.invoke(check, data[i]));
        }
    }

    static void display(_1670OneThousandSixHundredSeventy c) {
        InnerNode t = c.head.next;
        System.out.print("[");
        while (t != c.tail) {
            System.out.print(t.val + ", ");
            t = t.next;
        }
        System.out.print("] ");
        System.out.println("mid: " + c.mid.val + ", len: " + c.len);
    }

}
