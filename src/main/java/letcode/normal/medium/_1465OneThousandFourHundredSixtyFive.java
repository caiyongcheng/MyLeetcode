package letcode.normal.medium;

import java.util.Arrays;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/10/27 9:08
 * description 矩形蛋糕的高度为 h 且宽度为 w，给你两个整数数组 horizontalCuts 和 verticalCuts，
 * 其中：   horizontalCuts[i] 是从矩形蛋糕顶部到第  i 个水平切口的距离 verticalCuts[j] 是从矩形蛋糕的左侧到第 j 个竖直切口的距离
 * 请你按数组 horizontalCuts 和 verticalCuts 中提供的水平和竖直位置切割后，请你找出 面积最大 的那份蛋糕，并返回其 面积 。
 * 由于答案可能是一个很大的数字，因此需要将结果 对 109 + 7 取余 后返回。
 */
public class _1465OneThousandFourHundredSixtyFive {


    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        /**
         * 这不就是找出最大的长和宽就好了？ 怎么中等难度的题越来越简单了？
         */
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        int maxH = horizontalCuts[0];
        int maxW = verticalCuts[0];
        for (int i = 1; i < horizontalCuts.length; i++) {
            if (horizontalCuts[i] - horizontalCuts[i-1] > maxH) {
                maxH = horizontalCuts[i] - horizontalCuts[i-1];
            }
        }
        for (int i = 1; i < verticalCuts.length; i++) {
            if (verticalCuts[i] - verticalCuts[i-1] > maxW) {
                maxW = verticalCuts[i] - verticalCuts[i-1];
            }
        }
        if (h - horizontalCuts[horizontalCuts.length - 1] > maxH) {
            maxH = h - horizontalCuts[horizontalCuts.length - 1];
        }
        if (w - verticalCuts[verticalCuts.length - 1] > maxW) {
            maxW = w - verticalCuts[verticalCuts.length - 1];
        }
        return ((int) ((long) maxH * maxW % (1000_000_000 + 7)));
    }


    /**
     *
     输入：h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
     输出：4
     解释：上图所示的矩阵蛋糕中，红色线表示水平和竖直方向上的切口。切割蛋糕后，绿色的那份蛋糕面积最大。
     示例 2：
     输入：h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1]
     输出：6
     解释：上图所示的矩阵蛋糕中，红色线表示水平和竖直方向上的切口。切割蛋糕后，绿色和黄色的两份蛋糕面积最大。
     示例 3：
     输入：h = 5, w = 4, horizontalCuts = [3], verticalCuts = [3]
     输出：9
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1465OneThousandFourHundredSixtyFive().maxArea(
                4,
                5,
                new int[]{3},
                new int[]{3}
        ));
    }

}
