package letcode.normal.easy;

import letcode.utils.TestCaseOutputUtils;

/**
 * 作为一位web开发者， 懂得怎样去规划一个页面的尺寸是很重要的。 现给定一个具体的矩形页面面积，你的任务是设计一个长度为 L 和宽度为 W 且满足以下要求的矩形的页面。
 * 要求：  1. 你设计的矩形页面必须等于给定的目标面积。
 * 2. 宽度 W 不应大于长度 L，换言之，要求 L >= W 。  3. 长度 L 和宽度 W 之间的差距应当尽可能小。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/construct-the-rectangle 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-10-25 22:13
 **/
public class _492 {

    public int[] constructRectangle(int area) {
        int[] ans = new int[2];
        ans[1] = (int) Math.sqrt(area);
        while (ans[1] > 0 && area % ans[1] != 0) {
            --ans[1];
        }
        ans[0] = area / ans[1];
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(TestCaseOutputUtils.formatArray(new _492().constructRectangle(1000)));
    }

}
