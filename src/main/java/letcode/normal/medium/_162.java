package letcode.normal.medium;

/**
 * 峰值元素是指其值严格大于左右相邻值的元素。  给你一个整数数组nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * 你可以假设nums[-1] = nums[n] = -∞ 。  你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/find-peak-element 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * nums[i] != nums[i + 1]
 *
 * @author CaiYongcheng
 * @since 2021-09-15 10:40
 **/
public class _162 {

    private int[] arr;

    public int findPeakElement(int[] nums) {
        /*
         * 分析：
         * nums[-1] = nums[n] = -∞ 意味着 nums[0] > nums[1]  nums[n-1] > nums[n]
         * nums[i] != nums[i + 1] 意味着相邻元素不会相等
         * O(log n) 的算法 要求 每次迭代都要减少 查询范围
         * 如果 nums[index] 大于 nums[index-1] 说明 [index, end]区域一定存在峰值
         * 如果 nums[index] 小于 nums[index-1] 说明 [0, index-1]区域一定有峰值
         * 不断压缩区域即可
         */
        arr = nums;
        int left = 0;
        int right = nums.length;
        int mid;
        while (right - left >= 2) {
            mid = (left + right) >> 1;
            if (greater(mid, mid - 1)) {
                if (greater(mid, mid + 1)) {
                    return mid;
                }
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return greater(left, right) ? left : right;
    }

    public boolean greater(int i, int j) {
        if (i == -1 || i == arr.length) {
            return false;
        }
        if (j == -1 || j == arr.length) {
            return true;
        }
        return arr[i] > arr[j];
    }

}
