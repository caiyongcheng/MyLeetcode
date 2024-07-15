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

import letcode.utils.TestCaseUtils;

import java.util.*;

/**
 * @program: Leetcode
 * @description: 给定一个列表 accounts，每个元素 accounts[i]是一个字符串列表，
 * 其中第一个元素 accounts[i][0]是名称 (name)，其余元素是 emails 表示该账户的邮箱地址。
 * 现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。
 * 请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。
 * 一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。
 * 合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是按顺序排列的邮箱地址。
 * 账户本身可以以任意顺序返回。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/accounts-merge 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2021-01-18 09:19
 */
public class N_721 {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        /*
        并查集的应用

            public int[] parent;
            public String[]  owners, emails;
            public Map<String, Integer> emailToId;
            int emailId = 0;

            public List<List<String>> accountsMerge(List<List<String>> accounts) {

                int n = accounts.size() * 9;
                parent  =  new int[n];
                owners  =  new String[n];
                emails  =  new String[n];
                emailToId = new HashMap<>(n);

                for(int i = 0; i < n; i++)
                    parent[i] = i;

                for(List<String>account: accounts){
                    String owner = account.get(0);
                    int first = getId(account.get(1), owner);
                    for(int i = 2; i < account.size(); i++)
                         union(first, getId(account.get(i), owner));

                }

                int size = emailId;
                List<String> [] merge = new List[size];
                for(int i = 0; i < size; i++){
                    int parent = find(i);
                    if(merge[parent] == null)
                        merge[parent] = new ArrayList<>();

                    merge[parent].add(emails[i]);
                }

                List<List<String>> result=new ArrayList<>();
                for(int i = 0 ; i < size; i++){
                    if(merge[i] == null)
                        continue;

                    Collections.sort(merge[i]);
                    merge[i].add(0, owners[i]);
                    result.add(merge[i]);
                }
                return result;
            }

            public int getId(String email, String owner){

                Integer id = emailToId.get(email);
                if(id == null){
                    id = emailId++;
                    emailToId.put(email, id);
                }

                owners[id] = owner;
                emails[id] = email;
                return id;
            }

            private int find(int x) {
                return parent[x] == x ? x : (parent[x] = find(parent[x]));
            }

            private void union(int x, int y) {
                int rootX = find(x);
                int rootY = find(y);
                if (rootX == rootY)
                    return;

                parent[rootX] = rootY;
            }
         */
        List<Set<String>> unionFindList = new ArrayList<>();
        Set<Integer> mergeIndexSet = new TreeSet<>((o1, o2) -> o2 - o1);
        Map<String, String> email2AccountMap = new HashMap<>();
        Set<String> unionFindSet;
        String account;
        String email;
        for (List<String> data : accounts) {
            mergeIndexSet = new TreeSet<>((o1, o2) -> o2 - o1);
            account = data.get(0);
            for (int i = 1; i < data.size(); i++) {
                email = data.get(i);
                email2AccountMap.put(email, account);
                for (int index = 0; index < unionFindList.size(); index++) {
                    if (unionFindList.get(index).contains(email)) {
                        mergeIndexSet.add(index);
                        break;
                    }
                }
            }
            if (mergeIndexSet.size() > 1 || mergeIndexSet.size() == 0) {
                unionFindSet = new HashSet<>();
                for (Integer index : mergeIndexSet) {
                    unionFindSet.addAll(unionFindList.get(index));
                    unionFindList.remove(index.intValue());
                }
                unionFindList.add(unionFindSet);
            } else {
                unionFindSet = unionFindList.get(mergeIndexSet.iterator().next());
            }
            for (int i = 1; i < data.size(); i++) {
                unionFindSet.add(data.get(i));
            }
        }

        List<List<String>> ans = new ArrayList<>();
        List<String> item;
        for (Set<String> unionFind : unionFindList) {
            unionFind.add("");
            item = new ArrayList<>(unionFind);
            Collections.sort(item);
            item.set(0, email2AccountMap.get(item.get(1)));
            ans.add(item);
        }
        return ans;
    }


    /**
     * 示例 1：
     * 输入：
     * accounts = {{"John", "johnsmith@mail.com", "john00@mail.com"}, {"John", "johnnybravo@mail.com"}, {"John", "johnsmith@mail.com", "john_newyork@mail.com"}, {"Mary", "mary@mail.com"}}
     * 输出：
     * [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
     * 解释：
     * 第一个和第三个 John 是同一个人，因为他们有共同的邮箱地址 "johnsmith@mail.com"。
     * 第二个 John 和 Mary 是不同的人，因为他们的邮箱地址没有被其他帐户使用。
     * 可以以任何顺序返回这些列表，例如答案 [['Mary'，'mary@mail.com']，['John'，'johnnybravo@mail.com']，
     * ['John'，'john00@mail.com'，'john_newyork@mail.com'，'johnsmith@mail.com']] 也是正确的。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/accounts-merge
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com","johnsmith@mail.com"],["John","johnnybravo@mail.com"],["Mary","mary@mail.com"]]
     * [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
     * 
     * 
     * {{"Alex","Alex5@m.co","Alex4@m.co","Alex0@m.co"},{"Ethan","Ethan3@m.co","Ethan3@m.co","Ethan0@m.co"},{"Kevin","Kevin4@m.co","Kevin2@m.co","Kevin2@m.co"},{"Gabe","Gabe0@m.co","Gabe3@m.co","Gabe2@m.co"},{"Gabe","Gabe3@m.co","Gabe4@m.co","Gabe2@m.co"}}
     * {{"David","David0@m.co","David4@m.co","David3@m.co"},{"David","David5@m.co","David5@m.co","David0@m.co"},{"David","David1@m.co","David4@m.co","David0@m.co"},{"David","David0@m.co","David1@m.co","David3@m.co"},{"David","David4@m.co","David1@m.co","David3@m.co"}}
     * [["David","David0@m.co","David1@m.co"],["David","David3@m.co","David4@m.co"],["David","David4@m.co","David5@m.co"],["David","David2@m.co","David3@m.co"],["David","David1@m.co","David2@m.co"]]
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new N_721().accountsMerge(
                TestCaseUtils.get2DList(
                        TestCaseUtils.getStringFromFile(),
                        ",",
                        TestCaseUtils::getArr
                )
        ));
    }

}
