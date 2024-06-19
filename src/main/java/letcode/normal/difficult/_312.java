package letcode.normal.difficult;

/**
 * You are given n balloons, indexed from 0 to n - 1.
 * Each balloon is painted with a number on it represented by an array nums.
 * You are asked to burst all the balloons.
 * If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins.
 * If i - 1 or i + 1 goes out of bounds of the array, then treat it as if there is a balloon with a 1 painted on it.
 * Return the maximum coins you can collect by bursting the balloons wisely.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-06-12 09:14
 */
public class _312 {

    public int maxCoins(int[] nums) {
        /*
        当前移除的元素会对后续元素造成影响 所以肯定使用动态规划
        a b = a * b + max(a,b)
        a b c = max((b + 1) * (a * c) + max(a, c), b * (a + c) + max(a, b, c))
        a b c d
         */
        return 0;
    }

}
