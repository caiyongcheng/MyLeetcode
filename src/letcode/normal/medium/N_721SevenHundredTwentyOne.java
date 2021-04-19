package letcode.normal.medium;

import letcode.utils.CastUtils;

import java.util.*;

/**
 * @program: Leetcode
 * @description: 给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表，
 * 其中第一个元素 accounts[i][0] 是 名称 (name)，其余元素是 emails 表示该账户的邮箱地址。
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
public class N_721SevenHundredTwentyOne {


    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        //按照名称分组
        //再确定每个名称组中哪些可以合并
        for (List<String> account : accounts) {
            account.sort(String::compareTo);
            for (int i = account.size() - 1; i >= 0; i--) {
                if (i-1>-1 && account.get(i).equals(account.get(i-1))) {
                    account.remove(i);
                }
            }
        }
        String key;
        HashMap<String, List<List<String>>> listHashMap = new HashMap<>(accounts.size());
        ArrayList<List<String>> ans = new ArrayList<>();
        List<List<String>> lists;
        for (List<String> account : accounts) {
            key = account.get(0);
            if (listHashMap.containsKey(key)) {
                listHashMap.get(key).add(account);
            } else {
                lists = new ArrayList<>();
                lists.add(account);
                listHashMap.put(key, lists);
            }
        }
        Set<String> keySet = listHashMap.keySet();
        for (String name : keySet) {
            lists = listHashMap.get(name);
            ans.addAll(merge(lists));
        }
        for (List<String> list : ans) {
            list.sort(String::compareTo);
        }
        return ans;
    }


    public List<List<String>> merge(List<List<String>> lists) {
        List<String> tail;
        List<String> head;
        boolean isMerge = false;
        for (int i = 0; i < lists.size(); i++) {
            isMerge = false;
            head = lists.get(i);
            for (int j = lists.size() - 1; j > i; j--) {
                tail = lists.get(j);
                if (sameOne(head, tail)) {
                    head.addAll(tail.subList(1, tail.size()));
                    lists.remove(j);
                    isMerge = true;
                }
            }
            if (isMerge) {
                --i;
            }
        }
        return lists;
    }


    public boolean sameOne(List<String> l1, List<String> l2) {
        boolean res = false;
        for (int i = l1.size() - 1; i > 0; i--) {
            for (int j = l2.size() - 1; j > 0; j--) {
                if (l1.get(i).equals(l2.get(j))) {
                    l2.remove(j);
                    res = true;
                }
            }
        }
        return res;
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
        String[][] strings = {
                {"David","David0@m.co","David1@m.co"},
                {"David","David3@m.co","David4@m.co"},
                {"David","David4@m.co","David5@m.co"},
                {"David","David2@m.co","David3@m.co"},
                {"David","David1@m.co","David2@m.co"}
        };
        List<List<String>> lists = CastUtils.array2List(strings);
        System.out.println(new N_721SevenHundredTwentyOne().accountsMerge(lists));
    }

}