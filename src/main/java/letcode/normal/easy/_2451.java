package letcode.normal.easy;

/**
 * @author Caiyongcheng
 * @description 给你一个字符串数组 words，每一个字符串长度都相同，令所有字符串的长度都为 n。
 * 每个字符串words[i]可以被转化为一个长度为n - 1的差值整数数组difference[i]，
 * 其中对于0 <= j <= n - 2有difference[i][j] = words[i][j+1] - words[i][j]。
 * 意两个字母的差值定义为它们在字母表中位置之差，也就是说'a'的位置是0，'b'的位置是1，'z'的位置是25。
 * 比方说，字符串"acb"的差值整数数组是[2 - 0, 1 - 2] = [2, -1]。 words中所有字符串 除了一个字符串以外，
 * 其他字符串的差值整数数组都相同。你需要找到那个不同的字符串。  请你返回words中差值整数数组不同的字符串。
 * 来源：力扣（LeetCode） 链接：https://leetcode.cn/problems/odd-string-difference 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 * @since 2023/5/25 9:01
 */
public class _2451 {


    public String oddString(String[] words) {
        int c1 = 0;
        int c2 = 0;
        for (int i = 0; i < words[0].length() - 1; i++) {
            c1 = words[0].charAt(i + 1) - words[0].charAt(i);
            for (int j = 1; j < words.length; j++) {
                c2 = words[j].charAt(i + 1) - words[j].charAt(i);
                if (c1 != c2) {
                    if (j == words.length - 1) {
                        return words[1].charAt(i + 1) - words[1].charAt(i) == c1 ? words[j] : words[i];
                    } else {
                        return words[words.length - 1].charAt(i + 1) - words[words.length - 1].charAt(i) == c1 ? words[j] : words[i];
                    }
                }
            }
        }
        return "";
    }

}
