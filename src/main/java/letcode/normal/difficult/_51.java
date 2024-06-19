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

    /**
     * 输入：n = 4
     * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
     * 解释：如上图所示，4 皇后问题存在两个不同的解法。
     * 示例 2：
     * <p>
     * 输入：n = 1
     * 输出：[["Q"]]
     *
     * @param args
     */
    public static void main(String[] args) {
        List<List<String>> lists = new _51().solveNQueens(4);
        lists.stream().map(list -> String.join("\n", list)).forEach(ans -> {
            System.out.println(ans);
            System.out.println("==========================");
        });
    }


}
