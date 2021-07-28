package letcode.normal.medium;

import letcode.utils.FormatPrintUtils;

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
public class _658SixHundredFiftyEight {



    public List<Integer> copyFromArray(int[] arr, int copyStartIndex, int copyLenth) {
        int endIndex = copyStartIndex + copyLenth;
        final ArrayList<Integer> copyList = new ArrayList<>(copyLenth);
        while (copyStartIndex < endIndex) {
            copyList.add(arr[copyStartIndex++]);
        }
        return copyList;
    }


    
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        if (arr[0] >= x) {
            return copyFromArray(arr, 0, k);
        }
        if (arr[arr.length-1] <= x) {
            return copyFromArray(arr, arr.length-k, k);
        }
        int lo = 0;
        int hi = arr.length-1;
        int mid = 0;
        while (lo < hi) {
            mid = (lo + hi) >> 1;
            if (arr[mid] > x) {
                hi = mid-1;
            }else if(arr[mid] < x) {
                lo = mid+1;
            }else{
                hi = mid;
                break;
            }
        }
        if (k == 1) {
            return copyFromArray(arr, Math.abs(arr[hi]-x) >= Math.abs(arr[hi-1]-x)?hi-1:hi,1);
        }
        int rlndex = hi+1;
        int lindex = hi-1;
        int length = k-1;
        while (length > 0 && rlndex < arr.length && lindex > -1) {
            if (Math.abs(arr[rlndex]-x) >= Math.abs(arr[lindex]-x)) {
                --lindex;
            }else {
                ++rlndex;
            }
            --length;
        }
        if (length == 0) {
            return copyFromArray(arr, lindex+1, k);
        } else if (rlndex >= arr.length) {
            return copyFromArray(arr, arr.length-k, k);
        } else {
            return copyFromArray(arr, 0, k);
        }
    }

    /**
     * 示例 1：
     * 输入：arr = [1,2,3,4,5], k = 4, x = 3
     * 输出：[1,2,3,4]
     *
     * 示例 2：
     * 输入：arr = [1,2,3,4,5], k = 4, x = -1
     * 输出：[1,2,3,4]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-k-closest-elements
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {

        final List<Integer> closestElements = new _658SixHundredFiftyEight().findClosestElements(
                new int[]{
                        1,3
                },
                1,
                2
        );
        System.out.println(FormatPrintUtils.formatList(closestElements));
    }

}