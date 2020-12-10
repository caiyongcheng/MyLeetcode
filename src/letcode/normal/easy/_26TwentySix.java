package letcode.easy;

/**
 * @program: StudyHTTP
 * @description: 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-06-19 15:31
 */
public class _26TwentySix {


    /**
     * 示例 1:
     * 给定数组 nums = [1,1,2],
     * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
     * 你不需要考虑数组中超出新长度后面的元素。
     *
     * 示例 2:
     * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
     * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
     * 你不需要考虑数组中超出新长度后面的元素。
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        if(nums.length == 0) return 0;
        int newIndex = 0;
        int oldIndex = 1;
        for(oldIndex = 1; oldIndex < nums.length; ++oldIndex){
            if(nums[oldIndex] != nums[newIndex]){
                nums[++newIndex] = nums[oldIndex];
            }
        }
        return newIndex+1;
    }

    public static void main(String[] args) {
        int[] ints = new int[]{1,2,3,4,5,6,7,8};
        int length = removeDuplicates(ints);
        for(int i=0; i<length; ++i){
            System.out.print(ints[i] + " ");
        }
    }
}