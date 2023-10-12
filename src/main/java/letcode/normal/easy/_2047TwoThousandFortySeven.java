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
 * 句子仅由小写字母（'a' 到 'z'）、数字（'0' 到 '9'）、连字符（'-'）、标点符号（'!'、'.' 和 ','）以及空格（' '）组成。
 * 每个句子可以根据空格分解成 一个或者多个 token ，这些 token 之间由一个或者多个空格 ' ' 分隔。
 * 如果一个 token 同时满足下述条件，则认为这个 token 是一个有效单词：  仅由小写字母、连字符和/或标点（不含数字）。
 * 至多一个 连字符 '-' 。如果存在，连字符两侧应当都存在小写字母（"a-b" 是一个有效单词，但 "-ab" 和 "ab-" 不是有效单词）。
 * 至多一个 标点符号。如果存在，标点符号应当位于 token 的 末尾 。
 * 这里给出几个有效单词的例子："a-b."、"afad"、"ba-c"、"a!" 和 "!" 。
 * 给你一个字符串 sentence ，请你找出并返回 sentence 中 有效单词的数目 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/number-of-valid-words-in-a-sentence 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2022-01-27 17:38
 **/
public class _2047TwoThousandFortySeven {

    String statement;

    public int countValidWords(String sentence) {
        if (sentence.length() == 1) {
            return sentence.charAt(0) == ' ' || (sentence.charAt(0) >= '0' && sentence.charAt(0) <= '9') || sentence.charAt(0) == '-' ? 0 : 1;
        }
        statement = sentence;
        int length = statement.length();
        int start = -1;
        int ans = 0;
        for (int i = 0; i < length; i++) {
            if (sentence.charAt(i) == ' ' || i == length - 1) {
                if (start != -1 && check(start, i == length - 1 ? length - 1 : i - 1)) {
                    ++ans;
                }
                start = -1;
                continue;
            }
            if (start == -1) {
                start = i;
            }
        }
        return ans;
    }

    public boolean check(int start, int end) {
        int punctuationCount = 0;
        int hyphenCount = 0;
        char ch;
        for (int index = start; index <= end; ++index) {
            ch = statement.charAt(index);
            if (ch >= '0' && ch <= '9') {
                return false;
            }
            if (ch == '!' || ch == '.' || ch == ',') {
                if (index != end) {
                    return false;
                }
                if (punctuationCount > 0) {
                    return false;
                }
                ++punctuationCount;
            }
            if (ch == '-') {
                if (hyphenCount > 0) {
                    return false;
                }
                ++hyphenCount;
                if (index - 1 < start || index + 1 > end) {
                    return false;
                }
                ch = statement.charAt(index - 1);
                if (ch > 'z' || ch < 'a') {
                    return false;
                }
                ch = statement.charAt(index + 1);
                if (ch > 'z' || ch < 'a') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 示例 1：
     * <p>
     * 输入：sentence = "cat and  dog"
     * 输出：3
     * 解释：句子中的有效单词是 "cat"、"and" 和 "dog"
     * 示例 2：
     * <p>
     * 输入：sentence = "!this  1-s b8d!"
     * 输出：0
     * 解释：句子中没有有效单词
     * "!this" 不是有效单词，因为它以一个标点开头
     * "1-s" 和 "b8d" 也不是有效单词，因为它们都包含数字
     * 示例 3：
     * <p>
     * 输入：sentence = "alice and  bob are playing stone-game10"
     * 输出：5
     * 解释：句子中的有效单词是 "alice"、"and"、"bob"、"are" 和 "playing"
     * "stone-game10" 不是有效单词，因为它含有数字
     * 示例 4：
     * <p>
     * 输入：sentence = "he bought 2 pencils, 3 erasers, and 1  pencil-sharpener."
     * 输出：6
     * 解释：句子中的有效单词是 "he"、"bought"、"pencils,"、"erasers,"、"and" 和 "pencil-sharpener."
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/number-of-valid-words-in-a-sentence
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2047TwoThousandFortySeven().countValidWords("b-a-c f-d"));
    }

}
