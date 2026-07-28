package letcode.normal.difficult;

import letcode.utils.TestCaseOutputUtils;

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
 * @since 2021-09-09 08:59
 **/
public class _68 {


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


}
