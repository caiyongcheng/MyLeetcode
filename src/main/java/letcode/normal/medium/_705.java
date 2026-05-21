package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * 不使用任何内建的哈希表库设计一个哈希集合（HashSet）。
 * 实现 MyHashSet 类：  void add(key) 向哈希集合中插入值 key 。 bool contains(key) 返回哈希集合中是否存在这个值 key 。
 * void remove(key) 将给定值 key 从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/4/18 17:18
 */
public class _705 {

    int[] set = new int[1000001];

    public _705() {

    }

    public void add(int key) {
        set[key] = 1;
    }

    public void remove(int key) {
        set[key] = 0;
    }

    public boolean contains(int key) {
        return set[key] == 1;
    }

}
