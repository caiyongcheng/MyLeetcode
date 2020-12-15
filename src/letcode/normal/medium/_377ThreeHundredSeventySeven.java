package normal.medium;

import java.util.Arrays;

/**
 * @program: Leetcode
 * @description: 给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。
 * @author: 蔡永程
 * @create: 2020-12-11 22:35
 */
public class _377ThreeHundredSeventySeven {


    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        int[] targets = new int[target+1];
        for (int num : nums) {
            if (num > target) {
                break;
            }
            targets[num] = 1;
        }
        for (int index = 1; index < targets.length; index++) {
            for (int num : nums) {
                if (index - num > 0) {
                    targets[index] += targets[index-num];
                }else{
                    break;
                }
            }
        }
        return targets[target];
    }

    /**
     * nums = [1, 2, 3]
     * target = 4
     * 所有可能的组合为：
     * (1, 1, 1, 1)
     * (1, 1, 2)
     * (1, 2, 1)
     * (1, 3)
     * (2, 1, 1)
     * (2, 2)
     * (3, 1)
     * 请注意，顺序不同的序列被视作不同的组合。
     * 因此输出为 7。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/combination-sum-iv
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _377ThreeHundredSeventySeven().combinationSum4(
                new int[]{1, 2, 5}, 5
        ));
    }
}