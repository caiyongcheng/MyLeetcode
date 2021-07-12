package letcode.normal.medium;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 创建一个基于时间的键值存储类TimeMap，它支持下面两个操作：  1. set(string key, string value, int timestamp)  存储键key、值value，以及给定的时间戳timestamp。 
 * 2. get(string key, int timestamp)  返回先前调用set(key, value, timestamp_prev)所存储的值，其中timestamp_prev <= timestamp。 
 * 如果有多个这样的值，则返回对应最大的timestamp_prev的那个值。 如果没有值，则返回空字符串（""）。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/time-based-key-value-store 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-07-10 22:28
 **/
public class _981NineHundredEightyOne {


    class InValue {
        int time;
        String value;

        public InValue(int time, String value) {
            this.time = time;
            this.value = value;
        }
    }

    HashMap<String, ArrayList<InValue>> dataMap;

    public _981NineHundredEightyOne() {
        dataMap = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        ArrayList<InValue> dataForKey = dataMap.getOrDefault(key, new ArrayList<>());
        dataForKey.add(new InValue(timestamp, value));
        dataMap.put(key, dataForKey);
    }

    public String get(String key, int timestamp) {
        ArrayList<InValue> dataForKey = dataMap.get(key);
        if (dataForKey == null) {
            return "";
        }
        if (dataForKey.get(0).time > timestamp) {
            return "";
        }
        int index = dataForKey.size()-1;
        if (dataForKey.get(index).time <= timestamp) {
            return dataForKey.get(index).value;
        }
        return dataForKey.get(binarySearch(dataForKey, timestamp)).value;
    }

    public int binarySearch(ArrayList<InValue> list, int target) {
        int left = 0;
        int right = list.size() - 1;
        int mid;
        while (left < right) {
            mid = (left + right) >> 1;
            if (list.get(mid).time == target) {
                return mid;
            } else if (list.get(mid).time > target){
                right = mid;
            } else {
                if (left == mid) {
                    return left;
                }
                left = mid;
            }
        }
        return left;
    }

    /**
     * 示例 1：
     * 输入：inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
     * 输出：[null,null,"bar","bar",null,"bar2","bar2"]
     * 解释： 
     * TimeMap kv;  
     * kv.set("foo", "bar", 1); // 存储键 "foo" 和值 "bar" 以及时间戳 timestamp = 1  
     * kv.get("foo", 1);  // 输出 "bar"  
     * kv.get("foo", 3); // 输出 "bar" 因为在时间戳 3 和时间戳 2 处没有对应 "foo" 的值，所以唯一的值位于时间戳 1 处（即 "bar"）  
     * kv.set("foo", "bar2", 4);  
     * kv.get("foo", 4); // 输出 "bar2"  
     * kv.get("foo", 5); // 输出 "bar2"  
     *
     * 示例 2：
     * 输入：inputs = ["TimeMap","set","set","get","get","get","get","get"],
     * inputs = [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
     * 输出：[null,null,null,"","high","high","low","low"]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/time-based-key-value-store
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        _981NineHundredEightyOne test = new _981NineHundredEightyOne();
        test.set("love","high",10);
        test.set("love","low",20);
        System.out.println(test.get("love",5));
        System.out.println(test.get("love",10));
        System.out.println(test.get("love",15));
        System.out.println(test.get("love",20));
        System.out.println(test.get("love",25));
    }

}
