package letcode.normal.medium;

import java.util.Arrays;

/**
 * @program: Leetcode
 * @description: 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
 * （这里，平面上两点之间的距离是欧几里德距离。）  你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
 * @author: 蔡永程
 * @create: 2021-01-12 11:47
 */
public class _973NineHundredSeventyThree {

    private int[][] datas;

    /**
     * 示例 1：
     * 输入：points = [[1,3],[-2,2]], K = 1
     * 输出：[[-2,2]]
     * 解释：
     * (1, 3) 和原点之间的距离为 sqrt(10)，
     * (-2, 2) 和原点之间的距离为 sqrt(8)，
     * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
     * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
     * 示例 2：
     * 输入：points = [[3,3],[5,-1],[-2,4]], K = 2
     * 输出：[[3,3],[-2,4]]
     * （答案 [[-2,4],[3,3]] 也会被接受。）
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/k-closest-points-to-origin
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        final int[][] ints = {{1, 3}, {-2, 2}};
        final int[][] ints1 = new _973NineHundredSeventyThree().kClosest(ints, 1);
        for (int[] ints2 : ints1) {
            System.out.println(Arrays.toString(ints2));
        }
    }

    public void quickSortForArray(int left, int right, int k) {
        if (left >= right) {
            return;
        }
        int l = left;
        int r = right;
        int tmp1 = 0;
        int tmp2 = 0;
        int baseValue = datas[left][0] * datas[left][0] + datas[left][1] * datas[left][1];
        int base1 = datas[left][0];
        int base2 = datas[left][1];
        while (l < r) {
            while (datas[r][0] * datas[r][0] + datas[r][1] * datas[r][1] > baseValue && r > l) --r;
            if (r > l) {
                datas[l][0] = datas[r][0];
                datas[l][1] = datas[r][1];
                ++l;
            }
            while (datas[l][0] * datas[l][0] + datas[l][1] * datas[l][1] <= baseValue && r > l) ++l;
            if (l < r) {
                datas[r][0] = datas[l][0];
                datas[r][1] = datas[l][1];
                --r;
            }
        }
        datas[l][0] = base1;
        datas[l][1] = base2;
        if (l + 1 < k) {
            quickSortForArray(l + 1, right, k);
        } else if (l + 1 > k) {
            quickSortForArray(left, l - 1, k);
        }
    }

    public int[][] kClosest(int[][] points, int K) {
        int[][] resultArray = new int[K][2];
        datas = points;
        quickSortForArray(0, datas.length - 1, K);
        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i][0] = datas[i][0];
            resultArray[i][1] = datas[i][1];
        }
        return resultArray;
    }

}