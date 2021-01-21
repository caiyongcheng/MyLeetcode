package normal.medium;

/**
 * @program: Leetcode
 * @description: 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。 请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素
 * @author: 蔡永程
 * @create: 2021-01-21 09:49
 */
public class N_378ThreeHundredSeventyEight {

    public int kthSmallest(int[][] matrix, int k) {
        int limit = (int) Math.sqrt(k);
        if (limit * limit != k) {
            ++limit;
        }
        return 0;
    }


    public static void main(String[] args) {

    }

}