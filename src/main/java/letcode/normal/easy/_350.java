package normal.easy;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @program: Leetcode
 * @description: 给定两个数组，编写一个函数来计算它们的交集。
 * @author: 蔡永程
 * @create: 2021-01-12 16:15
 */
public class _350 {


    public void quickSortForArray(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int l = left;
        int r = right;
        int separateValue = nums[left];
        while (l < r) {
            while (l < r && nums[r] > separateValue) --r;
            if (l < r) nums[l++] = nums[r];
            while (l < r && nums[l] <= separateValue) ++l;
            if (l < r) nums[r--] = nums[l];
        }
        nums[r] = separateValue;
        quickSortForArray(nums, left, r - 1);
        quickSortForArray(nums, r + 1, right);
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        final ArrayList<Integer> uniteSet = new ArrayList<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int index1 = 0;
        int index2 = 0;
        while (index1 < nums1.length && index2 < nums2.length) {
            if (nums1[index1] < nums2[index2]) {
                ++index1;
            } else if (nums1[index1] > nums2[index2]) {
                ++index2;
            } else {
                uniteSet.add(nums1[index1]);
                ++index1;
                ++index2;
            }
        }
        final int[] resultArray = new int[uniteSet.size()];
        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i] = uniteSet.get(i);
        }
        return resultArray;
    }

}
