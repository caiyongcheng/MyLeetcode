package letcode.normal.medium;

/**
 * 3568. Minimum Moves to Clean the Classroom
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/minimum-moves-to-clean-the-classroom/
 * <p>
 * You are given an m x n grid classroom where a student volunteer is tasked with cleaning up litter
 * scattered around the room. Each cell in the grid is one of the following:
 * <p>
 * - 'S' : Starting position of the student
 * <p>
 * - 'L' : Litter that must be collected (once collected, the cell becomes empty)
 * <p>
 * - 'R' : Reset area that restores the student's energy to full capacity, regardless of their current
 * energy level (can be used multiple times)
 * <p>
 * - 'X' : Obstacle the student cannot pass through
 * <p>
 * - '.' : Empty space
 * <p>
 * You are also given an integer energy , representing the student's maximum energy capacity. The
 * student starts with this energy from the starting position 'S' .
 * <p>
 * Each move to an adjacent cell (up, down, left, or right) costs 1 unit of energy. If the energy
 * reaches 0, the student can only continue if they are on a reset area 'R' , which resets the energy
 * to its maximum capacity energy .
 * <p>
 * Return the minimum number of moves required to collect all litter items, or -1 if it's impossible.
 * <p>
 * Example 1:
 * <p>
 * Input: classroom = ["S.", "XL"], energy = 2
 * <p>
 * Output: 2
 * <p>
 * Explanation:
 * <p>
 * - The student starts at cell (0, 0) with 2 units of energy.
 * <p>
 * - Since cell (1, 0) contains an obstacle 'X', the student cannot move directly downward.
 * <p>
 * - A valid sequence of moves to collect all litter is as follows:
 * <p>
 * - Move 1: From (0, 0) &rarr; (0, 1) with 1 unit of energy and 1 unit remaining.
 * <p>
 * - Move 2: From (0, 1) &rarr; (1, 1) to collect the litter 'L' .
 * <p>
 * - The student collects all the litter using 2 moves. Thus, the output is 2.
 * <p>
 * Example 2:
 * <p>
 * Input: classroom = ["LS", "RL"], energy = 4
 * <p>
 * Output: 3
 * <p>
 * Explanation:
 * <p>
 * - The student starts at cell (0, 1) with 4 units of energy.
 * <p>
 * - A valid sequence of moves to collect all litter is as follows:
 * <p>
 * - Move 1: From (0, 1) &rarr; (0, 0) to collect the first litter 'L' with 1 unit of energy used and 3
 * units remaining.
 * <p>
 * - Move 2: From (0, 0) &rarr; (1, 0) to 'R' to reset and restore energy back to 4.
 * <p>
 * - Move 3: From (1, 0) &rarr; (1, 1) to collect the second litter 'L' .
 * <p>
 * - The student collects all the litter using 3 moves. Thus, the output is 3.
 * <p>
 * Example 3:
 * <p>
 * Input: classroom = ["L.S", "RXL"], energy = 3
 * <p>
 * Output: -1
 * <p>
 * Explanation:
 * <p>
 * No valid path collects all 'L' .
 * <p>
 * Constraints:
 * <p>
 * - 1 <= m == classroom.length <= 20
 * <p>
 * - 1 <= n == classroom[i].length <= 20
 * <p>
 * - classroom[i][j] is one of 'S' , 'L' , 'R' , 'X' , or '.'
 * <p>
 * - 1 <= energy <= 50
 * <p>
 * - There is exactly one 'S' in the grid.
 * <p>
 * - There are at most 10 'L' cells in the grid.
 */
public class _3568 {

    public int minMoves(String[] classroom, int energy) {
        return 0;
    }
}
