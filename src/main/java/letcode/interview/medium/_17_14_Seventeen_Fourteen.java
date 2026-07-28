package letcode.interview.medium;

import letcode.utils.TestCaseOutputUtils;

import java.util.Arrays;

/**
 * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
 *
 * @author CaiYongcheng
 * @since 2021-09-03 09:08
 **/
public class _17_14_Seventeen_Fourteen {


    public int[] smallestK(int[] arr, int k) {
        useQuickSort(arr, 0, arr.length - 1, k - 1);
        return Arrays.copyOf(arr, k);
    }


    public void useQuickSort(int[] arr, int start, int end, int target) {
        if (start >= arr.length || end < 0) {
            return;
        }
        int separator = arr[start];
        int left = start;
        int right = end;
        while (left < right) {
            while (left < right && arr[right] > separator) {
                --right;
            }
            if (right > left) {
                arr[left++] = arr[right];
            }
            while (left < right && arr[left] <= separator) {
                ++left;
            }
            if (right > left) {
                arr[right--] = arr[left];
            }
        }
        arr[right] = separator;
        if (right > target) {
            useQuickSort(arr, start, right - 1, target);
        } else if (right < target) {
            useQuickSort(arr, right+1, end, target);
        }
    }


}
