package letcode.normal.difficult;

/**
 * 如果一个字符串满足以下条件，则称其为 美丽字符串 ：  它由英语小写字母表的前 k 个字母组成。 它不包含任何长度为 2 或更长的回文子字符串。
 * 给你一个长度为 n 的美丽字符串 s 和一个正整数 k 。
 * 请你找出并返回一个长度为 n 的美丽字符串，该字符串还满足：在字典序大于 s 的所有美丽字符串中字典序最小。如果不存在这样的字符串，则返回一个空字符串。
 * 对于长度相同的两个字符串 a 和 b ，如果字符串 a 在与字符串 b 不同的第一个位置上的字符字典序更大，则字符串 a 的字典序大于字符串 b 。
 * 例如，"abcd" 的字典序比 "abcc" 更大，因为在不同的第一个位置（第四个字符）上 d 的字典序大于 c 。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-06-24 14:46
 */
public class _2663 {

    public String smallestBeautifulString(String s, int k) {
        /*
        首先考虑 先依次构造出下一个比s大的字符串，再去验证是不是美丽字符串
        所以从后往前 依次尝试替换idx位置上的字母
        替换完成后，在构造idx到len上的字符 使其尽可能的小 并且不会导致回文的出现
        只要与前一个 或者前第二个字符不等 就不会出现回文的情况
         */
        int limit = k + 'a' - 1;
        int length = s.length();
        char ch;
        for (int i = length - 1; i >= 0; i--) {
            ch = s.charAt(i);
            // 替换当前位置为更大数
            while (ch <= limit - 1) {
                ++ch;
                // 如果替换后 与前面字符串构成了回文 那么跳过
                if ((i - 1 >= 0 && s.charAt(i - 1) == ch)
                        || (i - 2 >= 0 && s.charAt(i - 2) == ch)
                ) {
                    continue;
                }
                StringBuilder sb = new StringBuilder();
                if (i - 1 >= 0) {
                    sb.append(s.charAt(i - 1));
                } else {
                    sb.append("0");
                }
                sb.append(ch);
                int j = i + 1;
                for (; j < length; ++j) {
                    char appendCh = 'a';
                    while (appendCh <= 'z') {
                        if (appendCh != sb.charAt(j - i) && appendCh != sb.charAt(j - 1 - i)) {
                            sb.append(appendCh);
                            break;
                        }
                        ++appendCh;
                    }
                    if (appendCh > 'z') {
                        break;
                    }
                }
                if (j >= length) {
                    sb.deleteCharAt(0);
                    for (int p = 0; p < i; p++) {
                        sb.insert(p, s.charAt(p));
                    }
                    return sb.toString();
                }
            }
        }
        return "";
    }

    /**
     * 示例 1：
     *
     * 输入：s = "abcz", k = 26
     * 输出："abda"
     * 解释：字符串 "abda" 既是美丽字符串，又满足字典序大于 "abcz" 。
     * 可以证明不存在字符串同时满足字典序大于 "abcz"、美丽字符串、字典序小于 "abda" 这三个条件。
     * 示例 2：
     *
     * 输入：s = "dc", k = 4
     * 输出：""
     * 解释：可以证明，不存在既是美丽字符串，又字典序大于 "dc" 的字符串。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2663().smallestBeautifulString(
                "ced",
                6
        ));
    }

}
