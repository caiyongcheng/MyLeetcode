package interview.easy;

import java.util.Arrays;

/**
 * @program: Leetcode
 * @description: 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。
 * 编写一个方法，将 B 合并入 A 并排序。
 * 初始化 A 和 B 的元素数量分别为 m 和 n。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/sorted-merge-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-12-10 15:16
 */
public class _10_01_Ten_One {

    /**
     * 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
     * 初始化 A 和 B 的元素数量分别为 m 和 n。
     * 示例:
     * 输入:
     * A = [1,2,3,0,0,0], m = 3
     * B = [2,5,6],       n = 3
     * 输出: [1,2,2,3,5,6]
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sorted-merge-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] intsA = {4, 5, 6, 0, 0, 0};
        int[] intB = {1, 2, 3};
        new _10_01_Ten_One().merge(intsA, 3, intB, 3);
        System.out.println(Arrays.toString(intsA));
    }

    public void merge(int[] A, int m, int[] B, int n) {
        int[] result = new int[m + n];
        int indexA = 0;
        int indexB = 0;
        int indexC = 0;
        while (indexA < m && indexB < n) {
            if (A[indexA] <= B[indexB]) {
                result[indexC] = A[indexA];
                ++indexA;
            } else {
                result[indexC] = B[indexB];
                ++indexB;
            }
            ++indexC;
        }
        if (indexA >= m) {
            while (indexB < n) {
                result[indexC] = B[indexB];
                ++indexB;
                ++indexC;
            }
        } else {
            while (indexA < m) {
                result[indexC] = A[indexA];
                ++indexA;
                ++indexC;
            }
        }
        for (int index = 0; index < m + n; index++) {
            A[index] = result[index];
        }
    }

}