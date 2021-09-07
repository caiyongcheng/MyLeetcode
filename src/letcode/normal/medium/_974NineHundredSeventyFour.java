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

import java.util.HashMap;
import java.util.Set;

/**
 * @program: Leetcode
 * @description: 给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。
 * @author: 蔡永程
 * @create: 2020-12-22 10:54
 */
public class _974NineHundredSeventyFour {


    /**
     * 输入：A = [4,5,0,-2,-3,1], K = 5
     * 输出：7
     * 解释：
     * 有 7 个子数组满足其元素之和可被 K = 5 整除：
     * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/subarray-sums-divisible-by-k
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _974NineHundredSeventyFour().subarraysDivByK2(
                new int[]{4, 5, 0, -2, -3, 1},
                5
        ));
    }

    /**
     * timeout
     *
     * @param A
     * @param K
     * @return
     */
    public int subarraysDivByK(int[] A, int K) {
        HashMap<Integer, Integer>[] hashMaps = new HashMap[A.length];
        hashMaps[0] = new HashMap();
        hashMaps[0].put(A[0] % K, 1);
        int count = 0;
        Integer nowCount = 0;
        Set<Integer> integers;
        for (int index = 1; index < A.length; index++) {
            integers = hashMaps[index - 1].keySet();
            hashMaps[index] = new HashMap<>();
            for (Integer integer : integers) {
                hashMaps[index].put((A[index] + integer) % K, hashMaps[index - 1].get(integer));
            }
            nowCount = hashMaps[index].get(A[index] % K);
            if (nowCount == null) {
                nowCount = 0;
            }
            hashMaps[index].put(A[index] % K, nowCount + 1);
        }
        for (int index = 0; index < hashMaps.length; index++) {
            nowCount = hashMaps[index].get(0);
            if (nowCount != null) {
                count += nowCount;
            }
        }
        return count;
    }

    public int subarraysDivByK2(int[] A, int K) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(0, 1);
        int sum = 0;
        int res = 0;
        for (int index = 0; index < A.length; index++) {
            sum += A[index];
            Integer orDefault = hashMap.getOrDefault((sum % K + K) % K,
                    0);
            res += orDefault;
            hashMap.put((sum % K + K) % K, orDefault + 1);
        }
        return res;
    }

}