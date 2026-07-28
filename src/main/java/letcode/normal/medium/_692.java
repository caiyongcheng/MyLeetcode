package letcode.normal.medium;

import letcode.utils.TestCaseOutputUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 给一非空的单词列表，返回前 k 个出现次数最多的单词。  返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
 *
 * @author CaiYongcheng
 * @since 2021-05-20 17:30
 **/
public class _692 {

    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> hashMap = new HashMap<>(words.length);
        for (String word : words) {
            hashMap.put(word, hashMap.getOrDefault(word, 0) + 1);
        }
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>(hashMap.entrySet());
        return list.stream().sorted(
                (o1, o2) ->
                        o1.getValue() > o2.getValue()
                                ? -1 : o1.getValue() < o2.getValue()
                                ? 1 : o1.getKey().compareTo(o2.getKey())
        ).limit(k).map(Map.Entry::getKey).collect(Collectors.toList());
    }

}
