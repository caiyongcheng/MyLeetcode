package letcode.medium;

/**
 * Leetcode
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。   
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : CaiYongcheng
 * @date : 2020-07-18 11:38
 **/
public class _79SevenNine {

    private boolean[][] use;
    private char[][] data;
    private char[] patten;
    private int[][] moveStep = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word = "ABCB";
        System.out.println(new _79SevenNine().exist(board, word));
    }

    private boolean dfs(int y, int x, int index) {
        if (index == patten.length) {
            return true;
        }
        for (int i = 0; i < 4; ++i) {
            int ny = y + moveStep[i][0];
            int nx = x + moveStep[i][1];
            if (ny > -1 && ny < use.length
                    && nx > -1 && nx < use[ny].length
                    && data[ny][nx] == patten[index] && !use[ny][nx]) {
                use[ny][nx] = true;
                if (dfs(ny, nx, index + 1)) {
                    return true;
                }
                use[ny][nx] = false;
            }
        }
        return false;
    }

    /**
     * 示例:
     * board =
     * [
     * ['A','B','C','E'],
     * ['S','F','C','S'],
     * ['A','D','E','E']
     * ]
     * 给定 word = "ABCCED", 返回 true
     * 给定 word = "SEE", 返回 true
     * 给定 word = "ABCB", 返回 false
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        data = board;
        use = new boolean[board.length][board[0].length];
        patten = word.toCharArray();
        for (int y = 0; y < board.length; ++y) {
            for (int x = 0; x < board[y].length; ++x) {
                if (data[y][x] == patten[0]) {
                    use[y][x] = true;
                    if (dfs(y, x, 1)) {
                        return true;
                    }
                    use[y][x] = false;
                }
            }
        }
        return false;
    }


}
