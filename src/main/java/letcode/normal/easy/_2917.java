package letcode.normal.easy;

import letcode.utils.TestCaseInputUtils;

/**
 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。  nums 中的 K-or 是一个满足以下条件的非负整数：  只有在 nums 中，至少存在 k 个元素的第 i 位值为 1 ，
 * 那么 K-or 中的第 i 位的值才是 1 。 返回 nums 的 K-or 值。  注意 ：对于整数 x ，如果 (2i AND x) == 2i ，则 x 中的第 i 位值为 1 ，其中 AND 为按位与运算符。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/3/7 14:32
 */
public class _2917 {

    public int findKOr(int[] nums, int k) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            int grade = 1 << i;
            int sum = 0;
            for (int num : nums) {
                if ((grade & num) == grade) {
                    sum++;
                }
            }
            if (sum >= k) {
                result |= grade;
            }
        }
        return result;
    }

}
