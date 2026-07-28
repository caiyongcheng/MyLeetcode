package letcode.normal.easy;

/**
 * @program: MyLeetcode
 * @description: 给定一个整数类型的数组nums，请编写一个能够返回数组 “中心索引” 的方法。  我们是这样定义数组 中心索引 的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
 * 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/find-pivot-index 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2021-01-28 09:14
 */
public class _724 {


    public int pivotIndex(int[] nums) {
        int sum = 0;
        int preSumArr = 0;
        for (int num : nums) {
            sum += num;
        }
        for (int i = 0; i < nums.length; i++) {
            if (sum - preSumArr - nums[i] == preSumArr) {
                return i;
            }
            preSumArr += nums[i];
        }
        return -1;
    }

}
