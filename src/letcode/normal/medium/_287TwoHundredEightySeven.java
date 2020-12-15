package normal.medium;

/**
 * @program: Leetcode
 * @description: 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），
 * 可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/find-the-duplicate-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-12-14 14:34
 */
public class _287TwoHundredEightySeven {


    public int findDuplicate(int[] nums) {
        int[] deRepetition = new int[nums.length];
        for (int num : nums) {
            ++deRepetition[num];
            if (deRepetition[num] > 1) {
                return num;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new _287TwoHundredEightySeven().findDuplicate(
                new int[]{
                        1,3,4,2,2
                }
        ));
    }

}