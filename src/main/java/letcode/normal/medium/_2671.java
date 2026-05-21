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

}
