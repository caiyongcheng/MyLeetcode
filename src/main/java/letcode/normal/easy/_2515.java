package letcode.normal.easy;

import letcode.utils.TestUtil;

/**
 * You are given a 0-indexed circular string array words and a string target. A circular array means that the array's end connects to the array's beginning.  Formally, the next element of words[i] is words[(i + 1) % n] and the previous element of words[i] is words[(i - 1 + n) % n], where n is the length of words. Starting from startIndex, you can move to either the next word or the previous word with 1 step at a time.  Return the shortest distance needed to reach the string target. If the string target does not exist in words, return -1.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2026-04-15 22:39
 */
public class _2515 {

    public int closestTarget(String[] words, String target, int startIndex) {
        int minDist = words.length + 1;
        for (int i = 0; i < words.length; i++) {
            if (words[(startIndex + i) % words.length].equals(target)) {
                minDist = Math.min(
                        minDist, Math.min(i, words.length - i)
                );
            }
        }
        return minDist == words.length + 1 ? -1 : minDist;
    }


}
