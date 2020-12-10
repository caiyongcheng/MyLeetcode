package letcode.medium;

/**
 * Leetcode
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个位置。
 * @author : CaiYongcheng
 * @date : 2020-07-10 09:59
 **/
public class _55Fifty {
    /**
     * 示例 1:
     *
     * 输入: [2,3,1,1,4]
     * 输出: true
     * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
     *
     * 示例 2:
     * 输入: [3,2,1,0,4]
     * 输出: false
     * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
        boolean[] could = new boolean[nums.length];
        could[could.length-1]=true;
        for (int i = could.length-2; i>-1; --i){
            for (int j = 1; i+j<could.length && j<=nums[i]; ++j){
                if (could[i+j]){
                    could[i] = true;
                    break;
                }
            }
        }
        return could[0];
    }

    public static void main(String[] args) {
        System.out.println(canJump(new int[]{3,2,1,0,4}));
    }
}
