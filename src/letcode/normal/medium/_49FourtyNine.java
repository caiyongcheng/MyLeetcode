package normal.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @program: Leetcode
 * @description: 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * 示例:  输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [
 * ["ate","eat","tea"],   ["nat","tan"],   ["bat"]
 * ]
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/group-anagrams
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-12-14 14:15
 */
public class _49FourtyNine {


    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> stringListHashMap = new HashMap<>(strs.length);
        for (String str : strs) {
            byte[] bytes = str.getBytes();
            Arrays.sort(bytes);
            String sortStr = new String(bytes);
            if (stringListHashMap.containsKey(sortStr)) {
                stringListHashMap.get(sortStr).add(str);
            }else{
                ArrayList<String> strings = new ArrayList<>();
                strings.add(str);
                stringListHashMap.put(sortStr, strings);
            }
        }
        return new ArrayList<>(stringListHashMap.values());
    }

    public static void main(String[] args) {
        String[] strings = new String[]{
                "eat", "tea", "tan", "ate", "nat", "bat"
        };
        List<List<String>> lists = new _49FourtyNine().groupAnagrams(strings);
        for (List<String> list : lists) {
            for (String s : list) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }

}