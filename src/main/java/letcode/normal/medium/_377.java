package letcode.normal.medium;

import java.util.Arrays;

/**
 * @program: Leetcode
 * @description: 给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。
 * @author: 蔡永程
 * @create: 2020-12-11 22:35
 */
public class _377 {


    public int combinationSum4(int[] nums, int target) {
        /*
        targets[num] 表示num的组合方式
        那么 target[num+1]的组合 可以由 nums中的数与target[1]到target[num]组合
        nums中的数num只选一次 因为如果选了多次num*k
        那么相对应的target[target+1-num*k] 在计算num和target[num+1-num]时会被重复计算
         */
        Arrays.sort(nums);
        int[] targets = new int[target + 1];
        for (int num : nums) {
            if (num > target) {
                break;
            }
            targets[num] = 1;
        }
        for (int index = 1; index < targets.length; index++) {
            for (int num : nums) {
                if (index - num > 0) {
                    targets[index] += targets[index - num];
                } else {
                    break;
                }
            }
        }
        return targets[target];
    }
}
