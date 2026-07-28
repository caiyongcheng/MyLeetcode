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
public class _2731 {

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

}
