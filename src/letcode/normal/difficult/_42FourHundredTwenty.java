package letcode.normal.difficult;

import java.util.Stack;

/**
 * @program: MyLeetcode
 * @description: 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * @packagename: letcode.normal.difficult
 * @author: 6JSh5rC456iL
 * @date: 2021-03-30 15:20
 **/
public class _42FourHundredTwenty {


    public int trap(int[] height) {
        if (height.length < 3) {
            return 0;
        }
        int index;
        int minHeightIndex = 0;
        int minHeight = 0;
        int ans = 0;
        int[] record = new int[height.length];
        record[height.length-1] = height.length-1;
        for (int i = record.length - 2; i >= 0; i--) {
            record[i] = height[record[i+1]] > height[i+1] ? record[i+1] : i+1;
        }
        for (index = 0; index < height.length; index++) {
            if (height[index] != 0) {
                minHeight = height[index];
                minHeightIndex = index;
                break;
            }
        }
        for (; index < height.length; ++index) {
            if (height[index] >= minHeight) {
                minHeightIndex = index;
                minHeight = height[minHeightIndex];
                if (minHeight > height[record[minHeightIndex]]) {
                    minHeightIndex = record[minHeightIndex];
                    minHeight = height[minHeightIndex];
                }
            } else {
                ans += minHeight - height[index];
            }
        }
        return ans;
    }


    /**
     * 示例 1：
     * \              \
     * \              \
     * \              \
     * \    =~~~===~= \
     * \  =~==~====== \
     * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出：6
     * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
     * 示例 2：
     *
     * 输入：height = [4,2,0,3,2,5]
     * 输出：9
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/trapping-rain-water
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args){
        System.out.println( new _42FourHundredTwenty().trap(
                new int[]{6,8,5,0,0,6,5}
                ));
    }

}
