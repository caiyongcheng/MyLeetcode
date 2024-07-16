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

package letcode.normal.easy;

import datastructure.utils.FormatPrintUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 假设 Andy 和 Doris 想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。
 * 你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。 如果答案不止一个，则输出所有答案并且不考虑顺序。 
 * 你可以假设答案总是存在。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/minimum-index-sum-of-two-lists 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-03-14 09:02
 **/
public class _599 {

    class Item {
        String name;
        int index;
        public Item(String name, int index) {
            this.name = name;
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }

    public String[] findRestaurant(String[] list1, String[] list2) {
        int minLen = Math.min(list1.length, list2.length);
        int minIndexSum = Integer.MAX_VALUE;
        int i1 = 0, i2 = 0;
        Item[] items1 = new Item[list1.length];
        Item[] items2 = new Item[list2.length];
        for (int i = 0; i < list1.length; i++) {
            items1[i] = new Item(list1[i], i);
        }
        for (int i = 0; i < list2.length; i++) {
            items2[i] = new Item(list2[i], i);
        }
        ArrayList<String> ans = new ArrayList<>(minLen);
        Comparator<Item> itemComparator = Comparator.comparing(Item::getName).thenComparing(Item::getIndex);
        Arrays.sort(items1, itemComparator);
        Arrays.sort(items2, itemComparator);
        while (i1 < items1.length && i2 < items2.length) {
            int res = items1[i1].name.compareTo(items2[i2].name);
            if (res == 0) {
                if (items1[i1].index + items2[i2].index < minIndexSum) {
                    ans.clear();
                    ans.add(items1[i1].name);
                    minIndexSum = items1[i1].index + items2[i2].index;
                } else if (items1[i1].index + items2[i2].index == minIndexSum) {
                    ans.add(items1[i1].name);
                }
                ++i1;
                ++i2;
            } else if (res < 0) {
                ++i1;
            } else {
                ++i2;
            }
        }
        return ans.toArray(new String[]{});
    }


    /**
     * 示例 1:
     *
     * 输入: list1 = {"Shogun", "Tapioca Express", "Burger King", "KFC"}，list2 = {"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"}
     * 输出: {"Shogun"}
     * 解释: 他们唯一共同喜爱的餐厅是“Shogun”。
     * 示例 2:
     *
     * 输入:list1 = {"Shogun", "Tapioca Express", "Burger King", "KFC"}，list2 = {"KFC", "Shogun", "Burger King"}
     * 输出: {"Shogun"}
     * 解释: 他们共同喜爱且具有最小索引和的餐厅是“Shogun”，它有最小的索引和1(0+1)。
     *
     *
     * ["Shogun","Tapioca Express","Burger King","KFC"]
     * ["KFC","Burger King","Tapioca Express","Shogun"]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-index-sum-of-two-lists
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(FormatPrintUtils.formatArray(new _599().findRestaurant(
                new String[]{"Shogun","Tapioca Express","Burger King","KFC"},
                new String[]{"KFC","Burger King","Tapioca Express","Shogun"}
        )));
    }


}
