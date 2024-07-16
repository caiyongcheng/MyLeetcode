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

package letcode.normal.medium;

import java.util.Stack;

/**
 * @program: MyLeetcode
 * @description: 给定一个编码字符串 S。请你找出 解码字符串 并将其写入磁带。解码时，从编码字符串中 每次读取一个字符 ，并采取以下步骤：
 * 如果所读的字符是字母，则将该字母写在磁带上。 如果所读的字符是数字（例如 d），则整个当前磁带总共会被重复写d-1 次。 
 * 现在，对于给定的编码字符串 S 和索引 K，查找并返回解码字符串中的第K个字母。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/decoded-string-at-index 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @since: 2021-03-30 09:03
 **/
public class _880 {

    public String decodeAtIndex(String S, int K) {
        long nowLength = 0;
        long k = K;
        long num = 1;
        Stack<Integer> indexStack = new Stack<>();
        Stack<Long> lengthStack = new Stack<>();
        Stack<Long> numberStack = new Stack<>();
        char[] chars = S.toCharArray();
        int index = 0;
        long length = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] <= '9' && chars[i] >= '0') {
                lengthStack.push(nowLength);
                indexStack.push(i);
                num = 1;
                for (index = i; index < chars.length && chars[index] <= '9' && chars[index] >= '0'; ++index) {
                    num *= (chars[index] - '0');
                }
                numberStack.push(num);
                i = index - 1;
                nowLength *= num;
                if (nowLength > K) {
                    break;
                }
            } else {
                ++nowLength;
                if (nowLength == K) {
                    return chars[i]+"";
                }
            }
        }
        while (!lengthStack.empty()) {
            length = lengthStack.pop();
            num = numberStack.pop();
            index = indexStack.pop();
            k = k % length;
            if (k == 0) {
                return chars[index-1] + "";
            }
            if (!lengthStack.empty() && k > lengthStack.peek() * numberStack.peek()) {
                k -= lengthStack.peek() * numberStack.peek();
                return chars[(int) (indexStack.peek()+k)] + "";
            }
        }
        return chars[(int) (k-1)] + "";
    }


    /**
     * 示例 1：
     * 输入：S = "leet2code3", K = 19 7
     * 输出："o"
     * 解释：
     * 解码后的字符串为 "leetleetcodeleetleetcodeleetleetcode"。
     * 字符串中的第 10 个字母是 "o"。
     *
     * 示例 2：
     * 输入：S = "ha22", K = 5
     * 输出："h"
     * 解释：
     * 解码后的字符串为 "hahahaha"。第 5 个字母是 "h"。
     *
     * 示例 3：
     * 输入：S = "a2345678999999999999999", K = 1
     * 输出："a"
     * 解释：
     * 解码后的字符串为 "a" 重复 8301530446056247680 次。第 1 个字母是 "a"。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/decoded-string-at-index
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        System.out.println(98);
        System.out.println(new _880().decodeAtIndex("vzpp636m8y",
                2920));
    }

}
