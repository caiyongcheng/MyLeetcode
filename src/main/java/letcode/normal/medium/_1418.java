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


    /**
     * 示例 1：
     *
     * 输入：orders = {{"David","3","Ceviche"},{"Corina","10","Beef Burrito"},{"David","3","Fried Chicken"},{"Carla","5","Water"},{"Carla","5","Ceviche"},{"Rous","3","Ceviche"}}
     * 输出：{{"Table","Beef Burrito","Ceviche","Fried Chicken","Water"},{"3","0","2","1","0"},{"5","0","1","0","1"},{"10","1","0","0","0"}}
     * 解释：
     * 点菜展示表如下所示：
     * Table,Beef Burrito,Ceviche,Fried Chicken,Water
     * 3    ,0           ,2      ,1            ,0
     * 5    ,0           ,1      ,0            ,1
     * 10   ,1           ,0      ,0            ,0
     * 对于餐桌 3：David 点了 "Ceviche" 和 "Fried Chicken"，而 Rous 点了 "Ceviche"
     * 而餐桌 5：Carla 点了 "Water" 和 "Ceviche"
     * 餐桌 10：Corina 点了 "Beef Burrito"
     *
     * 示例 2：
     * 输入：orders = {{"James","12","Fried Chicken"},{"Ratesh","12","Fried Chicken"},{"Amadeus","12","Fried Chicken"},{"Adam","1","Canadian Waffles"},{"Brianna","1","Canadian Waffles"}}
     * 输出：{{"Table","Canadian Waffles","Fried Chicken"},{"1","2","0"},{"12","0","3"}}
     * 解释：
     * 对于餐桌 1：Adam 和 Brianna 都点了 "Canadian Waffles"
     * 而餐桌 12：James, Ratesh 和 Amadeus 都点了 "Fried Chicken"
     *
     * 示例 3：
     * 输入：orders = {{"Laura","2","Bean Burrito"},{"Jhon","2","Beef Burrito"},{"Melissa","2","Soda"}}
     * 输出：{{"Table","Bean Burrito","Beef Burrito","Soda"},{"2","1","1","1"}}
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/display-table-of-food-orders-in-a-restaurant
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        String[][] params = {{"Laura","2","Bean Burrito"},{"Jhon","2","Beef Burrito"},{"Melissa","2","Soda"}};
        List<List<String>> lists = new _1418().displayTable(CastUtils.array2List(params));
        System.out.println(TestCaseOutputUtils.formatList(lists));
    }

}
