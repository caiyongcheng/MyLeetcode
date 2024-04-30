package letcode.normal.easy;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 你的笔记本键盘存在故障，每当你在上面输入字符 'i' 时，它会反转你所写的字符串。而输入其他字符则可以正常工作。
 * 给你一个下标从 0 开始的字符串 s ，请你用故障键盘依次输入每个字符。  返回最终笔记本屏幕上输出的字符串。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/4/2 14:05
 */
public class _2810TwoThousandEightHundredTen {

    public String finalString(String s) {
        Deque<Character> q = new ArrayDeque<Character>();
        boolean head = false;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch != 'i') {
                if (head) {
                    q.offerFirst(ch);
                } else {
                    q.offerLast(ch);
                }
            } else {
                head = !head;
            }
        }
        StringBuilder ans = new StringBuilder();
        if (head) {
            while (!q.isEmpty()) {
                ans.append(q.pollLast());
            }
        } else {
            while (!q.isEmpty()) {
                ans.append(q.pollFirst());
            }
        }
        return ans.toString();
    }

    /**
     * 示例 1：
     *
     * 输入：s = "string"
     * 输出："rtsng"
     * 解释：
     * 输入第 1 个字符后，屏幕上的文本是："s" 。
     * 输入第 2 个字符后，屏幕上的文本是："st" 。
     * 输入第 3 个字符后，屏幕上的文本是："str" 。
     * 因为第 4 个字符是 'i' ，屏幕上的文本被反转，变成 "rts" 。
     * 输入第 5 个字符后，屏幕上的文本是："rtsn" 。
     * 输入第 6 个字符后，屏幕上的文本是： "rtsng" 。
     * 因此，返回 "rtsng" 。
     * 示例 2：
     *
     * 输入：s = "poiinter"
     * 输出："ponter"
     * 解释：
     * 输入第 1 个字符后，屏幕上的文本是："p" 。
     * 输入第 2 个字符后，屏幕上的文本是："po" 。
     * 因为第 3 个字符是 'i' ，屏幕上的文本被反转，变成 "op" 。
     * 因为第 4 个字符是 'i' ，屏幕上的文本被反转，变成 "po" 。
     * 输入第 5 个字符后，屏幕上的文本是："pon" 。
     * 输入第 6 个字符后，屏幕上的文本是："pont" 。
     * 输入第 7 个字符后，屏幕上的文本是："ponte" 。
     * 输入第 8 个字符后，屏幕上的文本是："ponter" 。
     * 因此，返回 "ponter" 。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2810TwoThousandEightHundredTen().finalString(
                "ksi"
        ));
    }

}
