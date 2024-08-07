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

    /**
     * 示例：
     *
     * 输入：
     * ["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
     * [[], [1], [2], [1], [3], [2], [2], [2], [2]]
     * 输出：
     * [null, null, null, true, false, null, true, null, false]
     *
     * 解释：
     * MyHashSet myHashSet = new MyHashSet();
     * myHashSet.add(1);      // set = [1]
     * myHashSet.add(2);      // set = [1, 2]
     * myHashSet.contains(1); // 返回 True
     * myHashSet.contains(3); // 返回 False ，（未找到）
     * myHashSet.add(2);      // set = [1, 2]
     * myHashSet.contains(2); // 返回 True
     * myHashSet.remove(2);   // set = [1]
     * myHashSet.contains(2); // 返回 False ，（已移除）
     * @param args
     */
    public static void main(String[] args) {
        _705 sevenHundredFive = new _705();
        System.out.println(TestUtil.operation(
                sevenHundredFive,
                "[\"MyHashSet\", \"add\", \"add\", \"contains\", \"contains\", \"add\", \"contains\", \"remove\", \"contains\"]",
                "[[], [1], [2], [1], [3], [2], [2], [2], [2]]"
        ));
    }

}
