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

import java.util.HashMap;

/**
 * 使用下面描述的算法可以扰乱字符串 s 得到字符串 t ： 
 * 如果字符串的长度为 1 ，算法停止 
 * 如果字符串的长度 > 1 ，执行下述步骤： 
 * 在一个随机下标处将字符串分割成两个非空的子字符串。
 * 即，如果已知字符串 s ，则可以将其分成两个子字符串 x 和 y ，且满足 s = x + y 。 
 * 随机 决定是要「交换两个子字符串」还是要「保持这两个子字符串的顺序不变」。
 * 即，在执行这一步骤之后，s 可能是 s = x + y 或者 s = y + x 。 在 x 和 y 这两个子字符串上继续从步骤 1 开始递归执行此算法。 
 * 给你两个 长度相等 的字符串 s1 和s2，判断s2是否是s1的扰乱字符串。如果是，返回 true ；否则，返回 false 。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/scramble-string 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author CaiYongcheng
 * @since 2021-04-16 11:50
 **/
public class _87 {


    private char[] origin;
    private char[] present;
    private HashMap<String, Boolean> cache;

    public boolean equals(int[] c1, int[] c2) {
        for (int i = c1.length - 1; i >= 0; i--) {
            if (c1[i] != c2[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean isScramble(String s1, String s2) {
        //判断s2是不是s1的扰流字符串 等价于s2能否经过某些变换得到s1
        //假设s1 = 0..i, i+1..x, x+1..j, j+1...n
        //第一次交换后 x+1..j, j+1...n, 0..i, i+1..x
        //第二次交换后 j+1...n,x+1..j,i+1..x,0..i,
        //从上可以看出 如果将s分割成 x，y两部分 如果进行了交换变成了y，x
        //则之后不管进行怎么样的变化 y的元素一定在x元素之前
        //判断转化成 找出每个字符串 可能的分割位置，递归验证是否正确
        if (s1.length() != s2.length()) {
            return false;
        }
        origin = s1.toCharArray();
        present = s2.toCharArray();
        cache = new HashMap<>();
        return isScramble(0, s1.length()-1, 0);
    }


    public boolean isScramble(int left, int right, int nLeft) {
        if (left == right) {
            return origin[left] == present[nLeft];
        }
        String key = left + "-" + right + "-" + nLeft;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        int hIndex = left;
        int tIndex = right;
        int nIndex = nLeft;
        String k1;
        String k2;
        int[] head = new int[26];
        int[] tail = new int[26];
        int[] compare = new int[26];
        for (; hIndex < right; ++hIndex, --tIndex, ++nIndex) {
            compare[present[nIndex] - 'a']++;
            head[origin[hIndex] - 'a']++;
            if (equals(compare, head)) {
                k1 = left + "-" + hIndex + "-" + nLeft;
                if ((cache.containsKey(k1) && cache.get(k1)) || isScramble(left, hIndex, nLeft)) {
                    cache.put(k1, true);
                    k2 = (hIndex+1) + "-" + right + "-" + (nIndex+1);
                    if ((cache.containsKey(k2) && cache.get(k2)) || isScramble((hIndex+1), right, (nIndex+1))) {
                        cache.put(k2, true);
                        cache.put(key, true);
                        return true;
                    } else {
                        cache.put(k2, false);
                    }
                } else {
                    cache.put(k1, false);
                }
            }
            tail[origin[tIndex] - 'a']++;

            if (equals(compare, tail)) {
                k1 = tIndex + "-" + right + "-" + nLeft;
                if ((cache.containsKey(k1) && cache.get(k1)) || isScramble(tIndex, right, nLeft)) {
                    cache.put(k1, true);
                    k2 = left + "-" + (tIndex-1) + "-" + (nIndex+1);
                    if ((cache.containsKey(k2) && cache.get(k2)) || isScramble(left, (tIndex-1), nIndex+1)) {
                        cache.put(k2, true);
                        cache.put(key, true);
                        return true;
                    } else {
                        cache.put(k2, false);
                    }
                } else {
                    cache.put(k1, false);
                }
            }
        }
        cache.put(key, false);
        return false;
    }


    /**
     * 示例 1：
     * 输入：s1 = "great", s2 = "rgeat"
     * 输出：true
     * 解释：s1 上可能发生的一种情形是：
     * "great" --> "gr/eat" // 在一个随机下标处分割得到两个子字符串
     * "gr/eat" --> "gr/eat" // 随机决定：「保持这两个子字符串的顺序不变」
     * "gr/eat" --> "g/r / e/at" // 在子字符串上递归执行此算法。两个子字符串分别在随机下标处进行一轮分割
     * "g/r / e/at" --> "r/g / e/at" // 随机决定：第一组「交换两个子字符串」，第二组「保持这两个子字符串的顺序不变」
     * "r/g / e/at" --> "r/g / e/ a/t" // 继续递归执行此算法，将 "at" 分割得到 "a/t"
     * "r/g / e/ a/t" --> "r/g / e/ a/t" // 随机决定：「保持这两个子字符串的顺序不变」
     * 算法终止，结果字符串和 s2 相同，都是 "rgeat"
     * 这是一种能够扰乱 s1 得到 s2 的情形，可以认为 s2 是 s1 的扰乱字符串，返回 true
     *
     * 示例 2：
     * 输入：s1 = "abcde", s2 = "caebd"
     * 输出：false
     *
     * 示例 3：
     * 输入：s1 = "a", s2 = "a"
     * 输出：true
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/scramble-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        /**
         * "eebaacbcbcadaaedceaaacadccd"
         * "eadcaacabaddaceacbceaabeccd"
         *
         * "abcd bdacbdac"
         * "bdac abcdbdac"
         */
        System.out.println(new _87().isScramble("abcde", "caebd"));
    }

}
