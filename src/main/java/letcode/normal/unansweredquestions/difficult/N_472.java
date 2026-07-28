package letcode.normal.unansweredquestions.difficult;

import datastructure.utils.FormatPrintUtils;

import java.util.*;

/**
 * 给你一个 不含重复 单词的字符串数组 words ，请你找出并返回 words 中的所有 连接词 。
 * 连接词 定义为：一个完全由给定数组中的至少两个较短单词组成的字符串  来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/concatenated-words 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-12-28 09:11
 **/
public class N_472 {

    class TreeNode {
        HashMap<Character, TreeNode> nextNode = new HashMap<>();
        HashMap<Character, Boolean> hashEnd = new HashMap<>();
    }

    TreeNode head = new TreeNode();

    int hasEmpty = 0;

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        /*
        如果一个单词由一个或者更多个连接词组成，那么一定可以分解为由多个非连接词组成
        按字符串长度排序，长度小的单词一定不可能由长度更长的单词构成
         */
        ArrayList<String> ans = new ArrayList<>();
        Arrays.sort(words, Comparator.comparingInt(String::length));
        hasEmpty = words[0].length() == 0 ? 1 : 0;
        for (String word : words) {
            if (dfs(word) + hasEmpty > 1) {
                ans.add(word);
            }
        }
        return ans;
    }


    public int dfs(String str) {
        int length = str.length();
        TreeNode currentNode = head;
        TreeNode subNode;
        boolean success = true;
        int index = 0;
        int cnt;
        for (index = 0; index < length; index++) {
            subNode = currentNode.nextNode.get(str.charAt(index));
            if (subNode == null) {
                success = false;
                break;
            }
            if (currentNode.hashEnd.getOrDefault(str.charAt(index), false)) {
                if (index + 1 == length) {
                    return 1;
                }
                cnt = 1 + dfs(str.substring(index + 1, length));
                if (cnt > 1) {
                    return cnt;
                }
            }
            currentNode = subNode;
        }
        if (!success) {
            for (; index < length; ++index) {
                subNode = new TreeNode();
                currentNode.nextNode.put(str.charAt(index), subNode);
                if (index == length - 1) {
                    currentNode.hashEnd.put(str.charAt(index), true);
                }
                currentNode = subNode;
            }
        }
        return Integer.MIN_VALUE;
    }

}
