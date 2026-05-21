package letcode.normal.easy;

import letcode.utils.TestCaseInputUtils;
import letcode.utils.TestCaseOutputUtils;

import java.util.Arrays;

/**
 * 你有一个下标从 0 开始、长度为 偶数 的整数数组 nums ，同时还有一个空数组 arr 。
 * Alice 和 Bob 决定玩一个游戏，游戏中每一轮 Alice 和 Bob 都会各自执行一次操作。游戏规则如下：  每一轮，Alice 先从 nums 中移除一个 最小 元素，然后 Bob 执行同样的操作。
 * 接着，Bob 会将移除的元素添加到数组 arr 中，然后 Alice 也执行同样的操作。
 * 游戏持续进行，直到 nums 变为空。 返回结果数组 arr 。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-07-12 09:00
 */
public class _2974 {

    public int[] numberGame(int[] nums) {
        int[] ans = Arrays.copyOf(nums, nums.length);
        Arrays.sort(ans);

        int temp;
        for (int i = 0; i < ans.length - 1; i+=2) {
            temp = ans[i];
            ans[i] = ans[i + 1];
            ans[i + 1] = temp;
        }
        return ans;
    }

}
