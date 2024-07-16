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
        _981 test = new _981();
        test.set("love","high",10);
        test.set("love","low",20);
        System.out.println(test.get("love",5));
        System.out.println(test.get("love",10));
        System.out.println(test.get("love",15));
        System.out.println(test.get("love",20));
        System.out.println(test.get("love",25));
    }

}
