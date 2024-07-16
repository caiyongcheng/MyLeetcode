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
import datastructure.utils.FormatPrintUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @program: MyLeetcode
 * @description: 给定两个以升序排列的整形数组 nums1 和 nums2, 以及一个整数 k。  定义一对值(u,v)，其中第一个元素来自nums1，第二个元素来自 nums2。
 * 找到和最小的 k 对数字(u1,v1), (u2,v2) ... (uk,vk)。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @since: 2021-04-09 14:29
 **/
public class _373 {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        /*
         * 使用优先队列 维护 nums1的从0-length-1下标 与 nums2对应下标构成数据
         * 不停取出即可
         */
        if (nums1.length * nums2.length < k) {
            k = nums1.length * nums2.length;
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(nums1.length, (o1, o2) -> nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]]);
        List<List<Integer>> ans = new ArrayList<>(k);
        List<Integer> item;
        int[] arr;
        for (int i = 0; i < nums1.length; i++) {
            queue.add(new int[]{i, 0});
        }
        while (k > 0) {
            arr = queue.poll();
            item = new ArrayList<>(2);
            item.add(nums1[arr[0]]);
            item.add(nums2[arr[1]]);
            ans.add(item);
            if (arr[1] + 1 < nums2.length) {
                queue.add(new int[]{arr[0], arr[1] + 1});
            }
            --k;
        }
        return ans;
    }

    /**
     * 示例 1:
     *
     * 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
     * 输出: [1,2],[1,4],[1,6]
     * 解释: 返回序列中的前 3 对数：
     *      [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
     * 示例 2:
     *
     * 输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
     * 输出: [1,1],[1,1]
     * 解释: 返回序列中的前 2 对数：
     *      [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
     * 示例 3:
     *
     * 输入: nums1 = [1,2], nums2 = [3], k = 3
     * 输出: [1,3],[2,3]
     * 解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(FormatPrintUtils.formatList(new _373().kSmallestPairs(
                new int[]{1, 2},
                new int[]{3},
                3
        )));
    }

}
