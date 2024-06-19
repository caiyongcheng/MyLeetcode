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

package letcode.normal.difficult;

import java.util.*;

/**
 * 实现 AllOne 类：  AllOne() 初始化数据结构的对象。 inc(String key) 字符串 key 的计数增加 1 。
 * 如果数据结构中尚不存在 key ，那么插入计数为 1 的 key 。 dec(String key) 字符串 key 的计数减少 1 。
 * 如果 key 的计数在减少后为 0 ，那么需要将这个 key 从数据结构中删除。测试用例保证：在减少计数前，key 存在于数据结构中。
 * getMaxKey() 返回任意一个计数最大的字符串。如果没有元素存在，返回一个空字符串 "" 。
 * getMinKey() 返回任意一个计数最小的字符串。如果没有元素存在，返回一个空字符串 "" 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/all-oone-data-structure 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2022-03-16 09:02
 **/
public class _432 {

    private HashMap<String, Node> idxMap;

    private Node head;

    private Node tail;


    class Node {
        Set<String> keys;
        int size;
        Node pre;
        Node next;
        public Node(Set<String> keys, int size, Node pre, Node next) {
            this.keys = keys;
            this.size = size;
            this.pre = pre;
            this.next = next;
        }
    }

    public _432() {
        /*
        LFU
         */
        idxMap = new HashMap<>(4096);
        head = new Node(new HashSet<>(), 0, null, null);
        tail = new Node(new HashSet<>(), 0, head, null);
        head.keys.add("");
        tail.keys.add("");
        head.next = tail;
    }

    public void inc(String key) {
        Node node = idxMap.getOrDefault(key, head);
        if (node.size + 1 == node.next.size) {
            node.next.keys.add(key);
            node.keys.remove(key);
            if (node.keys.isEmpty()) {
                node.pre.next = node.next;
                node.pre.next.pre = node.pre;
            }
            idxMap.put(key, node.next);
            return;
        }
        HashSet<String> keys = new HashSet<>();
        keys.add(key);
        node.next = new Node(keys, node.size + 1, node, node.next);
        node.next.next.pre = node.next;
        idxMap.put(key, node.next);
        node.keys.remove(key);
        if (node.keys.isEmpty()) {
            node.pre.next = node.next;
            node.pre.next.pre = node.pre;
        }
    }

    public void dec(String key) {
        Node node = idxMap.getOrDefault(key, tail);
        if (node.size - 1 == node.pre.size) {
            node.pre.keys.add(key);
            node.keys.remove(key);
            if (node.keys.isEmpty()) {
                node.pre.next = node.next;
                node.pre.next.pre = node.pre;
            }
            idxMap.put(key, node.pre);
            return;
        }
        HashSet<String> keys = new HashSet<>();
        keys.add(key);
        node.pre = new Node(keys, node.size - 1, node.pre, node);
        node.pre.pre.next = node.pre;
        idxMap.put(key, node.pre);
        node.keys.remove(key);
        if (node.keys.isEmpty()) {
            node.pre.next = node.next;
            node.pre.next.pre = node.pre;
        }
    }

    public String getMaxKey() {
        for (String key : tail.pre.keys) {
            return key;
        }
        return "";
    }

    public String getMinKey() {
        for (String key : head.next.keys) {
            return key;
        }
        return "";
    }


    /**
     ["AllOne","inc","inc","inc","inc","getMaxKey","inc","inc","inc","dec","inc","inc","inc","getMaxKey"]
     [[],["hello"],["goodbye"],["hello"],["hello"],[],["leet"],["code"],["leet"],["hello"],["leet"],["code"],["code"],[]]
     * @param args
     */
    public static void main(String[] args) {
        _432 allOne  = new _432();
        allOne.inc("hello");
        allOne.inc("goodbye");
        allOne.inc("hello");
        allOne.inc("hello");
        System.out.println(allOne.getMaxKey());
        allOne.inc("leet");
        allOne.inc("code");
        allOne.inc("leet");
        allOne.dec("hello");
        allOne.inc("leet");
        allOne.inc("code");
        allOne.inc("code");
        System.out.println(allOne.getMaxKey());
    }



}
