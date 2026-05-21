package letcode.normal.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Design a data structure that is initialized with a list of different words. Provided a string,
 * you should determine if you can change exactly one character in this string to match any word in the data structure.
 * Implement the MagicDictionary class:  MagicDictionary() Initializes the object.
 * void buildDict(String[] dictionary) Sets the data structure with an array of distinct strings dictionary.
 * bool search(String searchWord) Returns true if you can change exactly one character in searchWord to match any string
 * in the data structure, otherwise returns false.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-08-12 09:29
 */
public class _676 {

    private Map<Integer, Set<String>> strLen2StrSetMap;

    public _676() {
        strLen2StrSetMap = new HashMap<>();
    }

    public void buildDict(String[] dictionary) {
        /*
         *  实际上 buildDict只调用一次 最大调用search次数是100次，每个dictionary的长度最大是100
         *  所以不对dictionary数据做处理 这样使用内存少一点 如果search次数大于100的话 还是对dictionary做处理在速度上更有优势
         */
        for (String str : dictionary) {
            strLen2StrSetMap.computeIfAbsent(str.length(), k -> new HashSet<>()).add(str);
        }
    }

    public boolean search(String searchWord) {
        char[] searchCharArr = searchWord.toCharArray();
        Set<String> strSet = strLen2StrSetMap.get(searchCharArr.length);
        if (null == strSet) {
            return false;
        }
        char ch;
        char originCh;
        for (int i = 0; i < searchCharArr.length; i++) {
            originCh = searchCharArr[i];
            for (ch = 'a'; ch <= 'z'; ++ch) {
                if (ch == originCh) {
                    continue;
                }
                searchCharArr[i] = ch;
                if (strSet.contains(new String(searchCharArr))) {
                    return true;
                }
            }
            searchCharArr[i] = originCh;
        }
        return false;
    }

}
