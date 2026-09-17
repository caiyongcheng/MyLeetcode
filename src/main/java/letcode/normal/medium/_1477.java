package letcode.normal.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 1477. Find Two Non-overlapping Sub-arrays Each With Target Sum
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/
 * <p>
 * You are given an array of integers arr and an integer target .
 * <p>
 * You have to find two non-overlapping sub-arrays of arr each with a sum equal target . There can be
 * multiple answers so you have to find an answer where the sum of the lengths of the two sub-arrays is
 * minimum .
 * <p>
 * Return the minimum sum of the lengths of the two required sub-arrays, or return -1 if you cannot
 * find such two sub-arrays.
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [3,2,2,4,3], target = 3
 * Output: 2
 * Explanation: Only two sub-arrays have sum = 3 ([3] and [3]). The sum of their lengths is 2.
 * <p>
 * Example 2:
 * <p>
 * Input: arr = [7,3,4,7], target = 7
 * Output: 2
 * Explanation: Although we have three non-overlapping sub-arrays of sum = 7 ([7], [3,4] and [7]), but
 * we will choose the first and third sub-arrays as the sum of their lengths is 2.
 * <p>
 * Example 3:
 * <p>
 * Input: arr = [4,3,2,6,2,3,4], target = 6
 * Output: -1
 * Explanation: We have only one sub-array of sum = 6.
 * <p>
 * Constraints:
 * <p>
 * - 1 <= arr.length <= 10 5
 * <p>
 * - 1 <= arr[i] <= 1000
 * <p>
 * - 1 <= target <= 10 8
 */
public class _1477 {

    public int minSumOfLengths(int[] arr, int target) {

        // 保存匹配的子数组
        List<int[]> subArrays = new ArrayList<>();

        int start = 0;
        int end = 0;
        int curSum = arr[0];

        // 复杂度n
        while (start < arr.length) {
            while (curSum < target && end < arr.length - 1) {
                curSum += arr[++end];
            }
            if (curSum == target) {
                subArrays.add(new int[]{start, end - start + 1});
                curSum -= arr[start++];
            } else if (curSum < target) {
                break;
            }  else {
                curSum -= arr[start++];
            }
        }

        int size = subArrays.size();
        if (size < 2) {
            return -1;
        }

        // 预先计算好后缀数组
        int[] suffixMinLen = new int[size];
        suffixMinLen[size - 1] = subArrays.get(size - 1)[1];
        for (int i = suffixMinLen.length - 2; i >= 0; i--) {
            suffixMinLen[i] = Math.min(suffixMinLen[i + 1], subArrays.get(i)[1]);
        }

        // 复杂度n
        int ans = arr.length + 1;
        int l = 0;
        int r = 1;
        int[] lSubArray;
        int[] rSubArray;
        while (l < size - 1) {
            lSubArray = subArrays.get(l);
            while (r < size) {
                rSubArray = subArrays.get(r);
                if (rSubArray[0] > lSubArray[0] + lSubArray[1] - 1) {
                    ans = Math.min(ans, lSubArray[1] + suffixMinLen[r]);
                    break;
                }
                ++r;
            }
            ++l;
        }

        return ans > size ? -1 : ans;

    }





}
