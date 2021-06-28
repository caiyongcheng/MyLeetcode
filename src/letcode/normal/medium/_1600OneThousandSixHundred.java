package letcode.normal.medium;

import letcode.utils.FormatPrintUtils;

import java.util.*;

/**
 * 一个王国里住着国王、他的孩子们、他的孙子们等等。每一个时间点，这个家庭里有人出生也有人死亡。  
 * 这个王国有一个明确规定的皇位继承顺序，第一继承人总是国王自己。
 * 我们定义递归函数Successor(x, curOrder)，给定一个人x和当前的继承顺序，该函数返回 x的下一继承人。  
 * Successor(x, curOrder):     如果 x 没有孩子或者所有 x 的孩子都在 curOrder 中：         
 * 如果 x 是国王，那么返回 null         
 * 否则，返回 Successor(x 的父亲, curOrder)     
 * 否则，返回 x 不在 curOrder 中最年长的孩子 
 * 比方说，假设王国由国王，他的孩子Alice 和 Bob （Alice 比 Bob年长）和 Alice 的孩子Jack 组成。 
 * 一开始，curOrder为["king"]. 调用Successor(king, curOrder)，返回 Alice ，
 * 所以我们将 Alice 放入 curOrder中，得到["king", "Alice"]。 调用Successor(Alice, curOrder)，返回 Jack ，
 * 所以我们将 Jack 放入curOrder中，得到["king", "Alice", "Jack"]。 调用Successor(Jack, curOrder)，返回 Bob ，
 * 所以我们将 Bob 放入curOrder中，得到["king", "Alice", "Jack", "Bob"]。 调用Successor(Bob, curOrder)，
 * 返回null。最终得到继承顺序为["king", "Alice", "Jack", "Bob"]。
 * 通过以上的函数，我们总是能得到一个唯一的继承顺序。  
 * 请你实现ThroneInheritance类：  ThroneInheritance(string kingName) 初始化一个ThroneInheritance类的对象。
 * 国王的名字作为构造函数的参数传入。 void birth(string parentName, string childName)
 * 表示parentName新拥有了一个名为childName的孩子。 
 * void death(string name)表示名为name的人死亡。一个人的死亡不会影响Successor函数，也不会影响当前的继承顺序。
 * 你可以只将这个人标记为死亡状态。 string[] getInheritanceOrder()返回 除去死亡人员的当前继承顺序列表。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/throne-inheritance 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-06-20 08:34
 **/
public class _1600OneThousandSixHundred {

    class RelationTreeNode {

        /**
         * 姓名
         */
        String name;

        /**
         * 是否死亡
         */
        boolean isDie;

        /**
         * 孩子列表
         */
        List<RelationTreeNode> childes;

        public RelationTreeNode(String name) {
            this.name = name;
            isDie = false;
            childes = new LinkedList<>();
        }

    }

    RelationTreeNode root;

    Map<String, RelationTreeNode> nameToNode;

    public _1600OneThousandSixHundred(String kingName) {
        root = new RelationTreeNode(kingName);
        nameToNode = new HashMap<>();
        nameToNode.put(kingName, root);
    }

    public void birth(String parentName, String childName) {
        RelationTreeNode childNode = new RelationTreeNode(childName);
        RelationTreeNode parentNode = nameToNode.get(parentName);
        parentNode.childes.add(childNode);
        nameToNode.put(childName, childNode);
    }

    public void death(String name) {
        nameToNode.get(name).isDie = true;
    }

    public List<String> getInheritanceOrder() {
        LinkedList<RelationTreeNode> queue = new LinkedList<>();
        List<String> ans = new LinkedList<String>();
        RelationTreeNode now;
        queue.add(root);
        while (!queue.isEmpty()) {
            now = queue.remove(0);
            if (!now.isDie) {
                ans.add(now.name);
            }
            LinkedList<RelationTreeNode> temperator = new LinkedList<>(now.childes);
            temperator.addAll(queue);
            queue = temperator;
        }
        return ans;
    }

    /**
     * 输入：
     * ["ThroneInheritance", "birth", "birth", "birth", "birth", "birth", "birth", "getInheritanceOrder", "death", "getInheritanceOrder"]
     * [["king"], ["king", "andy"], ["king", "bob"], ["king", "catherine"], ["andy", "matthew"], ["bob", "alex"], ["bob", "asha"], [null], ["bob"], [null]]
     * 输出：
     * [null, null, null, null, null, null, null, ["king", "andy", "matthew", "bob", "alex", "asha", "catherine"], null, ["king", "andy", "matthew", "alex", "asha", "catherine"]]
     * 解释：
     * ThroneInheritance t= new ThroneInheritance("king"); // 继承顺序：king
     * t.birth("king", "andy"); // 继承顺序：king > andy
     * t.birth("king", "bob"); // 继承顺序：king > andy > bob
     * t.birth("king", "catherine"); // 继承顺序：king > andy > bob > catherine
     * t.birth("andy", "matthew"); // 继承顺序：king > andy > matthew > bob > catherine
     * t.birth("bob", "alex"); // 继承顺序：king > andy > matthew > bob > alex > catherine
     * t.birth("bob", "asha"); // 继承顺序：king > andy > matthew > bob > alex > asha > catherine
     * t.getInheritanceOrder(); // 返回 ["king", "andy", "matthew", "bob", "alex", "asha", "catherine"]
     * t.death("bob"); // 继承顺序：king > andy > matthew > bob（已经去世）> alex > asha > catherine
     * t.getInheritanceOrder(); // 返回 ["king", "andy", "matthew", "alex", "asha", "catherine"]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/throne-inheritance
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {


        _1600OneThousandSixHundred test = new _1600OneThousandSixHundred("king");
        test.birth("king", "andy");
        test.birth("king", "bob");
        test.birth("king", "catherine");
        test.birth("andy", "matthew");
        test.birth("bob", "alex");
        test.birth("bob", "asha");
        System.out.println(FormatPrintUtils.formatList(test.getInheritanceOrder()));
        test.death("bob");
        System.out.println(FormatPrintUtils.formatList(test.getInheritanceOrder()));

    }

}
