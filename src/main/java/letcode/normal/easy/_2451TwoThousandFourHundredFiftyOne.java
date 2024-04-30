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
 * @description 给你一个字符串数组 words，每一个字符串长度都相同，令所有字符串的长度都为 n。
 * 每个字符串words[i]可以被转化为一个长度为n - 1的差值整数数组difference[i]，
 * 其中对于0 <= j <= n - 2有difference[i][j] = words[i][j+1] - words[i][j]。
 * 意两个字母的差值定义为它们在字母表中位置之差，也就是说'a'的位置是0，'b'的位置是1，'z'的位置是25。
 * 比方说，字符串"acb"的差值整数数组是[2 - 0, 1 - 2] = [2, -1]。 words中所有字符串 除了一个字符串以外，
 * 其他字符串的差值整数数组都相同。你需要找到那个不同的字符串。  请你返回words中差值整数数组不同的字符串。
 * 来源：力扣（LeetCode） 链接：https://leetcode.cn/problems/odd-string-difference 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 * @since 2023/5/25 9:01
 */
public class _2451TwoThousandFourHundredFiftyOne {


    public String oddString(String[] words) {
        int c1 = 0;
        int c2 = 0;
        for (int i = 0; i < words[0].length() - 1; i++) {
            c1 = words[0].charAt(i + 1) - words[0].charAt(i);
            for (int j = 1; j < words.length; j++) {
                c2 = words[j].charAt(i + 1) - words[j].charAt(i);
                if (c1 != c2) {
                    if (j == words.length - 1) {
                        return words[1].charAt(i + 1) - words[1].charAt(i) == c1 ? words[j] : words[i];
                    } else {
                        return words[words.length - 1].charAt(i + 1) - words[words.length - 1].charAt(i) == c1 ? words[j] : words[i];
                    }
                }
            }
        }
        return "";
    }

    /**
     * 示例 1：
     * <p>
     * 输入：words = {"adc","wzy","abc"}
     * 输出："abc"
     * 解释：
     * - "adc" 的差值整数数组是 {3 - 0, 2 - 3} = {3, -1} 。
     * - "wzy" 的差值整数数组是 {25 - 22, 24 - 25}= {3, -1} 。
     * - "abc" 的差值整数数组是 {1 - 0, 2 - 1} = {1, 1} 。
     * 不同的数组是 {1, 1}，所以返回对应的字符串，"abc"。
     * 示例 2：
     * <p>
     * 输入：words = {"aaa","bob","ccc","ddd"}
     * 输出："bob"
     * 解释：除了 "bob" 的差值整数数组是 {13, -13} 以外，其他字符串的差值整数数组都是 {0, 0} 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/odd-string-difference
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2451TwoThousandFourHundredFiftyOne().oddString(
                new String[]{"aaa", "bob", "ccc", "ddd"}
        ));
    }

}
