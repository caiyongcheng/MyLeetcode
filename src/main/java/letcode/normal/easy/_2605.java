package letcode.normal.easy;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/9/5 8:40
 * description 给你两个只包含 1 到 9 之间数字的数组 nums1 和 nums2 ，每个数组中的元素 互不相同 ，请你返回 最小 的数字，两个数组都 至少 包含这个数字的某个数位。
 */
public class _2605 {


    int[] cntSort;

    public int searchMin(int[] num) {
        int minItem = 10;
        for (int i : num) {
            cntSort[i]++;
            if (i < minItem) {
                minItem = i;
            }
        }
        return minItem;
    }

    public int minNumber(int[] nums1, int[] nums2) {
        cntSort = new int[10];
        int min1 = searchMin(nums1);
        int min2 = searchMin(nums2);
        if (min1 == min2) {
            return min1;
        }
        for (int i = 0; i < cntSort.length; i++) {
            if (cntSort[i] > 1) {
                return i;
            }
        }
        return min1 > min2 ? min2 * 10 + min1 : min1 * 10 + min2;
    }


}
