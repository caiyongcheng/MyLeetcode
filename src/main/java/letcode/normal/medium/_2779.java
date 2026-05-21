package letcode.normal.medium;

import letcode.utils.TestCaseInputUtils;

import java.util.Arrays;

/**
 * 给你一个下标从 0 开始的整数数组 nums 和一个 非负 整数 k 。
 * 在一步操作中，你可以执行下述指令：  在范围 [0, nums.length - 1] 中选择一个 此前没有选过 的下标 i 。
 * 将 nums[i] 替换为范围 [nums[i] - k, nums[i] + k] 内的任一整数。
 * 数组的 美丽值 定义为数组中由相等元素组成的最长子序列的长度。
 * 对数组 nums 执行上述操作任意次后，返回数组可能取得的 最大 美丽值。
 * 注意：你 只 能对每个下标执行 一次 此操作。
 * 数组的 子序列 定义是：经由原数组删除一些元素（也可能不删除）得到的一个新数组，且在此过程中剩余元素的顺序不发生改变。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-06-17 09:03
 */
public class _2779 {


    public int maximumBeauty1(int[] nums, int k) {
        /*
        相对于maximumBeauty的优化在于 maximumBeauty枚举每个元素，然后分别计算三种情况，然后取最大值
        实际上nums已经是排序的 可以利用上一次的检索结果 往前不停的追加窗口即可


        第二种 换个角度考虑 对于一个数 num，可以变成的数的范围是 [num - k, num + k];
        那么定义一个数组p, p[i]表示有多少个num可以变成i；
        明显对于每个num，p[num - k, num + k]的范围内都要+1，
        此时运用差分数组即可
         */

        int m = 0;
        for (int x : nums) {
            m = Math.max(m, x);
        }
        // 如果低于0或者超过m，都没意义，能变成比m大的数和变成m并没有区别
        int[] diff = new int[m + 2];
        for (int x : nums) {
            diff[Math.max(x - k, 0)]++;
            diff[Math.min(x + k + 1, m + 1)]--;
        }
        int res = 0, count = 0;
        for (int x : diff) {
            count += x;
            res = Math.max(res, count);
        }
        return res;


    }


    public int maximumBeauty(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = 0;
        for (int num : nums) {
            ans = Math.max(ans, binarySearchFloor(nums, num + k) - binarySearchCell(nums, num - k) - 1);
            ans = Math.max(ans, binarySearchFloor(nums, num + k + k) - binarySearchCell(nums, num) - 1);
            ans = Math.max(ans, binarySearchFloor(nums, num) - binarySearchCell(nums, num - k - k) - 1);
        }
        return ans;
    }


    public int binarySearchFloor(int[] nums, int target) {
        if (nums[nums.length - 1] <= target) {
            return nums.length;
        }
        if (nums[0] > target) {
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while (left < right) {
            mid = (left + right) >> 1;
            if (mid == left) {
                return right;
            }
            if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }


    public int binarySearchCell(int[] nums, int target) {
        if (nums[nums.length - 1] < target) {
            return nums.length - 1;
        }
        if (nums[0] >= target) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while (left < right) {
            mid = (left + right) >> 1;
            if (mid == left) {
                return left;
            }
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return left;
    }


}
