package letcode.normal.difficult;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 给你一个字符串 s ，考虑其所有 重复子串 ：即，s 的连续子串，在 s 中出现 2 次或更多次。这些出现之间可能存在重叠。
 * 返回 任意一个 可能具有最长长度的重复子串。如果 s 不含重复子串，那么答案为 "" 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/longest-duplicate-substring 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-12-23 09:02
 **/
public class _1044 {


    private int[] subSum;

    private int[] pow;

    private final int P = 13131;

    private int length;

    private String str;


    public String longestDupSubstring(String s) {
        /*
         * 二分查找 长度[2,n-1]范围内符合要求的字符串
         * 保证左边一定成立 右边一定不成立
         * 验证的时候使用hash 计算出长度为l的的字符串Hash值 如果该长度的hash值出现了 就认为是相等的字符串
         * 对于字符串，hash使用类似于jdk的方法，只不过乘的质数不一样 这样可以使用前缀和的方法
         */
        //编码
        str = s;
        length = s.length();
        subSum = new int[length];
        pow = new int[length];
        subSum[0] = (s.charAt(0) - 'a' + 1);
        pow[0] = 1;
        int ansStart = -1;
        int tmpStart;
        int len = -1;
        HashMap<Character, Integer> hashMap = new HashMap<>();
        hashMap.put(s.charAt(0), 0);
        for (int i = 1; i < subSum.length; i++) {
            subSum[i] = subSum[i - 1] * P + (s.charAt(i) - 'a' + 1);
            pow[i] = pow[i - 1] * P;
            if (hashMap.containsKey(s.charAt(i))) {
                len = 1;
                ansStart = i;
            }
            hashMap.put(s.charAt(i), i);
        }
        //二分
        int left = 1;
        int right = length;
        int mid;
        while (left < right) {
            mid = (left + right) >> 1;
            if (mid == left) {
                break;
            }
            tmpStart = hasRepeatSubstring(mid);
            if (tmpStart > -1) {
                left = mid;
                ansStart = tmpStart;
                len = mid;
            } else {
                right = mid;
            }
        }
        return len < 0 ? "" : s.substring(ansStart, ansStart + len);
    }


    public int hasRepeatSubstring(int len) {
        /*
        用arraylist主要是验证hash碰撞
         */
        HashMap<Integer, ArrayList<Integer>> hashMap = new HashMap<>();
        int hash = subSum[len - 1];
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        hashMap.put(hash, list);
        int startLimit = length - len + 1;
        for (int start = 1; start < startLimit; start++) {
            hash = (hash - (str.charAt(start - 1) - 'a' + 1) * pow[len - 1]) * P + str.charAt(start + len - 1) - 'a' + 1;
            ArrayList<Integer> arrayList = hashMap.getOrDefault(hash, new ArrayList<>());
            if (!arrayList.isEmpty()) {
                for (Integer lastIndex : arrayList) {
                    if (str.substring(start, start + len).equals(str.substring(lastIndex, lastIndex + len))) {
                        return start;
                    }
                }
            }
            arrayList.add(start);
            hashMap.put(hash, arrayList);
        }
        return -1;
    }


}
