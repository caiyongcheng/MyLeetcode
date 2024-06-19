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

import letcode.utils.FormatUtils;

import java.util.*;

/**
 * 存在一个由 n 个不同元素组成的整数数组 nums ，但你已经记不清具体内容。好在你还记得 nums 中的每一对相邻元素。
 * 给你一个二维整数数组 adjacentPairs ，大小为 n - 1 ，其中每个 adjacentPairs[i] = [ui, vi] 表示元素 ui 和 vi 在 nums 中相邻。
 * 题目数据保证所有由元素 nums[i] 和 nums[i+1] 组成的相邻元素对都存在于 adjacentPairs 中，存在形式可能是 [nums[i], nums[i+1]] ，也可能是 [nums[i+1], nums[i]] 。
 * 这些相邻元素对可以 按任意顺序 出现。  返回 原始数组 nums 。如果存在多种解答，返回 其中任意一个 即可。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/restore-the-array-from-adjacent-pairs 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-07-25 15:29
 **/
public class  _1743 {


    public int[] restoreArray(int[][] adjacentPairs) {
        /**
         * 明显题目答案不唯一
         * 根据题目 找出 只有一个关系的元素 那么这个元素一定是头或者尾
         * 再根据头或者尾去一步步推到即可
         */
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        int[] ans;
        for (int[] item : adjacentPairs) {
            HashSet<Integer> set = map.getOrDefault(item[0], new HashSet<>());
            set.add(item[1]);
            map.put(item[0], set);
            set = map.getOrDefault(item[1], new HashSet<>());
            set.add(item[0]);
            map.put(item[1], set);
        }
        /**
         * 遍历找到头尾
         *
         */
        ans = new int[map.keySet().size()];
        Set<Map.Entry<Integer, HashSet<Integer>>> entries = map.entrySet();
        for (Map.Entry<Integer, HashSet<Integer>> entry : entries) {
            if (entry.getValue().size() == 1) {
                ans[0] = entry.getKey();
                for (Integer item : entry.getValue()) {
                    ans[1] = item;
                }
                break;
            }
        }
        for (int i = 1; i < ans.length - 1; i++) {
            HashSet<Integer> set = map.get(ans[i]);
            for (Integer item : set) {
                if (item != ans[i-1]) {
                    ans[i+1] = item;
                    break;
                }
            }
        }
        return ans;
    }


    /**
     * 示例 1：
     *
     * 输入：adjacentPairs = {{2,1},{3,4},{3,2}}
     * 输出：{1,2,3,4}
     * 解释：数组的所有相邻元素对都在 adjacentPairs 中。
     * 特别要注意的是，adjacentPairs{i} 只表示两个元素相邻，并不保证其 左-右 顺序。
     * 示例 2：
     *
     * 输入：adjacentPairs = {{4,-2},{1,4},{-3,1}}
     * 输出：{-2,4,1,-3}
     * 解释：数组中可能存在负数。
     * 另一种解答是 {-3,1,4,-2} ，也会被视作正确答案。
     * 示例 3：
     *
     * 输入：adjacentPairs = {{100000,-100000}}
     * 输出：{100000,-100000}
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/restore-the-array-from-adjacent-pairs
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(FormatUtils.formatArray(new _1743().restoreArray(
                new int[][]{{100000, -100000}}
        )));
    }

}
