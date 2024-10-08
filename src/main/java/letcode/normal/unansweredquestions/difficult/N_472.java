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

    /**
     * 示例 1：
     * <p>
     * 输入：words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
     * 输出：["catsdogcats","dogcatsdog","ratcatdogcat"]
     * 解释："catsdogcats" 由 "cats", "dog" 和 "cats" 组成;
     * "dogcatsdog" 由 "dog", "cats" 和 "dog" 组成;
     * "ratcatdogcat" 由 "rat", "cat", "dog" 和 "cat" 组成。
     * 示例 2：
     * <p>
     * 输入：words = ["cat","dog","catdog"]
     * 输出：["catdog"]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/concatenated-words
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(FormatPrintUtils.formatList(new N_472().findAllConcatenatedWordsInADict(
                new String[]{"cat", "dog", "catdog"}
        )));
    }


}
