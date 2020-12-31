package normal.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: Leetcode
 * @description: 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）,
 * 其中有些元素出现两次而其他元素出现一次。  找到所有出现两次的元素。
 * 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-all-duplicates-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-12-24 09:51
 */
public class _424FourHundredTwentyFour {

    public List<Integer> findDuplicates(int[] nums) {
        final ArrayList<Integer> integers = new ArrayList<>(nums.length);
        for (int num : nums) {
            if (nums[Math.abs(num)-1] < 0) {
                integers.add(Math.abs(num));
            }
            nums[Math.abs(num)-1] = -nums[Math.abs(num)-1];
        }
        return integers;
    }

    /**
     * 输入:
     * [4,3,2,7,8,2,3,1]
     * 输出:
     * [2,3]
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _424FourHundredTwentyFour().findDuplicates(
                new int[]{4,3,2,7,8,2,3,1}
        ));
    }

}
