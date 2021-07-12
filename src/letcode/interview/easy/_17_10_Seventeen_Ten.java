package letcode.interview.easy;

/**
 * 数组中占比超过一半的元素称之为主要元素。给你一个 整数 数组，找出其中的主要元素。若没有，返回 -1 。请设计时间复杂度为 O(N) 、空间复杂度为 O(1) 的解决方案。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/find-majority-element-lcci 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-07-09 15:27
 **/
public class _17_10_Seventeen_Ten {

    public int majorityElement(int[] nums) {
        //摩尔投票算法
        int ans = 0;
        int support = 0;
        for (int num : nums) {
            if (support == 0) {
                ans = num;
                support = 1;
            } else {
                support += num == ans ? 1 : -1;
            }
        }
        if (support < 1) {
            return -1;
        }
        support = 0;
        for (int num : nums) {
            support += num == ans ? 1 : 0;
        }
        return support > (nums.length >> 1) ? ans : -1;
    }

    /**
     * 示例 1：
     * 输入：[1,2,5,9,5,9,5,5,5]
     * 输出：5
     *
     * 示例 2：
     * 输入：[3,2]
     * 输出：-1
     *
     * 示例 3：
     * 输入：[2,2,1,1,1,2,2]
     * 输出：2
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-majority-element-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _17_10_Seventeen_Ten().majorityElement(new int[]{2,2,1,1,1,2,2}));
    }


}
