package letcode.normal.medium;

/**
 * 给你一个字符串 word ，你可以向其中任何位置插入 "a"、"b" 或 "c" 任意次，返回使 word 有效 需要插入的最少字母数。
 * 如果字符串可以由 "abc" 串联多次得到，则认为该字符串 有效 。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/1/11 09:04
 */
public class _2645 {

    public int addMinimum(String word) {
        int len = word.length();
        int insertCnt = 0;
        for (int i = 0; i < len;) {
            // 没有下一个
            if (i >= len - 1) {
                insertCnt += 2;
                break;
            }
            // c 开头
            if (word.charAt(i) == 'c') {
                insertCnt += 2;
                i++;
                continue;
            }
            // b 开头
            if (word.charAt(i) == 'b') {
                // 下一个是c
                if (word.charAt(i + 1) == 'c') {
                    insertCnt += 1;
                    i += 2;
                    continue;
                }
                // 下一个不是c
                insertCnt += 2;
                i += 1;
                continue;
            }
            // a 开头
            // 下一个是b
            if (word.charAt(i + 1) == 'b') {
                // 没有再下一个
                if (i >= len - 2) {
                    insertCnt += 1;
                    break;
                }
                // 再下一个是c
                if (word.charAt(i + 2) == 'c') {
                    i += 3;
                    continue;
                }
                // 不是c
                insertCnt += 1;
                i += 2;
                continue;
            }
            // 下一个是c
            if (word.charAt(i + 1) == 'c') {
                // 中间补上一个b
                insertCnt += 1;
                i += 2;
                continue;
            }
            // 下一个是a
            if (word.charAt(i + 1) == 'a') {
                insertCnt += 2;
                i += 1;
            }
        }
        return insertCnt;
    }


    public int addMinimum1(String word) {
        // 如果word[i+1] > word[i] 那么是在同一组的
        // 否则不在同一组中
        int groupCnt = 1;
        int len = word.length();
        for (int i = 1; i < len; i++) {
            groupCnt += word.charAt(i) > word.charAt(i - 1) ? 0 : 1;
        }
        return groupCnt * 3 - len;
    }

    public int addMinimum2(String word) {
        // 字符串拼接后的结果 应该是 abcabcabcabc
        // 如果word[i+1] > word[i] 那么是在同一组的
        //  需要拼接的字符数量就是 word[i+1] - word[i] - 1;
        // 如果word[i+1] > word[i] 那么就不是在同一组的 word[i+1]是在下一组的
        //  需要拼接的字符数量就是 word[i+1] + （组的长度） - word[i] - 1
        // 合并得到 （word[i+1] + （组的长度 - 1） - word[i] - 1） % (组的长度)
        // 初始值呢？ 也就是 开头要补上 word[0] - 'a' 结尾要补上 c - word[len-1] 的字符数量
        // 也就是 word[0] - 'a' + c - word[len-1] = word[0] + （组的长度-1） - word[len-1]
        int len = word.length();
        int insertCnt = word.charAt(0) - word.charAt(len - 1) + 2;
        for (int i = 1; i < len; i++) {
            insertCnt += (word.charAt(i) + 2 - word.charAt(i - 1)) % 3;
        }
        return insertCnt;
    }


    /**
     * 示例 1：
     *
     * 输入：word = "b"
     * 输出：2
     * 解释：在 "b" 之前插入 "a" ，在 "b" 之后插入 "c" 可以得到有效字符串 "abc" 。
     * 示例 2：
     *
     * 输入：word = "aaa"
     * 输出：6
     * 解释：在每个 "a" 之后依次插入 "b" 和 "c" 可以得到有效字符串 "abcabcabc" 。
     * 示例 3：
     *
     * 输入：word = "abc"
     * 输出：0
     * 解释：word 已经是有效字符串，不需要进行修改。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2645().addMinimum(
                "aaacca"
        ));
    }

}
