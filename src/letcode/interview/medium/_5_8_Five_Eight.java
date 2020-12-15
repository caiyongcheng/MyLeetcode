package interview.medium;

import java.util.Arrays;

/**
 * @program: Leetcode
 * @description: 绘制直线。有个单色屏幕存储在一个一维数组中，
 * 使得32个连续像素可以存放在一个 int 里。屏幕宽度为w，且w可被32整除（即一个 int 不会分布在两行上），
 * 屏幕高度可由数组长度及屏幕宽度推算得出。请实现一个函数，绘制从点(x1, y)到点(x2, y)的水平线。
 * 给出数组的长度 length，宽度 w（以比特为单位）、直线开始位置 x1（比特为单位）、直线结束位置 x2（比特为单位）、直线所在行数 y。返回绘制过后的数组。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/draw-line-lcci 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-12-10 14:23
 */
public class _5_8_Five_Eight {


    private int getIntForBinaryStr(int startInex, int endIndex) {
        int b = 1 << (31 - endIndex);
        int resultInt = b;
        while (endIndex > startInex) {
            b = b << 1;
            resultInt += b;
            --endIndex;
        }
        return resultInt;
    }

    public int[] drawLine(int length, int w, int x1, int x2, int y) {
        int[] result = new int[length];
        //行高、列宽 以int表示
        int widthInt = w/32;
        //计算x1所在int
        int xInt1 = y*widthInt + x1/32;
        int xInt2 = y*widthInt + x2/32;
        if (xInt1 == xInt2) {
            result[xInt1] = getIntForBinaryStr(x1%32, x2%32);
            return result;
        }
        result[xInt1] = getIntForBinaryStr(x1%32, 31);
        //计算x2所在int
        result[xInt2] = getIntForBinaryStr(0, x2%32);
        ++xInt1;
        //计算中间int
        while (xInt1 < xInt2) {
            result[xInt1] = -1;
            ++xInt1;
        }
        return result;
    }

    /**
     * 示例1:
     *  输入：length = 1, w = 32, x1 = 30, x2 = 31, y = 0
     *  输出：[3]
     *  说明：在第0行的第30位到第31为画一条直线，屏幕表示为[0b000000000000000000000000000000011]
     * 示例2:
     *  输入：length = 3, w = 96, x1 = 0, x2 = 95, y = 0
     *  输出：[-1, -1, -1]
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/draw-line-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new _5_8_Five_Eight().drawLine(3, 96, 0, 95, 0)));
    }
}