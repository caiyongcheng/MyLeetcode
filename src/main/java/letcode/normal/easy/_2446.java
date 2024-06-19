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

package letcode.normal.easy;

/**
 * @author Caiyongcheng
 * @description 给你两个字符串数组 event1 和event2，表示发生在同一天的两个闭区间时间段事件，
 * 其中：  event1 = [startTime1, endTime1] 且 event2 = [startTime2, endTime2] 事件的时间为有效的 24 小时制且按HH:MM格式给出。
 * 当两个事件存在某个非空的交集时（即，某些时刻是两个事件都包含的），则认为出现 冲突。
 * 如果两个事件之间存在冲突，返回true；否则，返回false 。
 * 来源：力扣（LeetCode） 链接：https://leetcode.cn/problems/determine-if-two-events-have-conflict
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @since 2023/5/17 10:58
 */
public class _2446 {

    public boolean haveConflict(String[] event1, String[] event2) {
        //字符串可以直接进行比较 但是需要说明 这依赖于比较算法的实现
        int[] event1Int = parseInt(event1);
        int[] event2Int = parseInt(event2);
        return (event1Int[0] >= event2Int[0] && event1Int[0] <= event2Int[1])
                || (event2Int[0] >= event1Int[0] && event2Int[0] <= event1Int[1]);
    }

    public int[] parseInt(String[] time) {
        int[] parseRst = new int[2];
        parseRst[0] = parseDoubleDigitNum(time[0], 0) * 60 + parseDoubleDigitNum(time[0], 3);
        parseRst[1] = parseDoubleDigitNum(time[1], 0) * 60 + parseDoubleDigitNum(time[1], 3);
        return parseRst;
    }

    public int parseDoubleDigitNum(String numStr, int startIdx) {
        return (numStr.charAt(startIdx) - '0') * 10 + numStr.charAt(startIdx + 1) - '0';
    }

    /**
     * 示例 1：
     * <p>
     * 输入：event1 = {"01:15","02:00"}, event2 = {"02:00","03:00"}
     * 输出：true
     * 解释：两个事件在 2:00 出现交集。
     * 示例 2：
     * <p>
     * 输入：event1 = {"01:00","02:00"}, event2 = {"01:20","03:00"}
     * 输出：true
     * 解释：两个事件的交集从 01:20 开始，到 02:00 结束。
     * 示例 3：
     * <p>
     * 输入：event1 = {"10:00","11:00"}, event2 = {"14:00","15:00"}
     * 输出：false
     * 解释：两个事件不存在交集。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/determine-if-two-events-have-conflict
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2446().haveConflict(
                new String[]{"10:00", "11:00"},
                new String[]{"14:00", "15:00"}
        ));
    }
}
