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
public class N_778SevenHundredSeventyEight {

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
