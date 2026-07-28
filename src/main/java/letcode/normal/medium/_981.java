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
 * @since 2021-07-10 22:28
 **/
public class _981 {


    class InValue {
        int time;
        String value;

        public InValue(int time, String value) {
            this.time = time;
            this.value = value;
        }
    }

    HashMap<String, ArrayList<InValue>> dataMap;

    public _981() {
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

}
