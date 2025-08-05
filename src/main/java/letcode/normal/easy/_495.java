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

package letcode.normal.easy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 在《英雄联盟》的世界中，有一个叫 “提莫” 的英雄。他的攻击可以让敌方英雄艾希（编者注：寒冰射手）进入中毒状态。  当提莫攻击艾希，艾希的中毒状态正好持续 duration 秒。  正式地讲，提莫在 t 发起发起攻击意味着艾希在时间区间 [t, t + duration - 1]（含 t 和 t + duration - 1）处于中毒状态。如果提莫在中毒影响结束 前 再次攻击，中毒状态计时器将会 重置 ，在新的攻击之后，中毒影响将会在 duration 秒后结束。  给你一个 非递减 的整数数组 timeSeries ，其中 timeSeries[i] 表示提莫在 timeSeries[i] 秒时对艾希发起攻击，以及一个表示中毒持续时间的整数 duration 。  返回艾希处于中毒状态的 总 秒数。  来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/teemo-attacking 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-11-10 09:11
 **/
public class _495 {

    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int ans = 0;
        int finishTime = 0;
        for (int i = 0; i < timeSeries.length; ) {
            finishTime = timeSeries[i] + duration;
            int j = i + 1;
            for (; j < timeSeries.length; j++) {
                if (finishTime <= timeSeries[j]) {
                    break;
                }
                finishTime = timeSeries[j] + duration;
            }
            ans += finishTime - timeSeries[i];
            i = j;
        }
        return ans;
    }

    /**
     * 示例 1：
     * <p>
     * 输入：timeSeries = [1,4], duration = 2
     * 输出：4
     * 解释：提莫攻击对艾希的影响如下：
     * - 第 1 秒，提莫攻击艾希并使其立即中毒。中毒状态会维持 2 秒，即第 1 秒和第 2 秒。
     * - 第 4 秒，提莫再次攻击艾希，艾希中毒状态又持续 2 秒，即第 4 秒和第 5 秒。
     * 艾希在第 1、2、4、5 秒处于中毒状态，所以总中毒秒数是 4 。
     * 示例 2：
     * <p>
     * 输入：timeSeries = [1,2], duration = 2
     * 输出：3
     * 解释：提莫攻击对艾希的影响如下：
     * - 第 1 秒，提莫攻击艾希并使其立即中毒。中毒状态会维持 2 秒，即第 1 秒和第 2 秒。
     * - 第 2 秒，提莫再次攻击艾希，并重置中毒计时器，艾希中毒状态需要持续 2 秒，即第 2 秒和第 3 秒。
     * 艾希在第 1、2、3 秒处于中毒状态，所以总中毒秒数是 3 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/teemo-attacking
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader =
                new BufferedReader(new FileReader(new File("C:\\Users\\10761\\Downloads\\testDownload\\202108096661249913934110846.txt")));
        String str = null;
        System.out.println("insert into YLBZXXPT_MZMTB(BZMLDM, BZDLDM,BZMC,)");
        while ((str = bufferedReader.readLine()) != null) {

        }
    }

}
