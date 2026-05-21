package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.Arrays;

/**
 * Alice manages a company and has rented some floors of a building as office space. Alice has decided some of these
 * floors should be special floors, used for relaxation only.  You are given two integers bottom and top, which denote
 * that Alice has rented all the floors from bottom to top (inclusive). You are also given the integer array special,
 * where special[i] denotes a special floor that Alice has designated for relaxation.  Return the maximum number of
 * consecutive floors without a special floor.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-01-06 09:08
 */
public class _2274 {

    public int maxConsecutive(int bottom, int top, int[] special) {
        Arrays.sort(special);

        int maxConsecutiveFloor = 0;
        for (int rightFloor : special) {
            if (rightFloor - bottom > maxConsecutiveFloor) {
                maxConsecutiveFloor = rightFloor - bottom;
            }
            bottom = Math.max(bottom, rightFloor + 1);
        }
        if (top - bottom >= maxConsecutiveFloor) {
            maxConsecutiveFloor = top - bottom + 1;
        }
        return maxConsecutiveFloor;
    }


}
