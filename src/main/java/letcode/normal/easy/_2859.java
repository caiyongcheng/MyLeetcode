package letcode.normal.easy;

import letcode.utils.TestCaseInputUtils;

import java.util.List;

/**
 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。  请你用整数形式返回 nums 中的特定元素之 和 ，
 * 这些特定元素满足：其对应下标的二进制表示中恰存在 k 个置位。
 * 整数的二进制表示中的 1 就是这个整数的 置位 。  例如，21 的二进制表示为 10101 ，其中有 3 个置位。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/1/25 11:21
 */
public class _2859 {

    public int sumIndicesWithKSetBits(List<Integer> nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.size(); i++) {
            ans += Integer.bitCount(i) == k ? nums.get(i) : 0;
        }
        return ans;
    }


}
