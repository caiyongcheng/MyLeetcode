package letcode.normal.medium;

import letcode.utils.CastUtils;

import java.util.*;

/**
 * @program: MyLeetcode
 * @description: 给你一个字符串s，以及该字符串中的一些「索引对」数组pairs，其中pairs[i] =[a, b]表示字符串中的两个索引（编号从 0 开始）。  
 * 你可以 任意多次交换 在pairs中任意一对索引处的字符。  返回在经过若干次交换后，s可以变成的按字典序最小的字符串。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/smallest-string-with-swaps 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 提示：
 * 1 <= s.length <= 10^5
 * 0 <= pairs.length <= 10^5
 * 0 <= pairs[i][0], pairs[i][1] < s.length
 * s中只含有小写英文字母
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/smallest-string-with-swaps
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @date: 2021-04-14 09:56
 **/
public class _1202OneThousandTwoHundredTwo {




    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        //因为 可以任意多次交换在pairs中任意一对索引处的字符
        //所以 按照能否相互交换
        // 将字符串划分为几个集合（并查集）
        // 每个集合中的字符能相互交换位置
        // 不同集合的字符串不能相互交换
        // 将每个集合中的字符使用计数排序排列成字典序即可
        // 最后再合并每个集合的结果

        int[] parent = new int[s.length()];
        char[] ans = s.toCharArray();
        int[] chs = new int[26];
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int start;
        int end;
        int key;
        int index;
        //划分集合
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        for (List<Integer> pair : pairs) {
            start = pair.get(0);
            end = pair.get(1);
            if (find(start, parent) != find(end, parent)) {
                union(start, end, parent);
            }
        }
        for (int i = 0; i < parent.length; i++) {
            key = find(i, parent);
            if (map.containsKey(key)) {
                map.get(key).add(i);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(key, list);
            }
        }
        //集合内 字典序排序
        for (List<Integer> value : map.values()) {
            for (Integer integer : value) {
                chs[ans[integer]-'a']++;
            }
            index = 0;
            for (int i = 0; i < chs.length; i++) {
                while (chs[i] > 0) {
                    ans[value.get(index)] = (char) ('a' + i);
                    --chs[i];
                    ++index;
                }
            }
            Arrays.fill(chs,0);
        }
        return new String(ans);
    }



    public int find(int index, int[] parent) {
        Stack<Integer> stack = new Stack<>();
        while (parent[index] != index) {
            index = parent[index];
            stack.push(index);
        }
        while (!stack.empty()) {
            parent[stack.pop()] = index;
        }
        return index;
    }


    public void union(int one, int two, int[] parent) {
        parent[find(two, parent)] = find(one, parent);
    }


    /**
     * 示例 1:
     * 输入：s = "dcab", pairs = {{0,3},{1,2}}
     * 输出："bacd"
     * 解释：
     * 交换 s{0} 和 s{3}, s = "bcad"
     * 交换 s{1} 和 s{2}, s = "bacd"
     *
     * 示例 2：
     * 输入：s = "dcab", pairs = {{0,3},{1,2},{0,2}}
     * 输出："abcd"
     * 解释：
     * 交换 s{0} 和 s{3}, s = "bcad"
     * 交换 s{0} 和 s{2}, s = "acbd"
     * 交换 s{1} 和 s{2}, s = "abcd"
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/smallest-string-with-swaps
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1202OneThousandTwoHundredTwo().smallestStringWithSwaps(
                "dcab",
                CastUtils.array2List(new Integer[][]{{0,3},{1,2},{0,2}})
        ));
    }
    
    
    
}
