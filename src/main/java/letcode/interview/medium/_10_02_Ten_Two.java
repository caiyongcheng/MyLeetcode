package letcode.interview.medium;

import letcode.utils.TestCaseOutputUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。变位词是指字母相同，但排列不同的字符串。
 *
 * @author CaiYongcheng
 * @since 2021-07-18 18:37
 **/
public class _10_02_Ten_Two {


    public List<List<String>> groupAnagrams(String[] strs) {
        return new ArrayList<>(Arrays.stream(strs).collect(Collectors.groupingBy((Function<String, Object>) s -> {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            return new String(chars);
        })).values());
    }


}
