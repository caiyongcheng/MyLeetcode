package letcode.normal.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/8/4 17:01
 * description 给定一个二进制数组 nums 和一个整数 k，如果可以翻转最多 k 个 0 ，则返回 数组中连续 1 的最大个数 。
 */
public class _1004 {

    public int longestOnes(int[] nums, int k) {
        /*
        找出所有0的位置 将相邻位置的0变成1即可 计算值
         */
        List<Integer> list = new ArrayList<>(k);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                list.add(i);
            }
        }
        int size = list.size();
        if (size <= k) {
            return nums.length;
        }
        int rst = 0;
        for (int i = 0; i < size; i++) {
            int lidx = i - 1 >= 0 ? list.get(i - 1) : -1;
            int ridx = i + k < size ? list.get(i + k) : nums.length;
            rst = Math.max(rst, ridx - lidx - 1);
        }
        Integer last0 = list.get(size - 1);
        if (last0 != nums.length) {
            rst = Math.max(rst, nums.length - last0 - 1);
        }
        return rst;
    }

    public static void main(String[] args) {
        System.out.println(new _1004().longestOnes(
                new int[]{
                        1, 1, 1, 1, 1
                },
                0
        ));
    }

}
