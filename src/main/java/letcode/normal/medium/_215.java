package letcode.normal.medium;

/**
 * @program: Leetcode
 * @description: 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，
 * 而不是第 k 个不同的元素。
 * @author: 蔡永程
 * @create: 2021-01-06 10:46
 */
public class _215 {


    public int segmentation(int[] nums, int left, int right, int k) {
        if (left >= right) {
            return nums[left];
        }
        if (left + 1 == right) {
            if (right == k) {
                return Math.min(nums[left], nums[right]);
            }
            return Math.max(nums[left], nums[right]);
        }
        int l = left;
        int r = right;
        int iniValue = nums[left];
        while (l < r) {
            while (l < r && nums[r] < iniValue) --r;
            if (r <= l) break;
            nums[l++] = nums[r];
            while (l < r && nums[l] >= iniValue) ++l;
            if (l >= r) break;
            nums[r--] = nums[l];
        }
        nums[l] = iniValue;
        if (r == k) {
            return nums[r];
        } else if (r > k) {
            return segmentation(nums, left, r - 1, k);
        } else {
            return segmentation(nums, r + 1, right, k);
        }
    }

    public int findKthLargest(int[] nums, int k) {
        return segmentation(nums, 0, nums.length - 1, k - 1);
    }

}
