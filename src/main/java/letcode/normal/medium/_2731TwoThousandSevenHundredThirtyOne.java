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
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/10/10 9:14
 * description 对于坐标在 i 和 j 的两个机器人，(i,j) 和 (j,i) 视为相同的坐标对。也就是说，机器人视为无差别的。
 * 当机器人相撞时，它们 立即改变 它们的前进时间，这个过程不消耗任何时间。 当两个机器人在同一时刻占据相同的位置时，就会相撞。
 * 例如，如果一个机器人位于位置 0 并往右移动，另一个机器人位于位置 2 并往左移动，下一秒，它们都将占据位置 1，并改变方向。
 * 再下一秒钟后，第一个机器人位于位置 0 并往左移动，而另一个机器人位于位置 2 并往右移动。
 * 例如，如果一个机器人位于位置 0 并往右移动，另一个机器人位于位置 1 并往左移动，下一秒，第一个机器人位于位置 0 并往左行驶，而另一个机器人位于位置 1 并往右移动。
 */
public class _2731TwoThousandSevenHundredThirtyOne {

    public int sumDistance2(int[] nums, String s, int d) {
        /*
        A 和 B相撞 即便不改变方向 也没有影响 因为A会代替B B会代替A 也就是可以看成无碰撞 直接穿透即可
        所以只需要排序 计算最后结果即可
         */
        long[] lNums = new long[nums.length];
        for (int i = 0; i < s.length(); i++) {
            lNums[i] = nums[i];
            lNums[i] = lNums[i] + d * (s.charAt(i) == 'R' ? 1 : -1);
        }
        Arrays.sort(lNums);
        long preSum = 0;
        long ans = 0;
        for (int i = 0; i < lNums.length; i++) {
            ans = (ans + i * lNums[i] - preSum) % (1_000_000_000 + 7);
            preSum = (preSum + lNums[i]) % (1_000_000_000 + 7);
        }
        return (int) ans;
    }

    public int sumDistance(int[] nums, String s, int d) {
        /*
        如果 i 点 左侧 i-1向L运动 右侧 i+1向运动 那么不会发生碰撞
        也就是说 每个点的 位置与左右点的位置有关
        对于i0点 如果一开始向L 那么不管最后如何 都不会发生相撞
        对于i1点 如果一开始向L 不存在相撞情况 如果一开始向R 那么方向会不会改变首先取决于i2点 因为如果碰撞的话会先与i2点发生碰撞
        所以问题的关键在于 那些会碰撞的点 也就是 ix ix+1 的方向是R，L 
         */
        long[][] cdata = new long[s.length()][2];
        for (int i = 0; i < s.length(); i++) {
            cdata[i][0] = nums[i];
            cdata[i][1] = s.charAt(i) == 'R' ? 1 : -1;
        }
        //排序
        Arrays.sort(cdata, Comparator.comparingLong(o -> o[0]));

        //找到会碰撞的点
        Set<Integer> collisionList = new HashSet<>();
        long minCollDist;
        boolean hasColl = true;
        while (d > 0 && hasColl) {
            minCollDist = d * 2L;
            hasColl = false;
            collisionList.clear();
            for (int i = 0; i < cdata.length - 1; i++) {
                //RL形式
                if (cdata[i][1] == 1 && cdata[i + 1][1] == -1) {
                    //相撞时间更早
                    long collDist = cdata[i + 1][0] - cdata[i][0];
                    if (collDist < minCollDist) {
                        collisionList.clear();
                        collisionList.add(i);
                        minCollDist = collDist;
                        hasColl = true;
                    } else if (collDist == minCollDist) {
                        collisionList.add(i);
                        hasColl = true;
                    }
                }
            }
            // 1 2 3 4
            //发生了相撞
            if (hasColl) {
                int collTime = (int) ((minCollDist + 1) >> 1);
                //更新此时位置
                for (int i = 0; i < cdata.length; i++) {
                    if (collisionList.contains(i)) {
                        if ((minCollDist & 1) == 1) {
                            cdata[i][0] += cdata[i][1] * (collTime - 1);
                            cdata[i + 1][0] += cdata[i + 1][1] * (collTime - 1);
                        } else {
                            cdata[i][0] += cdata[i][1] * collTime;
                            cdata[i + 1][0] += cdata[i + 1][1] * collTime;
                        }
                        cdata[i][1] *= -1;
                        cdata[i + 1][1] *= -1;
                        ++i;
                    } else {
                        cdata[i][0] += cdata[i][1] * collTime;
                    }
                }
                d -= collTime;
            } else {
                for (int i = 0; i < cdata.length; i++) {
                    cdata[i][0] += cdata[i][1] * d;
                }
            }
        }
        //计算结果 将数组统一向左移动 利用前缀和快速计算
        long preSum = 0;
        long ans = 0;
        for (int i = 0; i < cdata.length; i++) {
            ans = (ans + i * cdata[i][0] - preSum) % (1_000_000_000 + 7);
            preSum = (preSum + cdata[i][0]) % (1_000_000_000 + 7);
        }
        return (int) ans;
    }

    /**
     * 示例 1：
     * <p>
     * 输入：nums = [-2,0,2], s = "RLL", d = 3
     * 输出：8
     * 解释：
     * 1 秒后，机器人的位置为 [-1,-1,1] 。现在下标为 0 的机器人开始往左移动，下标为 1 的机器人开始往右移动。
     * 2 秒后，机器人的位置为 [-2,0,0] 。现在下标为 1 的机器人开始往左移动，下标为 2 的机器人开始往右移动。
     * 3 秒后，机器人的位置为 [-3,-1,1] 。
     * 下标为 0 和 1 的机器人之间距离为 abs(-3 - (-1)) = 2 。
     * 下标为 0 和 2 的机器人之间的距离为 abs(-3 - 1) = 4 。
     * 下标为 1 和 2 的机器人之间的距离为 abs(-1 - 1) = 2 。
     * 所有机器人对之间的总距离为 2 + 4 + 2 = 8 。
     * 示例 2：
     * <p>
     * 输入：nums = [1,0], s = "RL", d = 2
     * 输出：5
     * 解释：
     * 1 秒后，机器人的位置为 [2,-1] 。
     * 2 秒后，机器人的位置为 [3,-2] 。
     * 两个机器人的距离为 abs(-2 - 3) = 5 。
     * <p>
     * -10,-13,10,14,11
     * "LRLLR"
     * 2
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2731TwoThousandSevenHundredThirtyOne().sumDistance(
                new int[]{-10, -13, 10, 14, 11},
                "LRLLR",
                2
        ));
        System.out.println(new _2731TwoThousandSevenHundredThirtyOne().sumDistance2(
                new int[]{-10, -13, 10, 14, 11},
                "LRLLR",
                2
        ));
        // -2 0 2  -3 RLL
        // -1 -1 1  -2 LRL
        // -2 0 0  -1 LLR
        // -3 -1 1  -0 RLL

    }

}
