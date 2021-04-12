package letcode.normal.medium;

import letcode.utils.FormatPrintUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: MyLeetcode
 * @description: 给定两个以升序排列的整形数组 nums1 和 nums2, 以及一个整数 k。  定义一对值(u,v)，其中第一个元素来自nums1，第二个元素来自 nums2。 
 * 找到和最小的 k 对数字(u1,v1), (u2,v2) ... (uk,vk)。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @date: 2021-04-09 14:29
 **/
public class N_373ThreeHundredSeventyThree {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        if (k > nums1.length * nums2.length) {
            k = nums1.length * nums2.length;
        }
        ArrayList<List<Integer>> ans = new ArrayList<>(k);
        int index1 = 0;
        int index2 = 0;
        int index;
        int limit = 0;
        while (k > 0) {
            ArrayList<Integer> ansItem = new ArrayList<>();
            ansItem.add(nums1[index1]);
            ansItem.add(nums2[index2]);
            ans.add(ansItem);
            --k;
            if (index1 == nums1.length - 1) {
                ++index2;
            } else if (index2 == nums2.length - 1) {
                ++index1;
            } else {
                limit = nums1[index1+1] + nums2[index2+1];
                for (index = index1 + 1; index < nums1.length && nums2[index2] + nums1[index] <= limit; ++index) {
                    if (--k > 0) {
                        ansItem = new ArrayList<>();
                        ansItem.add(nums1[index]);
                        ansItem.add(nums2[index2]);
                        ans.add(ansItem);
                    } else {
                        return ans;
                    }
                }
                for (index = index2 + 1; index < nums2.length && nums2[index] + nums1[index1] <= limit; ++index) {
                    if (--k > 0) {
                        ansItem = new ArrayList<>();
                        ansItem.add(nums1[index1]);
                        ansItem.add(nums2[index]);
                        ans.add(ansItem);
                    } else {
                        return ans;
                    }
                }
                ++index1;
                ++index2;
            }
        }
        return ans;
    }


    /**
     * 示例 1:
     * 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
     * 输出: [1,2],[1,4],[1,6]
     * 解释: 返回序列中的前 3 对数：
     *      [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
     *
     * 示例 2:
     * 输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
     * 输出: [1,1],[1,1]
     * 解释: 返回序列中的前 2 对数：
     *     [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
     *
     * 示例 3:
     * 输入: nums1 = [1,2], nums2 = [3], k = 3
     * 输出: [1,3],[2,3]
     * 解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        List<List<Integer>> lists = new N_373ThreeHundredSeventyThree().kSmallestPairs(
                new int[]{1,1,2},
                new int[]{1,2,3},
                10
        );
        for (List<Integer> list : lists) {
            System.out.println(FormatPrintUtils.formatList(list));
        }
    }

    /**
     * [1,1,2]
     * [1,2,3]
     * 10
     */

}
