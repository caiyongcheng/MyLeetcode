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


}
