package letcode.normal.medium;

/**
 * 2075. Decode the Slanted Ciphertext
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/decode-the-slanted-ciphertext/
 * <p>
 * A string originalText is encoded using a slanted transposition cipher to a string encodedText with
 * the help of a matrix having a fixed number of rows rows .
 * <p>
 * originalText is placed first in a top-left to bottom-right manner.
 * <p>
 * The blue cells are filled first, followed by the red cells, then the yellow cells, and so on, until
 * we reach the end of originalText . The arrow indicates the order in which the cells are filled. All
 * empty cells are filled with ' ' . The number of columns is chosen such that the rightmost column
 * will not be empty after filling in originalText .
 * <p>
 * encodedText is then formed by appending all characters of the matrix in a row-wise fashion.
 * <p>
 * The characters in the blue cells are appended first to encodedText , then the red cells, and so on,
 * and finally the yellow cells. The arrow indicates the order in which the cells are accessed.
 * <p>
 * For example, if originalText = "cipher" and rows = 3 , then we encode it in the following manner:
 * <p>
 * The blue arrows depict how originalText is placed in the matrix, and the red arrows denote the order
 * in which encodedText is formed. In the above example, encodedText = "ch ie pr" .
 * <p>
 * Given the encoded string encodedText and number of rows rows , return the original string
 * originalText .
 * <p>
 * Note: originalText does not have any trailing spaces ' ' . The test cases are generated such that
 * there is only one possible originalText .
 * <p>
 * Example 1:
 * <p>
 * Input: encodedText = "ch ie pr", rows = 3
 * Output: "cipher"
 * Explanation: This is the same example described in the problem description.
 * <p>
 * Example 2:
 * <p>
 * Input: encodedText = "iveo eed l te olc", rows = 4
 * Output: "i love leetcode"
 * Explanation: The figure above denotes the matrix that was used to encode originalText.
 * The blue arrows show how we can find originalText from encodedText.
 * <p>
 * Example 3:
 * <p>
 * Input: encodedText = "coding", rows = 1
 * Output: "coding"
 * Explanation: Since there is only 1 row, both originalText and encodedText are the same.
 * <p>
 * Constraints:
 * <p>
 * - 0 <= encodedText.length <= 10 6
 * <p>
 * - encodedText consists of lowercase English letters and ' ' only.
 * <p>
 * - encodedText is a valid encoding of some originalText that does not have trailing spaces.
 * <p>
 * - 1 <= rows <= 1000
 * <p>
 * - The testcases are generated such that there is only one possible originalText .
 */
public class _2075 {

    public String decodeCiphertext(String encodedText, int rows) {
        // 计算出辅助矩阵是几列的
        int len = encodedText.length();
        int cols = (len + rows - 1) / rows;

        // 按原始文本的顺序去读取
        char[] charArray = encodedText.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cols; i++) {
            int r = 0;
            int c = i;
            while (r < rows && c < len && r * cols + c < len) {
                sb.append(charArray[r * cols + c]);
                r++;
                c++;
            }
        }

        // 根据题意去除末尾空格
        int ansStrLen = sb.length();
        for (int strLen = ansStrLen - 1; strLen >= 0; strLen--) {
            if (sb.charAt(strLen) == ' ') {
                sb.deleteCharAt(strLen);
            } else {
                break;
            }
        }

        return sb.toString();
    }
}
