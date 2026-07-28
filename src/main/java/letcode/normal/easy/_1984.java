package letcode.normal.easy;

import java.util.Arrays;

/**
 * 给你一个 下标从 0 开始 的整数数组 nums ，其中 nums[i] 表示第 i 名学生的分数。另给你一个整数 k 。
 * 从数组中选出任意 k 名学生的分数，使这 k 个分数间 最高分 和 最低分 的 差值 达到 最小化 。  返回可能的 最小差值 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/minimum-difference-between-highest-and-lowest-of-k-scores
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-02-11 09:06
 **/
public class _1984 {

    public int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i + k <= nums.length; i++) {
            if (nums[i + k - 1] - nums[i] < ans) {
                ans = nums[i + k - 1] - nums[i];
            }
        }
        return ans;
    }

}
