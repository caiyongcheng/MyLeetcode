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

import java.util.*;

/**
 * 给定一个m x n 二维字符网格board和一个单词（字符串）列表 words，
 * 找出所有同时在二维网格和字典中出现的单词。
 * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母在一个单词中不允许被重复使用。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/word-search-ii 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-09-16 09:03
 **/
public class _212 {

    public List<String> findWords(char[][] board, String[] words) {
        /*
        如果 单词a 是 单词b 的子串，那么如果能找到单词a也可能找到单词b。
        同理，如果找到了单词n也一定能找到单词a。
        制作一个缓存，缓存每个单词搜索时的路径，搜索单词前先验证是否存在该缓存的路径，
        存在就先以该路径进行搜索。搜索不到在进行全局搜索。应按照单词字典顺序加上长度排序
        进行搜索，这样长的单词先被搜索添加到缓存中。
        第二是制作二维网格的缓存，缓存起始字母的开始列表。
         */
        ArrayList<Integer>[] lists = new ArrayList[26];
        ArrayList<String> ans = new ArrayList<>();
        HashSet<String> cache = new HashSet<>();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (lists[board[row][col] - 'a'] == null) {
                    lists[board[row][col] - 'a'] = new ArrayList<Integer>();
                }
                lists[board[row][col] - 'a'].add(row * board[row].length + col);
            }
        }
        Arrays.sort(words, (s1, s2) -> {
            int lenth = Math.min(s1.length(), s2.length());
            for (int index = 0; index < lenth; index++) {
                if (s1.charAt(index) == s2.charAt(index)) {
                    continue;
                }
                return s1.charAt(index) - s2.charAt(index);
            }
            return s2.length() - s1.length();
        });
        for (String word : words) {
            if (cache.contains(word)) {
                ans.add(word);
                continue;
            }
            if (lists[word.charAt(0) - 'a'] == null) {
                continue;
            }
            for (Integer position : lists[word.charAt(0) - 'a']) {
                if (find(board, word, position / board[0].length,
                        position % board[0].length, 0, cache)) {
                    ans.add(word);
                    break;
                }
            }
        }
        return ans;
    }


    public boolean find(char[][] board, String word, int row, int col, int position, HashSet<String> cache) {
        cache.add(word.substring(0, position));
        if (position >= word.length() - 1) {
            return true;
        }
        ++position;
        board[row][col] = '.';
        if (row + 1 < board.length && board[row + 1][col] == word.charAt(position)
                && find(board, word, row + 1, col, position, cache)) {
            board[row][col] = word.charAt(position - 1);
            return true;
        }
        if (row - 1 > -1 && board[row - 1][col] == word.charAt(position)
                && find(board, word, row - 1, col, position, cache)) {
            board[row][col] = word.charAt(position - 1);
            return true;
        }
        if (col + 1 < board[0].length && board[row][col + 1] == word.charAt(position)
                && find(board, word, row, col + 1, position, cache)) {
            board[row][col] = word.charAt(position - 1);
            return true;
        }
        if (col - 1 > -1 && board[row][col - 1] == word.charAt(position)
                && find(board, word, row, col - 1, position, cache)) {
            board[row][col] = word.charAt(position - 1);
            return true;
        }
        board[row][col] = word.charAt(position - 1);
        return false;
    }


    /**
     * 输入：board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}},
     * words = {"oath","pea","eat","rain"}
     * 输出：{"eat","oath"}
     * <p>
     * 示例 2：
     * 输入：board = {{'a','b'},{'c','d'}}, words = {"abcb"}
     * 输出：{}
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/word-search-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]]
     * ["oath","pea","eat","rain","hklf", "hf"]
     * 输出：
     * ["eat","hf","hklf"]
     * 预期结果：
     * ["oath","eat","hklf","hf"]
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _212().findWords(
                new char[][]{{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}},
                new String[]{"oath", "pea", "eat", "rain", "hklf", "hf"}
        ));
    }


}
