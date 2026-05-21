package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * There are n cars on an infinitely long road.
 * The cars are numbered from 0 to n - 1 from left to right and each car is present at a unique point.
 * You are given a 0-indexed string directions of length n.
 * directions[i] can be either 'L', 'R', or 'S' denoting whether the ith car is moving towards the left,
 * towards the right, or staying at its current point respectively. Each moving car has the same speed.
 * The number of collisions can be calculated as follows:
 * When two cars moving in opposite directions collide with each other, the number of collisions increases by 2.
 * When a moving car collides with a stationary car, the number of collisions increases by 1.
 * After a collision, the cars involved can no longer move and will stay at the point where they collided.
 * Other than that, cars cannot change their state or direction of motion.
 * Return the total number of collisions that will happen on the road.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-12-05 16:47
 */
public class _2211 {

    public int countCollisions(String directions) {
        char[] charArray = directions.toCharArray();
        int ans = 0;
        int leftIdx = 0;
        int rightIdx = charArray.length - 1;

        while (leftIdx < charArray.length && charArray[leftIdx] == 'L') {
            ++leftIdx;
        }

        while (rightIdx > -1 && charArray[rightIdx] == 'R') {
            --rightIdx;
        }

        while (leftIdx <= rightIdx) {
            if (charArray[leftIdx++] == 'S') {
                continue;
            }
            ++ans;
        }
        return ans;
    }


}
