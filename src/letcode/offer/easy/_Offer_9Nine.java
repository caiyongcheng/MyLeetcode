package letcode.offer.easy;

/**
 * @program: MyLeetcode
 * @description: 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead操作返回 -1 )  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.offer.medium.easy
 * @author: 6JSh5rC456iL
 * @date: 2021-03-23 14:34
 **/

import java.util.Stack;

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
public class _Offer_9Nine {

    private Stack<Integer> s1;

    private Stack<Integer> s2;

    public _Offer_9Nine() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    public void appendTail(int value) {
        s1.push(value);
    }

    public int deleteHead() {
        while (!s1.empty()) {
            s2.push(s1.pop());
        }
        int val = s2.empty() ? -1 : s2.pop();
        while (!s2.empty()) {
            s1.push(s2.pop());
        }
        return val;
    }

}
