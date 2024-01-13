package letcode.normal.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你一个仅由 大写 英文字符组成的字符串 s 。  你可以对此字符串执行一些操作，在每一步操作中，你可以从 s 中删除 任一个 "AB" 或 "CD" 子字符串。
 * 通过执行操作，删除所有 "AB" 和 "CD" 子串，返回可获得的最终字符串的 最小 可能长度。
 * 注意，删除子串后，重新连接出的字符串可能会产生新的 "AB" 或 "CD" 子串。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/1/10 09:06
 */
public class _2696TwoThousandSixHundredNinetySix {

    public int minLength(String s) {
        // 最简单的办法 循环删除
        // 优化方式的话 每次删除后 往两边扩展 这样避免了下次循环时 对于不可能字符的再次判断
        StringBuilder sb = new StringBuilder(s);
        int len = s.length();
        int l;
        int r;
        boolean delFlag = false;
        for (int i = 0; i < len - 1; i++) {
            l = i;
            r = i + 1;
            delFlag = false;
            while (l > -1 && r < len
                    && ((sb.charAt(l) == 'A' && sb.charAt(r) == 'B') || (sb.charAt(l) == 'C' && sb.charAt(r) == 'D'))) {
                delFlag = true;
                --l;
                ++r;
            }
            if (delFlag) {
                sb.delete(l + 1, r);
                i = Math.max(-1, l - 1);
                len = len - r + l + 1;
            }

        }
        return len;
    }


    /**
     * 示例 1：
     *
     * 输入：s = "ABFCACDB"
     * 输出：2
     * 解释：你可以执行下述操作：
     * - 从 "ABFCACDB" 中删除子串 "AB"，得到 s = "FCACDB" 。
     * - 从 "FCACDB" 中删除子串 "CD"，得到 s = "FCAB" 。
     * - 从 "FCAB" 中删除子串 "AB"，得到 s = "FC" 。
     * 最终字符串的长度为 2 。
     * 可以证明 2 是可获得的最小长度。
     * 示例 2：
     *
     * 输入：s = "ACBBD"
     * 输出：5
     * 解释：无法执行操作，字符串长度不变。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2696TwoThousandSixHundredNinetySix().minLength("ACBBD"));
    }


}
