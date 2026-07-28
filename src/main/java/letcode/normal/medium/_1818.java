package letcode.normal.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给你两个正整数数组 nums1 和 nums2 ，数组的长度都是 n 。  数组 nums1 和 nums2 的 绝对差值和 定义为所有 |nums1[i] - nums2[i]|（0 <= i < n）的 总和（下标从 0 开始）。
 * 你可以选用 nums1 中的 任意一个 元素来替换 nums1 中的 至多 一个元素，以 最小化 绝对差值和。  在替换数组 nums1 中最多一个元素 之后 ，返回最小绝对差值和。
 * 因为答案可能很大，所以需要对 109 + 7 取余 后返回。  |x| 定义为：  如果 x >= 0 ，值为 x ，或者 如果 x <= 0 ，值为 -x
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/minimum-absolute-sum-difference 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-07-14 09:56
 **/
public class _1818 {

    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        /**
         * 考虑 如果要换的话
         * 换哪个位置上的 用哪个值去换
         * 如果我们确定换i位置上的， 用nums1中与nums[i]最接近的值去换即可
         * 如果 暴力的话 每个位置需要遍历n 找出最接近的值 logn 注意溢出
         *
         * 考虑第二种方式
         * 按相应位置的差距排序 优先考虑差距大的 一直处理 处理到后面的变化值 无法超过当前的变化值位置
         * 变化值是
         *
         * 两种方式本质是一样的，只是第二种遍历过程中不考虑溢出
         *
         */
        int length = nums1.length;
        int[][] different = new int[length][2];
        //表示原本差距减去现在差距，越大越好
        int variation = 0;
        int maxVariation = 0;
        int modValue = 1000000000 + 7;
        int ans = 0;
        int index = 0;
        for (int i = nums1.length - 1; i >= 0; i--) {
            different[i] = new int[]{Math.abs(nums1[i] - nums2[i]), i};
        }
        Arrays.sort(different, Comparator.comparingInt(o -> o[0]));
        Arrays.sort(nums1);
        for (int i = different.length - 1; i >= 0 && maxVariation < different[i][0]; i--) {
            variation = different[i][0] - binarySearch(nums1, nums2[different[i][1]]);
            if (variation > maxVariation) {
                index = different[i][1];
                maxVariation = variation;
            }
        }
        different[index][0] -= maxVariation;
        for (int[] differ : different) {
            ans = (ans + differ[0]) % modValue;
        }
        return ans;
    }


    public int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        int mid = 0;
        int ans = 0;
        while (left <= right) {
            mid = (left + right) >>> 1;
            if (array[mid] > target) {
                right = mid - 1;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                return 0;
            }
        }
        ans = Math.abs(array[mid] - target);
        if (mid - 1 > -1) {
            ans = Math.min(ans, Math.abs(array[mid-1] - target));
        }
        if (mid +1  < array.length) {
            ans = Math.min(ans, Math.abs(array[mid+1] - target));
        }
        return ans;
    }

}
