package letcode.normal.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，例如："ACGAATTCCG"。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
 * 编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/repeated-dna-sequences 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-10-08 10:30
 **/
public class _187 {

    public List<String> findRepeatedDnaSequences(String s) {
        if (s.length() < 10) {
            return new ArrayList<>();
        }
        char[] chars = new char[10];
        char[] strArr = s.toCharArray();
        LinkedList<String> ans = new LinkedList<>();
        HashMap<String, Integer> hashMap = new HashMap<>();
        String nowStr;
        int count;
        for (int index = 0; index + 9 < strArr.length; index++) {
            System.arraycopy(strArr, index, chars, 0, 10);
            nowStr = new String(chars);
            count = hashMap.getOrDefault(nowStr, 0);
            if (count == 1) {
                ans.add(nowStr);
            }
            hashMap.put(nowStr, count + 1);
        }
        return ans;
    }


}
