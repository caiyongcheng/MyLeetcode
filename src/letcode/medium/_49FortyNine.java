package letcode.medium;

import java.util.*;

/**
 * Leetcode
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * @author : CaiYongcheng
 * @date : 2020-07-09 08:54
 **/
public class _49FortyNine {

    static HashMap<String, List<String>> map = new HashMap<String, List<String>>();


    /**
     * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
     * 输出:
     * [
     *   ["ate","eat","tea"],
     *   ["nat","tan"],
     *   ["bat"]
     * ]
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        ArrayList<List<String>> lists = new ArrayList<>();
        for (String str : strs) {
            byte[] bytes = str.getBytes();
            Arrays.sort((bytes));
            String newStr = new String(bytes);
            List<String> strings = map.get(newStr);
            if (strings != null){
                strings.add(str);
            }else{
                ArrayList<String> strList = new ArrayList<>();
                strList.add(str);
                map.put(newStr, strList);
            }
        }
        Collection<List<String>> values = map.values();
        for (List<String> value : values) {
            lists.add(value);
        }
        return lists;
    }

    public static void main(String[] args) {
        List<List<String>> lists = groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        for (List<String> list : lists) {
            System.out.println(Arrays.toString(list.toArray()));
        }
    }

}
