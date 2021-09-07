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

package letcode.medium;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @version 2.0 动态规划？
 * @program: StudyHTTP
 * @description: 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * @author: 蔡永程
 * @create: 2020-06-15 20:31
 */
public class _3Three {

    /**
     * 示例1:
     * <p>
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     * <p>
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     * <p>
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。
     *     请注意，你的答案必须是 子串 的长度，"pwke"是一个子序列，不是子串。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    public static int lengthOfLongestSubstring(String s) {

        HashSet<Character> chars = new HashSet<Character>();
        HashMap<Integer, Integer> repetition = new HashMap<>();
        int n = s.length();
        int res = n;

        for (int i = 0; i < n - 1; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (s.charAt(i) == s.charAt(j)) {
                    repetition.put(i, j);
                    break;
                }
            }
        }
        for (; res > 0; --res) {
            for (int i = 0; i < n - res + 1; ++i) {
                int k = i;
                for (; k < res + i; ++k) {
                    Integer p = repetition.get(k);
                    if (p != null && p < res + i) break;
                }
                if (k >= res + i) return res;
            }
        }
        return 0;
    }


    public static int lengthOfLongestSubstringT(String s) {


        HashSet<Character> chars = new HashSet<Character>();
        HashMap<Integer, Integer> repetition = new HashMap<>();
        int n = s.length();
        int maxLength = 0;
        int i = 0;
        int j = 1;
        for (; i < n; ++i) {
            chars.add(s.charAt(i));
            if (j == i) j = i + 1;
            for (; j < n; ++j) {
                if (!chars.add(s.charAt(j))) break;
            }
            System.out.println(chars.toString());
            if (chars.size() > maxLength) maxLength = chars.size();
            chars.remove(s.charAt(i));
        }
        return maxLength;
    }


    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("aabaab!bb"));
        System.out.println(lengthOfLongestSubstringT("aabaab!bb"));
    }

}