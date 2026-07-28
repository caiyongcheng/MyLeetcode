package letcode.normal.medium;

import letcode.utils.TestCaseOutputUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: MyLeetcode
 * @description: 给定一个排序好的数组arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。
 * 整数 a 比整数 b 更接近 x 需要满足：  |a - x| < |b - x| 或者 |a - x| == |b - x| 且 a < b
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/find-k-closest-elements 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2021-01-28 09:52
 */
public class _658 {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int startIndex = binarySearch(arr, x, k);
        List<Integer> rst = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            rst.add(arr[startIndex + i]);
        }
        return rst;
    }


    public int binarySearch(int[] arr, int x, int k) {
        int left = 0;
        if (arr[left] >= x) {
            return 0;
        }
        int right = arr.length - 1;
        if (arr[right] <= x) {
            return arr.length - k;
        }
        int mid;
        while (true) {
            mid = (left + right) >> 1;
            if (mid == left) {
                mid = Math.abs(arr[right] - x) < Math.abs(arr[left] - x) ? right : left;
                break;
            }
            if (arr[mid] == x) {
                while (mid > -1 && arr[mid] == x) {
                    --mid;
                }
                ++mid;
                break;
            }
            if (arr[mid] > x) {
                right = mid;
            } else {
                left = mid;
            }
        }
        left = mid - 1;
        right = mid + 1;
        --k;
        while (k > 0) {
            if (left < 0) {
                return 0;
            }
            if (right < arr.length && Math.abs(arr[right] - x) < Math.abs(arr[left] - x)) {
                ++right;
            } else {
                --left;
            }
            --k;
        }
        return left + 1;
    }


}
