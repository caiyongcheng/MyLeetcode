package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.Arrays;

/**
 * In a special ranking system, each voter gives a rank from highest to lowest to all teams participating in the competition.
 * The ordering of teams is decided by who received the most position-one votes. If two or more teams tie in the first position,
 * we consider the second position to resolve the conflict, if they tie again, we continue this process until the ties are resolved.
 * If two or more teams are still tied after considering all positions, we rank them alphabetically based on their team letter.
 * You are given an array of strings votes which is the votes of all voters in the ranking systems.
 * Sort all teams according to the ranking system described above.
 * Return a string of all teams sorted by the ranking system.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-12-30 11:25
 */
public class _1366 {

    public String rankTeams(String[] votes) {
        int[][] rankArr = new int[26][26];

        int len = votes[0].length();
        for (String vote : votes) {
            for (int i = 0; i < len; i++) {
                rankArr[vote.charAt(i) - 'A'][i]++;
            }
        }

        Integer[] teamArr = new Integer[len];
        for (int i = 0; i < len; i++) {
            teamArr[i] = (int) votes[0].charAt(i);
        }

        Arrays.sort(teamArr, ((Integer a, Integer b) -> {
            for (int i = 0; i < 26; i++) {
                if (rankArr[a - 'A'][i] != rankArr[b - 'A'][i]) {
                    return rankArr[b - 'A'][i] - rankArr[a - 'A'][i];
                }
            }
            return a - b;
        }));

        StringBuilder ans = new StringBuilder();
        for (Integer i : teamArr) {
            ans.append(((char) i.intValue()));
        }
        return ans.toString();
    }

}
