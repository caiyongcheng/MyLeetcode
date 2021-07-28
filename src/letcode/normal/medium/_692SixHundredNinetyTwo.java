package letcode.normal.medium;

import letcode.utils.FormatPrintUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 给一非空的单词列表，返回前 k 个出现次数最多的单词。  返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
 *
 * @author CaiYongcheng
 * @date 2021-05-20 17:30
 **/
public class _692SixHundredNinetyTwo {

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

    /**
     * 示例 1：
     * 输入: {"i", "love", "leetcode", "i", "love", "coding"}, k = 2
     * 输出: {"i", "love"}
     * 解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
     *     注意，按字母顺序 "i" 在 "love" 之前。
     * 
     *
     * 示例 2：
     * 输入: {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, k = 4
     * 输出: {"the", "is", "sunny", "day"}
     * 解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
     *     出现次数依次为 4, 3, 2 和 1 次。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/top-k-frequent-words
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(FormatPrintUtils.formatList(new _692SixHundredNinetyTwo().topKFrequent(
                new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"},
                4
        )));
    }



}
