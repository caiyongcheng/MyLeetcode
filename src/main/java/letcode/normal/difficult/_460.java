package letcode.normal.difficult;

import java.util.PriorityQueue;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/9/25 9:02
 * description 请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。  实现 LFUCache 类：
 * LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对象 int get(int key) - 如果键 key 存在于缓存中，则获取键的值，否则返回 -1 。
 * void put(int key, int value) - 如果键 key 已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量 capacity 时，则应该在插入新项之前，移除最不经常使用的项。
 * 在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最近最久未使用 的键。 为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。
 * 使用计数最小的键是最久未使用的键。  当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 */
public class _460 {

    int capacity;

    int date = 0;

    int curCapacity = 0;

    int[] cache = new int[1000_00 + 1];

    int[] times = new int[1000_00 + 1];

    int[] dates = new int[1000_00 + 1];

    PriorityQueue<Integer> orderSet = new PriorityQueue<>(1000_00 + 1, (o1, o2) -> {
        if (times[o1] != times[o2]) {
            return times[o1] - times[o2];
        }
        return dates[o1] - dates[o2];
    });


    public _460(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (times[key] == 0) {
            return -1;
        }
        orderSet.remove(key);
        ++times[key];
        dates[key] = date++;
        orderSet.add(key);
        return cache[key];
    }

    public void put(int key, int value) {
        if (times[key] == 0 && curCapacity >= capacity) {
            delete(orderSet.peek());
        }
        delete(key);
        cache[key] = value;
        ++times[key];
        dates[key] = date++;
        orderSet.add(key);
        ++curCapacity;
    }

    public void delete(int removeKey) {
        if (orderSet.remove(removeKey)) {
            --curCapacity;
        }
        cache[removeKey] = -1;
        times[removeKey] = 0;
        dates[removeKey] = 0;
    }

}
