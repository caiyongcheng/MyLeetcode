package letcode.easy;

/**
 * @program: StudyHTTP
 * @description: 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素.
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/remove-element 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-06-19 15:46
 */
public class _27TwentySeven {

    /**
     * 示例 1:
     * 给定 nums = [3,2,2,3], val = 3,
     * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
     * 你不需要考虑数组中超出新长度后面的元素。
     *
     * 示例 2:
     * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
     * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
     * 注意这五个元素可为任意顺序。
     * 你不需要考虑数组中超出新长度后面的元素。
     * @param nums
     * @param val
     * @return
     */

    public static int removeElement(int[] nums, int val) {
        int left = 0;
        int border = nums.length-1;
        while (left <= border){
            // if index < left => nums[index] != var
            if(nums[left] == val){
                // exit: 1.border < left    2.nums[border] != var && border > left
                while (border >= left && nums[border] == val) --border;
                // if border < left => nums[left] is last val => [0,left-1] is not val array => border is left
                if(border < left ) break;
                // else nums[border] != var && border > left
                nums[left] = nums[border];
                --border;
            }
            ++left;
        }
        return left;
    }

    public static void main(String[] args) {
        int[] ints = new int[]{2};
        int length = removeElement(ints, 2);
        for(int i=0; i<length; ++i){
            System.out.print(ints[i] + " ");
        }
    }

}