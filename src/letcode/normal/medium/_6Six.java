package letcode.medium;

/**
 * @program: StudyHTTP
 * @description: 将一个给定字符串根据给定的行数，以从上往下、从左到右进行Z 字形排列。
 * 比如输入字符串为 "LEETCODEISHIRING"行数为 3 时，排列如下：
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * 请你实现这个将字符串进行指定行数变换的函数：
 * string convert(string s, int numRows);
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-06-19 20:06
 */
public class _6Six {


    /**
     * 示例1:
     * 输入: s = "LEETCODEISHIRING", numRows = 3
     * 输出: "LCIRETOESIIGEDHN"
     * <p>
     * 示例2:
     * 输入: s = "LEETCODEISHIRING", numRows =4
     * 输出:"LDREOEIIECIHNTSG"
     * 解释:
     * L     D     R
     * E   O E   I I
     * E C   I H   N
     * T     S     G
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/zigzag-conversion
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @param numRows
     * @return
     */
    public static String convert(String s, int numRows) {
        if (numRows <= 1 || s.length() == 0) return s;
        int n = s.length();
        int i = 0;
        int j = 0;
        int t = 0;
        int step = 2 * numRows - 2;
        StringBuilder stringBuilder = new StringBuilder();
        // 依次填充每一行
        for (; i < numRows; ++i) {
            for (j = 0; j <= n / step; ++j) {
                t = j * step + i;
                if (t < n) {
                    //填充每行的开头
                    stringBuilder.append(s.charAt(t));
                    //下一个字符
                    t += step - i * 2;
                    if (i != 0 && i != numRows - 1 && t < n) {
                        stringBuilder.append(s.charAt(t));
                    }
                }
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String leetcodeishiring = convert("LsET", 2);
        System.out.println(leetcodeishiring);
    }


}