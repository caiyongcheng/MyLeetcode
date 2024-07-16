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

package normal.easy;


class Node {
    public int lsum;    // 包含左端点l的最大值
    public int rsum;    // 包含右端点r的最大值
    public int totalsum;    //区间和
    public int maxsum;  // 区间最大值

    public Node(int lsum, int rsum, int totalsum, int maxsum) {
        this.lsum = lsum;
        this.rsum = rsum;
        this.totalsum = totalsum;
        this.maxsum = maxsum;
    }
}

/**
 * StudyHTTP
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * @author : CaiYongcheng
 * @since : 2020-06-24 14:48
 **/
public class _53 {

    public static int[] snums;

    /**
     * 输入: [-2,1,-3,4,-1,2,1,-5,4],
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     *
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        int maxNegative = max;
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            if (sum <= 0) {
                sum = 0;
                if (maxNegative < nums[i]) {
                    maxNegative = nums[i];
                }
            } else if (sum > max) {
                max = sum;
            }
        }
        return max > 0 ? max : maxNegative;
    }

    public static Node divideAndRule(int l, int r) {
        if (l == r) return new Node(snums[l], snums[l], snums[l], snums[l]);
        int m = (l + r) / 2;
        Node nl = divideAndRule(l, m);
        Node nr = divideAndRule(m + 1, r);
        //计算左区间最大值,根据定义可知[ [l..m] [m+1..r] ]的左区间最大和,
        // 等于[l..m]的左区间最大和,
        // 与[l..m]的区间和加上[m+1..r]的最大左区间和
        int maxl = Math.max(nl.lsum, nl.totalsum + nr.lsum);
        //计算右区间最大值
        int maxr = Math.max(nr.rsum, nr.totalsum + nl.rsum);
        //计算区间和
        int total = nl.totalsum + nr.totalsum;
        //计算区间最大值，区间最大和要么在左区间，要么在右区间，要么包含中间节点横跨左右区间。
        int maxsum = Math.max(Math.max(nl.maxsum, nr.maxsum), nl.rsum + nr.lsum);
        return new Node(maxl, maxr, total, maxsum);
    }

    /**
     * 使用分治法，将区间分为两部分，左区间和右区间。
     * 区间最大和要么在左区间，要么在右区间，要么包含中间节点横跨左右区间。
     *
     * @param nums
     * @return
     */
    public static int maxSubArray2(int[] nums) {
        snums = nums;
        return divideAndRule(0, nums.length - 1).maxsum;
    }

    public static void main(String[] args) {
        int[] ints = {-1, 0, -2};
        System.out.println(maxSubArray(ints));
        System.out.println(maxSubArray2(ints));
    }


}
