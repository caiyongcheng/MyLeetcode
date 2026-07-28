package letcode.normal.medium;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/10/11 9:16
 * description 给你两个字符串数组 positive_feedback 和 negative_feedback ，分别包含表示正面的和负面的词汇。不会 有单词同时是正面的和负面的。
 * 一开始，每位学生分数为 0 。每个正面的单词会给学生的分数 加 3 分，每个负面的词会给学生的分数 减  1 分。
 * 给你 n 个学生的评语，用一个下标从 0 开始的字符串数组 report 和一个下标从 0 开始的整数数组 student_id 表示，其中 student_id[i] 表示这名学生的 ID ，
 * 这名学生的评语是 report[i] 。每名学生的 ID 互不相同。  给你一个整数 k ，请你返回按照得分 从高到低 最顶尖的 k 名学生。
 * 如果有多名学生分数相同，ID 越小排名越前。
 */
public class _2512 {


    public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback,
                                     String[] report, int[] student_id, int k) {
        //数据量不大 模拟即可
        Map<String, Integer> word2Score = new HashMap<>(positive_feedback.length + negative_feedback.length);
        for (String word : positive_feedback) {
            word2Score.put(word, 3);
        }
        for (String word : negative_feedback) {
            word2Score.put(word, -1);
        }
        Map<Integer, Integer> id2Score = new HashMap<>(student_id.length);
        for (int i = 0; i < student_id.length; i++) {
            for (String word : report[i].split(" ")) {
                id2Score.put(
                        student_id[i],
                        id2Score.getOrDefault(student_id[i], 0)
                                + word2Score.getOrDefault(word, 0)
                );
            }
        }
        return id2Score.entrySet().stream()
                .sorted(
                    Comparator.comparingInt((ToIntFunction<Map.Entry<Integer, Integer>>) Map.Entry::getValue)
                            .reversed()
                            .thenComparingInt(Map.Entry::getKey)
                )
                .limit(k)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

}
