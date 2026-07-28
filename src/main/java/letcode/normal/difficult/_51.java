package letcode.normal.difficult;

import java.util.*;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/8/4 9:03
 * description 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 */
public class _51 {

    private int[] colUse;

    private int[] bias1;

    private final Set<Integer> bias2 = new HashSet<>();

    private final Map<Integer, Integer> cache = new HashMap<>();

    char[][] map;


    int n;

    List<List<String>> rst = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        colUse = new int[n];
        map = new char[n][n];
        bias1 = new int[n * 2];
        this.n = n;
        for (char[] row : map) {
            Arrays.fill(row, '.');
        }
        for (int i = 0; i < n; i++) {
            int point = i;
            cache.put(i, i);
            while (point < n * n) {
                point = point + n + 1;
                if (point >= n * n) {
                    break;
                }
                cache.put(point, i);
            }
        }
        for (int i = 1; i < n; i++) {
            int point = i * n;
            cache.put(point, i * n);
            while (true) {
                point = point + n + 1;
                if (point >= n * n) {
                    break;
                }
                cache.put(point, i * n);
            }
        }
        dps(0);
        return rst;
    }


    private void dps(int row) {
        if (row >= n) {
            List<String> ans = new ArrayList<>(n);
            for (char[] chars : map) {
                ans.add(new String(chars));
            }
            rst.add(ans);
            return;
        }
        for (int col = 0; col < n; col++) {
            Integer bs = cache.getOrDefault(row * n + col, -1);
            if (colUse[col] == 1 || bias1[row + col] == 1 || bias2.contains(bs)) {
                continue;
            }
            map[row][col] = 'Q';
            colUse[col] = 1;
            bias1[row + col] = 1;
            bias2.add(bs);
            dps(row + 1);
            map[row][col] = '.';
            colUse[col] = 0;
            bias1[row + col] = 0;
            bias2.remove(bs);
        }
    }

}
