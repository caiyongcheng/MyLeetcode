package letcode.normal.medium;

import java.util.Arrays;

/**
 * 元素的 频数 是该元素在一个数组中出现的次数。  给你一个整数数组 nums 和一个整数 k 。在一步操作中，你可以选择 nums 的一个下标，并将该下标对应元素的值增加 1 。
 * 执行最多 k 次操作后，返回数组中最高频元素的 最大可能频数 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/frequency-of-the-most-frequent-element 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-07-19 08:57
 **/
public class _1838 {

    public int maxFrequency(int[] nums, int k) {
        /**
         * total（l，r） 表示 将 arr[l]...arr[r]之间的数变为 arr[r]需要的操作数
         * 也就是total（l，r） = arr[r]*(r-l+1) - sum(l,r) = arr[r]*(r-l) - sum(l,r-1)
         * 所以枚举每个r即可求出结果
         */
        Arrays.sort(nums);
        int n = nums.length;
        long total = 0;
        int l = 0, res = 1;
        for (int r = 1; r < n; ++r) {
            total += (long) (nums[r] - nums[r - 1]) * (r - l);
            while (total > k) {
                total -= nums[r] - nums[l];
                ++l;
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }




}
