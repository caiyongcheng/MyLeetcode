package letcode.normal.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/8/4 17:27
 * description 给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，
 * 该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。  如果不存在满足条件的子数组，则返回 0 。
 */
public class _1438 {

    public int longestSubarray(int[] nums, int limit) {

        TreeMap<Integer, Integer> minMap = new TreeMap<>(Integer::compare);
        TreeMap<Integer, Integer> maxMap = new TreeMap<>((n1, n2) -> -Integer.compare(n1, n2));
        maxMap.put(nums[0], 0);
        minMap.put(nums[0], 0);
        int rst = 0;
        int rangeMax = nums[0];
        int rangeMin = nums[0];
        int curLen = 1;
        for (int i = 1; i < nums.length; i++) {
            if (Math.abs(nums[i] - rangeMin) <= limit && Math.abs(rangeMax - nums[i]) <= limit) {
                ++curLen;
            } else {
                rst = Math.max(curLen, rst);
                //丢弃不满足部分
                List<Integer> rmKey = new ArrayList<>();
                for (Integer integer : minMap.keySet()) {
                    rangeMin = integer;
                    if (rangeMax - rangeMin > limit) {
                        curLen = i - minMap.get(rangeMin);
                        rmKey.add(rangeMin);
                    } else {
                        break;
                    }
                }
                if (!rmKey.isEmpty()) {
                    for (Integer key : rmKey) {
                        minMap.remove(key);
                    }
                }
                rmKey = new ArrayList<>();
                for (Integer integer : maxMap.keySet()) {
                    rangeMax = integer;
                    if (rangeMax - rangeMin > limit) {
                        curLen = i - maxMap.get(rangeMax);
                        rmKey.add(rangeMax);
                    } else {
                        break;
                    }
                }
                if (!rmKey.isEmpty()) {
                    for (Integer key : rmKey) {
                        maxMap.remove(key);
                    }
                }
            }
            maxMap.put(nums[i], i);
            minMap.put(nums[i], i);
            rangeMin = minMap.keySet().iterator().next();
            rangeMax = maxMap.keySet().iterator().next();
        }
        rst = Math.max(curLen, rst);
        return rst;
    }

    public static void main(String[] args) {
        System.out.println(new _1438().longestSubarray(
                new int[]{
                        8, 2, 4, 7
                },
                3
        ));
    }

}
