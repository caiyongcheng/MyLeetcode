package letcode.normal.medium;

import java.util.Arrays;

/**
 * @program: Leetcode
 * @description: 给你一个正整数的数组 A（其中的元素不一定完全不同），请你返回可在一次交换
 * （交换两数字 A[i] 和 A[j] 的位置）后得到的、按字典序排列小于 A 的最大可能排列。
 * 如果无法这么操作，就请返回原数组。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/previous-permutation-with-one-swap
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-12-14 16:38
 */
public class _1053 {

    public int[] prevPermOpt1(int[] A) {
        if (A == null || A.length < 2) {
            return A;
        }
        int minValue = Integer.MAX_VALUE;
        int maxIndex;
        int maxValue;
        int index = A.length - 1;
        for (; index >= 0; index--) {
            if (A[index] > minValue) {
                break;
            } else {
                minValue = A[index];
            }
        }
        if (index >= 0) {
            maxIndex = index + 1;
            maxValue = Integer.MIN_VALUE;
            for (int minIndex = index + 1; minIndex < A.length; ++minIndex) {
                if (A[minIndex] > maxValue && A[minIndex] < A[index]) {
                    maxValue = A[minIndex];
                    maxIndex = minIndex;
                }
            }
            int tmp = A[index];
            A[index] = A[maxIndex];
            A[maxIndex] = tmp;
        }
        return A;
    }


}
