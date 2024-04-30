/*
 * 版权所有（c）<2021><蔡永程>
 *
 * 反996许可证版本1.0
 *
 * 在符合下列条件的情况下，特此免费向任何得到本授权作品的副本（包括源代码、文件和/或相关内容，以
 * 下统称为“授权作品”）的个人和法人实体授权：被授权个人或法人实体有权以任何目的处置授权作品，包括
 * 但不限于使用、复制，修改，衍生利用、散布，发布和再许可：
 *
 * 1. 个人或法人实体必须在许可作品的每个再散布或衍生副本上包含以上版权声明和本许可证，不得自行修
 * 改。
 * 2. 个人或法人实体必须严格遵守与个人实际所在地或个人出生地或归化地、或法人实体注册地或经营地（
 * 以较严格者为准）的司法管辖区所有适用的与劳动和就业相关法律、法规、规则和标准。如果该司法管辖区
 * 没有此类法律、法规、规章和标准或其法律、法规、规章和标准不可执行，则个人或法人实体必须遵守国际
 * 劳工标准的核心公约。
 * 3. 个人或法人不得以任何方式诱导、暗示或强迫其全职或兼职员工或其独立承包人以口头或书面形式同意
 * 直接或间接限制、削弱或放弃其所拥有的，受相关与劳动和就业有关的法律、法规、规则和标准保护的权利
 * 或补救措施，无论该等书面或口头协议是否被该司法管辖区的法律所承认，该等个人或法人实体也不得以任
 * 何方法限制其雇员或独立承包人向版权持有人或监督许可证合规情况的有关当局报告或投诉上述违反许可证
 * 的行为的权利。
 *
 * 该授权作品是"按原样"提供，不做任何明示或暗示的保证，包括但不限于对适销性、特定用途适用性和非侵
 * 权性的保证。在任何情况下，无论是在合同诉讼、侵权诉讼或其他诉讼中，版权持有人均不承担因本软件或
 * 本软件的使用或其他交易而产生、引起或与之相关的任何索赔、损害或其他责任。
 */

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
public class _2512TwoThousandFiveHundredTwelve {


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

    /**
     * 示例 1：
     *
     * 输入：positive_feedback = ["smart","brilliant","studious"], negative_feedback = ["not"], report = ["this student is studious","the student is smart"], student_id = [1,2], k = 2
     * 输出：[1,2]
     * 解释：
     * 两名学生都有 1 个正面词汇，都得到 3 分，学生 1 的 ID 更小所以排名更前。
     * 示例 2：
     *
     * 输入：positive_feedback = ["smart","brilliant","studious"], negative_feedback = ["not"], report = ["this student is not studious","the student is smart"], student_id = [1,2], k = 2
     * 输出：[2,1]
     * 解释：
     * - ID 为 1 的学生有 1 个正面词汇和 1 个负面词汇，所以得分为 3-1=2 分。
     * - ID 为 2 的学生有 1 个正面词汇，得分为 3 分。
     * 学生 2 分数更高，所以返回 [2,1] 。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2512TwoThousandFiveHundredTwelve().topStudents(
                new String[]{"smart","brilliant","studious"},
                new String[]{"not"},
                new String[]{"this student is studious","the student is smart"},
                new int[]{1, 2},
                2
        ));
    }

}
