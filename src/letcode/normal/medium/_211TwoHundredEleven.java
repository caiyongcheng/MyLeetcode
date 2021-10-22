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

import java.util.*;

/**
 * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
 * 实现词典类 WordDictionary ：  WordDictionary() 初始化词典对象
 * void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
 * bool search(word) 如果数据结构中存在字符串与word 匹配，则返回 true ；否则，返回 false 。
 * word 中可能包含一些 '.' ，每个. 都可以表示任何一个字母。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/design-add-and-search-words-data-structure
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-10-19 09:04
 **/
public class _211TwoHundredEleven {

    static class Wrapper {
        String str;
        int scoreLow;
        int scoreHigh;

        public Wrapper(String str) {
            scoreLow = 0;
            scoreHigh = 0;
            this.str = str;
            int length = str.length();
            for (int index = 0; index < length; index++) {
                if (str.charAt(index) == '.') {
                    scoreLow += 'a';
                    scoreHigh += 'z';
                } else {
                    scoreLow += str.charAt(index);
                    scoreHigh += str.charAt(index);
                }
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Wrapper wrapper = (Wrapper) o;
            if (scoreLow > wrapper.scoreHigh || wrapper.scoreLow > scoreHigh) {
                return false;
            }
            int length = str.length();
            for (int index = 0; index < length; index++) {
                if (str.charAt(index) == '.' || wrapper.str.charAt(index) == '.'
                        || str.charAt(index) == wrapper.str.charAt(index)) {
                    continue;
                }
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            return Objects.hash(str, scoreLow, scoreHigh);
        }
    }

    private final LinkedList<Wrapper>[] dictionary = new LinkedList[501];

    public _211TwoHundredEleven() {

    }

    public void addWord(String word) {
        int length = word.length();
        if (dictionary[length] == null) {
            dictionary[length] = new LinkedList<>();
        }
        dictionary[length].push(new Wrapper(word));
    }

    public boolean search(String word) {
        int length = word.length();
        if (dictionary[length] == null) {
            return false;
        }
        return dictionary[length].contains(new Wrapper(word));
    }

    public static void main(String[] args) {
        _211TwoHundredEleven wordDictionary = new _211TwoHundredEleven();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad"));
        System.out.println(wordDictionary.search("bad"));
        System.out.println(wordDictionary.search(".ad"));
        System.out.println(wordDictionary.search("b.."));

    }

}
