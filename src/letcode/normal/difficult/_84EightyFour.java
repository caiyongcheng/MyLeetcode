package letcode.difficult;

/**
 * Leetcode
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。  求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * @author : CaiYongcheng
 * @date : 2020-08-02 09:12
 **/
public class _84EightyFour {

    /**
     * 表示每个柱子从i to j的最小高度 = min（当前柱子的高度， 从i to j-1的最小高度）
     */
    public int[][] toLeftMinHight;

    public int[] datas;

    public int dp(int[] heights) {
        toLeftMinHight = new int[heights.length][heights.length];
        for (int i = 0; i < toLeftMinHight.length; i++) {
            toLeftMinHight[i][i] = heights[i];
            for (int j = i+1; j < toLeftMinHight.length; j++) {
                toLeftMinHight[i][j] = Math.min(toLeftMinHight[i][j-1], heights[j]);
            }
        }
        int maxArea = 0;
        int nowArea = 0;
        for (int i = 0; i < toLeftMinHight.length; i++) {
            for (int j = i; j < toLeftMinHight.length; j++) {
                nowArea = (j - i + 1) * toLeftMinHight[i][j];
                if (nowArea > maxArea) {
                    maxArea = nowArea;
                }
            }
        }
        return maxArea;
    }

    public int binary(int left, int right) {
        if (left == right) {
            return datas[left];
        }
        if (left+1 == right) {
            int minHeight = Math.min(datas[left], datas[right]);
            return Math.max(2*minHeight, Math.max(datas[left], datas[right]));
        }
        int mid = (left + right) >>> 1;
        int maxArea = Math.max(binary(left, mid-1), binary(mid+1, right));
        int r = mid;
        int l = mid;
        int minHeight = datas[mid];
        maxArea = Math.max(maxArea, datas[mid]);
        while ((r < right) || (l > left)) {
            if (r == right) {
                while (l >= left) {
                    if (datas[l] < minHeight) {
                        minHeight = datas[l];
                    }
                    maxArea = Math.max(maxArea, (r-l+1)*minHeight);
                    --l;
                }
                break;
            }
            if (l == left) {
                while (r <= right) {
                    if (datas[r] < minHeight) {
                        minHeight = datas[r];
                    }
                    maxArea = Math.max(maxArea, (r-l+1)*minHeight);
                    ++r;
                }
                break;
            }
            if (datas[r+1] > datas[l-1]) {
                ++r;
                if (datas[r] < minHeight) {
                    minHeight = datas[r];
                }
                maxArea = Math.max(maxArea, (r-l+1)*minHeight);
            } else {
                --l;
                if (datas[l] < minHeight) {
                    minHeight = datas[l];
                }
                maxArea = Math.max(maxArea, (r-l+1)*minHeight);
            }
        }
        return maxArea;
    }

    /**
     * 输入: [2,1,5,6,2,3]
     * 输出: 10
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        datas = heights;
        return binary(0, heights.length-1);
    }

    public static void main(String[] args) {
        _84EightyFour eightyFour = new _84EightyFour();
        System.out.println(eightyFour.largestRectangleArea(new int[]{2,1,5,6,2,3}));

    }
}
