package letcode.normal.easy;

/**
 * @author 蔡永程
 * @description 给你一个数组 nums ，数组中有 2n 个元素，按 [x1,x2,...,xn,y1,y2,...,yn] 的格式排列。
 * 请你将数组按 [x1,y1,x2,y2,...,xn,yn] 格式重新排列，返回重排后的数组。
 * @since 2022/8/29 9:18
 */
public class _1470 {


    public int[] shuffle(int[] nums, int n) {
        int[] rst = new int[nums.length];
        for (int i = 0; i < n; i++) {
            rst[i * 2] = nums[i];
            rst[i * 2 + 1] = nums[n + i];
        }
        return rst;
    }

}
