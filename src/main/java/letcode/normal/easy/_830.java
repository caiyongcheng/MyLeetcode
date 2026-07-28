package normal.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: Leetcode
 * @description: 在一个由小写字母构成的字符串 s 中，包含由一些连续的相同字符所构成的分组。
 * 例如，在字符串 s = "abbxxxxzyy"中，就含有 "a", "bb", "xxxx", "z" 和 "yy" 这样的一些分组。
 * 分组可以用区间 [start, end] 表示，其中 start 和 end 分别表示该分组的起始和终止位置的下标。
 * 上例中的 "xxxx" 分组用区间表示为 [3,6] 。  我们称所有包含大于或等于三个连续字符的分组为 较大分组 。
 * 找到每一个 较大分组 的区间，按起始位置下标递增顺序排序后，返回结果。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/positions-of-large-groups
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2021-01-05 09:32
 */
public class _830 {

    public List<List<Integer>> largeGroupPositions(String s) {
        s = s + "1";
        char[] chars = s.toCharArray();
        ArrayList<List<Integer>> resultLists = new ArrayList<>();
        int ch = 0;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] != chars[i - 1]) {
                if (i - ch > 2) {
                    ArrayList<Integer> resultItemList = new ArrayList<>();
                    resultItemList.add(ch);
                    resultItemList.add(i - 1);
                    resultLists.add(resultItemList);
                }
                ch = i;
            }
        }
        return resultLists;
    }
}
