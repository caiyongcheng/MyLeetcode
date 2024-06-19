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

import java.util.Arrays;

/**
 * @program: MyLeetCode
 * @description: 给你一个以二进制形式表示的数字 s 。请你返回按下述规则将其减少到 1 所需要的步骤数：  如果当前数字为偶数，则将其除以 2 。
 * 如果当前数字为奇数，则将其加上 1 。  题目保证你总是可以按上述规则将测试用例变为 1 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2021-02-02 09:43
 */
public class _1404 {


    /**
     * 示例 1：
     * 输入：s = "1101"
     * 输出：6
     * 解释："1101" 表示十进制数 13 。
     * Step 1) 13 是奇数，加 1 得到 14
     * Step 2) 14 是偶数，除 2 得到 7
     * Step 3) 7  是奇数，加 1 得到 8
     * Step 4) 8  是偶数，除 2 得到 4
     * Step 5) 4  是偶数，除 2 得到 2
     * Step 6) 2  是偶数，除 2 得到 1
     *
     * 示例 2：
     * 输入：s = "10"
     * 输出：1
     * 解释："10" 表示十进制数 2 。
     * Step 1) 2 是偶数，除 2 得到 1
     *
     * 示例 3：
     * 输入：s = "1"
     * 输出：0
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    public int numSteps(String s) {
        final char[] chars = new char[s.length() + 1];
        for (int i = 1; i < chars.length; i++) {
            chars[i] = s.charAt(i-1);
        }
        chars[0] = '0';
        int oneHighIndex = getHighOneIndex(chars);
        if (oneHighIndex == -1) {
            return 1;
        }
        if (oneHighIndex == chars.length-1) {
            return 0;
        }
        int ans = 0;
        int lowIndex = chars.length-1;
        while (oneHighIndex != lowIndex) {
            if (chars[lowIndex] == '1') {
                oneHighIndex = increase(chars, oneHighIndex);
            } else {
                System.arraycopy(chars, oneHighIndex, chars, oneHighIndex+1, lowIndex-oneHighIndex);
                Arrays.fill(chars, 0, oneHighIndex+1, '0');
                oneHighIndex++;
            }
            ++ans;
        }
        return ans;
    }

    public int getHighOneIndex(char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '1') {
                return i;
            }
        }
        return -1;
    }

    public int increase(char[] chars, int oneHighIndex) {
        int lowIndex = chars.length-1;
        if (chars[lowIndex] == '1') {
            chars[lowIndex] = '2';
            int i = lowIndex;
            while (i>-1 && chars[i] == '2') {
                chars[i] = '0';
                chars[--i]++;
            }
            return Math.min(i, oneHighIndex);
        }
        chars[lowIndex] = '1';
        return oneHighIndex;
    }

    /**
     * 示例 1：
     *
     * 输入：s = "1101"
     * 输出：6
     * 解释："1101" 表示十进制数 13 。
     * Step 1) 13 是奇数，加 1 得到 14
     * Step 2) 14 是偶数，除 2 得到 7
     * Step 3) 7  是奇数，加 1 得到 8
     * Step 4) 8  是偶数，除 2 得到 4
     * Step 5) 4  是偶数，除 2 得到 2
     * Step 6) 2  是偶数，除 2 得到 1
     *
     * 示例 2：
     * 输入：s = "10"
     * 输出：1
     * 解释："10" 表示十进制数 2 。
     * Step 1) 2 是偶数，除 2 得到 1
     *
     * 示例 3：
     * 输入：s = "1"
     * 输出：0
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1404().numSteps("1"));
    }

}
