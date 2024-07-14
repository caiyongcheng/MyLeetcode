package letcode.normal.medium;

import letcode.utils.TestCaseUtils;

/**
 * 给你一个整数数组 nums。  返回两个（不一定不同的）质数在 nums 中 下标 的 最大距离。
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 105
 * 1 <= nums[i] <= 100
 * 输入保证 nums 中至少有一个质数。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-07-02 09:54
 */
public class _3115 {

    static int[] primeArr = new int[101];

    static {
        primeArr[0] = 1;
        primeArr[1] = 1;
        // 使用筛法
        for (int i = 2; i < 51; i++) {
            if (primeArr[i] == 0) {
                continue;
            }
            for (int j = 2; j * i < 101; j++) {
                primeArr[j * i] = 1;
            }
        }
    }

    public int maximumPrimeDifference(int[] nums) {
        int leftIdx = 0;
        int rightIdx = 0;

        for (leftIdx = 0; leftIdx < nums.length; leftIdx++) {
            if (primeArr[nums[leftIdx]] == 0) {
                break;
            }
        }

        for (rightIdx = nums.length - 1; rightIdx >= 0; rightIdx--) {
            if (primeArr[nums[rightIdx]] == 0) {
                break;
            }
        }

        return rightIdx - leftIdx;
    }


    /**
     * 示例 1：
     * 输入： nums = [4,2,9,5,3]
     * 输出： 3
     * 解释： nums[1]、nums[3] 和 nums[4] 是质数。因此答案是 |4 - 1| = 3。
     * 示例 2：
     * 输入： nums = [4,8,2,8]
     * 输出： 0
     * 解释： nums[2] 是质数。因为只有一个质数，所以答案是 |2 - 2| = 0。
     */
    public static void main(String[] args) {
        System.out.println(new _3115().maximumPrimeDifference(
                TestCaseUtils.getIntArr("[4,8,2,8]")
        ));
    }

}
