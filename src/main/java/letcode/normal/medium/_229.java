package letcode.normal.medium;

import letcode.utils.TestCaseOutputUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 *
 * @author CaiYongcheng
 * @since 2021-10-22 09:05
 **/
public class _229 {

    public List<Integer> majorityElement(int[] nums) {
        /*
         * 摩尔投票 求n个数中出现次数 大于 n/k 的元素[x1..xn]
         * 设 n个数中出现次数 大于 n/k 的元素 的个数为p
         * 则 k * (n/k) = n => p < k
         * 取 k - 1 个桶，对元素遍历，
         * 如果 有空桶 就放入
         * 否则 如果 当前元素等于桶里的某个元素，那么该桶值+1
         * 否则 如果 整体-1
         * 那么 [x1..xn] 最后就会剩余在桶中
         * 因为 对于 xi 而言，
         * 如果每次选择都不在桶中，则每次都可以抵消k-1元素，连自己在内一共是k个元素，而count(xi)是大于n/k的，所以
         * count(xi)*k > n，故一定会剩余。
         * 如果xi在桶中了，则xi会出现count(xi)次，xi最后不在桶中，说明被抵消了，同时也有k-1个（包括当前遍历元素）被抵消，一共k个元素。
         * 而能抵消xi，则抵消次数p >= count(xi) > n/k, 结论同上
         */
        int bucket1 = 0;
        int bucket2 = 0;
        int count1 = 0;
        int count2 = 0;
        ArrayList<Integer> ans = new ArrayList<>(2);
        for (int num : nums) {
            if (bucket1 == num && count1 > 0) {
                ++count1;
            } else if (bucket2 == num && count2 > 0) {
                ++count2;
            } else if (count1 == 0) {
                bucket1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                bucket2 = num;
                count2 = 1;
            } else {
                --count1;
                --count2;
            }
        }
        extracted(nums, bucket1, count1, ans);
        extracted(nums, bucket2, count2, ans);
        return ans;
    }

    private void extracted(int[] nums, int bucket1, int count1, ArrayList<Integer> ans) {
        if (count1 > 0) {
            count1 = 0;
            for (int num : nums) {
                if (num == bucket1) {
                    ++count1;
                }
            }
            if (count1 > nums.length / 3) {
                ans.add(bucket1);
            }
        }
    }

}
