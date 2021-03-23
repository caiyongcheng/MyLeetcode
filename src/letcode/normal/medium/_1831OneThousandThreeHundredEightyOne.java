package letcode.normal.medium;

/**
 * @program: MyLeetcode
 * @description: 请你设计一个支持下述操作的栈。  实现自定义栈类 CustomStack ：
 * CustomStack(int maxSize)：用 maxSize 初始化对象，maxSize 是栈中最多能容纳的元素数量，栈在增长到 maxSize 之后则不支持 push 操作。
 * void push(int x)：如果栈还未增长到 maxSize ，就将 x 添加到栈顶。
 * int pop()：弹出栈顶元素，并返回栈顶的值，或栈为空时返回 -1 。
 * void inc(int k, int val)：栈底的 k 个元素的值都增加 val 。如果栈中元素总数小于 k ，则栈中的所有元素都增加 val 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/design-a-stack-with-increment-operation 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @date: 2021-03-23 15:11
 **/
public class _1831OneThousandThreeHundredEightyOne {


    private int[] data;

    int top;


    public _1831OneThousandThreeHundredEightyOne(int maxSize) {
        data = new int[maxSize];
        top = -1;
    }

    public void push(int x) {
        if (top >= data.length - 1) {
            return;
        }
        data[++top] = x;
    }

    public int pop() {
        if (top < 0) {
            return -1;
        }
        return data[top--];
    }

    public void increment(int k, int val) {
        if (k > top + 1) {
            k = top + 1;
        }
        for (int i=0; i < k; ++i) {
            data[i] += val;
        }
    }

    public static void main(String[] args) {
        _1831OneThousandThreeHundredEightyOne customStack  = new _1831OneThousandThreeHundredEightyOne(3);
        customStack.push(1);
        customStack.push(2);
        customStack.pop();
        customStack.push(2);
        customStack.push(3);
        customStack.push(4);
        customStack.increment(5, 100);
        customStack.increment(2, 100);
        customStack.pop();
        customStack.pop();
        customStack.pop();
        customStack.pop();
    }

}
