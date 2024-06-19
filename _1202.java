/*
 * 版权所有（c）<2021><蔡永程>
 *
 * 反996许可证版本1.0
 *
 * 在符合下列条件的情况下，特此免费向任何得到本授权作品的副本（包括源代码、文件和/或相关内容，以
 * 下统称为“授权作品”）的个人和法人实体授权：被授权个人或法人实体有权以任何目的处置授权作品，包括
 * 但不限于使用、复制，修改，衍生利用、散布，发布和再许可：
 *
 * 1. 个人或法人实体必须在许可作品的每个再散布或衍生副本上包含以上版权声明和本许可证，不得自行修
 * 改。
 * 2. 个人或法人实体必须严格遵守与个人实际所在地或个人出生地或归化地、或法人实体注册地或经营地（
 * 以较严格者为准）的司法管辖区所有适用的与劳动和就业相关法律、法规、规则和标准。如果该司法管辖区
 * 没有此类法律、法规、规章和标准或其法律、法规、规章和标准不可执行，则个人或法人实体必须遵守国际
 * 劳工标准的核心公约。
 * 3. 个人或法人不得以任何方式诱导、暗示或强迫其全职或兼职员工或其独立承包人以口头或书面形式同意
 * 直接或间接限制、削弱或放弃其所拥有的，受相关与劳动和就业有关的法律、法规、规则和标准保护的权利
 * 或补救措施，无论该等书面或口头协议是否被该司法管辖区的法律所承认，该等个人或法人实体也不得以任
 * 何方法限制其雇员或独立承包人向版权持有人或监督许可证合规情况的有关当局报告或投诉上述违反许可证
 * 的行为的权利。
 *
 * 该授权作品是"按原样"提供，不做任何明示或暗示的保证，包括但不限于对适销性、特定用途适用性和非侵
 * 权性的保证。在任何情况下，无论是在合同诉讼、侵权诉讼或其他诉讼中，版权持有人均不承担因本软件或
 * 本软件的使用或其他交易而产生、引起或与之相关的任何索赔、损害或其他责任。
 */

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
public class _1202 {




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
        System.out.println(new _1202().smallestStringWithSwaps(
                "dcab",
                CastUtils.array2List(new Integer[][]{{0,3},{1,2},{0,2}})
        ));
    }
    
    
    
}
