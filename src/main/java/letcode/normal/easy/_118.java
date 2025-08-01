package letcode.normal.easy;

import letcode.utils.TestUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer numRows, return the first numRows of Pascal's triangle.  In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-08-01 09:48
 */
public class _118 {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> rst = new ArrayList<>();
        ArrayList<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        rst.add(firstRow);
        if (numRows == 1) {
            return rst;
        }
        ArrayList<Integer> secondRow = new ArrayList<>();
        secondRow.add(1);
        secondRow.add(1);
        rst.add(secondRow);
        if (numRows == 2) {
            return rst;
        }

        List<Integer> aboveRow = rst.get(1);
        for (int i = 2; i < numRows; i++) {
            List<Integer> iRow = new ArrayList<>();
            iRow.add(1);
            for (int j = 1; j < i; j++) {
                iRow.add(aboveRow.get(j - 1) + aboveRow.get(j));
            }
            iRow.add(1);
            rst.add(iRow);
            aboveRow = iRow;
        }
        return rst;
    }


    /**
     * Example 1:
     *
     * Input: numRows = 5
     * Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
     * Example 2:
     *
     * Input: numRows = 1
     * Output: [[1]]
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test("=30");
    }

}
