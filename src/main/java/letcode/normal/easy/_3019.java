package letcode.normal.easy;

import letcode.utils.TestUtil;

/**
 * You are given a 0-indexed string s typed by a user.
 * Changing a key is defined as using a key different from the last used key.
 * For example, s = "ab" has a change of a key while s = "bBBb" does not have any.
 * Return the number of times the user had to change the key.
 * Note: Modifiers like shift or caps lock won't be counted in changing the key that is if a user typed the letter 'a'
 * and then the letter 'A' then it will not be considered as a changing of key.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-01-07 17:48
 */
public class _3019 {

    public int countKeyChanges(String s) {
        int lastCh = s.charAt(0);
        int len = s.length();
        int keyChangeCount = 0;
        int curCh;
        int diff;
        for (int i = 1; i < len; i++) {
            curCh = s.charAt(i);
            if (lastCh == curCh) {
                continue;
            }
            diff = curCh - lastCh;
            diff = diff * diff;
            if (diff == 1024) {
                continue;
            }
            ++keyChangeCount;
            lastCh = curCh;
        }
        return keyChangeCount;
    }

    /**
     * Example 1:
     *
     * Input: s = "aAbBcC"
     * Output: 2
     * Explanation:
     * From s[0] = 'a' to s[1] = 'A', there is no change of key as caps lock or shift is not counted.
     * From s[1] = 'A' to s[2] = 'b', there is a change of key.
     * From s[2] = 'b' to s[3] = 'B', there is no change of key as caps lock or shift is not counted.
     * From s[3] = 'B' to s[4] = 'c', there is a change of key.
     * From s[4] = 'c' to s[5] = 'C', there is no change of key as caps lock or shift is not counted.
     *
     * Example 2:
     *
     * Input: s = "AaAaAaaA"
     * Output: 0
     * Explanation: There is no change of key since only the letters 'a' and 'A' are pressed which does not require change of key.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }

}
