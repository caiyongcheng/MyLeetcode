package letcode.normal.easy;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2024/1/7 21:13
 * description 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
 * 如果可以，返回 true ；否则返回 false 。
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 */
public class _383ThreeHundredEightyThree {

    public boolean canConstruct(String ransomNote, String magazine) {
        int len = magazine.length();
        int[] hasCharArr = new int[26];
        for (int i = 0; i < len; i++) {
            hasCharArr[magazine.charAt(i) - 'a']++;
        }
        len = ransomNote.length();
        int idx = 0;
        for (int i = 0; i < len; i++) {
            idx = ransomNote.charAt(i) - 'a';
            hasCharArr[idx]--;
            if (hasCharArr[idx] < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 示例 1：
     *
     * 输入：ransomNote = "a", magazine = "b"
     * 输出：false
     * 示例 2：
     *
     * 输入：ransomNote = "aa", magazine = "ab"
     * 输出：false
     * 示例 3：
     *
     * 输入：ransomNote = "aa", magazine = "aab"
     * 输出：true
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _383ThreeHundredEightyThree().canConstruct(
                "aa",
                "b"
        ));
    }

}
