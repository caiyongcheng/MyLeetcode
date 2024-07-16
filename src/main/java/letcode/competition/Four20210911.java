/*
 * 版权所有（c）<2021><蔡永程>
 *
 * 反996许可证版本1.0
 *
 * 在符合下列条件的情况下，特此免费向任何得到本授权作品的副本（包括源代码、文件和/或相关内容，以
 * 下统称为“授权作品”）的个人和法人实体授权：被授权个人或法人实体有权以任何目的处置授权作品，包括
 * 但不限于使用、复制，修改，衍生利用、散布，发布和再许可：
 *
 * 1. 个人或法人实体必须在许可作品的每个再散布或衍生副本上包含以上版权声明和本许可证，不得自行修
 * 改。
 * 2. 个人或法人实体必须严格遵守与个人实际所在地或个人出生地或归化地、或法人实体注册地或经营地（
 * 以较严格者为准）的司法管辖区所有适用的与劳动和就业相关法律、法规、规则和标准。如果该司法管辖区
 * 没有此类法律、法规、规章和标准或其法律、法规、规章和标准不可执行，则个人或法人实体必须遵守国际
 * 劳工标准的核心公约。
 * 3. 个人或法人不得以任何方式诱导、暗示或强迫其全职或兼职员工或其独立承包人以口头或书面形式同意
 * 直接或间接限制、削弱或放弃其所拥有的，受相关与劳动和就业有关的法律、法规、规则和标准保护的权利
 * 或补救措施，无论该等书面或口头协议是否被该司法管辖区的法律所承认，该等个人或法人实体也不得以任
 * 何方法限制其雇员或独立承包人向版权持有人或监督许可证合规情况的有关当局报告或投诉上述违反许可证
 * 的行为的权利。
 *
 * 该授权作品是"按原样"提供，不做任何明示或暗示的保证，包括但不限于对适销性、特定用途适用性和非侵
 * 权性的保证。在任何情况下，无论是在合同诉讼、侵权诉讼或其他诉讼中，版权持有人均不承担因本软件或
 * 本软件的使用或其他交易而产生、引起或与之相关的任何索赔、损害或其他责任。
 */

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


    /**
     * source = {{1,3},{5,4}}, target = {{3,1},{6,5}}
     * <p>
     * {{1,2,3},{3,4,5}}, target = {{1,3,5},{2,3,4}}
     * <p>
     * toys = {{3,3,1},{3,2,1}}, circles = {{4,3}}, r = 2
     * <p>
     * <p>
     * {{1,3,2},{4,3,1},{7,1,2}}, circles = {{1,0},{3,3}}, r = 4
     * <p>
     * 输出：1
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new Four20210911().circleGame(
                new int[][]{{1, 3, 2}, {4, 3, 1}, {7, 1, 2}},
                new int[][]{{1, 0}, {3, 3}},
                4
        ));
    }

}
