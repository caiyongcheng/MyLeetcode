package letcode.normal.medium;

import letcode.utils.TestCaseInputUtils;
import letcode.utils.TestUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Design a data structure to find the frequency of a given value in a given subarray.
 * The frequency of a value in a subarray is the number of occurrences of that value in the subarray.
 * Implement the RangeFreqQuery class:  RangeFreqQuery(int[] arr) Constructs an instance of the class with
 * the given 0-indexed integer array arr. int query(int left, int right, int value) Returns the frequency
 * of value in the subarray arr[left...right]. A subarray is a contiguous sequence of elements within an array.
 * arr[left...right] denotes the subarray that contains the elements of nums between indices left and right (inclusive).
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-02-18 09:06
 */
public class _2080 {

    private Map<Integer, List<Integer>> num2IdxListMap;

    public _2080(int[] arr) {
        num2IdxListMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            num2IdxListMap.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
    }

    public int query(int left, int right, int value) {
        List<Integer> idxList = num2IdxListMap.get(value);
        int leftIdx = binarySearchCeil(idxList, left);
        if (leftIdx == -1 || leftIdx > right) {
            return 0;
        }
        int rightIdx = binarySearchFloor(idxList, right);
        return rightIdx - leftIdx + 1;
    }

    public int binarySearchCeil(List<Integer> idxList, int targetIdx) {
        if (idxList == null) {
            return -1;
        }
        if (idxList.get(0) >= targetIdx) {
            return 0;
        }
        if (idxList.get(idxList.size() - 1) < targetIdx) {
            return idxList.size();
        }
        int left = 0;
        int right = idxList.size() - 1;
        int mid;
        int idx;
        while (true) {
            mid = (left + right) >> 1;
            if (left == mid) {
                return right;
            }
            idx = idxList.get(mid);
            if (idx == targetIdx) {
                return mid;
            } else if (idx > targetIdx) {
                right = mid;
            } else {
                left = mid;
            }
        }
    }

    public int binarySearchFloor(List<Integer> idxList, int targetIdx) {
        if (idxList == null) {
            return -1;
        }
        if (idxList.get(0) > targetIdx) {
            return -1;
        }
        int size = idxList.size();
        if (idxList.get(size - 1) <= targetIdx) {
            return size - 1;
        }
        int left = 0;
        int right = size - 1;
        int mid;
        int idx;
        while (true) {
            mid = (left + right) >> 1;
            if (left == mid) {
                return left;
            }
            idx = idxList.get(mid);
            if (idx == targetIdx) {
                return mid;
            } else if (idx > targetIdx) {
                right = mid;
            } else {
                left = mid;
            }
        }
    }

}
