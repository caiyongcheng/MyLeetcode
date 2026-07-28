package letcode.normal.medium;

import letcode.utils.TestCaseOutputUtils;

import java.util.Arrays;

/**
 * @program: MyLeetcode
 * @description: 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @since: 2021-03-15 14:41
 **/
public class _347 {

    static class Entry{
        int k;
        int v;

        public Entry(int k, int v) {
            this.k = k;
            this.v = v;
        }

        public int getK() {
            return k;
        }

        public void setK(int k) {
            this.k = k;
        }

        public int getV() {
            return v;
        }

        public void setV(int v) {
            this.v = v;
        }
    }


    public void quickDivide(Entry[] entries, int lo, int hi, int k) {
        int l = lo;
        int r = hi;
        Entry iniV = entries[lo];
        while (lo < hi) {
            while (hi > lo && entries[hi].v < iniV.v) {
                --hi;
            }
            if (hi > lo) {
                entries[lo++] = entries[hi];
            }
            while (hi > lo && entries[lo].v >= iniV.v) {
                ++lo;
            }
            if (hi > lo) {
                entries[hi--] = entries[lo];
            }
        }
        entries[hi] = iniV;
        if (hi == k + l) {
            return;
        } else if (hi < k + l) {
            quickDivide(entries, hi+1, r, k - hi - 1 + l);
        } else {
            quickDivide(entries, l, hi-1, k);
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        Arrays.sort(nums);
        Entry[] entries = new Entry[nums.length];
        int eLength = 0;
        int[] ints = new int[k];
        for (int i = 0; i < nums.length; ) {
            int j = i;
            while (j < nums.length && nums[i] == nums[j]) {
                ++j;
            }
            entries[eLength++] = new Entry(nums[i], j-i);
            i = j;
        }
        quickDivide(entries, 0, eLength-1, k-1);
        for (int i = 0; i < k; i++) {
            ints[i] = entries[i].k;
        }
        return ints;
    }

}
