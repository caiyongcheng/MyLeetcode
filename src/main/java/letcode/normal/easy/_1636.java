package letcode.normal.easy;

import datastructure.utils.FormatPrintUtils;

/**
 * @author Caiyongcheng
 * @description 给你一个整数数组nums，请你将数组按照每个值的频率 升序 排序。如果有多个值的频率相同，
 * 请你按照数值本身将它们 降序 排序。  请你返回排序后的数组。
 * 来源：力扣（LeetCode） 链接：https://leetcode.cn/problems/sort-array-by-increasing-frequency
 * 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 * @since 2022/9/19 17:55
 */
public class _1636 {

    private int[] tempArr;
    private int temp;

    private int[] nums;

    private final int[] cnt = new int[202];

    public int[] frequencySort(int[] nums) {
        this.nums = nums;
        for (int i = 0; i < this.nums.length; i++) {
            this.nums[i] += 100;
            cnt[this.nums[i]]++;
        }
        tempArr = new int[nums.length];
        mergeSort(0, nums.length / 2, nums.length - 1);
        for (int i = 0; i < this.nums.length; i++) {
            this.nums[i] -= 100;
        }
        return nums;
    }


    public void mergeSort(int left, int mid, int right) {
        if (left == right) {
            return;
        }
        if (left + 1 == mid) {
            if (cnt[nums[mid]] < cnt[nums[left]] || (cnt[nums[mid]] == cnt[nums[left]] && nums[mid] > nums[left])) {
                temp = nums[mid];
                nums[mid] = nums[left];
                nums[left] = temp;
            }
        } else if (mid > left) {
            mergeSort(left, (left + mid) / 2, mid);
        }
        if (mid + 2 == right) {
            if (cnt[nums[mid + 1]] > cnt[nums[right]] || (cnt[nums[mid + 1]] == cnt[nums[right]] && nums[mid + 1] < nums[right])) {
                temp = nums[mid + 1];
                nums[mid + 1] = nums[right];
                nums[right] = temp;
            }
        } else if (right > mid + 2) {
            mergeSort(mid + 1, (right + mid + 1) / 2, right);
        }
        int i = left;
        int len = right;
        int rightleft = mid + 1;
        int index = 0;
        while (left <= mid || rightleft <= right) {
            if (left > mid) {
                tempArr[index++] = nums[rightleft++];
                continue;
            }
            if (rightleft > right) {
                tempArr[index++] = nums[left++];
                continue;
            }
            if (cnt[nums[left]] < cnt[nums[rightleft]] || (cnt[nums[left]] == cnt[nums[rightleft]] && nums[left] > nums[rightleft])) {
                tempArr[index++] = nums[left++];
            } else {
                tempArr[index++] = nums[rightleft++];
            }
        }
        for (int j = 0; i <= len; ++i, ++j) {
            nums[i] = tempArr[j];
        }
    }


}
