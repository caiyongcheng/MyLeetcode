package letcode.medium;

/**
 * Leetcode
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : CaiYongcheng
 * @date : 2020-07-19 11:09
 **/
public class _80Eighty {


    public static void main(String[] args) {
        int[] ints = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
        int length = new _80Eighty().removeDuplicates(ints);
        for (int i = 0; i < length; i++) {
            System.out.println(ints[i]);
        }
    }

    /**
     * 示例1:
     * 给定 nums = [1,1,1,2,2,3],
     * 函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
     * 你不需要考虑数组中超出新长度后面的元素。
     * <p>
     * 示例2:
     * 给定 nums = [0,0,1,1,1,1,2,3,3],
     * 函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为0, 0, 1, 1, 2, 3, 3 。
     * 你不需要考虑数组中超出新长度后面的元素。
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (null == nums || nums.length < 1) {
            return 0;
        }
        if (nums.length < 3) {
            return nums.length;
        }
        int startIndex = nums.length - 3;
        int endIndex = 0;
        int coverLength = 0;
        int length = nums.length;
        for (; startIndex > -1; --startIndex) {
            if (nums[startIndex] == nums[startIndex + 2]) {
                for (; startIndex > 0 && nums[startIndex - 1] == nums[startIndex]; --startIndex) ;
                for (endIndex = startIndex + 2; endIndex < length
                        && nums[endIndex] == nums[startIndex]; ++endIndex);
                coverLength = endIndex - startIndex - 2;
                for (; endIndex < length; ++endIndex) {
                    nums[endIndex - coverLength] = nums[endIndex];
                }
                length -= coverLength;
            }
        }
        return length;
    }


}
