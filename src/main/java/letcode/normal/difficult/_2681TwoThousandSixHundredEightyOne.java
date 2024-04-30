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

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/8/1 9:04
 * description 给你一个下标从 0 开始的整数数组 nums ，它表示英雄的能力值。
 * 如果我们选出一部分英雄，这组英雄的 力量 定义为：  i0 ，i1 ，... ik 表示这组英雄在数组中的下标。
 * 那么这组英雄的力量为 max(nums[i0],nums[i1] ... nums[ik])2 * min(nums[i0],nums[i1] ... nums[ik]) 。
 * 请你返回所有可能的 非空 英雄组的 力量 之和。由于答案可能非常大，请你将结果对 109 + 7 取余。
 */
public class _2681TwoThousandSixHundredEightyOne {

    static BigDecimal MOD_NUM = BigDecimal.valueOf(1000000000 + 7);


    public int sumOfPower(int[] nums) {
        /*
        英雄组共有(2^n)-1个集合数量 所以暴力一定会超时
        考虑 问题的关键在于 集合内的最大值与最小值 集合内元素数量
        所以先将元素 按从小到大排序 得到序列 a0、a1、a2、a3 .... an
        其中
        [a0, aj] 的结果为 aj * aj * dp[j]  dp[j]表示为(a0, aj) 所有子序列中的最小值的和
        [a0, a[j+1]]的结果为 a[j+1] * a[j+1] * dp[j+1]  那么 已知 dp[j] 如何求出 dp[j+1]
        首先 dp[j]表示为(a0, aj) 所有包含[aj]子序列中的最小值的和
        那么 dp[j+1]表示为(a0, a[j+1]) 所有包含a[j+1]子序列中的最小值的和，
        (a0, a[j+1]) 所有子序列可以看作是由dp[0]、dp[1]、...dp[j]的每个子序列加上a[j+1]得到，再加上单独的一个a[j+1]

         */

        BigDecimal rst = BigDecimal.ZERO;
        //排序
        Arrays.sort(nums);
        BigDecimal[] numsWrap = new BigDecimal[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numsWrap[i] = BigDecimal.valueOf(nums[i]);
        }
        //计算前缀和
        BigDecimal preSum = BigDecimal.ZERO;
        BigDecimal dpNum = BigDecimal.ZERO;
        //遍历得出结果
        for (int i = 0; i < nums.length; i++) {
            dpNum = preSum.add(numsWrap[i]).remainder(MOD_NUM);
            rst = rst.add(
                    numsWrap[i].pow(2).remainder(MOD_NUM).multiply(dpNum).remainder(MOD_NUM)
            ).remainder(MOD_NUM);
            preSum = preSum.add(MOD_NUM).add(dpNum).remainder(MOD_NUM);
        }
        return rst.intValue();
    }

    /**
     * 输入：nums = [2,1,4]
     * 输出：141
     * 解释：1 12 16*7
     * 第 2 组：[1] 的力量为 12 * 1 = 1 。
     * 第 1 组：[2] 的力量为 22 * 2 = 8 。
     * 第 4 组：[2,1] 的力量为 22 * 1 = 4 。
     * 第 3 组：[4] 的力量为 42 * 4 = 64 。
     * 第 5 组：[2,4] 的力量为 42 * 2 = 32 。
     * 第 6 组：[1,4] 的力量为 42 * 1 = 16 。
     * 第 7 组：[2,1,4] 的力量为 42 * 1 = 16 。
     * 所有英雄组的力量之和为 8 + 1 + 64 + 4 + 32 + 16 + 16 = 141 。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(BigDecimal.valueOf(1000000000 + 7));
        System.out.println(BigDecimal.valueOf(10e9 + 7));
    }

}
