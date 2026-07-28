package letcode.normal.medium;

import java.util.LinkedList;
import java.util.Objects;

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
 * @since 2021-10-19 09:04
 **/
public class _211 {

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
            if (scoreLow > wrapper.scoreHigh || wrapper.scoreHigh > scoreHigh) {
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

    public _211() {

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
        _211 wordDictionary = new _211();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad"));
        System.out.println(wordDictionary.search("bad"));
        System.out.println(wordDictionary.search(".ad"));
        System.out.println(wordDictionary.search("b.."));

    }

}
