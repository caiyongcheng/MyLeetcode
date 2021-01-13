package normal.medium;

/**
 * @program: Leetcode
 * @description: 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，
 * 而不是第 k 个不同的元素。
 * @author: 蔡永程
 * @create: 2021-01-06 10:46
 */
public class _215TwoHundredFifteen {


    public int segmentation(int[] nums, int left, int right, int k) {
        if (left >= right) {
            return nums[left];
        }
        if (left + 1 == right) {
            if (right == k) {
                return Math.min(nums[left], nums[right]);
            }
            return Math.max(nums[left], nums[right]);
        }
        int l = left;
        int r = right;
        int iniValue = nums[left];
        while (l < r) {
            while (l < r && nums[r]<iniValue) --r;
            if (r <= l) break;
            nums[l++] = nums[r];
            while (l < r && nums[l]>=iniValue) ++l;
            if (l >= r) break;
            nums[r--] = nums[l];
        }
        nums[l] = iniValue;
        if (r == k) {
            return nums[r];
        }else if (r > k) {
            return segmentation(nums, left, r-1, k);
        } else {
            return segmentation(nums, r+1, right, k);
        }
    }

    public int findKthLargest(int[] nums, int k) {
        return segmentation(nums, 0, nums.length-1, k-1);
    }

    /**
     * 示例 1:
     * 输入: [3,2,1,5,6,4] 和 k = 2
     * 输出: 5
     * 示例 2:
     * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
     * 输出: 4
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _215TwoHundredFifteen().findKthLargest(
                new int[]{3,2,3,1,2,4,5,5,6},
                4
        ));
    }

}