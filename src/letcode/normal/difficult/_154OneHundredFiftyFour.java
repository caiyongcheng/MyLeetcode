package letcode.normal.difficult;

/**
 * @program: MyLeetcode
 * @description: 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。
 * 例如，原数组 nums = [0,1,4,4,5,6,7] 在变化后可能得到： 若旋转 4 次，则可以得到 [4,5,6,7,0,1,4] 若旋转 7 次，则可以得到 [0,1,4,4,5,6,7] 注意，
 * 数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 * 给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.difficult
 * @author: 6JSh5rC456iL
 * @date: 2021-04-09 10:46
 **/
public class _154OneHundredFiftyFour {


    /**
     * 原数组是 a[0]....a[j-1], a[j]....a[n]
     * 满足 a[0] <= a[j-1] <= a[j] <= a[n]
     * 变形后
     * a[j]...a[n],a[0]...a[j-1]
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        // 此时数组为 a[j]...a[n],a[0]...a[j-1] 的形式，其中a[0]...a[j-1]是递增序列，a[j]...a[n]是递增序列，且a[n]>=a[0]
        // 要寻找的答案是 a[j]
        int left = 0;
        int right = nums.length - 1;
        int middle;
        while (left < right) {
            middle = (left + right) / 2;
            //表明mid一定在[j]...a[n]
            if (nums[middle] > nums[right]) {
                left = middle + 1;
            }
            //表明mid一定在a[0]...a[j-1]
            //如果写作right = middle-1 当a[right] = a[0]时，将找不出答案
            else if(nums[middle] < nums[right]) {
                right = middle;
            }
            //只知道right可以舍去
            else{
                --right;
            }
        }
        return nums[left];
    }


    /**
     * 示例 1：
     * 输入：nums = [1,3,5]
     * 输出：1
     *
     * 示例 2：
     * 输入：nums = [2,2,2,0,1]
     * 输出：0


     * 提示：
     * n == nums.length
     * 1 <= n <= 5000
     * -5000 <= nums[i] <= 5000
     * nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
     *
     * 进阶：
     * 这道题是寻找旋转排序数组中的最小值的延伸题目。
     * 允许重复会影响算法的时间复杂度吗？会如何影响，为什么？
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _154OneHundredFiftyFour().findMin(new int[]{2,2,2,0,1}));
    }

}
