package letcode.normal.difficult;

/**
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。  请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 *
 * @author CaiYongcheng
 * @since 2021-09-16 10:50
 **/
public class _41 {


    public int firstMissingPositive(int[] nums) {
        int length = nums.length + 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0 || nums[i] >= length) {
                nums[i] = 0;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % length != 0) {
                nums[nums[i] % length - 1] = nums[nums[i] % length - 1] % length + length;
            }
        }
        int i;
        for (i = 0; i < nums.length; i++) {
            if (nums[i] < length) {
                return i + 1;
            }
        }
        return length;
    }

    public static void main(String[] args) {
        System.out.println(new _41().firstMissingPositive(
                new int[]{1}
        ));
    }


}
