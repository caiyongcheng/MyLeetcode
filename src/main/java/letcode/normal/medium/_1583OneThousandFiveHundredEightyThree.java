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

import java.util.HashSet;

/**
 * 给你一份 n 位朋友的亲近程度列表，其中 n 总是 偶数 。
 * 对每位朋友 i，preferences[i] 包含一份 按亲近程度从高到低排列 的朋友列表。
 * 换句话说，排在列表前面的朋友与 i 的亲近程度比排在列表后面的朋友更高。每个列表中的朋友均以 0 到 n-1 之间的整数表示。
 * 所有的朋友被分成几对，配对情况以列表 pairs 给出，其中 pairs[i] = [xi, yi] 表示 xi 与 yi 配对，且 yi 与 xi 配对。
 * 但是，这样的配对情况可能会是其中部分朋友感到不开心。
 * 在 x 与 y 配对且 u 与 v 配对的情况下，如果同时满足下述两个条件，
 * x 就会不开心：  x 与 u 的亲近程度胜过 x 与 y，且 u 与 x 的亲近程度胜过 u 与 v 返回 不开心的朋友的数目 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/count-unhappy-friends 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-08-14 22:12
 **/
public class _1583OneThousandFiveHundredEightyThree {
    
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        /**
         * 第一 pairs 数组化
         * 第二 记录下 每个x下每个朋友的排序
         * 第三 hashset保存已经记录的结果
         */
        int[] relationships = new int[n];
        int[][] relationIndexes = new int[n][n];
        int another;
        HashSet<Integer> unHappy = new HashSet<>();
        int currentFriend;
        int limit;
        for (int[] pair : pairs) {
            relationships[pair[0]] = pair[1];
            relationships[pair[1]] = pair[0];
        }
        for (int i = 0; i < preferences.length; i++) {
            int index = 0;
            for (int j : preferences[i]) {
                relationIndexes[i][j] = index;
                ++index;
            }
        }
        for (int x = 0; x < relationships.length; x++) {
            if (unHappy.contains(x)) {
                continue;
            }
            currentFriend = relationships[x];
            limit = relationIndexes[x][currentFriend];
            for (int u = 0; u < limit; u++) {
                another = preferences[x][u];
                if (relationIndexes[another][x] < relationIndexes[another][relationships[another]]) {
                    unHappy.add(x);
                    unHappy.add(another);
                    break;
                }
            }
        }
        return unHappy.size();
    }

    /**
     * 示例 1：
     * 输入：n = 4, preferences = {{1, 2, 3}, {3, 2, 0}, {3, 1, 0}, {1, 2, 0}}, pairs = {{0, 1}, {2, 3}}
     * 输出：2
     * 解释：
     * 朋友 1 不开心，因为：
     * - 1 与 0 配对，但 1 与 3 的亲近程度比 1 与 0 高，且
     * - 3 与 1 的亲近程度比 3 与 2 高。
     * 朋友 3 不开心，因为：
     * - 3 与 2 配对，但 3 与 1 的亲近程度比 3 与 2 高，且
     * - 1 与 3 的亲近程度比 1 与 0 高。
     * 朋友 0 和 2 都是开心的。
     * 
     * 示例 2：
     * 输入：n = 2, preferences = {{1}, {0}}, pairs = {{1, 0}}
     * 输出：0
     * 解释：朋友 0 和 1 都开心。
     * 
     * 示例 3：
     * 输入：n = 4, preferences = {{1, 3, 2}, {2, 3, 0}, {1, 3, 0}, {0, 2, 1}}, pairs = {{1, 3}, {0, 2}}
     * 输出：4
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/count-unhappy-friends
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1583OneThousandFiveHundredEightyThree().unhappyFriends(
                4,
                new int[][]{{1, 2, 3}, {3, 2, 0}, {3, 1, 0}, {1, 2, 0}},
                new int[][]{{0, 1}, {2, 3}}
        ));
    }


}
