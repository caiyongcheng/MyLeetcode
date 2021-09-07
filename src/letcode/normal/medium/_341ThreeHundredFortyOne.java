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
