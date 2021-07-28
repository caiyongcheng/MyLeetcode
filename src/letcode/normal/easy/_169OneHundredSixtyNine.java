package normal.easy;

/**
 * @program: Leetcode
 * @description: 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于⌊ n/2 ⌋的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/majority-element 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2021-01-06 10:37
 */
public class _169OneHundredSixtyNine {

    public static void main(String[] args) {
        System.out.println(new _169OneHundredSixtyNine().majorityElement(
                new int[]{1, 1, 4, 1, 4, 5, 1}
        ));
    }

    public int majorityElement(int[] nums) {
        int count = 1;
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                count = 1;
                res = nums[i];
            } else {
                count += (res == nums[i] ? 1 : -1);
            }
        }
        return res;
    }

}