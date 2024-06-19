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

import java.util.Arrays;
import java.util.HashSet;

/**
 * @program: MyLeetcode
 * @description: 在一个 N x N 的坐标方格grid 中，每一个方格的值 grid[i][j] 表示在位置 (i,j) 的平台高度。
 * 现在开始下雨了。当时间为t时，此时雨水导致水池中任意位置的水位为t。
 * 你可以从一个平台游向四周相邻的任意一个平台，但是前提是此时水位必须同时淹没这两个平台。假定你可以瞬间移动无限距离，也就是默认在方格内部游动是不耗时的。
 * 当然，在你游泳的时候你必须待在坐标方格里面。  你从坐标方格的左上平台 (0，0) 出发。最少耗时多久你才能到达坐标方格的右下平台(N-1, N-1)？  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/swim-in-rising-water 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.difficult
 * @author: 6JSh5rC456iL
 * @date: 2021-04-12 10:17
 **/
public class N_778 {

    private int n;

    class Wrap implements Comparable<Wrap>{
        int v;
        int y;
        int x;
        boolean link = false;
        @Override
        public int compareTo(Wrap o) {
            return v < o.v ? -1 : v > o.v ? 1 : y > o.y ? 1 : y < o.y ? -1 : x > o.x ? 1 : -1;
        }

        @Override
        public int hashCode() {
            return y * n + x;
        }

        @Override
        public boolean equals(Object obj) {
            return hashCode() == obj.hashCode();
        }
    }

    /**
     * 会做 但是原先做法考虑使用并查集
     * 所以转头去刷了波 并查集
     * @param grid
     * @return
     */
    public int swimInWater(int[][] grid) {
        Wrap[] Wraps = new Wrap[grid.length * grid.length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Wrap wrap = new Wrap();
                Wraps[i*grid.length+j] = wrap;
                wrap.x = j;
                wrap.y = i;
                wrap.v = grid[i][j];
            }
        }
        Arrays.sort(Wraps);
        HashSet<Wrap> can = new HashSet<>();
        HashSet<Wrap> start = new HashSet<>();
        return 0;

    }

}
