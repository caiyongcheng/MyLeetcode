package letcode.normal.medium;
/**
 * @program: MyLeetcode
 * @description: 给你一个整数数组nums，其中nums[i]表示第i个袋子里球的数目。同时给你一个整数maxOperations。 
 * 你可以进行如下操作至多maxOperations次：  选择任意一个袋子，并将袋子里的球分到2 个新的袋子中，每个袋子里都有 正整数个球。 
 * 比方说，一个袋子里有5个球，你可以把它们分到两个新袋子里，分别有 1个和 4个球，或者分别有 2个和 3个球。 
 * 你的开销是单个袋子里球数目的 最大值，你想要 最小化开销。  请你返回进行上述操作后的最小开销。 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/minimum-limit-of-balls-in-a-bag 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @date: 2021-03-17 16:46
 **/
public class _1760OneThousandSevenHundredSixty {

    public int minimumSize(int[] nums, int maxOperations) {
        int hi = nums[0];
        int lo = 1;
        int ans;
        int mid;
        int count;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > hi) {
                hi = nums[i];
            }
        }
        ans = hi;
        while (lo <= hi) {
            mid = (lo + hi) >> 1;
            count = 0;
            for (int num : nums) {
                count += (num-1) / mid;
            }
            if (count <= maxOperations) {
                hi = mid - 1;
                if (mid < ans) {
                    ans = mid;
                }
            } else {
                lo = mid + 1;
            }
        }
        return ans;
    }

    /**
     * 示例 1：
     * 输入：nums = [9], maxOperations = 2
     * 输出：3
     * 解释：
     * - 将装有 9 个球的袋子分成装有 6 个和 3 个球的袋子。[9] -> [6,3] 。
     * - 将装有 6 个球的袋子分成装有 3 个和 3 个球的袋子。[6,3] -> [3,3,3] 。
     * 装有最多球的袋子里装有 3 个球，所以开销为 3 并返回 3 。
     *
     * 示例 2：
     * 输入：nums = [2,4,8,2], maxOperations = 4
     * 输出：2
     * 解释：
     * - 将装有 8 个球的袋子分成装有 4 个和 4 个球的袋子。[2,4,8,2] -> [2,4,4,4,2] 。
     * - 将装有 4 个球的袋子分成装有 2 个和 2 个球的袋子。[2,4,4,4,2] -> [2,2,2,4,4,2] 。
     * - 将装有 4 个球的袋子分成装有 2 个和 2 个球的袋子。[2,2,2,4,4,2] -> [2,2,2,2,2,4,2] 。
     * - 将装有 4 个球的袋子分成装有 2 个和 2 个球的袋子。[2,2,2,2,2,4,2] -> [2,2,2,2,2,2,2,2] 。
     * 装有最多球的袋子里装有 2 个球，所以开销为 2 并返回 2 。
     *
     * 示例 3：
     * 输入：nums = [7,17], maxOperations = 2
     * 输出：7
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-limit-of-balls-in-a-bag
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1760OneThousandSevenHundredSixty().minimumSize(
                new int[]{431,922,158,60,192,14,788,146,788,775,772,792,68,143,376,375,877,516,595,82,56,704,160,403,713,504,67,332,26},
        80
        ));
    }
}
