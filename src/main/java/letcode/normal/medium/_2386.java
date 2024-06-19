package letcode.normal.medium;

import letcode.utils.TestCaseUtils;

/**
 * 给你一个整数数组 nums 和一个 正 整数 k 。你可以选择数组的任一 子序列 并且对其全部元素求和。
 * 数组的 第 k 大和 定义为：可以获得的第 k 个 最大 子序列和（子序列和允许出现重复）  返回数组的 第 k 大和 。
 * 子序列是一个可以由其他数组删除某些或不删除元素排生而来的数组，且派生过程不改变剩余元素的顺序。  注意：空子序列的和视作 0 。
 * n == nums.length
 * 1 <= n <= 105
 * -109 <= nums[i] <= 109
 * 1 <= k <= min(2000, 2n)
 *
 * @author 蔡永程
 * @version 1.0.0`
 * @since 2024/3/9 08:54
 */
public class _2386 {


    public long kSum(int[] nums, int k) {
        /**
         * 从数据规模上来看，如果枚举所有结果 那么可能性会非常多
         * 首先从穷举的角度考虑 set[i]
         * 表示从nums从0-i选择子序列结果的集合
         * 那么对于set[i+1]就可以由从set[0]...set[i]中的每个结果+nums[i]得到
         * 那么我们保留前k个结果即可
         * 也就是每次迭代都保存前k个结果 然后将当前结果与当前nums[i]相加 再保留2k个数据中的k个数据
         */
        long[] sortArr = new long[k];
        long[] tempSortArr = new long[k];
        long[] mergeArr = new long[k];
        long[] tmpMergeArr;
        sortArr[0] = 0;
        int sortSize = 1;
        for (int num : nums) {
            for (int i = 0; i < sortSize; i++) {
                tempSortArr[i] = sortArr[i] + num;
            }
            if (sortSize >= k && sortArr[0] + num <= sortArr[sortSize - 1]) {
                continue;
            }
            merge(sortArr, tempSortArr, sortSize, k, mergeArr);
            tmpMergeArr = mergeArr;
            mergeArr = sortArr;
            sortArr = tmpMergeArr;
            if (sortSize << 1 < k) {
                sortSize <<= 1;
            } else {
                sortSize = k;
            }
        }
        return sortArr[k -1];
    }

    public void merge(long[] sortArr1, long[] sortArr2, int len, int mergeArrLen, long[] mergeArr) {
        int idx1 = 0;
        int idx2 = 0;
        int mergeIdx = 0;
        while (idx1 < len && idx2 < len && mergeIdx < mergeArrLen) {
            if (sortArr1[idx1] > sortArr2[idx2]) {
                mergeArr[mergeIdx++] = sortArr1[idx1++];
            } else {
                mergeArr[mergeIdx++] = sortArr2[idx2++];
            }
        }
        while (idx1 < len && mergeIdx < mergeArrLen) {
            mergeArr[mergeIdx++] = sortArr1[idx1++];
        }
        while (idx2 < len && mergeIdx < mergeArrLen) {
            mergeArr[mergeIdx++] = sortArr2[idx2++];
        }
    }

    /**
     * 示例 1：
     *
     * 输入：nums = [2,4,-2], k = 5
     * 输出：2
     * 解释：所有可能获得的子序列和列出如下，按递减顺序排列：
     * - 6、4、4、2、2、0、0、-2
     * 数组的第 5 大和是 2 。
     * 示例 2：
     *
     * 输入：nums = [1,-2,3,4,-10,12], k = 16
     * 输出：10
     * 解释：数组的第 16 大和是 10 。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2386().kSum(
                TestCaseUtils.getIntArr("[1,-2,3,4,-10,12]"),
                16
        ));
    }

}
