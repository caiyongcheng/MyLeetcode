package letcode.normal.medium;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



  // This is the interface that allows for creating nested lists.
  // You should not implement it, or speculate about its implementation
interface NestedInteger {

  // @return true if this NestedInteger holds a single integer, rather than a nested list.
  public boolean isInteger();

  // @return the single integer that this NestedInteger holds, if it holds a single integer
  // Return null if this NestedInteger holds a nested list
  public Integer getInteger();

  // @return the nested list that this NestedInteger holds, if it holds a nested list
  // Return null if this NestedInteger holds a single integer
  public List<NestedInteger> getList();
}
/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */

class NestedIntegerImpl implements NestedInteger{

    private List<NestedInteger> list;

    private Integer val;

    @Override
    public boolean isInteger() {
        return list.isEmpty();
    }

    @Override
    public Integer getInteger() {
        return val;
    }

    @Override
    public List<NestedInteger> getList() {
        return list;
    }

    public Integer getVal() {
        return val;
    }

    public void setList(List<NestedInteger> list) {
        this.list = list;
    }

    public void setVal(Integer val) {
        this.val = val;
    }
}

/**
 * @program: MyLeetcode
 * @description: 给你一个嵌套的整型列表。请你设计一个迭代器，使其能够遍历这个整型列表中的所有整数。
 * 列表中的每一项或者为一个整数，或者是另一个列表。其中列表的元素也可能是整数或是其他列表。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/flatten-nested-list-iterator 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @date: 2021-03-23 09:58
 **/
public class _341ThreeHundredFortyOne implements Iterator<Integer> {


    static class InnerNode {
        NestedInteger val;
        InnerNode next;
    }

    static class InnerStack {
        InnerNode head;

        public InnerStack() {
            head = new InnerNode();
        }

        public void push(NestedInteger val) {
            InnerNode node = new InnerNode();
            node.val = val;
            node.next = head.next;
            head.next = node;
        }

        public NestedInteger pop() {
            if (head.next == null) {
                return null;
            }
            NestedInteger val = head.next.val;
            head.next = head.next.next;
            return val;
        }

        public boolean empty() {
            return head.next == null;
        }

    }

    private InnerStack innerStack;


    public _341ThreeHundredFortyOne(List<NestedInteger> nestedList) {
        innerStack = new InnerStack();
        InnerStack tmpStack = new InnerStack();
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                tmpStack.push(nestedInteger);
            } else {
                List<NestedInteger> list = nestedInteger.getList();
                for (NestedInteger integer : list) {
                    tmpStack.push(integer);
                }
            }
        }
        while (!tmpStack.empty()) {
            NestedInteger nestedInteger = tmpStack.pop();
            if (nestedInteger.isInteger()) {
                innerStack.push(nestedInteger);
            } else {
                List<NestedInteger> list = nestedInteger.getList();
                for (NestedInteger integer : list) {
                    tmpStack.push(integer);
                }
            }
        }
    }

    @Override
    public boolean hasNext() {
        return !innerStack.empty();
    }


    @Override
    public Integer next() {
        return innerStack.pop().getInteger();
    }



    public static void main(String[] args) {

    }



}
