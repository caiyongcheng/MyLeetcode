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

package letcode.normal.difficult;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 给你一个数组target，包含若干 互不相同的整数，以及另一个整数数组arr，arr可能 包含重复元素。 
 * 每一次操作中，你可以在 arr的任意位置插入任一整数。比方说，如果arr = [1,4,1,2]，那么你可以在中间添加 3得到[1,4,3,1,2]。你可以在数组最开始或最后面添加整数。 
 * 请你返回 最少操作次数，使得target成为arr的一个子序列。  
 * 一个数组的 子序列指的是删除原数组的某些元素（可能一个元素都不删除），同时不改变其余元素的相对顺序得到的数组。
 * 比方说，[2,7,4]是[4,2,3,7,2,1,4]的子序列（加粗元素），但[2,4,2]不是子序列。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/minimum-operations-to-make-a-subsequence 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-07-26 09:04
 **/
public class _1713 {

    public int minOperations2(int[] target, int[] arr) {
        /*
         * 问题可以看成是求 arr 与 target 的最长公共子序列
         * 动态规划做法
         * a = a0...an
         * b = b0...bn
         * lcs（ax， by） 表示 a0...ax 与 b0...by 的最长公共子序列
         * 如果 ax = by 那么 lcs（ax， by） =  lcs(ax-1, by-1) + 1;
         * 如果 ax != by 那么 lsc(ax, by) = max(lcs(ax-1, by),lcs(ax, by-1))
         * 最后求的 lcs（an, bn）就是结果
         * 该方法的时间复杂度为 O（mn）
         */
        int[][] dp = new int[target.length][arr.length];
        dp[0][0] = target[0] == arr[0] ? 1 : 0;
        int left = 0;
        int up = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (target[i] == arr[j]) {
                    dp[i][j] = (i > 0 && j > 0 ? dp[i-1][j-1] : 0) + 1;
                } else {
                    left = j > 0 ? dp[i][j-1] : 0;
                    up = i > 0 ? dp[i-1][j] : 0;
                    dp[i][j] = Math.max(left, up);
                }
            }
        }
        return target.length - dp[target.length-1][arr.length-1];
    }



    /**
     *  target中的元素都是唯一的，可以压缩arr数组。将其转变为只保留target中元素的数组。
     *  考虑题目要求，将arr再次转变为对应target下标的情况。最后就变成了求转变后的arr数组中的
     *  最长递增子序列。
     *  1 dp：
     *  dp[i]表示 包含arr[i]的最长子序列 那么 dp[i] = for(j=0; j<i-1; ++j) max(dp[i], dp[j] + arr[j] < arr[i] ? 1 : 0);
     *  将arr表示为target下标，需要进行m次循环，每次循环可以使用hash获取对应target中的下标，也就是需要n的复杂度。
     *  dp过程需要len(Unite(arr, target))^2的复杂度 要小于 O（mn）
     */
    public int minOperations(int[] target, int[] arr) {
        HashMap<Integer, Integer> hashMap = new HashMap<>(target.length);
        for (int i = 0; i < target.length; i++) {
            hashMap.put(target[i], i);
        }
        int[] newArr = Arrays.stream(arr).filter(hashMap::containsKey).map(hashMap::get).toArray();
        int[] dp = new int[newArr.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (newArr[j] < newArr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return target.length - Arrays.stream(dp).max().orElse(0);
    }


    /**
     * 示例 1：
     * 输入：target = [5,1,3], arr = [9,4,2,3,4]
     * 输出：2
     * 解释：你可以添加 5 和 1 ，使得 arr 变为 [5,9,4,1,2,3,4] ，target 为 arr 的子序列。
     *
     * 示例 2：
     * 输入：target = [6,4,8,1,3,2], arr = [4,7,6,2,3,8,6,1]
     * 输出：3
     * [5,10,8,11,3,15,9,20,18,13]
     * [15,8,2,9,11,20,8,11,7,2]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-operations-to-make-a-subsequence
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1713().minOperations(
                new int[]{5,10,8,11,3,15,9,20,18,13},
                new int[]{15,8,2,9,11,20,8,11,7,2}
        ));
    }


}
