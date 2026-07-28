package letcode.normal.easy;

/**
 * 符合下列属性的数组 arr 称为 山峰数组（山脉数组） ：  arr.length >= 3 存在
 * i（0 < i< arr.length - 1）使得：
 * arr[0] < arr[1] < ... arr[i-1] < arr[i] arr[i] > arr[i+1] > ... > arr[arr.length - 1]
 * 给定由整数组成的山峰数组 arr ，
 * 返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i，即山峰顶部。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/B1IidL
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-10-14 09:01
 **/
public class _852 {

    public int peakIndexInMountainArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        int mid = -1;
        while (left != mid) {
            mid = (left + right) >> 1;
            if (mid == left) {
                break;
            }
            if (arr[mid] > left) {
                if (arr[mid + 1] > arr[mid]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            } else {
                left = mid;
            }
        }
        return arr[left] > arr[right] ? left : right;
    }

}
