package letcode.normal.easy;

/**
 * 1385. Find the Distance Value Between Two Arrays
 * Difficulty: Easy
 * Link: https://leetcode.cn/problems/find-the-distance-value-between-two-arrays/
 * <p>
 * Given two integer arrays arr1 and arr2 , and the integer d , return the distance value between the
 * two arrays .
 * <p>
 * The distance value is defined as the number of elements arr1[i] such that there is not any element
 * arr2[j] where |arr1[i]-arr2[j]| <= d .
 * <p>
 * Example 1:
 * <p>
 * Input: arr1 = [4,5,8], arr2 = [10,9,1,8], d = 2
 * Output: 2
 * Explanation:
 * For arr1[0]=4 we have:
 * |4-10|=6 > d=2
 * |4-9|=5 > d=2
 * |4-1|=3 > d=2
 * |4-8|=4 > d=2
 * For arr1[1]=5 we have:
 * |5-10|=5 > d=2
 * |5-9|=4 > d=2
 * |5-1|=4 > d=2
 * |5-8|=3 > d=2
 * For arr1[2]=8 we have:
 * |8-10|=2 <= d=2
 * |8-9|=1 <= d=2
 * |8-1|=7 > d=2
 * |8-8|=0 <= d=2
 * <p>
 * Example 2:
 * <p>
 * Input: arr1 = [1,4,2,3], arr2 = [-4,-3,6,10,20,30], d = 3
 * Output: 2
 * <p>
 * Example 3:
 * <p>
 * Input: arr1 = [2,1,100,3], arr2 = [-5,-2,10,-3,7], d = 6
 * Output: 1
 * <p>
 * Constraints:
 * <p>
 * - 1 <= arr1.length, arr2.length <= 500
 * <p>
 * - -1000 <= arr1[i], arr2[j] <= 1000
 * <p>
 * - 0 <= d <= 100
 */
public class _1385 {

    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int[] sortArr = new int[3001];
        for (int num : arr2) {
            sortArr[num + 1000] = 1;
        }

        int[] preSum = new int[3001];
        preSum[0] = 0;
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + sortArr[i];
        }

        int dist = arr1.length;
        for (int num : arr1) {
            if (preSum[num + d + 1000] > 0 &&
                    preSum[num + d + 1000] > (num - d - 1 + 1000 < 0 ? 0 : preSum[num - d - 1 + 1000])) {
                --dist;
            }
        }
        return dist;
    }
}
