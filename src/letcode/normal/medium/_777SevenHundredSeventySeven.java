package normal.medium;

/**
 * @program: Leetcode
 * @description: 在一个由 'L' , 'R' 和 'X' 三个字符组成的字符串（例如"RXXLRXRXL"）中进行移动操作。一次移动操作指用一个"LX"替换一个"XL"，
 * 或者用一个"XR"替换一个"RX"。现给定起始字符串start和结束字符串end，请编写代码，当且仅当存在一系列移动操作使得start可以转换成end时， 返回True。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/swap-adjacent-in-lr-string 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-10-19 21:51
 */
public class _777SevenHundredSeventySeven {


    private char[] startArray;
    private char[] endArray;
    private int limitIndex = 0;

    /**
     * 检查是否匹配
     * @return true:匹配 false：不匹配
     */
    private boolean check() {
        for (int i=0; i<startArray.length; ++i) {
            if (startArray[i] != endArray[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int index) {
        boolean isCheck = false;
        for(; index<limitIndex; ++index) {
            if (startArray[index] == 'L' && startArray[index+1] == 'X') {
                break;
            }
            if (startArray[index] == 'X' && startArray[index+1] == 'R') {
                break;
            }
        }
        if (index == limitIndex) {
            return check();
        }
        if (startArray[index] == 'L') {
            startArray[index] = 'X';
            startArray[index+1] = 'L';
            if (check()) {
                return true;
            }
            isCheck = dfs(index + 1);
            startArray[index] = 'L';
            startArray[index+1] = 'X';
        }else {
            startArray[index] = 'R';
            startArray[index+1] = 'X';
            if (check()) {
                return true;
            }
            isCheck = dfs(index+1);
            startArray[index] = 'X';
            startArray[index+1] = 'R';
        }
        if (isCheck) {
            return true;
        }
        return dfs(index+1);
    }


    /**
     * 示例 :
     * 输入: start = "RXXLRXRXL", end = "XRLXXRRLX"
     * 输出: True
     * 解释:
     * 我们可以通过以下几步将start转换成end:
     * RXXLRXRXL ->
     * XRXLRXRXL ->
     * XRLXRXRXL ->
     * XRLXXRRXL ->
     * XRLXXRRLX
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/swap-adjacent-in-lr-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param start
     * @param end
     * @return
     */
    public boolean canTransform(String start, String end) {
        if (start == null) {
            return end == null;
        }
        if (end == null) {
            return false;
        }
        if (start.length() != end.length()) {
            return false;
        }
        if (start.equals(end)) {
            return true;
        }
        limitIndex = start.length()-1;
        startArray = start.toCharArray();
        endArray = end.toCharArray();
        return dfs(0);
    }

    public static void main(String[] args) {
        System.out.println(new _777SevenHundredSeventySeven().canTransform("RXXLRXRXL", "XRLXXRRLX"));
    }
}