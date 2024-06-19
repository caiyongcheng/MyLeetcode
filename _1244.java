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

package letcode.normal.difficult;/*
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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author 蔡永程
 * @date 2022/8/18 14:12
 */
public class _1244 {


    public int maxEqualFreq(int[] nums) {
        /*
         * 维护一个map a，key是次数，value是数量。
         * 维护一个map b，key是数值，value是次数。
         * 不停遍历， 从b中获取次数，再更新a。
         * 然后进行判断：
         * 如果其他数的次数都一样，有一个数的次数比其他数多1，那么删掉一个这个数可以满足条件；或者这个数只出现了一次，那么删除也满足条件。
         * 或者只有这个数，也满足条件
         *
         * 补充 使用计数排序的方法 代替hash更快
         */
        int maxLen = 0;
        Map<Integer, Integer> cntToItemCnt = new HashMap<>();
        Map<Integer, Integer> valToCnt = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer cnt = valToCnt.getOrDefault(nums[i], 0);
            valToCnt.put(nums[i], cnt + 1);
            Integer itemCnt = cntToItemCnt.getOrDefault(cnt, 0);
            if (itemCnt == 1) {
                cntToItemCnt.remove(cnt);
            } else if (itemCnt > 1) {
                cntToItemCnt.put(cnt, itemCnt - 1);
            }
            cntToItemCnt.put(cnt + 1, cntToItemCnt.getOrDefault(cnt + 1, 0) + 1);
            if (cntToItemCnt.size() == 1) {
                //出现次数都一样 都是1
                if (valToCnt.get(nums[i]) == 1) {
                    maxLen = i + 1;
                } else if (cntToItemCnt.get(valToCnt.get(nums[i])) == 1) {
                    //只出现了一个数
                    maxLen = i + 1;
                }
            } else if (cntToItemCnt.size() == 2) {
                //出现次数两种情况，出现1次的只有一种
                if (cntToItemCnt.getOrDefault(1, 0) == 1) {
                    maxLen = i + 1;
                } else {
                    //有一个数比其他数字多出现了一次
                    Iterator<Map.Entry<Integer, Integer>> entryIterator = cntToItemCnt.entrySet().iterator();
                    Map.Entry<Integer, Integer> e1 = entryIterator.next();
                    Map.Entry<Integer, Integer> e2 = entryIterator.next();
                    if (e1.getKey() + 1 == e2.getKey() && e2.getValue() == 1) {
                        maxLen = i + 1;
                    } else if (e2.getKey() + 1 == e1.getKey() && e1.getValue() == 1) {
                        maxLen = i + 1;
                    }
                }
            }
        }
        return maxLen;
    }


    /**
     * 示例 1：
     * <p>
     * 输入：nums = {2,2,1,1,5,3,3,5}
     * 输出：7
     * 解释：对于长度为 7 的子数组 {2,2,1,1,5,3,3}，如果我们从中删去 nums{4} = 5，就可以得到 {2,2,1,1,3,3}，里面每个数字都出现了两次。
     * 示例 2：
     * <p>
     * 输入：nums = {1,1,1,2,2,2,3,3,3,4,4,4,5}
     * 输出：13
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/maximum-equal-frequency
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1244().maxEqualFreq(
                new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5}
        ));
    }


}
