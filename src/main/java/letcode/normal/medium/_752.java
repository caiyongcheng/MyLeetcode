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


import java.util.Arrays;
import java.util.Stack;

/**
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
 * 每个拨轮可以自由旋转：例如把 '9' 变为'0'，'0' 变为 '9' 。
 * 每次旋转都只能旋转一个拨轮的一位数字。
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 * 字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/open-the-lock 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-06-25 15:13
 **/
public class _752 {


    /**
     * 示例 1:
     * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
     * 输出：6
     * 解释：
     * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
     * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
     * 因为当拨动到 "0102" 时这个锁就会被锁定。
     *
     * 示例 2:
     * 输入: deadends = ["8888"], target = "0009"
     * 输出：1
     * 解释：
     * 把最后一位反向旋转一次即可 "0000" -> "0009"。
     *
     * 示例 3:
     * 输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
     * 输出：-1
     * 解释：
     * 无法旋转到目标数字且不被锁定。
     *
     * 示例 4:
     * 输入: deadends = ["0000"], target = "8888"
     * 输出：-1
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/open-the-lock
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param deadends
     * @param target
     * @return
     */
    public int openLock(String[] deadends, String target) {
        int[] dp = new int[10000];
        Arrays.fill(dp, Integer.MAX_VALUE - 10000);
        int targetInt = Integer.parseInt(target);
        dp[targetInt] = 1;
        for (String deadend : deadends) {
            dp[Integer.parseInt(deadend)] = Integer.MAX_VALUE;
        }
        Stack<Integer> mockStack = new Stack<>();
        Integer nowIndex;
        Integer nextIndex;
        Integer minCost = Integer.MAX_VALUE;
        mockStack.push(targetInt);
        while (!mockStack.empty()) {
            nowIndex = mockStack.pop();
            if (dp[nowIndex] > minCost) {
                continue;
            }
            if (nowIndex == 0) {
                minCost = dp[nowIndex];
            }
            //个位
            nextIndex = nowIndex % 10 == 9 ? nowIndex - 9 : nowIndex + 1;
            if (dp[nextIndex] > dp[nowIndex] + 1 && dp[nextIndex] != Integer.MAX_VALUE) {
                dp[nextIndex] = dp[nowIndex] + 1;
                mockStack.push(nextIndex);
            }
            nextIndex = nowIndex % 10 == 0 ? nowIndex + 9 : nowIndex - 1;
            if (dp[nextIndex] > dp[nowIndex] + 1 && dp[nextIndex] != Integer.MAX_VALUE) {
                dp[nextIndex] = dp[nowIndex] + 1;
                mockStack.push(nextIndex);
            }
            //十位
            nextIndex = nowIndex % 100 > 89 ? nowIndex - 90 : nowIndex + 10;
            if (dp[nextIndex] > dp[nowIndex] + 1 && dp[nextIndex] != Integer.MAX_VALUE) {
                dp[nextIndex] = dp[nowIndex] + 1;
                mockStack.push(nextIndex);
            }
            nextIndex = nowIndex % 100 < 10 ? nowIndex + 90 : nowIndex - 10;
            if (dp[nextIndex] > dp[nowIndex] + 1 && dp[nextIndex] != Integer.MAX_VALUE) {
                dp[nextIndex] = dp[nowIndex] + 1;
                mockStack.push(nextIndex);
            }
            //百位
            nextIndex = nowIndex % 1000 > 899 ? nowIndex - 900 : nowIndex + 100;
            if (dp[nextIndex] > dp[nowIndex] + 1 && dp[nextIndex] != Integer.MAX_VALUE) {
                dp[nextIndex] = dp[nowIndex] + 1;
                mockStack.push(nextIndex);
            }
            nextIndex = nowIndex % 1000 < 100 ? nowIndex + 900 : nowIndex - 100;
            if (dp[nextIndex] > dp[nowIndex] + 1 && dp[nextIndex] != Integer.MAX_VALUE) {
                dp[nextIndex] = dp[nowIndex] + 1;
                mockStack.push(nextIndex);
            }
            //千位
            nextIndex = nowIndex > 8999 ? nowIndex - 9000 : nowIndex + 1000;
            if (dp[nextIndex] > dp[nowIndex] + 1 && dp[nextIndex] != Integer.MAX_VALUE) {
                dp[nextIndex] = dp[nowIndex] + 1;
                mockStack.push(nextIndex);
            }
            nextIndex = nowIndex  < 1000 ? nowIndex + 9000 : nowIndex - 1000;
            if (dp[nextIndex] > dp[nowIndex] + 1 && dp[nextIndex] != Integer.MAX_VALUE) {
                dp[nextIndex] = dp[nowIndex] + 1;
                mockStack.push(nextIndex);
            }
        }
        return dp[0] >= Integer.MAX_VALUE - 10000 ? -1 : dp[0] - 1;
    }

    public static void main(String[] args) {
        System.out.println(new _752().openLock(
                new String[]{"0120","0201","0120","1001","2100"},
                "2202"
        ));
    }


}
