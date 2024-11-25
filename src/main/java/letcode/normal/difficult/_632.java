package letcode.normal.difficult;

import letcode.utils.TestUtil;

import java.util.*;

/**
 * You have k lists of sorted integers in non-decreasing order.
 * Find the smallest range that includes at least one number from each of the k lists.
 * We define the range [a, b] is smaller than range [c, d] if b - a < d - c or a < c if b - a == d - c.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-11-25 10:33
 */
public class _632 {

    public int[] smallestRange(List<List<Integer>> nums) {
        /*
        合并成一个列表 不停枚举k个点、k+1个点 等满足条件的时候判断结果
         */
        int[][] mergeList = mergeList(nums);

        int[] kNum2CntMap = new int[nums.size()];
        Set<Integer> outkNumSet = new HashSet<>();
        for (int i = 0; i < nums.size(); i++) {
            outkNumSet.add(i);
        }
        int[] ans = new int[]{mergeList[0][0], mergeList[mergeList.length - 1][0]};
        int i = 0;
        int j = 0;
        kNum2CntMap[mergeList[0][1]]++;
        outkNumSet.remove(mergeList[0][1]);
        for (i = 0; i < mergeList.length; i++) {
            int[] num = mergeList[i];
            while (!outkNumSet.isEmpty()) {
                ++j;
                if (j == mergeList.length) {
                    break;
                }
                int[] num1 = mergeList[j];
                outkNumSet.remove(num1[1]);
                kNum2CntMap[num1[1]]++;
            }
            if (j == mergeList.length) {
                break;
            }
            int[] num1 = mergeList[j];
            if (ans[1] - ans[0] > num1[0] - num[0]) {
                ans = new int[]{num[0], num1[0]};
            }
            kNum2CntMap[num[1]]--;
            if (kNum2CntMap[num[1]] == 0) {
                outkNumSet.add(num[1]);
            }
        }
        return ans;
    }

    private static int[][] mergeList(List<List<Integer>> nums) {
        // 这里要考虑能不能使用类似规避排序的做法
        int len = 0;
        int mergeLen = 0;
        for (List<Integer> list : nums) {
            len += list.size();
        }
        int[][] mergeList = new int[len][2];
        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.get(i).size(); j++) {
                mergeList[mergeLen++] = new int[]{nums.get(i).get(j), i};
            }
        }
        Arrays.sort(mergeList, Comparator.comparingInt((int[] o) -> o[0]).thenComparing(o -> o[1]));
        return mergeList;
    }

    /**
     Example 1:

     Input: nums = [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
     Output: [20,24]
     Explanation:
     List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
     List 2: [0, 9, 12, 20], 20 is in range [20,24].
     List 3: [5, 18, 22, 30], 22 is in range [20,24].
     Example 2:

     Input: nums = [[1,2,3],[1,2,3],[1,2,3]]
     Output: [1,1]


     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test(_632.class);
        TestUtil.testUseTestFile(_632.class);
    }

}
