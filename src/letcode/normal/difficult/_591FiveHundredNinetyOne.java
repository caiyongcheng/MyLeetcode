package letcode.normal.difficult;

import java.util.Stack;

/**
 * 给定一个表示代码片段的字符串，你需要实现一个验证器来解析这段代码，并返回它是否合法。合法的代码片段需要遵守以下的所有规则：
 * 代码必须被合法的闭合标签包围。否则，代码是无效的。
 * 闭合标签（不一定合法）要严格符合格式：<TAG_NAME>TAG_CONTENT</TAG_NAME>。其中，<TAG_NAME>是起始标签，</TAG_NAME>是结束标签。
 * 起始和结束标签中的 TAG_NAME 应当相同。当且仅当TAG_NAME 和 TAG_CONTENT 都是合法的，闭合标签才是合法的。
 * 合法的TAG_NAME仅含有大写字母，长度在范围 [1,9] 之间。
 * 否则，该TAG_NAME是不合法的。
 * 合法的TAG_CONTENT可以包含其他合法的闭合标签，cdata（请参考规则7）和任意字符（注意参考规则1）
 * 除了不匹配的<、不匹配的起始和结束标签、不匹配的或带有不合法 TAG_NAME 的闭合标签。否则，TAG_CONTENT是不合法的。
 * 一个起始标签，如果没有具有相同TAG_NAME 的结束标签与之匹配，是不合法的。反之亦然。
 * 不过，你也需要考虑标签嵌套的问题。
 * 一个<，如果你找不到一个后续的>与之匹配，是不合法的。并且当你找到一个<或</时，所有直到下一个>的前的字符，都应当被解析为TAG_NAME（不一定合法）。
 * cdata 有如下格式：<![CDATA[CDATA_CONTENT]]>。CDATA_CONTENT的范围被定义成<![CDATA[和后续的第一个]]>之间的字符。
 * CDATA_CONTENT可以包含任意字符。cdata 的功能是阻止验证器解析CDATA_CONTENT，所以即使其中有一些字符可以被解析为标签（无论合法还是不合法），也应该将它们视为常规字符。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/tag-validator 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-05-08 14:10
 **/
public class _591FiveHundredNinetyOne {

    public boolean isValid(String code) {
        if (code == null || code.equals("") || code.charAt(0) != '<') {
            return false;
        }
        char[] chars = code.toCharArray();
        StringBuilder connectStr;
        Stack<String> stack = new Stack<>();
        boolean existTag = false;
        boolean betweenTag = false;
        for (int i = 0; i < chars.length; i++) {
            //遇到<开头的
            if (chars[i] == '<') {
                int j = i + 1;
                if (j < chars.length && chars[j] == '/') {
                    ++j;
                }
                if (j >= chars.length) {
                    return false;
                }
                //<TAG_NAME>TAG_CONTENT</TAG_NAME>情况
                if (chars[j] >= 'A' && chars[j] <= 'Z') {
                    connectStr = new StringBuilder();
                    int k = j;
                    j = tagTrim(chars, j, connectStr);
                    if (j < 0) {
                        return false;
                    }
                    if (chars[k-1] == '/') {
                        if (stack.empty()) {
                            return false;
                        }
                        if (!stack.pop().equals(connectStr.toString())) {
                            return false;
                        } else {
                            if (stack.empty() && j < chars.length - 1) {
                                return false;
                            }
                        }
                    } else {
                        existTag = true;
                        stack.push(connectStr.toString());
                    }
                } else {
                    //<![CDATA[CDATA_CONTENT]]>情况
                    j = checkCdata(chars, j);
                    if (j == -1 || j >= chars.length || !existTag) {
                        return false;
                    }
                }
                i = j;
            } else {
                if (!existTag) {
                    return false;
                }
            }
        }
        return existTag && stack.empty();
    }


