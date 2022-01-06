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

/**
 * 在社交媒体网站上有 n 个用户。给你一个整数数组 ages ，其中 ages[i] 是第 i 个用户的年龄。
 * 如果下述任意一个条件为真，那么用户 x 将不会向用户 y（x != y）发送好友请求：
 * age[y] <= 0.5 * age[x] + 7 age[y] > age[x] age[y] > 100 && age[x] < 100 否则，x 将会向 y 发送一条好友请求。
 * 注意，如果 x 向 y 发送一条好友请求，y 不必也向 x 发送一条好友请求。另外，用户不会向自己发送好友请求。
 * 返回在该社交媒体网站上产生的好友请求总数。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/friends-of-appropriate-ages 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-12-27 09:01
 **/
public class _825EightHundredTwentyFive {

    public int numFriendRequests(int[] ages) {
        /*
        根据数据规模 可以使用类似计数排序的方法进行压缩
        可以对于每个年龄段求出能发送请求的好友范围 加上前缀和进行计算
         */
        int ans = 0;
        int[] agesArr = new int[121];
        int[] preSum = new int[121];
        int minAge;
        for (int age : ages) {
            agesArr[age]++;
        }
        preSum[0] = 0;
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + agesArr[i];
        }
        for (int age = 0; age < agesArr.length; age++) {
            minAge = (int) (age * 0.5 + 7) + 1;
            if (minAge > age) {
                continue;
            }
            ans += (preSum[age - 1] - preSum[(int) (age * 0.5 + 7)]) * agesArr[age];
            ans += (agesArr[age] * (agesArr[age] - 1));
        }
        return ans;
    }


    /**
     * 示例 1：
     * <p>
     * 输入：ages = [16,16]
     * 输出：2
     * 解释：2 人互发好友请求。
     * 示例 2：
     * <p>
     * 输入：ages = [16,17,18]
     * 输出：2
     * 解释：产生的好友请求为 17 -> 16 ，18 -> 17 。
     * 示例 3：
     * <p>
     * 输入：ages = [20,30,100,110,120]
     * 输出：3
     * 解释：产生的好友请求为 110 -> 100 ，120 -> 110 ，120 -> 100 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/friends-of-appropriate-ages
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _825EightHundredTwentyFive().numFriendRequests(
                new int[]{20, 30, 100, 110, 120}
        ));
    }


}
