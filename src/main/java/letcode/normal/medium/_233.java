package letcode.normal.medium;

/**
 * 给你 二维 平面上两个 由直线构成的 矩形，请你计算并返回两个矩形覆盖的总面积。
 * 每个矩形由其 左下 顶点和 右上 顶点坐标表示：  第一个矩形由其左下顶点 (ax1, ay1) 和右上顶点 (ax2, ay2) 定义。
 * 第二个矩形由其左下顶点 (bx1, by1) 和右上顶点 (bx2, by2) 定义。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/rectangle-area 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-09-30 09:14
 **/
public class _233 {

    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        /*
         * 总面积 = 矩形面积之和 - 重叠面积（无重叠为0）
         * 重叠面积 是一个矩形 有一个矩形的左上和另一个矩形的右下(或其中一个的右上和另一个的左下)构成
         */
        int area1 = (ax2 - ax1) * (ay2 - ay1), area2 = (bx2 - bx1) * (by2 - by1);
        int overlapWidth = Math.min(ax2, bx2) - Math.max(ax1, bx1), overlapHeight = Math.min(ay2, by2) - Math.max(ay1, by1);
        int overlapArea = Math.max(overlapWidth, 0) * Math.max(overlapHeight, 0);
        return area1 + area2 - overlapArea;
    }


}
