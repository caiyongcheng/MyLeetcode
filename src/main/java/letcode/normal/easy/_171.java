package letcode.normal.easy;

/**
 * 给你一个字符串 columnTitle ，表示 Excel 表格中的列名称。返回该列名称对应的列序号。
 *
 * @author CaiYongcheng
 * @since 2021-07-30 11:25
 **/
public class _171 {


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


}
