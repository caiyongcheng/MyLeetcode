package letcode.interview.medium;

import datastructure.utils.FormatPrintUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 每年，政府都会公布一万个最常见的婴儿名字和它们出现的频率，也就是同名婴儿的数量。有些名字有多种拼法，例如，John 和 Jon 本质上是相同的名字，
 * 但被当成了两个名字公布出来。给定两个列表，一个是名字及对应的频率，另一个是本质相同的名字对。设计一个算法打印出每个真实名字的实际频率。
 * 注意，如果 John 和 Jon 是相同的，并且 Jon 和 Johnny 相同，则 John 与 Johnny 也相同，即它们有传递和对称性。
 * 在结果列表中，选择 字典序最小 的名字作为真实名字。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/baby-names-lcci 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-04-19 16:35
 **/
public class _17_7_Seventeen_Seven {


    public String[] trulyMostPopular(String[] names, String[] synonyms) {
        //并查集
        HashMap<String, Integer> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        int[] association = new int[names.length+synonyms.length];
        int[] frequency = new int[names.length+synonyms.length];
        String[] _names = new String[names.length + synonyms.length];
        String[] split;
        String str;
        int left;
        int right;
        int index = 0;
        int length = names.length;
        for (int i = 0; i < names.length; i++) {
            _names[i] = names[i];
        }
        for (int i = 0; i < association.length; i++) {
            association[i] = i;
        }
        for (int i = 0; i < length; i++) {
            str = _names[i];
            index = str.lastIndexOf('(');
            _names[i] = str.substring(0, index);
            frequency[i] = Integer.parseInt(str.substring(index+1, str.lastIndexOf(")")));
            map.put(_names[i], i);
            set.add(_names[i]);
        }
        for (String synonym : synonyms) {
            split = synonym.substring(1, synonym.length() - 1).split(",");
            if (!map.containsKey(split[0])) {
                map.put(split[0], length);
                _names[length] = split[0];
                ++length;
            }
            if (!map.containsKey(split[1])) {
                map.put(split[1], length);
                _names[length] = split[1];
                ++length;
            }
            left = find(map.get(split[0]), association);
            right = find(map.get(split[1]), association);
            if (left != right) {
                if ((set.contains(_names[left]) && (!set.contains(_names[right]) || _names[left].compareTo(_names[right]) < 0)
                        || (!set.contains(_names[left]) && !set.contains(_names[right]) && _names[left].compareTo(_names[right]) < 0))) {
                    association[right] = left;
                    frequency[left] += frequency[right];
                } else {
                    association[left] = right;
                    frequency[right] += frequency[left];
                }
            }
        }
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            if (i == association[i] && set.contains(_names[i])) {
                ans.add(_names[i] + "(" + frequency[i] + ")");
            }
        }
        return ans.toArray(new String[ans.size()]);
    }


    public int find(int index, int[] ass) {
        while (ass[index] != index) {
            index = ass[index];
        }
        return index;
    }


    /**
     * 示例：
     * 输入：names = ["John(15)","Jon(12)","Chris(13)","Kris(4)","Christopher(19)"], synonyms = ["(Jon,John)","(John,Johnny)","(Chris,Kris)","(Chris,Christopher)"]
     * 输出：["John(27)","Chris(36)"]
     * 来源：力扣（LeetCode）
     *
     *
     * ["a(10)","c(13)"]
     * ["(a,b)","(c,d)","(b,c)"]
     * 链接：https://leetcode-cn.com/problems/baby-names-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(FormatPrintUtils.formatArray(new _17_7_Seventeen_Seven().trulyMostPopular(
                new String[]{"John(15)","Jon(12)","Chris(13)","Kris(4)","Christopher(19)"},
                new String[]{"(Jon,John)","(John,Johnny)","(Chris,Kris)","(Chris,Christopher)"}
        )));
    }


}
