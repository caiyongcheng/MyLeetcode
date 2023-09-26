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
public class _460FourHundredSixty {

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


    public _460FourHundredSixty(int capacity) {
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

    /**
     * 输入：
     * ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
     * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
     * 输出：
     * [null, null, null, 1, null, -1, 3, null, -1, 3, 4]
     *
     * @param args
     */
    public static void main(String[] args) {
        _460FourHundredSixty fourHundredSixty = new _460FourHundredSixty(2);
        fourHundredSixty.put(1, 1);
        fourHundredSixty.put(2, 2);
        System.out.println(fourHundredSixty.get(1));
        fourHundredSixty.put(3, 3);
        System.out.println(fourHundredSixty.get(2));
        System.out.println(fourHundredSixty.get(3));
        fourHundredSixty.put(4, 4);
        System.out.println(fourHundredSixty.get(1));
        System.out.println(fourHundredSixty.get(3));
        System.out.println(fourHundredSixty.get(4));
    }
}
