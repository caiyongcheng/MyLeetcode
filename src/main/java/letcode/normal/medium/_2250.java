package letcode.normal.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/12/15 17:42
 * description 给你一个二维整数数组 rectangles ，其中 rectangles[i] = [li, hi] 表示第 i 个矩形长为 li 高为 hi 。
 * 给你一个二维整数数组 points ，其中 points[j] = [xj, yj] 是坐标为 (xj, yj) 的一个点。  第 i 个矩形的 左下角 在 (0, 0) 处，右上角 在 (li, hi) 。
 * 请你返回一个整数数组 count ，长度为 points.length，其中 count[j]是 包含 第 j 个点的矩形数目。
 * 如果 0 <= xj <= li 且 0 <= yj <= hi ，那么我们说第 i 个矩形包含第 j 个点。如果一个点刚好在矩形的 边上 ，这个点也被视为被矩形包含。
 */
public class _2250 {

    public class WrpData {
        int x;
        int y;
        int id;

        public WrpData(int x, int y, int id) {
            this.x = x;
            this.y = y;
            this.id = id;
        }
    }

    public int[] countRectangles(int[][] rectangles, int[][] points) {
        /*
        朴素想法就算 分别二分 找出 长满足条件 高满足条件的数据 交集就是结果

        如果先将点排序 例如按照 长从大小大
        此时 每次找出长符合条件的矩形再增多 把这些矩形排序即可 而每次排序都是在原来有序的基础上增加的排序 所以效率很高 但是这样会更快嘛
         */
        WrpData[] sortByX = new WrpData[rectangles.length];
        WrpData[] sortByY = new WrpData[rectangles.length];
        for (int i = 0; i < rectangles.length; i++) {
            sortByX[i] = new WrpData(rectangles[i][0], rectangles[i][1], i);
            sortByY[i] = sortByX[i];
        }
        Arrays.sort(sortByX, Comparator.comparingInt(p -> p.x));
        Arrays.sort(sortByX, Comparator.comparingInt(p -> p.y));
        return new int[0];
    }

}
