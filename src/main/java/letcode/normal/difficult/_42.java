package letcode.normal.difficult;

/**
 * @program: MyLeetcode
 * @description: 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * @packagename: letcode.normal.difficult
 * @author: 6JSh5rC456iL
 * @since: 2021-03-30 15:20
 **/
public class _42 {


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


}
