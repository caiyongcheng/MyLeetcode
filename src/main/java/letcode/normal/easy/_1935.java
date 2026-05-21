package letcode.normal.easy;

import letcode.utils.TestUtil;

import java.nio.charset.StandardCharsets;

/**
 * There is a malfunctioning keyboard where some letter keys do not work. All other keys on the keyboard work properly.
 * Given a string text of words separated by a single space (no leading or trailing spaces)
 * and a string brokenLetters of all distinct letter keys that are broken,
 * return the number of words in text you can fully type using this keyboard.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-09-15 15:10
 */
public class _1935 {

    public int canBeTypedWords(String text, String brokenLetters) {
        int[] brokenLetterSet = new int[26];
        byte[] brokenLettersByteArr = brokenLetters.getBytes(StandardCharsets.UTF_8);
        for (byte chByte : brokenLettersByteArr) {
            brokenLetterSet[chByte - 'a'] = 1;
        }

        int ans = 0;
        int valid = 1;
        byte[] textByteArr = text.getBytes(StandardCharsets.UTF_8);
        for (byte chByte : textByteArr) {
            if (chByte == ' ') {
                ans += valid;
                valid = 1;
            } else if (brokenLetterSet[chByte - 'a'] == 1) {
                valid = 0;
            }
        }
        ans += valid;
        return ans;
    }


}
