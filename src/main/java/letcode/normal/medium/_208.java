package letcode.normal.medium;

/**
 * @program: MyLeetcode
 * @description: Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，
 * 用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。  
 * 请你实现 Trie 类：  Trie() 初始化前缀树对象。 void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @since: 2021-04-14 09:21
 */
public class _208 {


    class CharNode {
        char ch;
        CharNode[] nexts = new CharNode[27];

        public CharNode(char ch) {
            this.ch = ch;
        }
    }


    private final CharNode root;

    /** Initialize your data structure here. */
    public _208() {
        root = new CharNode('-');
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        char[] chars = word.toCharArray();
        CharNode current = root;
        for (char aChar : chars) {
            if (current.nexts[aChar - 'a'] == null) {
                current.nexts[aChar - 'a'] = new CharNode(aChar);
            }
            current = current.nexts[aChar - 'a'];
        }
        current.nexts[26] = new CharNode('/');
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        char[] chars = word.toCharArray();
        CharNode current = root;
        for (char aChar : chars) {
            if (current.nexts[aChar - 'a'] == null) {
                return false;
            }
            current = current.nexts[aChar - 'a'];
        }
        return current.nexts[26] != null;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        char[] chars = prefix.toCharArray();
        CharNode current = root;
        for (char aChar : chars) {
            if (current.nexts[aChar - 'a'] == null) {
                return false;
            }
            current = current.nexts[aChar - 'a'];
        }
        return true;
    }


}
