package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.*;

/**
 * 请你设计并实现一个能够对其中的值进行跟踪的数据结构，并支持对频率相关查询进行应答。
 * 实现 FrequencyTracker 类：  FrequencyTracker()：使用一个空数组初始化 FrequencyTracker 对象。
 * void add(int number)：添加一个 number 到数据结构中。 void deleteOne(int number)：从数据结构中删除一个 number 。
 * 数据结构 可能不包含 number ，在这种情况下不删除任何内容。
 * bool hasFrequency(int frequency): 如果数据结构中存在出现 frequency 次的数字，则返回 true，否则返回 false。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/3/21 09:16
 */
public class _2671 {

    Map<Integer, Set<Integer>> count2Word;

    Map<Integer, Integer> word2Count;

    public _2671() {
        word2Count = new HashMap<>();
        count2Word = new HashMap<>();
        count2Word.put(0, new HashSet<>());
    }

    public void add(int number) {
        Integer count = word2Count.getOrDefault(number, 0);
        Set<Integer> wordSet = count2Word.get(count);
        word2Count.put(number, count + 1);
        if (wordSet.size() == 1) {
            count2Word.remove(count);
        } else {
            wordSet.remove(number);
        }
        ++count;
        wordSet = count2Word.get(count);
        if (Objects.isNull(wordSet)) {
            wordSet = new HashSet<>();
        }
        wordSet.add(number);
        count2Word.put(count, wordSet);
    }

    public void deleteOne(int number) {
        Integer count = word2Count.get(number);
        if (Objects.isNull(count)) {
            return;
        }
        Set<Integer> wordSet = count2Word.get(count);
        if (wordSet.size() == 1) {
            count2Word.remove(count);
        } else {
            wordSet.remove(number);
        }
        --count;
        if (count == 0) {
            word2Count.remove(number);
        } else {
            word2Count.put(number, count);
            wordSet = count2Word.get(count);
            if (Objects.isNull(wordSet)) {
                wordSet = new HashSet<>();
            }
            wordSet.add(number);
            count2Word.put(count, wordSet);
        }
    }

    public boolean hasFrequency(int frequency) {
        return Objects.nonNull(count2Word.get(frequency));
    }

    /**
     * 示例 1：
     *
     * 输入
     * ["FrequencyTracker", "add", "add", "hasFrequency"]
     * [[], [3], [3], [2]]
     * 输出
     * [null, null, null, true]
     *
     * 解释
     * FrequencyTracker frequencyTracker = new FrequencyTracker();
     * frequencyTracker.add(3); // 数据结构现在包含 [3]
     * frequencyTracker.add(3); // 数据结构现在包含 [3, 3]
     * frequencyTracker.hasFrequency(2); // 返回 true ，因为 3 出现 2 次
     * 示例 2：
     *
     * 输入
     * ["FrequencyTracker", "add", "deleteOne", "hasFrequency"]
     * [[], [1], [1], [1]]
     * 输出
     * [null, null, null, false]
     *
     * 解释
     * FrequencyTracker frequencyTracker = new FrequencyTracker();
     * frequencyTracker.add(1); // 数据结构现在包含 [1]
     * frequencyTracker.deleteOne(1); // 数据结构现在为空 []
     * frequencyTracker.hasFrequency(1); // 返回 false ，因为数据结构为空
     * 示例 3：
     *
     * 输入
     * ["FrequencyTracker", "hasFrequency", "add", "hasFrequency"]
     * [[], [2], [3], [1]]
     * 输出
     * [null, false, null, true]
     *
     * 解释
     * FrequencyTracker frequencyTracker = new FrequencyTracker();
     * frequencyTracker.hasFrequency(2); // 返回 false ，因为数据结构为空
     * frequencyTracker.add(3); // 数据结构现在包含 [3]
     * frequencyTracker.hasFrequency(1); // 返回 true ，因为 3 出现 1 次
     *
     * ["FrequencyTracker","hasFrequency","add","add","hasFrequency","add","hasFrequency","add","hasFrequency","deleteOne","hasFrequency","deleteOne","add","deleteOne","hasFrequency","hasFrequency","add","add","add","hasFrequency","deleteOne","hasFrequency","hasFrequency"]
     * [[],[1],[3],[1],[1],[3],[2],[1],[2],[10],[2],[6],[7],[10],[2],[1],[1],[2],[3],[1],[3],[2],[3]]
     *
     * ["FrequencyTracker","add","hasFrequency","add","deleteOne","hasFrequency","hasFrequency","hasFrequency","add","deleteOne","hasFrequency","deleteOne","add","add","hasFrequency","deleteOne","add","deleteOne","add","hasFrequency","deleteOne","deleteOne","deleteOne"]
     * [[],[4],[1],[8],[2],[1],[1],[1],[4],[6],[2],[8],[8],[8],[1],[4],[1],[4],[4],[3],[9],[8],[2]]
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(TestUtil.operation(
                new _2671(),
                "[\"FrequencyTracker\",\"add\",\"hasFrequency\",\"add\",\"deleteOne\",\"hasFrequency\",\"hasFrequency\",\"hasFrequency\",\"add\",\"deleteOne\",\"hasFrequency\",\"deleteOne\",\"add\",\"add\",\"hasFrequency\",\"deleteOne\",\"add\",\"deleteOne\",\"add\",\"hasFrequency\",\"deleteOne\",\"deleteOne\",\"deleteOne\"]",
                "[[],[4],[1],[8],[2],[1],[1],[1],[4],[6],[2],[8],[8],[8],[1],[4],[1],[4],[4],[3],[9],[8],[2]]"
        ));
    }



}
