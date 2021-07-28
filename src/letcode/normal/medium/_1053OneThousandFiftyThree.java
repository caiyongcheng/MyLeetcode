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
public class _1053OneThousandFiftyThree {

    /**
     * 示例 1：
     * 输入：[3,2,1]
     * 输出：[3,1,2]
     * 解释：
     * 交换 2 和 1
     * <p>
     * 示例 2：
     * 输入：[1,1,5]
     * 输出：[1,1,5]
     * 解释：
     * 这已经是最小排列
     * <p>
     * 示例 3：
     * 输入：[1,9,4,6,7]
     * 输出：[1,7,4,6,9]
     * 解释：
     * 交换 9 和 7
     * <p>
     * 示例4：
     * 输入：[3,1,1,3]
     * 输出：[1,3,1,3]
     * 解释：
     * 交换 1 和 3
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/previous-permutation-with-one-swap
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                new _1053OneThousandFiftyThree().prevPermOpt1(
                        new int[]{3, 2, 1}
                )
        ));
    }

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