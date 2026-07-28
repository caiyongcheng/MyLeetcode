package letcode.normal.difficult;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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
 * @since 2022-03-16 09:02
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


}
