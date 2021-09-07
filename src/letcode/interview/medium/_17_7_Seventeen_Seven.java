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
