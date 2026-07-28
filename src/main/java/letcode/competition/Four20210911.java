package letcode.competition;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Stack;

/**
 * leetcode秋季编程大赛个人赛
 *
 * @author CaiYongcheng
 * @since 2021-09-11 14:54
 **/
public class Four20210911 {


    /**
     * 给定两个大小均为 N*M 的二维数组 source 和 target 表示无人机方阵表演的两种颜色图案，由于无人机切换灯光颜色的耗能很大，请返回从 source 到 target 最少需要多少架无人机切换灯光颜色。
     *
     * @param source
     * @param target
     * @return
     */
    public int minimumSwitchingTimes(int[][] source, int[][] target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int[] ints : source) {
            for (int anInt : ints) {
                map.put(anInt, map.getOrDefault(anInt, 0) + 1);
            }
        }
        for (int[] ints : target) {
            for (int anInt : ints) {
                Integer orDefault = map.getOrDefault(anInt, 0);
                if (orDefault == 0) {
                    ans++;
                } else {
                    map.put(anInt, orDefault - 1);
                }
            }
        }
        return ans;
    }


    public int maxmiumScore(int[] cards, int cnt) {
        Arrays.sort(cards);
        int ans = 0;
        for (int index = cards.length - cnt; index < cards.length; ++index) {
            ans += cards[index];
        }
        if ((ans & 1) == 0) {
            return ans;
        }
        int ans1 = ans;
        //减掉一个奇数 肯定可以减去
        for (int index = cards.length - cnt; index < cards.length; ++index) {
            if ((cards[index] & 1) == 1) {
                ans -= cards[index];
                break;
            }
        }
        int ans2 = ans;
        for (int index = cards.length - cnt - 1; index > -1; --index) {
            if ((cards[index] & 1) == 0) {
                ans += cards[index];
                break;
            }
        }
        //后面没有找到偶数
        ans = ans == ans2 ? 0 : ans;
        //剪掉一个偶数 不一定可以剪掉
        ans2 = ans1;
        for (int index = cards.length - cnt; index < cards.length; ++index) {
            if ((cards[index] & 1) == 0) {
                ans1 -= cards[index];
                break;
            }
        }
        if (ans1 != ans2) {
            ans2 = ans1;
            //找到一个奇数
            for (int index = cards.length - cnt - 1; index > -1; --index) {
                if ((cards[index] & 1) == 1) {
                    ans1 += cards[index];
                    break;
                }
            }
            ans1 = ans2 == ans1 ? 0 : ans1;
        } else {
            ans1 = 0;
        }
        return Math.max(ans, ans1);
    }


    public int flipChess(String[] chessboard) {
        //暴力 找出有意义的落点
        int ans = 0;
        for (int row = 0; row < chessboard.length; row++) {
            for (int col = 0; col < chessboard[0].length(); col++) {
                char[][] board = new char[chessboard.length][chessboard[0].length()];
                for (int i = 0; i < chessboard.length; i++) {
                    board[i] = chessboard[i].toCharArray();
                }
                if (!usable(row, col, board)) {
                    continue;
                }
                ans = Math.max(ans, find(row, col, board));
            }
        }
        return ans;
    }


    public boolean usable(int row, int col, char[][] chessboard) {
        if (chessboard[row][col] != '.') {
            return false;
        }
        int rowLimit = chessboard.length;
        int colLimit = chessboard[0].length;
        int[] rowMove = {0, -1, -1, -1, 0, 1, 1, 1};
        int[] colMove = {-1, -1, 0, 1, 1, 1, 0, -1};
        for (int i = 0; i < 8; i++) {
            int trow = row + rowMove[i];
            int tcol = col + colMove[i];
            //没有临近白字 在这个方向上探测没有意义
            if (!(trow < rowLimit && trow > -1
                    && tcol < colLimit && tcol > -1 && chessboard[trow][tcol] == 'O')) {
                continue;
            }
            trow += rowMove[i];
            tcol += colMove[i];
            while (trow < rowLimit && trow > -1 && tcol < colLimit && tcol > -1) {
                //有方向上有黑子才能反转
                if (chessboard[trow][tcol] == 'X') {
                    return true;
                }
                if (chessboard[trow][tcol] == '.') {
                    break;
                }
                trow += rowMove[i];
                tcol += colMove[i];
            }
        }
        return false;
    }

    public int find(int row, int col, char[][] chessboard) {
        int ans = 0;
        chessboard[row][col] = 'X';
        Stack<Integer> rowStack = new Stack<>();
        Stack<Integer> colStack = new Stack<>();
        int rowLimit = chessboard.length;
        int colLimit = chessboard[0].length;
        int[] rowMove = {0, -1, -1, -1, 0, 1, 1, 1};
        int[] colMove = {-1, -1, 0, 1, 1, 1, 0, -1};
        rowStack.push(row);
        colStack.push(col);
        while (!rowStack.isEmpty()) {
            row = rowStack.pop();
            col = colStack.pop();
            for (int i = 0; i < 8; i++) {
                int trow = row + rowMove[i];
                int tcol = col + colMove[i];
                //没有临近白字 在这个方向上探测没有意义
                if (!(trow < rowLimit && trow > -1
                        && tcol < colLimit && tcol > -1 && chessboard[trow][tcol] == 'O')) {
                    continue;
                }
                trow += rowMove[i];
                tcol += colMove[i];
                while (trow < rowLimit && trow > -1 && tcol < colLimit && tcol > -1
                        && chessboard[trow][tcol] == 'O') {
                    trow += rowMove[i];
                    tcol += colMove[i];
                }
                if (!(trow < rowLimit && trow > -1 && tcol < colLimit && tcol > -1
                        && chessboard[trow][tcol] == 'X')) {
                    continue;
                }
                trow -= rowMove[i];
                tcol -= colMove[i];
                while (trow != row || tcol != col) {
                    rowStack.push(trow);
                    colStack.push(tcol);
                    chessboard[trow][tcol] = 'X';
                    ++ans;
                    trow -= rowMove[i];
                    tcol -= colMove[i];
                }
            }
        }
        return ans;
    }


    public int circleGame(int[][] toys, int[][] circles, int r) {
        int ans = 0;
        int[][] circlesShortByRow = new int[circles.length][2];
        int[][] circlesShortByCol = new int[circles.length][2];
        for (int i = 0; i < circlesShortByCol.length; i++) {
            circlesShortByRow[i] = circles[i];
            circlesShortByCol[i] = circles[i];
        }
        Arrays.sort(circlesShortByRow, Comparator.comparingInt((int[] a) -> a[0]).thenComparingInt(a -> a[1]));
        Arrays.sort(circlesShortByCol, Comparator.comparingInt((int[] a) -> a[1]).thenComparingInt(a -> a[0]));
        for (int[] toy : toys) {
            int diff = r - toy[2];
            if (diff < 0) {
                continue;
            }
            //计算横坐标
            int left = search(circlesShortByRow, toy[0] - diff, true);
            int right = search(circlesShortByRow, toy[0] + diff, true);
            while (left <= right) {
                BigDecimal diff2 = BigDecimal.valueOf(diff).pow(2);
                BigDecimal row2 = BigDecimal.valueOf(toy[0]).subtract(BigDecimal.valueOf(circlesShortByRow[left][0])).pow(2);
                BigDecimal col2 = BigDecimal.valueOf(toy[1]).subtract(BigDecimal.valueOf(circlesShortByRow[left][1])).pow(2);
                if (diff2.compareTo(row2.add(col2)) >= 0) {
                    ++ans;
                    break;
                }
                ++left;
            }
            if (left <= right) {
                continue;
            }
            left = search(circlesShortByCol, toy[1] - diff, false);
            right = search(circlesShortByCol, toy[1] + diff, false);
            while (left <= right) {
                BigDecimal diff2 = BigDecimal.valueOf(diff).pow(2);
                BigDecimal row2 = BigDecimal.valueOf(toy[0]).subtract(BigDecimal.valueOf(circlesShortByCol[left][0])).pow(2);
                BigDecimal col2 = BigDecimal.valueOf(toy[1]).subtract(BigDecimal.valueOf(circlesShortByCol[left][1])).pow(2);
                if (diff2.compareTo(row2.add(col2)) >= 0) {
                    ++ans;
                    break;
                }
                ++left;
            }
        }
        return ans;
    }


    public int search(int[][] circles, int d, boolean isRow) {
        int left = 0;
        int right = circles.length - 1;
        int mid;
        if (isRow) {
            if (circles[left][0] > d) {
                return 0;
            }
            if (circles[right][0] <= d) {
                return right;
            }
        } else {
            if (circles[left][1] > d) {
                return 0;
            }
            if (circles[right][1] <= d) {
                return right;
            }
        }
        while (left != right) {
            mid = (left + right) >> 1;
            if (mid == left) {
                return left;
            }
            if (isRow) {
                if (circles[mid][0] > d) {
                    right = mid;
                } else {
                    left = mid;
                }
            } else {
                if (circles[mid][1] > d) {
                    right = mid;
                } else {
                    left = mid;
                }
            }
        }
        return left;
    }


}
