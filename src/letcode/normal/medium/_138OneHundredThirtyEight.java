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

import letcode.utils.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

public class _138OneHundredThirtyEight {


    public Node copyRandomList(Node head) {
        /*
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



















