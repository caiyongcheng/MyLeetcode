package letcode.normal.difficult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

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


}
