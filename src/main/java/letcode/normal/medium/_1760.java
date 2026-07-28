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
 * @since: 2021-03-17 16:46
 **/
public class _1760 {

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

}
