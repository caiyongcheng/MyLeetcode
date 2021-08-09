package letcode.normal.medium;

import java.util.Arrays;

/**
 * 给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。
 * @author CaiYongcheng
 * @date 2021-08-04 09:05
 **/
public class _611SixHundredEleven {

    public int triangleNumber(int[] nums) {
        /**
         * 朴素方法 暴力+二分搜索
         */
        Arrays.sort(nums);
        int ans = 0;
        int maxIndex;
        for (int i = 0; i < nums.length-2; i++) {
            for (int j = i+1; j < nums.length-1; j++) {
                if (nums[j+1] >= nums[i] + nums[j]) {
                    continue;
                }
                maxIndex = binarySearch(nums, j+1, nums[i]+nums[j]);
                ans += maxIndex - j;
            }
        }
        return ans;
    }



    public int binarySearch(int[] arr, int startIndex, int limit) {
        int right = arr.length;
        int mid;
        while (startIndex < right) {
            mid = (startIndex + right) >> 1;
            if (arr[mid] >= limit) {
                right = mid;
            } else {
                if (startIndex == mid) {
                    break;
                }
                startIndex = mid;
            }
        }
        return startIndex;
    }


    /**
     * 示例 1:
     * 输入: [2,2,3,4]
     * 输出: 3
     * 解释:
     * 有效的组合是:
     * 2,3,4 (使用第一个 2)
     * 2,3,4 (使用第二个 2)
     * 2,2,3
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _611SixHundredEleven().triangleNumber(
                new int[]{2,2,3,4}
        ));
    }


}
