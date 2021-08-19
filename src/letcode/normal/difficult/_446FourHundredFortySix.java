package letcode.normal.difficult;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums ，返回 nums 中所有 等差子序列 的数目。  如果一个序列中 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该序列为等差序列。
 * 例如，[1, 3, 5, 7, 9]、[7, 7, 7, 7] 和 [3, -1, -5, -9] 都是等差序列。 再例如，[1, 1, 2, 5, 7] 不是等差序列。
 * 数组中的子序列是从数组中删除一些元素（也可能不删除）得到的一个序列。  例如，[2,5,10] 是 [1,2,1,2,4,1,5,10] 的一个子序列。
 * 题目数据保证答案是一个 32-bit 整数。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/arithmetic-slices-ii-subsequence 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-08-11 09:05
 **/
public class _446FourHundredFortySix {

    public int numberOfArithmeticSlices(int[] nums) {
        /*
         * maps[i][diff] 表示 以nums[i]作为结尾，diff为差的长度最少是2的序列数量
         * 那么 maps[i][diff] =  maps[i][diff] + maps[j][diff] + 1; diff == nums[i]-nums[j]
         */
        Map<Long, Integer>[] maps = new HashMap[nums.length];
        for (int i = 0; i < maps.length; i++) {
            maps[i] = new HashMap<>();
        }
        long diff;
        int ans = 0;
        int lenJ;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; ++j) {
                diff = (long) nums[i] - nums[j];
                lenJ = maps[j].getOrDefault(diff, 0);
                maps[i].put(diff, maps[i].getOrDefault(diff, 0) + lenJ + 1);
                ans += lenJ;
            }
        }
        return ans;
    }

    /**
     * 示例 1：
     * 输入：nums = [2,4,6,8,10]
     * 输出：7
     * 解释：所有的等差子序列为：
     * [2,4,6]
     * [4,6,8]
     * [6,8,10]
     * [2,4,6,8]
     * [4,6,8,10]
     * [2,4,6,8,10]
     * [2,6,10]
     *
     * 示例 2：
     * 输入：nums = [7,7,7,7,7]
     * 输出：16
     * 解释：数组中的任意子序列都是等差子序列。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/arithmetic-slices-ii-subsequence
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _446FourHundredFortySix().numberOfArithmeticSlices(
                new int[]{7,7,7,7,7}
        ));
    }

}
