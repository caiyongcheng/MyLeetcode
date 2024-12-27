package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given an integer array nums, an integer array queries, and an integer x.
 * For each queries[i], you need to find the index of the queries[i]th occurrence of x in the nums array.
 * If there are fewer than queries[i] occurrences of x, the answer should be -1 for that query.
 * Return an integer array answer containing the answers to all queries.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-12-27 14:46
 */
public class _3159 {

    public int[] occurrencesOfElement(int[] nums, int[] queries, int x) {
        List<Integer> idxList = new ArrayList<>();
        int[] ans = new int[queries.length];
        int searchStart = 0;

        for (int i = 0; i < queries.length; i++) {
            if (queries[i] <= idxList.size()) {
                ans[i] = idxList.get(queries[i] - 1);
                continue;
            }
            while (searchStart < nums.length && queries[i] > idxList.size()) {
                if (nums[searchStart] == x) {
                    idxList.add(searchStart);
                }
                searchStart++;
            }
            ans[i] = queries[i] <= idxList.size() ? idxList.get(queries[i] - 1) : -1;
        }

        return ans;
    }


    /**
     * Example 1:
     *
     * Input: nums = [1,3,1,7], queries = [1,3,2,4], x = 1
     *
     * Output: [0,-1,2,-1]
     *
     * Explanation:
     *
     * For the 1st query, the first occurrence of 1 is at index 0.
     * For the 2nd query, there are only two occurrences of 1 in nums, so the answer is -1.
     * For the 3rd query, the second occurrence of 1 is at index 2.
     * For the 4th query, there are only two occurrences of 1 in nums, so the answer is -1.
     * Example 2:
     *
     * Input: nums = [1,2,3], queries = [10], x = 5
     *
     * Output: [-1]
     *
     * Explanation:
     *
     * For the 1st query, 5 doesn't exist in nums, so the answer is -1.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test(_3159.class);
    }

}
