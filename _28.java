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

package letcode.normal.easy;

import java.util.Arrays;

/**
 * @program: StudyHTTP
 * @description: 给定一个haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回 -1。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/implement-strstr
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-06-20 11:05
 */
public class _28 {





    


    /**
     * 示例 1:
     * 输入: haystack = "hello", needle = "ll"
     * 输出: 2
     * <p>
     * 示例 2:
     * 输入: haystack = "aaaaa", needle = "bba"
     * 输出: -1
     * 说明:
     * <p>
     * 当needle是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
     * 对于本题而言，当needle是空字符串时我们应当返回 0 。这与C语言的strstr()以及 Java的indexOf()定义相符。
     *
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStrUseKMP(String haystack, String needle) {
        if (needle.length() > haystack.length()) {
            return -1;
        }
        if ("".equals(needle)) {
            return 0;
        }
        char[] textArr = haystack.toCharArray();
        char[] targetArr = needle.toCharArray();
        int[] next = new int[targetArr.length];
        next[0] = -1;
        int start = -1;
        int end = 0;
        int length = targetArr.length - 1;
        while (end < length) {
            if (start == -1 || targetArr[start] == targetArr[end]) {
                ++end;
                ++start;
                next[end] = start;
            } else {
                start = next[start];
            }
        }
        int indexi = 0;
        int indexj = 0;
        while (indexi < textArr.length && indexj < targetArr.length) {
            if (indexj == -1 || textArr[indexi] == targetArr[indexj]) {
                ++indexj;
                ++indexi;
            } else {
                indexj = next[indexj];
            }
        }
        return indexj >= targetArr.length ? indexi - targetArr.length : -1;
    }

    /**
     * 示例 1：
     *
     * 输入：haystack = "hello", needle = "ll"
     * 输出：2
     * 示例 2：
     *
     * 输入：haystack = "aaaaa", needle = "bba"
     * 输出：-1
     * 示例 3：
     *
     * 输入：haystack = "", needle = ""
     * 输出：0
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/implement-strstr
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(strStrUseKMP("mississippi", "issi"));
    }
}
