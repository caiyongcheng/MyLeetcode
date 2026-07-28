package letcode.normal.medium;

import java.util.Arrays;

/**
 * @program: MyLeetcode
 * @description: 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
 * 这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
 * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/house-robber-ii 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.medium
 * @author: CaiYongcheng
 * @since: 2021-04-15 11:53
 **/
public class _213 {


    public int rob(int[] nums) {
        // 根据题目描述 不能同时取相邻箱子里的金币
        // 也就是每一步的选择会对后来的造成影响
        // 适用动态规划
        // 当前偷取最大收益 = 当前能偷取金币 + max（下下个能偷取的最大收益， 下下下个能偷取的最大收益）
        // 注意处理头尾情况
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        if (nums.length == 3) {
            return Math.max(nums[0], Math.max(nums[1], nums[2]));
        }
        int[] cuts = Arrays.copyOf(nums, nums.length);
        nums[nums.length-3] += nums[nums.length-1];
        //表示cuts数组去除了最后一个 则cuts[0]可取
        //nums数组只能判断nums[1], nums[2]，因为nums[0] = cuts[0]
        cuts[cuts.length-1] = 0;
        for (int i = nums.length - 4; i >= 0; i--) {
            nums[i] += Math.max(nums[i+2] , nums[i+3]);
            cuts[i] += Math.max(cuts[i+2] , cuts[i+3]);
        }
        return Math.max(cuts[0], Math.max(nums[1], nums[2]));
    }


}
