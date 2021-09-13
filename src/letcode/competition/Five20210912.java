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

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author CaiYongcheng
 * @date 2021-09-12 10:24
 **/
public class Five20210912 {


    /**
     * 给你一个下标从 0 开始的字符串 word 和一个字符 ch 。找出 ch 第一次出现的下标 i ，反转 word 中从下标 0 开始、直到下标 i 结束（含下标 i ）的那段字符。如果 word 中不存在字符 ch ，则无需进行任何操作。
     * 例如，如果 word = "abcdefd" 且 ch = "d" ，那么你应该 反转 从下标 0 开始、直到下标 3 结束（含下标 3 ）。结果字符串将会是 "dcbaefd" 。
     * 返回 结果字符串 。
     *
     * @param word
     * @param ch
     * @return
     */
    public String reversePrefix(String word, char ch) {
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ch) {
                int limit = i >> 1;
                char tmp;
                for (int j = 0; j <= limit; j++) {
                    tmp = chars[i - j];
                    chars[i - j] = chars[j];
                    chars[j] = tmp;
                }
                break;
            }
        }
        return new String(chars);
    }


    /**
     * 用一个下标从 0 开始的二维整数数组 rectangles 来表示 n 个矩形，其中 rectangles[i] = [widthi, heighti] 表示第 i 个矩形的宽度和高度。
     * <p>
     * 如果两个矩形 i 和 j（i < j）的宽高比相同，则认为这两个矩形 可互换 。更规范的说法是，两个矩形满足 widthi/heighti == widthj/heightj（使用实数除法而非整数除法），则认为这两个矩形 可互换 。
     * <p>
     * 计算并返回 rectangles 中有多少对 可互换 矩形。
     *
     * @param rectangles
     * @return
     */
    public long interchangeableRectangles(int[][] rectangles) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (int[] rectangle : rectangles) {
            int gcd = gcd(rectangle[0], rectangle[1]);
            String key = rectangle[0] / gcd + "-" + rectangle[1] / gcd;
            hashMap.put(key, hashMap.getOrDefault(key, 0) + 1);
        }
        long ans = 0;
        for (Integer value : hashMap.values()) {
            ans += ((long) value * (value - 1));
        }
        return ans >> 1;
    }


    public int gcd(int x, int y) {
        if (x < y) {
            return gcd(y, x);
        }
        if (x % y == 0) {
            return y;
        }
        return gcd(y, x % y);
    }


    public int maxProduct(String s) {
        char[] chars = s.toCharArray();
        int len = (1 << chars.length);
        int[][][] dp = new int[len][chars.length][chars.length];
        for (int k = 1; k < dp.length; k++) {
            ArrayList<Integer> useable = new ArrayList<>();
            for (int i = 0; i < chars.length; i++) {
                if ((k & (1 << i)) != 0) {
                    useable.add(i);
                }
            }
            int[][] mask = dp[k];
            for (int i = useable.size() - 1; i >= 0; i--) {
                Integer iIndex = useable.get(i);
                mask[iIndex][iIndex] = 1;
                for (int j = i + 1; j < useable.size(); j++) {
                    Integer jIndex = useable.get(j);
                    mask[iIndex][jIndex] = chars[iIndex] == chars[jIndex]
                            ? mask[useable.get(i + 1)][useable.get(j - 1)] + 2
                            : Math.max(mask[useable.get(i + 1)][jIndex], mask[iIndex][useable.get(j - 1)]);
                }
            }
            mask[0][0] = mask[useable.get(0)][useable.get(useable.size() - 1)];
        }
        int ans = 0;
        int nLen = len >> 1;
        for (int i = 1; i < nLen; i++) {
            ans = Math.max(ans, dp[i][0][0] * dp[len - i][0][0]);
        }
        return ans;
    }


    /**
     * 输入：word = "abcdefd", ch = "d"
     * 输出："dcbaefd"
     * 解释："d" 第一次出现在下标 3 。
     * 反转从下标 0 到下标 3（含下标 3）的这段字符，结果字符串是 "dcbaefd" 。
     * 示例 2：
     * <p>
     * 输入：word = "xyxzxe", ch = "z"
     * 输出："zxyxxe"
     * 解释："z" 第一次也是唯一一次出现是在下标 3 。
     * 反转从下标 0 到下标 3（含下标 3）的这段字符，结果字符串是 "zxyxxe" 。
     * 示例 3：
     * <p>
     * 输入：word = "abcd", ch = "z"
     * 输出："abcd"
     * 解释："z" 不存在于 word 中。
     * 无需执行反转操作，结果字符串是 "abcd" 。
     * <p>
     * 示例 1：
     * <p>
     * 输入：rectangles = {{4,8},{3,6},{10,20},{15,30}}
     * 输出：6
     * 解释：下面按下标（从 0 开始）列出可互换矩形的配对情况：
     * - 矩形 0 和矩形 1 ：4/8 == 3/6
     * - 矩形 0 和矩形 2 ：4/8 == 10/20
     * - 矩形 0 和矩形 3 ：4/8 == 15/30
     * - 矩形 1 和矩形 2 ：3/6 == 10/20
     * - 矩形 1 和矩形 3 ：3/6 == 15/30
     * - 矩形 2 和矩形 3 ：10/20 == 15/30
     * 示例 2：
     * <p>
     * 输入：rectangles = {{4,5},{7,8}}
     * 输出：0
     * 解释：不存在成对的可互换矩形。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new Five20210912().maxProduct(
                "bb"
        ));
    }


}
