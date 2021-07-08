package letcode.normal.medium;

import java.util.ArrayList;

/**
 * 给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。  子数组 是数组的一段连续部分。
 *
 * 提示：
 * 1 <= nums.length <= 3 * 104
 * nums[i] 不是 0 就是 1
 * 0 <= goal <= nums.length
 *
 * @author CaiYongcheng
 * @date 2021-07-08 09:09
 **/
public class _930NineHundredThirty {


    public int numSubarraysWithSum(int[] nums, int goal) {
        /**
         * 由提示可知 我们只关心1 并不关心0
         *
         * 假设 1q 01 02 ..... 0n 1a ... 00 ... 1s ..... 01 02 ... 0m 1b
         * 其中 sum:[1a...1s] = goal => 满足条件的子数组数量 = count(Index1a - Index1q) * (Index1b - index1s)
         *
         */
        ArrayList<Integer> list = new ArrayList<>();
        int length;
        int ans = 0;
        //将-1看作 起始
        list.add(-1);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                list.add(i);
            }
        }
        //添加一个虚拟的结束
        list.add(nums.length);

        //如果goal是0 那么统计 1之间的
        if (goal == 0) {
            length = list.size();
            int now;
            for (int i = 1; i < length; i++) {
                now = list.get(i) - list.get(i - 1);
                ans = ans + (now * (now - 1) >> 1);
            }
            return ans;
        }

        length = list.size() - goal;
        --goal;
        for (int i = 1; i < length; i++) {
            ans = ans + (list.get(i) - list.get(i-1)) * (list.get(i+goal+1) - list.get(i+goal));
        }
        return ans;
    }


    /**
     * 示例 1：
     *
     * 输入：nums = [1,0,1,0,1], goal = 2
     * 输出：4
     * 解释：
     * 如下面黑体所示，有 4 个满足题目要求的子数组：
     * [1,0,1,0,1]
     * [1,0,1,0,1]
     * [1,0,1,0,1]
     * [1,0,1,0,1]
     * 示例 2：
     *
     * 输入：nums = [0,0,0,0,0], goal = 0
     * 输出：15
     *
     * [0,0,0,0,1,0,0,0,0,0,1,1,0,0,0,0,0,1,0,0]
     * 3
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/binary-subarrays-with-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _930NineHundredThirty().numSubarraysWithSum(
                new int[]{0,0,0,0,1,0,0,0,0,0,1,1,0,0,0,0,0,1,0,0},
                3
        ));
    }

}
