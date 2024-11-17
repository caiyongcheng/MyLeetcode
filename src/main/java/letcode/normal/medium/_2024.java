package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 一位老师正在出一场由 n 道判断题构成的考试，每道题的答案为 true （用 'T' 表示）或者 false （用 'F' 表示）。
 * 老师想增加学生对自己做出答案的不确定性，方法是 最大化 有 连续相同 结果的题数。（也就是连续出现 true 或者连续出现 false）。
 * 给你一个字符串 answerKey ，其中 answerKey[i] 是第 i 个问题的正确结果。除此以外，还给你一个整数 k ，表示你能进行以下操作的最多次数：
 * 每次操作中，将问题的正确答案改为 'T' 或者 'F' （也就是将 answerKey[i] 改为 'T' 或者 'F' ）。
 * 请你返回在不超过 k 次操作的情况下，最大 连续 'T' 或者 'F' 的数目。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-09-02 09:05
 */
public class _2024 {

    public int maxConsecutiveAnswers(String answerKey, int k) {
        List<Integer> falseList = new ArrayList<>();
        List<Integer> trueList = new ArrayList<>();

        int length = answerKey.length();
        for (int i = 0; i < length; i++) {
            if (answerKey.charAt(i) == 'T') {
                trueList.add(i);
            } else {
                falseList.add(i);
            }
        }

        if (falseList.size() <= k || trueList.size() <= k) {
            return length;
        }
        falseList.add(length);
        trueList.add(length);

        int ans = falseList.get(k);
        int curLen;
        for (int i = k; i < falseList.size(); i++) {
            curLen = falseList.get(i) - falseList.get(i - k) + 1;
            ans = Math.max(ans, curLen);
        }
        ans = Math.max(ans, trueList.get(k) - 1);
        for (int i = k; i < trueList.size(); i++) {
            curLen = trueList.get(i) - trueList.get(i - k) + 1;
            ans = Math.max(ans, curLen);
        }
        return ans;
    }

    /**
     * Example 1:
     *
     * Input: answerKey = "TTFF", k = 2
     * Output: 4
     * Explanation: We can replace both the 'F's with 'T's to make answerKey = "TTTT".
     * There are four consecutive 'T's.
     * Example 2:
     *
     * Input: answerKey = "TFFT", k = 1
     * Output: 3
     * Explanation: We can replace the first 'T' with an 'F' to make answerKey = "FFFT".
     * Alternatively, we can replace the second 'T' with an 'F' to make answerKey = "TFFF".
     * In both cases, there are three consecutive 'F's.
     * Example 3:
     *
     * Input: answerKey = "TTFTTFTT", k = 1
     * Output: 5
     * Explanation: We can replace the first 'F' to make answerKey = "TTTTTFTT"
     * Alternatively, we can replace the second 'F' to make answerKey = "TTFTTTTT".
     * In both cases, there are five consecutive 'T's.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test(_2024.class);
    }


}
