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
 * @date: 2021-04-14 09:21
 */
public class _208TwoHundredEight {


    class Node {
        char ch;
        Node[] nexts = new Node[27];

        public Node(char ch) {
            this.ch = ch;
        }
    }


    private Node root;


    /** Initialize your data structure here. */
    public _208TwoHundredEight() {
        root = new Node('-');
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        char[] chars = word.toCharArray();
        Node current = root;
        for (char aChar : chars) {
            if (current.nexts[aChar - 'a'] == null) {
                current.nexts[aChar - 'a'] = new Node(aChar);
            }
            current = current.nexts[aChar - 'a'];
        }
        current.nexts[26] = new Node('/');
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        char[] chars = word.toCharArray();
        Node current = root;
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
        Node current = root;
        for (char aChar : chars) {
            if (current.nexts[aChar - 'a'] == null) {
                return false;
            }
            current = current.nexts[aChar - 'a'];
        }
        return true;
    }


    /**
     * 输入
     * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
     * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
     * 输出
     * [null, null, true, false, true, null, true]
     *
     * 解释
     * Trie trie = new Trie();
     * trie.insert("apple");
     * trie.search("apple");   // 返回 True
     * trie.search("app");     // 返回 False
     * trie.startsWith("app"); // 返回 True
     * trie.insert("app");
     * trie.search("app");     // 返回 True
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        _208TwoHundredEight trie = new _208TwoHundredEight();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        System.out.println(trie.search("app"));
    }


}
