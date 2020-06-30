package letcode.easy;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @program: StudyHTTP
 * @description: 给定一个整数数组 nums 和一个目标值 target，
 * 请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: 蔡永程
 * @create: 2020-06-15 16:02
 */
public class One {

    /**
     * 给定 nums = [2, 7, 11, 15], target = 9
     *
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/two-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 思路 用哈希表
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> seq = new HashMap<>();
        int[] res = {0, 0};
        for (int i=0; i<nums.length; ++i) {
            seq.put(nums[i], i);
        }

        for(int i=0; i<nums.length; ++i){
            Integer j = seq.get(target-nums[i]);
            if(j != null && j.intValue() != i){
                res[0] = i;
                res[1] = j;
                return res;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] ints = new One().twoSum(new int[]{15, 2, 7, 11}, 9);
        System.out.println(Arrays.toString(ints));
    }

}