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

import java.util.Map;
import java.util.TreeMap;

/**
 * 大餐 是指 恰好包含两道不同餐品 的一餐，其美味程度之和等于 2 的幂。  你可以搭配 任意 两道餐品做一顿大餐。 
 * 给你一个整数数组 deliciousness ，其中 deliciousness[i] 是第 i 道餐品的美味程度，
 * 返回你可以用数组中的餐品做出的不同 大餐 的数量。结果需要对 109 + 7 取余。  注意，只要餐品下标不同，就可以认为是不同的餐品，即便它们的美味程度相同。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/count-good-meals 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-07-07 21:45
 **/
public class _1711 {


    public int countPairs(int[] deliciousness) {
        TreeMap<Integer, Integer> compress = new TreeMap<>();
        final int modValue = 1000000007;
        int index = 1;
        int key;
        long value;
        int ans = 0;
        for (int delicious : deliciousness) {
            compress.put(delicious, compress.getOrDefault(delicious, 0) + 1);
        }
        for (int i = 0; i < 22; i++) {
            for (Map.Entry<Integer, Integer> entry : compress.entrySet()) {
                key = entry.getKey();
                if ((key << 1) > index) {
                    break;
                }
                value = entry.getValue();
                if ((key << 1) == index) {
                    if (value > 1) {
                        ans = (int) ((ans + (value * (value - 1) >> 1) % modValue) % modValue);
                    }
                    break;
                }
                ans = (int) ((ans + (value * compress.getOrDefault(index - key, 0)) % modValue) % modValue);
            }
            index <<= 1;
        }
        return ans;
    }

    /**
     * 示例 1：
     * 输入：deliciousness = [1,3,5,7,9]
     * 输出：4
     * 解释：大餐的美味程度组合为 (1,3) 、(1,7) 、(3,5) 和 (7,9) 。
     * 它们各自的美味程度之和分别为 4 、8 、8 和 16 ，都是 2 的幂。
     * 示例 2：
     *
     * 输入：deliciousness = [1,1,1,3,3,3,7]
     * 输出：15
     * 解释：大餐的美味程度组合为 3 种 (1,1) ，9 种 (1,3) ，和 3 种 (1,7) 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/count-good-meals
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1711().countPairs(
                new int[]{

                }
        ));
    }


}
