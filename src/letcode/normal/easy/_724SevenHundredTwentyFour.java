package letcode.normal.easy;

/**
 * @program: MyLeetcode
 * @description: 给定一个整数类型的数组nums，请编写一个能够返回数组 “中心索引” 的方法。  我们是这样定义数组 中心索引 的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
 * 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/find-pivot-index 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2021-01-28 09:14
 */
public class _724SevenHundredTwentyFour {


    public int pivotIndex(int[] nums) {
        int sum = 0;
        int left;
        int right;
        int[] preSumArr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            preSumArr[i] = sum;
        }
        for (int i = 0; i < nums.length; i++) {
            left = i - 1 < 0 ? 0 : preSumArr[i-1];
            right = sum - preSumArr[i];
            if (left == right) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 示例 1：
     * 输入：
     * nums = [1, 7, 3, 6, 5, 6]
     * 输出：3
     * 解释：
     * 索引 3 (nums[3] = 6) 的左侧数之和 (1 + 7 + 3 = 11)，与右侧数之和 (5 + 6 = 11) 相等。
     * 同时, 3 也是第一个符合要求的中心索引。
     *
     * 示例 2：
     * 输入：
     * nums = [1, 2, 3]
     * 输出：-1
     * 解释：
     * 数组中不存在满足此条件的中心索引。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-pivot-index
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _724SevenHundredTwentyFour().pivotIndex(new int[]{1, 2, 3}));
    }


}