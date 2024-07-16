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

package letcode.normal.difficult;

/**
 * 给定一个字符串 s 和一个整数 k。你可以从 s 的前 k 个字母中选择一个，并把它加到字符串的末尾。
 * 返回 在应用上述步骤的任意数量的移动后，字典上最小的字符串。
 * 提示：
 * 1 <= k <= S.length <= 1000
 * s 只由小写字母组成。
 * 来源：力扣（LeetCode） 链接：https://leetcode.cn/problems/orderly-queue 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-08-03 10:54
 **/
public class _899 {

    public String orderlyQueue(String s, int k) {
        /*
        考虑 如果k=1的情况，那么等价于字符串向左移动，一直移动到字典序最小的位置为止。
        如果 k=p的情况，我们一定可以把前p个最小的放到开头。
        剩下的问题就是 放置前p个后，剩下的字母顺序是不是固定的 如果是固定的，那么这就是答案。
        否则的话，要找出剩下字母在满足条件的情况下的最优排序。
        很明显，剩下的字母顺序我们是可以调整的。
        如果换一种思路 可以先保证前p个最大的是有序的（自左向右，从小到大），此时可以把第p+1大的字母添加进去。
        例如  k = 2 的 情况下 先把xyz拍到前面。以下演示会省略一些步骤
        1 x y z a b c c d c c
        2 z a b c c d c c x y
        3 b c c d c c x y z a
        4 c d c c x y z a b c
        5 d c c x y z a b c c
        6 d c x y z a b c c c
        7 d x y z a b c c c c
        同样的 第 p+2， p+3 ... 最小的元素都会被添加进去
        综合以上分析，得出：k=1时就是循环移动字符串，字典值最小的就是我们要的结果。k>1时，字符串升序就是我们要的结果
         */
        int length = s.length();
        if (k > 1) {
            int[] cache = new int[26];

            for (int index = 0; index < length; index++) {
                cache[s.charAt(index) - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            char ch;
            for (int i = 0; i < cache.length; i++) {
                ch = (char) ('a' + i);
                for (int j = 0; j < cache[i]; j++) {
                    sb.append(ch);
                }
            }
            return sb.toString();
        }
        String minStr = s;
        StringBuilder varStr = new StringBuilder(s);
        char ch1, ch2;
        for (int i = 0; i < length; i++) {
            varStr.append(varStr.charAt(0));
            varStr.deleteCharAt(0);
            for (int j = 0; j < length; j++) {
                ch1 = minStr.charAt(j);
                ch2 = varStr.charAt(j);
                if (ch1 < ch2) {
                    break;
                } else if (ch1 > ch2) {
                    minStr = varStr.toString();
                    break;
                }
            }
        }
        return minStr;
    }


    /**
     * 示例 1：
     * <p>
     * 输入：s = "cba", k = 1
     * 输出："acb"
     * 解释：
     * 在第一步中，我们将第一个字符（“c”）移动到最后，获得字符串 “bac”。
     * 在第二步中，我们将第一个字符（“b”）移动到最后，获得最终结果 “acb”。
     * 示例 2：
     * <p>
     * 输入：s = "baaca", k = 3
     * 输出："aaabc"
     * 解释：
     * 在第一步中，我们将第一个字符（“b”）移动到最后，获得字符串 “aacab”。
     * 在第二步中，我们将第三个字符（“c”）移动到最后，获得最终结果 “aaabc”。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/orderly-queue
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _899().orderlyQueue("baaca", 3));
    }
}
