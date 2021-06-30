package letcode.normal.easy;

/**
 * 给定一个正整数，返回它在 Excel 表中相对应的列名称。
 * 例如，      1 -> A     2 -> B     3 -> C     ...     26 -> Z     27 -> AA     28 -> AB      ...
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/excel-sheet-column-title 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-06-29 09:16
 **/
public class _168OneHundredSixtyEight {



    public String convertToTitle(int columnNumber) {
        StringBuilder ans = new StringBuilder();
        int n;
        while (columnNumber > 0) {
            n = columnNumber % 26;
            if (n == 0) {
                n = 26;
            }
            ans.insert(0, (char)(n + 64));
            columnNumber = (columnNumber - n) / 26;
        }
        return ans.toString();
    }


    /**
     * 示例 1:
     * 输入: 1
     * 输出: "A"
     *
     * 示例 2:
     * 输入: 28
     * 输出: "AB"
     *
     * 示例 3:
     * 输入: 701
     * 输出: "ZY"
     *
     * 示例 4：
     * 输入：columnNumber = 2147483647
     * 输出："FXSHRXW"
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _168OneHundredSixtyEight().convertToTitle(701));
    }



}
