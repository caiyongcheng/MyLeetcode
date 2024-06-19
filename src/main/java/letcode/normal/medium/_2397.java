package letcode.normal.medium;

/**
 * 给你一个下标从 0 开始、大小为 m x n 的二进制矩阵 matrix ；另给你一个整数 numSelect，表示你必须从 matrix 中选择的 不同 列的数量。
 * 如果一行中所有的 1 都被你选中的列所覆盖，则认为这一行被 覆盖 了。
 * 形式上，假设 s = {c1, c2, ...., cnumSelect} 是你选择的列的集合。对于矩阵中的某一行 row ，
 * 如果满足下述条件，则认为这一行被集合 s 覆盖：  对于满足 matrix[row][col] == 1 的每个单元格 matrix[row][col]（0 <= col <= n - 1），col 均存在于 s 中，
 * 或者 row 中 不存在 值为 1 的单元格。 你需要从矩阵中选出 numSelect 个列，使集合覆盖的行数最大化。
 * 返回一个整数，表示可以由 numSelect 列构成的集合 覆盖 的 最大行数 。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/1/5 09:21
 */
public class _2397 {

    public int maximumRows(int[][] matrix, int numSelect) {
        /*
        考虑数据规模 2^12 4096 穷举也可以
        贪心呢？ 优先选择什么样的？
        如果该列都是0 那么肯定不会选择该列
        如果该行上1的数量大于numSelect 那么无论如何选择都不会满足改行 所以要舍去
        但似乎刨除这些后 也不会优化多少
         */
        int m = matrix.length;
        int n = matrix[0].length;
        int[] mask = new int[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++){
                mask[i] += matrix[i][j] << (n - j - 1);
            }
        }
        int res = 0;
        int cur = 0;
        int limit = (1 << n);
        while (++cur < limit) {
            if (Integer.bitCount(cur) != numSelect) {
                continue;
            }
            int t = 0;
            for (int j = 0; j < m; j++) {
                if ((mask[j] & cur) == mask[j]) {
                    ++t;
                }
            }
            res = Math.max(res, t);
        }
        return res;
    }

}
