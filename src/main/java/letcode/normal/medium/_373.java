package letcode.normal.medium;
import datastructure.utils.FormatPrintUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @program: MyLeetcode
 * @description: 给定两个以升序排列的整形数组 nums1 和 nums2, 以及一个整数 k。  定义一对值(u,v)，其中第一个元素来自nums1，第二个元素来自 nums2。
 * 找到和最小的 k 对数字(u1,v1), (u2,v2) ... (uk,vk)。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @since: 2021-04-09 14:29
 **/
public class _373 {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        /*
         * 使用优先队列 维护 nums1的从0-length-1下标 与 nums2对应下标构成数据
         * 不停取出即可
         */
        if (nums1.length * nums2.length < k) {
            k = nums1.length * nums2.length;
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(nums1.length, (o1, o2) -> nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]]);
        List<List<Integer>> ans = new ArrayList<>(k);
        List<Integer> item;
        int[] arr;
        for (int i = 0; i < nums1.length; i++) {
            queue.add(new int[]{i, 0});
        }
        while (k > 0) {
            arr = queue.poll();
            item = new ArrayList<>(2);
            item.add(nums1[arr[0]]);
            item.add(nums2[arr[1]]);
            ans.add(item);
            if (arr[1] + 1 < nums2.length) {
                queue.add(new int[]{arr[0], arr[1] + 1});
            }
            --k;
        }
        return ans;
    }

}
