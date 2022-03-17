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

import java.util.Arrays;
import java.util.List;

/**
 * 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
 *
 * @author CaiYongcheng
 * @date 2022-01-18 09:06
 **/
public class _539FiveHundredThirtyNine {


    public int findMinDifference(List<String> timePoints) {
        if (timePoints.size() > 24 * 60) {
            return 0;
        }
        timePoints.sort(String::compareTo);
        int ans = 24 * 60;
        String firstTime = timePoints.get(0);
        String currentTime;
        int pre = (firstTime.charAt(0) - '0') * 600 + (firstTime.charAt(1) - '0') * 60
                + (firstTime.charAt(3) - '0') * 10 + (firstTime.charAt(4) - '0');
        int current = 0;
        for (int i = 1; i < timePoints.size(); i++) {
            currentTime = timePoints.get(i);
            current = (currentTime.charAt(0) - '0') * 600 + (currentTime.charAt(1) - '0') * 60
                    + (currentTime.charAt(3) - '0') * 10 + (currentTime.charAt(4) - '0');
            if (current - pre < ans) {
                ans = current - pre;
            }
            pre = current;
        }
        currentTime = timePoints.get(0);
        current = (currentTime.charAt(0) - '0') * 600 + (currentTime.charAt(1) - '0') * 60
                + (currentTime.charAt(3) - '0') * 10 + (currentTime.charAt(4) - '0') + 24 * 60;
        return Math.min(current - pre, ans);
    }


    /**
     * 示例 1：
     * <p>
     * 输入：timePoints = ["23:59","00:00"]
     * 输出：1
     * 示例 2：
     * <p>
     * 输入：timePoints = ["00:00","23:59","00:00"]
     * 输出：0
     * <p>
     * ["01:01","02:01","03:00"]
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-time-difference
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _539FiveHundredThirtyNine().findMinDifference(Arrays.asList("01:01", "02:01", "03:00")));
    }

}
