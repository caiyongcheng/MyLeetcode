package letcode.normal.medium;

import java.nio.charset.StandardCharsets;

/**
 * A spreadsheet is a grid with 26 columns (labeled from 'A' to 'Z') and a given number of rows.
 * Each cell in the spreadsheet can hold an integer value between 0 and 105.
 * Implement the Spreadsheet class:  Spreadsheet(int rows) Initializes a spreadsheet with 26 columns (labeled 'A' to 'Z')
 * and the specified number of rows. All cells are initially set to 0.
 * void setCell(String cell, int value) Sets the value of the specified cell.
 * The cell reference is provided in the format "AX" (e.g., "A1", "B10"),
 * where the letter represents the column (from 'A' to 'Z') and the number represents a 1-indexed row.
 * void resetCell(String cell) Resets the specified cell to 0.
 * int getValue(String formula) Evaluates a formula of the form "=X+Y",
 * where X and Y are either cell references or non-negative integers, and returns the computed sum.
 * Note: If getValue references a cell that has not been explicitly set using setCell, its value is considered 0.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-09-19 11:00
 */
public class _3484 {

    private int[][] table;

    public _3484(int rows) {
        table = new int[26][rows + 1];
    }

    public void setCell(String cell, int value) {
        int[] gridIdx = parseCellRef(cell);
        table[gridIdx[0]][gridIdx[1]] = value;
    }

    public void resetCell(String cell) {
        setCell(cell, 0);
    }

    public int getValue(String formula) {
        byte[] formulaBytes = formula.getBytes(StandardCharsets.UTF_8);
        int plusSingIdx = 0;
        for (int i = 1; i < formulaBytes.length; i++) {
            if (formulaBytes[i] == '+') {
                plusSingIdx = i;
                break;
            }
        }

        int[] firstAddNumGridIdx = parseCellRef(formulaBytes, 1, plusSingIdx);
        int[] secondAddNumGridIdx = parseCellRef(formulaBytes, plusSingIdx + 1, formulaBytes.length);
        return getGridValue(firstAddNumGridIdx) + getGridValue(secondAddNumGridIdx);
    }

    private int getGridValue(int[] gridIdx) {
        return gridIdx.length == 1 ? gridIdx[0] : table[gridIdx[0]][gridIdx[1]];
    }

    private int[] parseCellRef(String cell) {
        byte[] cellStrBytes = cell.getBytes();
        return parseCellRef(cellStrBytes, 0, cellStrBytes.length);
    }

    private int[] parseCellRef(byte[] cellStrBytes, int startIdx, int endIdx) {
        if (cellStrBytes[startIdx] > '9') {
            int[] parseRst;
            parseRst = new int[2];
            parseRst[0] = cellStrBytes[startIdx++] - 'A';
            while (startIdx < endIdx) {
                parseRst[1] = parseRst[1] * 10 + (cellStrBytes[startIdx++] - '0');
            }
            return parseRst;
        }

        int num = 0;
        while (startIdx < endIdx) {
            num = num * 10 + (cellStrBytes[startIdx++] - '0');
        }
        return new int[]{num};
    }


}
