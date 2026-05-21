package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.List;

/**
 * Given a triangle array, return the minimum path sum from top to bottom.
 * For each step, you may move to an adjacent number of the row below. More formally,
 * if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-09-25 10:15
 */
public class _120 {

    public int minimumTotal(List<List<Integer>> triangle) {

        List<Integer> curRow;
        List<Integer> nextRow;

        for (int i = triangle.size() - 2; i >= 0; i--) {
            curRow = triangle.get(i);
            nextRow = triangle.get(i + 1);
            for (int j = 0; j < curRow.size(); j++) {
                curRow.set(j, curRow.get(j) + Math.min(nextRow.get(j), nextRow.get(j + 1)));
            }
        }

        return triangle.get(0).get(0);

    }

}
