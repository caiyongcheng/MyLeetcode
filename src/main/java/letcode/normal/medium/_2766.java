package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * You are given a 0-indexed integer array nums representing the initial positions of some marbles. You are also given
 * two 0-indexed integer arrays moveFrom and moveTo of equal length.  Throughout moveFrom.length steps, you will change
 * the positions of the marbles. On the ith step, you will move all marbles at position moveFrom[i] to position
 * moveTo[i].  After completing all the steps, return the sorted list of occupied positions.  Notes:  We call a
 * position occupied if there is at least one marble in that position. There may be multiple marbles in a single
 * position.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-07-24 09:20
 */
public class _2766 {

    public List<Integer> relocateMarbles(int[] nums, int[] moveFrom, int[] moveTo) {
        HashSet<Integer> positionSet = new HashSet<>();
        for (int position : nums) {
            positionSet.add(position);
        }
        for (int i = 0; i < moveFrom.length; i++) {
            if (!positionSet.remove(moveFrom[i])) {
                continue;
            }
            positionSet.add(moveTo[i]);
        }
        return positionSet.stream().sorted().collect(Collectors.toList());
    }

}
