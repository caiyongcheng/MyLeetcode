package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * You are given a string word, and an integer numFriends.  Alice is organizing a game for her numFriends friends.
 * There are multiple rounds in the game, where in each round:  word is split into numFriends non-empty strings,
 * such that no previous round has had the exact same split. All the split words are put into a box.
 * Find the lexicographically largest string from the box after all the rounds are finished.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-06-04 16:59
 */
public class _3404 {

    public String answerString(String word, int numFriends) {
        if (numFriends == 1) {
            return word;
        }
        int length = word.length();
        int answerIdx = 0;
        int curStrIdx = 1;
        char ch1;
        char ch2;
        /*
         * 如果{ansStratIdx, ansDiffIdx} > {curStartIdx, curDiffIdx}, 在curDiffIdx位置不同
         *
         */
        while (curStrIdx < length) {
            for (int i = 0; curStrIdx + i < length; i++) {
                ch1 = word.charAt(i + answerIdx);
                ch2 = word.charAt(i + curStrIdx);
                if (ch2 > ch1) {
                    answerIdx = curStrIdx;
                    break;
                } else if (ch2 < ch1) {
                    break;
                }
            }
            curStrIdx++;
        }
        return word.substring(answerIdx, Math.min(answerIdx + length - numFriends + 1, length));
    }

    /**
     * Example 1:
     *
     * Input: word = "dbca", numFriends = 2
     *
     * Output: "dbc"
     *
     * Explanation:
     *
     * All possible splits are:
     *
     * "d" and "bca".
     * "db" and "ca".
     * "dbc" and "a".
     * Example 2:
     *
     * Input: word = "gggg", numFriends = 4
     *
     * Output: "g"
     *
     * Explanation:
     *
     * The only possible split is: "g", "g", "g", and "g".
     * @param args
     */
    public static void main(String[] args) {
        // TestUtil.test("=gh,=1");
        TestUtil.test();
    }

}
