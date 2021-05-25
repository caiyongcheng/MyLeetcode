package letcode.normal.medium;

/**
 * 给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为m x n 由非负整数组成。
 * 矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素 matrix[i][j]（下标从 0 开始计数）执行异或运算得到。
 * 请你找出matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/find-kth-largest-xor-coordinate-value 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-05-19 09:03
 **/
public class _1738OneThousandSevenHundredThirtyEight {

    public int kthLargestValue(int[][] matrix, int k) {
        //前缀和 xor[i,j] = xor[i-1, j] ^ x[i, j-1] ^ x[i-1, j-1] ^ matrix[i, j]
        //数组一维化 减治法求k大
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] preXor = new int[rows * cols];
        //初始化preXor[0][...]
        preXor[0] = matrix[0][0];
        for (int i = 1; i < cols; i++) {
            matrix[0][i] ^= matrix[0][i-1];
            preXor[i] = matrix[0][i];
        }
        //初始化preXor[...][0]
        for (int i = 1; i < rows; i++) {
            matrix[i][0] ^= matrix[i-1][0];
            preXor[i * cols] = matrix[i][0];
        }
        //从preXor[1][1]开始计算
        for (int row = 1; row < rows; row++) {
            for (int col = 1; col < cols; col++) {
                matrix[row][col] ^= (matrix[row-1][col] ^ matrix[row][col-1] ^ matrix[row-1][col-1]);
                preXor[row * cols + col] = matrix[row][col];
            }
        }
        return getAns(preXor, 0, cols * rows - 1, k -1);
    }


    public int getAns(int[] arr, int left, int right, int k) {
        int temporaryInt = arr[left];
        int r = right;
        int l = left;
        while (r > l) {
            while (r > l && arr[r] < temporaryInt) {
                --r;
            }
            if (r > l && arr[r] >= temporaryInt) {
                arr[l++] = arr[r];
            }
            while (r > l && l <= right && arr[l] >= temporaryInt) {
                ++l;
            }
            if (r > l && arr[l] < temporaryInt) {
                arr[r--] = arr[l];
            }
        }
        arr[l] = temporaryInt;
        if (k > l) {
            return getAns(arr, l+1, right, k);
        } else if (k < l) {
            return getAns(arr, left, l-1, k);
        } else {
            return temporaryInt;
        }
    }

    /**
     * 示例 1：
     * 输入：matrix = {{5,2},{1,6}}, k = 1  5 7
     * 输出：7
     * 解释：坐标 (0,1) 的值是 5 XOR 2 = 7 ，为最大的值。
     * 
     * 示例 2：
     * 输入：matrix = {{5,2},{1,6}}, k = 2
     * 输出：5
     * 解释：坐标 (0,0) 的值是 5 = 5 ，为第 2 大的值。
     * 
     * 示例 3：
     * 输入：matrix = {{5,2},{1,6}}, k = 3
     * 输出：4
     * 解释：坐标 (1,0) 的值是 5 XOR 1 = 4 ，为第 3 大的值。
     * 
     * 示例 4：
     * 输入：matrix = {{5,2},{1,6}}, k = 4
     * 输出：0
     * 解释：坐标 (1,1) 的值是 5 XOR 2 XOR 1 XOR 6 = 0 ，为第 4 大的值。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-kth-largest-xor-coordinate-value
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * 
     * {{8,10,5,8,5,7,6,0,1,4,10,6,4,3,6,8,7,9,4,2}}
     * 2
     * 
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1738OneThousandSevenHundredThirtyEight().kthLargestValue(
                new int[][]{{8,10,5,8,5,7,6,0,1,4,10,6,4,3,6,8,7,9,4,2}},
                2
        ));
    }

}
