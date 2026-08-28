package letcode.normal.unansweredquestions.medium;

import datastructure.utils.FormatPrintUtils;

import java.util.ArrayList;

/**
 * 给定一个整数数组 asteroids，表示在同一行的行星。  对于数组中的每一个元素，其绝对值表示行星的大小，
 * 正负表示行星的移动方向（正表示向右移动，负表示向左移动）。每一颗行星以相同的速度移动。
 * 找出碰撞后剩下的所有行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。
 * 如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。
 * 来源：力扣（LeetCode） 链接：https://leetcode.cn/problems/asteroid-collision 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-07-13 09:35
 **/
public class N_735 {


    public int[] asteroidCollision(int[] asteroids) {
        ArrayList<Integer> rst = new ArrayList<>();
        for (int i = 1; i < asteroids.length; i++) {
            Integer current = asteroids[i];
            if (current > 0) {
                rst.add(current);
                continue;
            }
            while (current != null && !rst.isEmpty()) {
                int size = rst.size();
                Integer head = rst.get(size - 1);
                if (head < 0 || (head > 0 && current > 0)) {
                    rst.add(current);
                    break;
                }
                if (head == 0) {
                    if (current >= 0) {
                        rst.add(current);
                        break;
                    } else {
                        rst.remove(size - 1);
                    }
                }
                if (head > 0) {

                }
            }
            return null;
        }
        return null;
    }


}
