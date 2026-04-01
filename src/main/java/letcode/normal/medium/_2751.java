package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

/**
 * There are n 1-indexed robots, each having a position on a line, health, and movement direction.
 * You are given 0-indexed integer arrays positions, healths, and a string directions
 * (directions[i] is either 'L' for left or 'R' for right). All integers in positions are unique.
 * All robots start moving on the line simultaneously at the same speed in their given directions.
 * If two robots ever share the same position while moving, they will collide.
 * If two robots collide, the robot with lower health is removed from the line,
 * and the health of the other robot decreases by one. The surviving robot continues in the same direction it was going.
 * If both robots have the same health, they are both removed from the line.
 * Your task is to determine the health of the robots that survive the collisions,
 * in the same order that the robots were given, i.e. final health of robot 1 (if survived),
 * final health of robot 2 (if survived), and so on. If there are no survivors, return an empty array.
 * Return an array containing the health of the remaining robots (in the order they were given in the input),
 * after no further collisions can occur.  Note: The positions may be unsorted.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2026-04-01 17:36
 */
public class _2751 {

    private static final class Robot {
        private final int idx;
        private final int pos;
        private int health;
        private final char dir;

        private Robot(int idx, int pos, int health, char dir) {
            this.idx = idx;
            this.pos = pos;
            this.health = health;
            this.dir = dir;
        }
    }

    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        Robot[] robots = new Robot[n];
        for (int i = 0; i < n; i++) {
            robots[i] = new Robot(i, positions[i], healths[i], directions.charAt(i));
        }

        // 按位置排序，模拟碰撞：栈里只保留“向右走且仍存活”的机器人
        Arrays.sort(robots, Comparator.comparingInt(r -> r.pos));
        Deque<Robot> rightStack = new ArrayDeque<>();

        for (Robot cur : robots) {
            if (cur.dir == 'R') {
                rightStack.addLast(cur);
                continue;
            }

            while (cur.health > 0 && !rightStack.isEmpty()) {
                Robot right = rightStack.peekLast();
                if (right.health < cur.health) {
                    rightStack.pollLast();
                    right.health = 0;
                    cur.health--;
                } else if (right.health == cur.health) {
                    rightStack.pollLast();
                    right.health = 0;
                    cur.health = 0;
                } else {
                    right.health--;
                    cur.health = 0;
                }
            }
        }

        // 输出顺序按输入下标恢复
        return Arrays.stream(robots)
                .filter(r -> r.health > 0)
                .sorted(Comparator.comparingInt(r -> r.idx))
                .map(r -> r.health)
                .collect(Collectors.toList());

    }

    /**
     * Example 1:
     *
     *
     *
     * Input: positions = [5,4,3,2,1], healths = [2,17,9,15,10], directions = "RRRRR"
     * Output: [2,17,9,15,10]
     * Explanation: No collision occurs in this example, since all robots are moving in the same direction. So, the health of the robots in order from the first robot is returned, [2, 17, 9, 15, 10].
     * Example 2:
     *
     *
     *
     * Input: positions = [3,5,2,6], healths = [10,10,15,12], directions = "RLRL"
     * Output: [14]
     * Explanation: There are 2 collisions in this example. Firstly, robot 1 and robot 2 will collide, and since both have the same health, they will be removed from the line. Next, robot 3 and robot 4 will collide and since robot 4's health is smaller, it gets removed, and robot 3's health becomes 15 - 1 = 14. Only robot 3 remains, so we return [14].
     * Example 3:
     *
     *
     *
     * Input: positions = [1,2,5,6], healths = [10,10,11,11], directions = "RLRL"
     * Output: []
     * Explanation: Robot 1 and robot 2 will collide and since both have the same health, they are both removed. Robot 3 and 4 will collide and since both have the same health, they are both removed. So, we return an empty array, [].
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }

}
