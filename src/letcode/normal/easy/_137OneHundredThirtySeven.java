package letcode.normal.easy;

/**
 * @program: MyLeetCode
 * @description: 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 * 说明：  你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/single-number-ii 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2021-02-01 10:21
 */
public class _137OneHundredThirtySeven {

    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        for(int num : nums){
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }
        return ones;
    }

    public static void main(String[] args) {

    }


}