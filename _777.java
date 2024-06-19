/*
 * 版权所有（c）<2021><蔡永程>
 *
 * 反996许可证版本1.0
 *
 * 在符合下列条件的情况下，特此免费向任何得到本授权作品的副本（包括源代码、文件和/或相关内容，以
 * 下统称为“授权作品”）的个人和法人实体授权：被授权个人或法人实体有权以任何目的处置授权作品，包括
 * 但不限于使用、复制，修改，衍生利用、散布，发布和再许可：
 *
 * 1. 个人或法人实体必须在许可作品的每个再散布或衍生副本上包含以上版权声明和本许可证，不得自行修
 * 改。
 * 2. 个人或法人实体必须严格遵守与个人实际所在地或个人出生地或归化地、或法人实体注册地或经营地（
 * 以较严格者为准）的司法管辖区所有适用的与劳动和就业相关法律、法规、规则和标准。如果该司法管辖区
 * 没有此类法律、法规、规章和标准或其法律、法规、规章和标准不可执行，则个人或法人实体必须遵守国际
 * 劳工标准的核心公约。
 * 3. 个人或法人不得以任何方式诱导、暗示或强迫其全职或兼职员工或其独立承包人以口头或书面形式同意
 * 直接或间接限制、削弱或放弃其所拥有的，受相关与劳动和就业有关的法律、法规、规则和标准保护的权利
 * 或补救措施，无论该等书面或口头协议是否被该司法管辖区的法律所承认，该等个人或法人实体也不得以任
 * 何方法限制其雇员或独立承包人向版权持有人或监督许可证合规情况的有关当局报告或投诉上述违反许可证
 * 的行为的权利。
 *
 * 该授权作品是"按原样"提供，不做任何明示或暗示的保证，包括但不限于对适销性、特定用途适用性和非侵
 * 权性的保证。在任何情况下，无论是在合同诉讼、侵权诉讼或其他诉讼中，版权持有人均不承担因本软件或
 * 本软件的使用或其他交易而产生、引起或与之相关的任何索赔、损害或其他责任。
 */

package letcode.normal.medium;

/**
 * @program: Leetcode
 * @description: 在一个由 'L' , 'R' 和 'X' 三个字符组成的字符串（例如"RXXLRXRXL"）中进行移动操作。一次移动操作指用一个"LX"替换一个"XL"，
 * 或者用一个"XR"替换一个"RX"。现给定起始字符串start和结束字符串end，请编写代码，当且仅当存在一系列移动操作使得start可以转换成end时， 返回True。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/swap-adjacent-in-lr-string 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-10-19 21:51
 */
public class _777 {


    private char[] startArray;
    private char[] endArray;
    private int limitIndex = 0;

    public static void main(String[] args) {
        System.out.println(new _777().canTransform("RXXLRXRXL", "XRLXXRRLX"));
    }

    /**
     * 检查是否匹配
     *
     * @return true:匹配 false：不匹配
     */
    private boolean check() {
        for (int i = 0; i < startArray.length; ++i) {
            if (startArray[i] != endArray[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int index) {
        boolean isCheck = false;
        for (; index < limitIndex; ++index) {
            if (startArray[index] == 'L' && startArray[index + 1] == 'X') {
                break;
            }
            if (startArray[index] == 'X' && startArray[index + 1] == 'R') {
                break;
            }
        }
        if (index == limitIndex) {
            return check();
        }
        if (startArray[index] == 'L') {
            startArray[index] = 'X';
            startArray[index + 1] = 'L';
            if (check()) {
                return true;
            }
            isCheck = dfs(index + 1);
            startArray[index] = 'L';
            startArray[index + 1] = 'X';
        } else {
            startArray[index] = 'R';
            startArray[index + 1] = 'X';
            if (check()) {
                return true;
            }
            isCheck = dfs(index + 1);
            startArray[index] = 'X';
            startArray[index + 1] = 'R';
        }
        if (isCheck) {
            return true;
        }
        return dfs(index + 1);
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
     *
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
        limitIndex = start.length() - 1;
        startArray = start.toCharArray();
        endArray = end.toCharArray();
        return dfs(0);
    }
}
