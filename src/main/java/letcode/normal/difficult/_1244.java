package letcode.normal.difficult;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author 蔡永程
 * @since 2022/8/18 14:12
 */
public class _1244 {


    public int maxEqualFreq(int[] nums) {
        /*
         * 维护一个map a，key是次数，value是数量。
         * 维护一个map b，key是数值，value是次数。
         * 不停遍历， 从b中获取次数，再更新a。
         * 然后进行判断：
         * 如果其他数的次数都一样，有一个数的次数比其他数多1，那么删掉一个这个数可以满足条件；或者这个数只出现了一次，那么删除也满足条件。
         * 或者只有这个数，也满足条件
         *
         * 补充 使用计数排序的方法 代替hash更快
         */
        int maxLen = 0;
        Map<Integer, Integer> cntToItemCnt = new HashMap<>();
        Map<Integer, Integer> valToCnt = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer cnt = valToCnt.getOrDefault(nums[i], 0);
            valToCnt.put(nums[i], cnt + 1);
            Integer itemCnt = cntToItemCnt.getOrDefault(cnt, 0);
            if (itemCnt == 1) {
                cntToItemCnt.remove(cnt);
            } else if (itemCnt > 1) {
                cntToItemCnt.put(cnt, itemCnt - 1);
            }
            cntToItemCnt.put(cnt + 1, cntToItemCnt.getOrDefault(cnt + 1, 0) + 1);
            if (cntToItemCnt.size() == 1) {
                //出现次数都一样 都是1
                if (valToCnt.get(nums[i]) == 1) {
                    maxLen = i + 1;
                } else if (cntToItemCnt.get(valToCnt.get(nums[i])) == 1) {
                    //只出现了一个数
                    maxLen = i + 1;
                }
            } else if (cntToItemCnt.size() == 2) {
                //出现次数两种情况，出现1次的只有一种
                if (cntToItemCnt.getOrDefault(1, 0) == 1) {
                    maxLen = i + 1;
                } else {
                    //有一个数比其他数字多出现了一次
                    Iterator<Map.Entry<Integer, Integer>> entryIterator = cntToItemCnt.entrySet().iterator();
                    Map.Entry<Integer, Integer> e1 = entryIterator.next();
                    Map.Entry<Integer, Integer> e2 = entryIterator.next();
                    if (e1.getKey() + 1 == e2.getKey() && e2.getValue() == 1) {
                        maxLen = i + 1;
                    } else if (e2.getKey() + 1 == e1.getKey() && e1.getValue() == 1) {
                        maxLen = i + 1;
                    }
                }
            }
        }
        return maxLen;
    }


}
