package interview.medium;

import java.util.Arrays;

/**
 * @program: Leetcode
 * @description: 给定两个整数数组，请交换一对数值（每个数组中取一个数值），使得两个数组所有元素的和相等。
 * 返回一个数组，第一个元素是第一个数组中要交换的元素，第二个元素是第二个数组中要交换的元素。
 * 若有多个答案，返回任意一个均可。若无满足条件的数值，返回空数组。
 * 示例:  来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/sum-swap-lcci 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-12-05 19:55
 */
public class _16_21_Sisteen_TwentyOne {


    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {4, 5, 6};
        int[] swapValues = new _16_21_Sisteen_TwentyOne().findSwapValues(arr1, arr2);
        System.out.println(Arrays.toString(swapValues));
    }

    public int getArrayElementSum(int[] array) {
        int elementSum = 0;
        for (int element : array) {
            elementSum += element;
        }
        return elementSum;
    }

    /**
     * 示例:
     * 输入: array1 = [4, 1, 2, 1, 1, 2], array2 = [3, 6, 3, 3]
     * 输出: [1, 3]
     * 示例:
     * 输入: array1 = [1, 2, 3], array2 = [4, 5, 6]
     * 输出: []
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sum-swap-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param array1
     * @param array2
     * @return
     */
    public int[] findSwapValues(int[] array1, int[] array2) {
        int[] resultArray = new int[2];
        Arrays.sort(array1);
        Arrays.sort(array2);
        int sum1 = getArrayElementSum(array1);
        int sum2 = getArrayElementSum(array2);
        int sumGap = sum1 - sum2;
        if (Math.abs(sumGap) % 2 == 1) {
            return new int[0];
        }
        sumGap /= 2;
        if (array2.length < array1.length) {
            sumGap = -sumGap;
            for (int element2 : array2) {
                int index = Arrays.binarySearch(array1, element2 - sumGap);
                if (index > 0) {
                    resultArray[1] = element2;
                    resultArray[0] = array1[index];
                    return resultArray;
                }
            }
        } else {
            for (int element1 : array1) {
                int index = Arrays.binarySearch(array2, element1 - sumGap);
                if (index > 0) {
                    resultArray[0] = element1;
                    resultArray[1] = array2[index];
                    return resultArray;
                }
            }
        }
        return new int[0];
    }

}