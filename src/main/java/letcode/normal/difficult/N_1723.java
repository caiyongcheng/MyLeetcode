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

/**
 * 给你一个整数数组 jobs ，其中 jobs[i] 是完成第 i 项工作要花费的时间。  请你将这些工作分配给 k 位工人。所有工作都应该分配给工人，且每项工作只能分配给一位工人。工人的 工作时间 是完成分配给他们的所有工作花费时间的总和。
 * 请你设计一套最佳的工作分配方案，使工人的 最大工作时间 得以 最小化 。  返回分配方案中尽可能 最小 的 最大工作时间 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/find-minimum-time-to-finish-all-jobs 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-05-08 08:57
 **/
public class N_1723 {


    public int minimumTimeRequired(int[] jobs, int k) {
        //按照题目要求 也就是将 jobs 分成 k 份 尽可能平均分配
        //二分 + 回溯
        //因为每个工人最少被分配一个工作
        //故取单个工作量的最大值 作为下界
        //取工作量之和作为上界
        int left = jobs[0];
        int right = jobs[0];
        int mid;
        for (int i = 1; i < jobs.length; i++) {
            if (jobs[i] > left) {
                left = jobs[i];
            }
            right += jobs[i];
        }
        //只能分配每人一项工作的情况 直接返回
        if (jobs.length == k) {
            return left;
        }

        Arrays.sort(jobs);

        // 二分 right总是可以满足划分结果
        // 假设在第n轮循环结束
        // 则在第n-1轮，
        // 若existCase结果为false，
        // 则left = mid， 又mid = (left + right) >>> 1 =》 mid <= right，且left<right == false
        // 可以得出 此时退出条件是 right == left 则在n-1轮就应当循环结束 与实际矛盾
        // 故第n-1轮existCase结果为true
        while (left < right) {
            mid = (left + right) >>> 1;
            if (existCase(jobs, k, 0, mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }


    public boolean existCase(int[] jobs, int k, int i, int mid) {
        return true;
    }


    /**
     * 示例 1：
     * 输入：jobs = [3,2,3], k = 3
     * 输出：3
     * 解释：给每位工人分配一项工作，最大工作时间是 3 。
     *
     * 示例 2：
     * 输入：jobs = [1,2,4,7,8], k = 2
     * 输出：11
     * 解释：按下述方式分配工作：
     * 1 号工人：1、2、8（工作时间 = 1 + 2 + 8 = 11）
     * 2 号工人：4、7（工作时间 = 4 + 7 = 11）
     * 最大工作时间是 11 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-minimum-time-to-finish-all-jobs
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {

    }

}
