package letcode.normal.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/11/21 9:54
 * description 给你一个下标从 0 开始的整数数组 nums ，如果满足下述条件，则认为数组 nums 是一个 美丽数组 ：
 * nums.length 为偶数 对所有满足 i % 2 == 0 的下标 i ，nums[i] != nums[i + 1] 均成立 注意，空数组同样认为是美丽数组。
 * 你可以从 nums 中删除任意数量的元素。当你删除一个元素时，被删除元素右侧的所有元素将会向左移动一个单位以填补空缺，而左侧的元素将会保持 不变 。
 * 返回使 nums 变为美丽数组所需删除的 最少 元素数目。
 */
public class _2216TwoThousandTwoHundredSixteen {

    public int minDeletion(int[] nums) {
        /**
         * 动态规划
         * n[i, 完美] = n[i] != n[i+1] ? n[i+1, 不完美] + 1 :  (n[i] != n[i+2] ? n[i+2, 不完美] + 1 : (...))
         * n[i, 不完美] = n[i+1, 完美] + 1
         * 找到每个元素 下一个和他不同的元素
         */
        if (nums.length == 1) {
            return 1;
        }
        int[] unLike = new int[nums.length];
        unLike[nums.length - 1] = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            unLike[i] = nums[i] == nums[i + 1] ? unLike[i + 1] : i + 1;
        }
        int[] perfect = new int[nums.length];
        int[] unPerfect = new int[nums.length];
        perfect[nums.length - 1] = 0;
        unPerfect[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            unPerfect[i] = 1 + perfect[i + 1];
            perfect[i] = unLike[i] >= 0 ? 1 + unPerfect[unLike[i]] : 0;
        }
        int max = Math.max(perfect[0], perfect[1]);
        return nums.length - max;
    }


    /**
     * 示例 1：
     *
     * 输入：nums = [1,1,2,3,5]
     * 输出：1
     * 解释：可以删除 nums[0] 或 nums[1] ，这样得到的 nums = [1,2,3,5] 是一个美丽数组。可以证明，要想使 nums 变为美丽数组，至少需要删除 1 个元素。
     * 示例 2：
     *
     * 输入：nums = [1,1,2,2,3,3]
     * 输出：2
     * 解释：可以删除 nums[0] 和 nums[5] ，这样得到的 nums = [1,2,2,3] 是一个美丽数组。可以证明，要想使 nums 变为美丽数组，至少需要删除 2 个元素。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2216TwoThousandTwoHundredSixteen().minDeletion(
                new int[]{1,1,2,2,3,3}
        ));
    }

}
