package letcode.normal.medium;

import letcode.utils.CastUtils;
import letcode.utils.TestCaseOutputUtils;

import java.util.*;

/**
 * 给你一个数组 orders，表示客户在餐厅中完成的订单，
 * 确切地说， orders[i]=[customerNamei,tableNumberi,foodItemi] ，其中 customerNamei 是客户的姓名，
 * tableNumberi 是客户所在餐桌的桌号，而 foodItemi 是客户点的餐品名称。
 * 请你返回该餐厅的 点菜展示表 。在这张表中，表中第一行为标题，其第一列为餐桌桌号 “Table” ，
 * 后面每一列都是按字母顺序排列的餐品名称。接下来每一行中的项则表示每张餐桌订购的相应餐品数量，第一列应当填对应的桌号，
 * 后面依次填写下单的餐品数量。  注意：客户姓名不是点菜展示表的一部分。此外，表中的数据行应该按餐桌桌号升序排列。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/display-table-of-food-orders-in-a-restaurant 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-07-06 09:09
 **/
public class _1418 {

    public List<List<String>> displayTable(List<List<String>> orders) {
        List<List<String>> ans = new ArrayList<>();
        TreeMap<Integer, Map<String, Integer>> ansMap = new TreeMap<>();
        TreeSet<String> menuNames = new TreeSet<>();
        Map<String, Integer> tableMap;
        int tableNum;
        String menuName;
        for (List<String> order : orders) {
            //保存菜品名称
            tableNum = Integer.parseInt(order.get(1));
            menuName = order.get(2);
            menuNames.add(menuName);
            //保存 桌子-菜品序号
            if (ansMap.containsKey(tableNum)) {
                tableMap = ansMap.get(tableNum);
                tableMap.put(order.get(2), tableMap.getOrDefault(menuName, 0)+1);
            } else {
                tableMap = new HashMap<>();
                tableMap.put(menuName, 1);
                ansMap.put(tableNum, tableMap);
            }
        }
        //表头
        ArrayList<String> title = new ArrayList<>();
        ArrayList<String> col;
        title.add("Table");
        title.addAll(menuNames);
        ans.add(title);
        for (Map.Entry<Integer, Map<String, Integer>> entry : ansMap.entrySet()) {
            col = new ArrayList<>();
            //桌号
            col.add(String.valueOf(entry.getKey()));
            for (String menu : menuNames) {
                col.add(String.valueOf(entry.getValue().getOrDefault(menu, 0)));
            }
            ans.add(col);
        }
        return ans;
    }


}
