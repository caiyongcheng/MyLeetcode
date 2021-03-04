package letcode.normal.easy;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @program: MyLeetcode
 * @description: 给定一个非空且只包含非负数的整数数组nums，数组的度的定义是指数组里任一元素出现频数的最大值。
 * 你的任务是在 nums 中找到与nums拥有相同大小的度的最短连续子数组，返回其长度。 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/degree-of-an-array 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2021-02-20 09:05
 */
public class _697SixHundredNinetySeven {


    public int findShortestSubArray(int[] nums) {
        final HashMap<Integer, Integer> degrees = new HashMap<>();
        int maxDegree = 0;
        final HashSet<Integer> hasDegreeItem = new HashSet<>();
        int minLength = Integer.MAX_VALUE;
        for (int num : nums) {
            if (degrees.containsKey(num)) {
                degrees.put(num, degrees.get(num) + 1);
            }else {
                degrees.put(num, 1);
            }
        }
        for (Integer value : degrees.values()) {
            if (value > maxDegree) {
                maxDegree = value;
            }
        }
        for (Integer item : degrees.keySet()) {
            if (maxDegree == degrees.get(item)) {
                hasDegreeItem.add(item);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (hasDegreeItem.contains(nums[i])) {
                for (int j = nums.length - 1; j >= 0; j--) {
                    if (nums[j] == nums[i]) {
                        if (j-i+1 < minLength) {
                            minLength = j-i+1;
                        }
                        break;
                    }
                }
                hasDegreeItem.remove(nums[i]);
            }
        }
        return minLength;
    }

    /**
     * 示例 1：
     * 输入：[1, 2, 2, 3, 1]
     * 输出：2
     * 解释：
     * 输入数组的度是2，因为元素1和2的出现频数最大，均为2.
     * 连续子数组里面拥有相同度的有如下所示:
     * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
     * 最短连续子数组[2, 2]的长度为2，所以返回2.
     * 示例 2：
     * 输入：[1,2,2,3,1,4,2]
     * 输出：6
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/degree-of-an-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _697SixHundredNinetySeven().findShortestSubArray(new int[]{1,2,2,1,2,1,1,1,1,2,2,2}));
    }
    
    
}