package letcode.normal.medium;

import java.util.Arrays;

/**
 * @program: Leetcode
 * @description: 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
 * （这里，平面上两点之间的距离是欧几里德距离。）  你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
 * @author: 蔡永程
 * @create: 2021-01-12 11:47
 */
public class _973 {

    private int[][] datas;

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
