package letcode.offer.difficult;

/**
 * @program: MyLeetcode
 * @description: 给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为 1。
 * @packagename: letcode.offer.difficult
 * @author: 6JSh5rC456iL
 * @date: 2021-04-02 15:31
 **/
public class Offer_17_21_Seventeen_TwentyOne {

    /**
     * 同normal.difficult._42
     * @param height
     * @return
     */
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
