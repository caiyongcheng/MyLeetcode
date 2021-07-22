package letcode.normal.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。  
 * 构造这个链表的深拷贝。深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。
 * 新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。
 * 复制链表中的指针都不应指向原链表中的节点 。  例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。
 * 那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。  返回复制链表的头节点。  用一个由n个节点组成的链表来表示输入/输出中的链表。
 * 每个节点用一个[val, random_index]表示：  val：一个表示Node.val的整数。 random_index：随机指针指向的节点索引（范围从0到n-1）；
 * 如果不指向任何节点，则为null。 你的代码 只 接受原链表的头节点 head 作为传入参数。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/copy-list-with-random-pointer 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-07-22 11:13
 **/
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
public class _138OneHundredThirtyEight {


    public Node copyRandomList(Node head) {
        /**
         * 重要的是保存随机指针的指向
         * 采用map + 数组的方式处理
         */
        if (head == null) {
            return null;
        }
        ArrayList<Node> list = new ArrayList<>();
        Map<Node, Integer> objToIndexMap = new HashMap<>();
        Map<Integer, Integer> randomInfo = new HashMap<>();
        int index = 0;
        Node temporary = head;
        while (temporary != null) {
            list.add(temporary);
            objToIndexMap.put(temporary, index++);
            temporary = temporary.next;
        }
        //记录随机指针信息
        temporary = head;
        index = 0;
        while (temporary != null) {
            if (temporary.random != null) {
                randomInfo.put(index, objToIndexMap.get(temporary.random));
            }
            ++index;
            temporary = temporary.next;
        }
        //构造深拷贝
        list.set(0, new Node(head.val));
        for (index = 1; index < list.size(); ++index) {
            temporary = new Node(list.get(index).val);
            list.get(index-1).next = temporary;
            list.set(index, temporary);
        }
        //附加随机指针
        for (Map.Entry<Integer, Integer> entry : randomInfo.entrySet()) {
            list.get(entry.getKey()).random = list.get(entry.getValue());
        }
        return list.get(0);
    }


    public static void main(String[] args) {
        new _138OneHundredThirtyEight().copyRandomList(null);
    }





}



