    public int tagTrim(char[] chars, int index, StringBuilder connectStr) {
        int k = 0;
        for (; k < 10 && k + index < chars.length; k++) {
            if (chars[k + index] >= 'A' && chars[k + index] <= 'Z') {
                connectStr.append(chars[k + index]);
            } else if (chars[k + index] == '>') {
                return k != 0 ? k + index : -1;
            } else {
                return -1;
            }
        }
        return  -1;
    }

    public int checkCdata(char[] chars, int index) {
        if (index + 10 > chars.length) {
            return -1;
        }
        if (chars[index++] == '!'
                && chars[index++] == '['
                && chars[index++] == 'C'
                && chars[index++] == 'D'
                && chars[index++] == 'A'
                && chars[index++] == 'T'
                && chars[index++] == 'A'
                && chars[index++] == '['
        ) {
            while (index <= chars.length - 3) {
                if (chars[index] == ']' && chars[index+1] == ']' && chars[index+2] == '>') {
                    return index+2;
                }
                ++index;
            }
            return -1;
        }
        return -1;
    }


    /**
     * 合法代码的例子:
     *
     * 输入: "<DIV>This is the first line <![CDATA[<div>]]></DIV>"
     *
     * 输出: True
     *
     * 解释:
     *
     * 代码被包含在了闭合的标签内： <DIV> 和 </DIV> 。
     *
     * TAG_NAME 是合法的，TAG_CONTENT 包含了一些字符和 cdata 。
     *
     * 即使 CDATA_CONTENT 含有不匹配的起始标签和不合法的 TAG_NAME，它应该被视为普通的文本，而不是标签。
     *
     * 所以 TAG_CONTENT 是合法的，因此代码是合法的。最终返回True。
     *
     *
     * 输入: "<DIV>>>  ![cdata[]] <![CDATA[<div>]>]]>]]>>]</DIV>"
     *
     * 输出: True
     *
     * 解释:
     *
     * 我们首先将代码分割为： start_tag|tag_content|end_tag 。
     *
     * start_tag -> "<DIV>"
     *
     * end_tag -> "</DIV>"
     *
     * tag_content 也可被分割为： text1|cdata|text2 。
     *
     * text1 -> ">>  ![cdata[]] "
     *
     * cdata -> "<![CDATA[<div>]>]]>" ，其中 CDATA_CONTENT 为 "<div>]>"
     *
     * text2 -> "]]>>]"
     *
     *
     * start_tag 不是 "<DIV>>>" 的原因参照规则 6 。
     * cdata 不是 "<![CDATA[<div>]>]]>]]>" 的原因参照规则 7 。
     * 不合法代码的例子:
     *
     * 输入: "<A>  <B> </A>   </B>"
     * 输出: False
     * 解释: 不合法。如果 "<A>" 是闭合的，那么 "<B>" 一定是不匹配的，反之亦然。
     *
     * 输入: "<DIV>  div tag is not closed  <DIV>"
     * 输出: False
     *
     * 输入: "<DIV>  unmatched <  </DIV>"
     * 输出: False
     *
     * 输入: "<DIV> closed tags with invalid tag name  <b>123</b> </DIV>"
     * 输出: False
     *
     * 输入: "<DIV> unmatched tags with invalid tag name  </1234567890> and <CDATA[[]]>  </DIV>"
     * 输出: False
     *
     * 输入: "<DIV>  unmatched start tag <B>  and unmatched end tag </C>  </DIV>"
     * 输出: False
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/tag-validator
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _591FiveHundredNinetyOne().isValid(
                "<DIV><YFSYYS><UVBNIQ><XPMXUNT><WNGMV><OJJGQREMT><Z><GEJDP><LIQS><NCVYU><RAS><UYFKCJCDN><NA><POJVYT><Z><TDC><VUIZQC><BNANGX><TOF><MR>MK</MR></TOF></BNANGX></VUIZQC></TDC></Z></POJVYT></NA></UYFKCJCDN></RAS></NCVYU></LIQS></GEJDP></Z></OJJGQREMT></WNGMV></XPMXUNT></UVBNIQ></YFSYYS></DIV>"
        ));
    }





}
