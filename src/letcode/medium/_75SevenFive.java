package letcode.medium;

import java.util.Arrays;

/**
 * Leetcode
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，
 * 使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 注意: 不能使用代码库中的排序函数来解决这道题。
 * 进阶：
 *
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-colors
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author : CaiYongcheng
 * @date : 2020-07-16 09:23
 **/
public class _75SevenFive {

    /**
     * 示例:
     * 输入: [2,0,2,1,1,0]
     * 输出: [0,0,1,1,2,2]
     * @param nums
     */
    public static void sortColors(int[] nums) {
        int left = 0;
        int right = nums.length-1;
        int l1 = -1;
        int r1 = -1;
        while (left <= right){
            while (left <= right&&nums[left]!=2){
                if (nums[left] == 1&&l1 != -1){
                    l1 = left;
                }
                ++left;
            }
            if (left > right){
                break;
            }
            while (left <= right&&nums[right]!=0){
                if (nums[right] == 1&& r1 != -1){
                    r1 = right;
                }
                --right;
            }
            if (left > right){
                break;
            }
            if (r1 != -1 && l1 != -1){
                nums[r1] = 2;
                nums[l1] = 0;
                nums[left] = nums[right] = 1;
                r1 = l1 = -1;
            }else if (r1 != -1){
                nums[r1] = 2;
                nums[left] = 0;
                nums[right] = 1;
                r1 = right;
            }else if (l1 != -1){
                nums[l1] = 0;
                nums[right] = 2;
                nums[left] = 1;
                l1 = left;
            }else{
                nums[right] = 2;
                nums[left] = 0;
            }
        }
    }

    public static void main(String[] args) {
        int[] ints = new int[]{2,0,2,1,1,0};
        sortColors(ints);
        System.out.println(Arrays.toString(ints));
    }
}
