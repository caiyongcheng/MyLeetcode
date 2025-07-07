package letcode.normal.easy;

import letcode.utils.TestUtil;

/**
 * Given an array of integers arr, a lucky integer is an integer that has a frequency in the array equal to its value.
 * Return the largest lucky integer in the array. If there is no lucky integer return -1.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-07-07 09:21
 */
public class _1394 {

    public int findLucky(int[] arr) {
/*        return Arrays.stream(arr)
                .parallel()
                .boxed()
                .collect(Collectors.groupingBy(
                        Integer::intValue,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .parallel()
                .filter(entry -> entry.getValue().longValue() == entry.getKey())
                .mapToInt(Map.Entry::getKey)
                .max()
                .orElse(-1);*/

        int[] num2CntArr = new int[501];
        for (int num : arr) {
            num2CntArr[num]++;
        }

        for (int i = num2CntArr.length - 1; i >= 1; i--) {
            if (num2CntArr[i] == i) {
                return i;
            }
        }
        return -1;

    }


    /**
     * Example 1:
     *
     * Input: arr = [2,2,3,4]
     * Output: 2
     * Explanation: The only lucky number in the array is 2 because frequency[2] == 2.
     * Example 2:
     *
     * Input: arr = [1,2,2,3,3,3]
     * Output: 3
     * Explanation: 1, 2 and 3 are all lucky numbers, return the largest of them.
     * Example 3:
     *
     * Input: arr = [2,2,2,3,3]
     * Output: -1
     * Explanation: There are no lucky numbers in the array.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }

}
