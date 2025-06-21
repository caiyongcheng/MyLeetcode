package letcode.normal.difficult;

/**
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n × n 的棋盘上，并且使皇后彼此之间不能相互攻击。  给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-12-02 09:21
 */
public class _52 {


    /**
     *     public int totalNQueens(int n) {
     *         return dfs(n, 0, 0, 0, 0);
     *     }
     *
     *     public int dfs(int n, int row, int mask, int maskLeft, int maskRight) {
     *         if (row == n) return 1;
     *
     *         int avaliable = ((1 << n) - 1) & (~(mask | maskLeft | maskRight));
     *
     *         int result = 0;
     *         while (avaliable != 0) {
     *             int choosePos = avaliable & (-avaliable);           //利用负数存补码的性质，取出最左边的1。
     *             avaliable = avaliable & (avaliable-1);              //把最左边的1置为0
     *             result += dfs(n, row+1, mask | choosePos, (maskLeft|choosePos)<<1, (maskRight|choosePos)>>1);
     *         }
     *
     *         return result;
     *     }
     * @param n
     * @return
     */
    public int totalNQueens(int n) {
        return dps(n, 0, new boolean[n], new int[n][n]);
    }


    private int dps(int len, int curRow, boolean[] colArr, int[][] pointArr) {
        // 所有n皇后已经放置到棋盘上
        if (curRow >= len) {
            return 1;
        }

        int ans = 0;
        // 找到当前行能放置的位置
        for (int col = 0; col < len; col++) {
            // 列已经被使用了
            if (colArr[col]) {
                continue;
            }
            // 位于上层某个皇后的对角线上
            if (pointArr[curRow][col] != 0) {
                continue;
            }
            // 当前位置可用，标记当前位置
            colArr[col] = true;
            int setRow = curRow;
            int setColRight = col;
            int setColLeft = col;
            while (setRow < len && (setColRight < len || setColLeft >= 0)) {
                if (setColRight < len) {
                    pointArr[setRow][setColRight]++;
                }
                if (setColLeft >= 0) {
                    pointArr[setRow][setColLeft]++;
                }
                setRow++;
                setColRight++;
                setColLeft--;
            }
            // 寻找下一个皇后的位置
            ans += dps(len, curRow + 1, colArr, pointArr);
            // 清空当前点的标记信息
            colArr[col] = false;
            setRow = curRow;
            setColRight = col;
            setColLeft = col;
            while (setRow < len && (setColRight < len || setColLeft >= 0)) {
                if (setColRight < len) {
                    pointArr[setRow][setColRight]--;
                }
                if (setColLeft >= 0) {
                    pointArr[setRow][setColLeft]--;
                }
                setRow++;
                setColRight++;
                setColLeft--;
            }
        }
        return ans;
    }

    /**
     示例 1：
     输入：n = 4
     输出：2
     解释：如上图所示，4 皇后问题存在两个不同的解法。
     示例 2：
     输入：n = 1
     输出：1
     * @param args
     */
    public static void main(String[] args) {
        //TestUtil.test(_52.class);
        System.out.println(new _52().totalNQueens(6));
    }


}
