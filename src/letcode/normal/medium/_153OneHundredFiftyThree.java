package letcode.normal.medium;

/**
 * @program: MyLeetcode
 * @description: 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。
 * 例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到： 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2] 若旋转 4 次，则可以得到 [0,1,2,4,5,6,7] 注意，
 * 数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @date: 2021-04-08 09:32
 **/
public class _153OneHundredFiftyThree {


    public int findMin(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        //排除开头或者结尾是最小值情况
        if (nums[0] < nums[1] && nums[nums.length-1] > nums[0]) {
            return nums[0];
        }
        if (nums[nums.length-1] < nums[nums.length-2] && nums[nums.length-1] < nums[0]) {
            return nums[nums.length-1];
        }
        // 此时数组为 0...j-1, j...n的形式，其中0...j-1是递增序列，j...n是递增序列，且a[n]<a[0]
        // 要寻找的答案是 a[j]
        int left = 0;
        int right = nums.length - 1;
        int middle;
        while (left != right) {
            middle = (left + right) / 2;
            //表明mid在0..j-1内
            if (nums[middle] > nums[left]) {
                left = middle;
            } else {
                //表明mid在j...n内
                right = middle;
            }
        }
        return nums[right+1];
    }

    /**
     * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
     * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
     * 若旋转 4 次，则可以得到 [0,1,2,4,5,6,7]
     * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
     * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _153OneHundredFiftyThree().findMin(new int[]{11,13,15,17}));
    }

}
