package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given a 0-indexed 2D array variables where variables[i] = [ai, bi, ci, mi], and an integer target.
 * An index i is good if the following formula holds
 * 0 <= i < variables.length
 * ((aibi % 10)ci) % mi == target
 * Return an array consisting of good indices in any order.
 * 
 * 1 <= variables.length <= 100
 * variables[i] == [ai, bi, ci, mi]
 * 1 <= ai, bi, ci, mi <= 103
 * 0 <= target <= 103
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-07-30 09:30
 */
public class _2961 {

    public List<Integer> getGoodIndices(int[][] variables, int target) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < variables.length; i++) {
            if (mod(mod(variables[i][0], variables[i][1], 10), variables[i][2], variables[i][3]) == target) {
                result.add(i);
            }
        }
        return result;
    }

    private int mod(int a, int b, int c) {
        int ans = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                ans = ans * a % c;
            }
            a = a * a % c;
            b = b >> 1;
        }
        return ans;
    }

    /**
     * Example 1:
     *
     * Input: variables = [[2,3,3,10],[3,3,3,1],[6,1,1,4]], target = 2
     * Output: [0,2]
     * Explanation: For each index i in the variables array:
     * 1) For the index 0, variables[0] = [2,3,3,10], (23 % 10)3 % 10 = 2.
     * 2) For the index 1, variables[1] = [3,3,3,1], (33 % 10)3 % 1 = 0.
     * 3) For the index 2, variables[2] = [6,1,1,4], (61 % 10)1 % 4 = 2.
     * Therefore we return [0,2] as the answer.
     * Example 2:
     *
     * Input: variables = [[39,3,1000,1000]], target = 17
     * Output: []
     * Explanation: For each index i in the variables array:
     * 1) For the index 0, variables[0] = [39,3,1000,1000], (393 % 10)1000 % 1000 = 1.
     * Therefore we return [] as the answer.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.testWithTestClassFile(_2961.class);
    }

}
