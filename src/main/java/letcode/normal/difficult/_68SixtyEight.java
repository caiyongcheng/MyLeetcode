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

import letcode.utils.FormatUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个单词数组和一个长度maxWidth，重新排版单词，使其成为每行恰好有maxWidth个字符，且左右两端对齐的文本。
 * 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格' '填充，使得每行恰好有 maxWidth个字符。
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 * 说明:  单词是指由非空格字符组成的字符序列。 每个单词的长度大于 0，小于等于maxWidth。 输入单词数组 words至少包含一个单词。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/text-justification 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-09-09 08:59
 **/
public class _68SixtyEight {


    public List<String> fullJustify(String[] words, int maxWidth) {
        /*
        1 先确定每行需要放哪些单词
        2 再去决定单词的排版
         */
        List<List<String>> group = new ArrayList<>();
        List<String> line = new ArrayList<>();
        //-1是每行第一个单词之前不需要空格
        int nowLineLen = 0;
        //确定每行需要放哪些单词
        for (String word : words) {
            if (nowLineLen + word.length() > maxWidth) {
                group.add(line);
                line = new ArrayList<>();
                line.add(word);
                nowLineLen = word.length() + 1;
            } else {
                line.add(word);
                nowLineLen += word.length() + 1;
            }
        }
        group.add(line);
        //决定单词的排版
        return formatLine(group, maxWidth);
    }


    public List<String> formatLine(List<List<String>> group, int lineLen) {
        int length = group.size() - 1;
        StringBuilder line;
        List<String> lineStrList;
        List<String> formatLine = new ArrayList<>(group.size());
        int residue;
        int spaceSum;
        int avgSpace;
        StringBuilder separator;
        for (int i = 0; i < length; i++) {
            line = new StringBuilder();
            lineStrList = group.get(i);
            //就一个单词
            if (lineStrList.size() == 1) {
                line.append(lineStrList.get(0));
                while (line.length() < lineLen) {
                    line.append(' ');
                }
            } else {
                //计算单词间隔 需要最少空格
                spaceSum = lineLen - lineStrList.stream().mapToInt(String::length).sum();
                avgSpace = spaceSum / (lineStrList.size() - 1);
                residue = spaceSum % (lineStrList.size() - 1);
                separator = new StringBuilder();
                for (int size = 0; size < avgSpace; size++) {
                    separator.append(' ');
                }
                for (int i1 = 0; i1 < lineStrList.size() - 1; i1++) {
                    line.append(lineStrList.get(i1)).append(separator);
                    if (residue > 0) {
                        line.append(' ');
                        --residue;
                    }
                }
                line.append(lineStrList.get(lineStrList.size() - 1));
            }
            formatLine.add(line.toString());
        }
        //最后一行需要左对齐
        lineStrList = group.get(length);
        line = new StringBuilder();
        for (String word : lineStrList) {
            line.append(word).append(' ');
        }
        while (line.length() < lineLen) {
            line.append(' ');
        }
        formatLine.add(line.substring(0, lineLen));
        return formatLine;
    }


    /**
     * 输入:
     * words = {"This", "is", "an", "example", "of", "text", "justification."}
     * maxWidth = 16
     * 输出:
     * {
     * "This  is  an",
     * "example of text",
     * "justification. "
     * }
     * <p>
     * 示例2:
     * 输入:
     * words = {"What","must","be","acknowledgment","shall","be"}
     * maxWidth = 16
     * 输出:
     * {
     * "What  must  be",
     * "acknowledgment ",
     * "shall be    "
     * }
     * 解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
     * 因为最后一行应为左对齐，而不是左右两端对齐。
     * 第二行同样为左对齐，这是因为这行只包含一个单词。
     * <p>
     * 示例3:
     * 输入:
     * words = {"Science","is","what","we","understand","well","enough","to","explain",
     * "to","a","computer.","Art","is","everything","else","we","do"}
     * maxWidth = 20
     * 输出:
     * {
     * "Science is what we",
     * "understand   well",
     * "enough to explain to",
     * "a computer. Art is",
     * "everything else we",
     * "do         "
     * }
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/text-justification
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(FormatUtils.formatList(new _68SixtyEight().fullJustify(
                new String[]{"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"},
                20
        ), "[\n", "\n]", "\n"));
    }

}
