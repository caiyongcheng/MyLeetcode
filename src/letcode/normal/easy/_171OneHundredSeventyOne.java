package letcode.normal.easy;

/**
 * 给你一个字符串 columnTitle ，表示 Excel 表格中的列名称。返回该列名称对应的列序号。
 *
 * @author CaiYongcheng
 * @date 2021-07-30 11:25
 **/
public class _171OneHundredSeventyOne {


    public int titleToNumber(String columnTitle) {
        char[] chars = columnTitle.toCharArray();
        char standardChar = 'A' - 1;
        int ans = 0;
        int expGrade = 1;
        for (int i = chars.length - 1; i >= 0; i--) {
            ans = ans + (chars[i] - standardChar) * expGrade;
            expGrade *= 26;
        }
        return ans;
    }


    /**
     * 示例 1:
     * 输入: columnTitle = "A"
     * 输出: 1
     *
     * 示例2:
     * 输入: columnTitle = "AB"
     * 输出: 28
     *
     * 示例3:
     * 输入: columnTitle = "ZY"
     * 输出: 701
     *
     * 示例 4:
     * 输入: columnTitle = "FXSHRXW"
     * 输出: 2147483647
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/excel-sheet-column-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _171OneHundredSeventyOne().titleToNumber("FXSHRXW"));
    }

}
