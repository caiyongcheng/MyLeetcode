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

package letcode.competition;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author CaiYongcheng
 * @since 2021-08-08 10:29
 **/
public class One20210808 {

    public boolean isPrefixString(String s, String[] words) {
        int index = 0;
        int i = 0;
        int nIndex = 0;
        for (String word : words) {
            nIndex = word.length();
            i = 0;
            for (; i < nIndex && index < s.length(); i++, index++) {
                if (s.charAt(index) != word.charAt(i)) {
                    return false;
                }
            }
            if (index >= s.length() && i >= nIndex) {
                return true;
            }
        }
        return index >= s.length() && i >= nIndex;
    }


    public int minStoneSum(int[] piles, int k) {
        Arrays.sort(piles);
        for (int i = 0; i < piles.length/2 ; i++) {
            int tmp = piles[i];
            piles[i] = piles[piles.length-1-i];
            piles[piles.length-1-i] = tmp;
        }
        for (int i = 0; i < k; i++) {
            piles[0] -= piles[0]/2;
            int index = 0;
            int nextIndex = 1;
            int left = 1;
            int right = 2;
            while (left < piles.length) {
                nextIndex = right < piles.length && piles[right] > piles[left] ? right : left;
                if (piles[index] < piles[nextIndex]) {
                    int tmp = piles[index];
                    piles[index] = piles[nextIndex];
                    piles[nextIndex] = tmp;
                    index = nextIndex;
                    left = (index << 1) + 1;
                    right = left + 1;
                } else {
                    break;
                }
            }
        }
        return Arrays.stream(piles).sum();
    }


    public int minSwaps(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        stack.push(chars[0]);
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == ']' && !stack.isEmpty() && stack.peek() == '[') {
                stack.pop();
            } else {
                stack.push(chars[i]);
            }
        }
        return ((stack.size() >> 1) + 1) >> 1;
    }



    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int[] ans = new int[obstacles.length];
        Arrays.fill(ans, 1);
        for (int i = 1; i < ans.length; i++) {
            for (int j = i - 1; j >= 0 - 1; j--) {
                if (obstacles[j] <= obstacles[i] && ans[j] + 1 > ans[i]) {
                    ans[i] = ans[j] + 1;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                new One20210808().longestObstacleCourseAtEachPosition(
                        new int[]{3,1,5,6,4,2}
                )
        ));
    }

}
